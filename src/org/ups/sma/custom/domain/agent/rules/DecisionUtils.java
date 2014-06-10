package org.ups.sma.custom.domain.agent.rules;

import org.ups.sma.custom.domain.environment.Location;
import org.ups.sma.domain.Action;
import org.ups.sma.domain.Choice;
import org.ups.sma.domain.environment.Env;
import org.ups.sma.domain.environment.InteractiveEnvironmentObject;
import org.ups.sma.impl.agent.Agent;
import org.ups.sma.impl.agent.impl.Decider;
import org.ups.sma.impl.environment.EnvironmentManager;

import java.util.List;
import java.util.Stack;

/**
 * Created by Ben on 07/06/14.
 */
public class DecisionUtils {

    public static boolean isInCorridor(Agent a){
        return isInCorridor(a.getLocation(),a.getState().partialEnvironment);
    }

    public static boolean isInCorridor(Location loca, Env perceivedEnvironment){
        Location loc = new Location(loca.x,loca.y);
        loc.y++;
        InteractiveEnvironmentObject object = perceivedEnvironment.get(loc);

        if (object != null && (object.is("Wall") || loc.y>perceivedEnvironment.size.height)){
            loc.y-=2;
            object = perceivedEnvironment.get(loc);
            if (object != null && (object.is("Wall") || loc.y<perceivedEnvironment.size.height)){
                return true;
            }
        }
        return false;
    }

    public static boolean wasInCorridor(Agent a){
        return a.getState().wasInCorridor;
    }

    public static boolean isCorridorEntrance(Agent a){
        if (isInCorridor(a)) return false;
        Location loc = new Location(a.getLocation().x,a.getLocation().y);
        loc.x++;
        if (a.getState().partialEnvironment.get(loc) == null) return false;
        if (a.getState().partialEnvironment.get(loc).is("Wall")) return false;
        if (isInCorridor(loc, a.getState().partialEnvironment)) return true;
        loc.x -= 2;
        if (a.getState().partialEnvironment.get(loc) == null) return false;
        if (a.getState().partialEnvironment.get(loc).is("Wall")) return false;
        if (isInCorridor(loc,a.getState().partialEnvironment)) return true;
        return false;
    }

    public static boolean isRobotInFront(Agent a){
        Env perceivedEnvironment = a.getState().partialEnvironment;
        Location loc = getForwardLocation(a);
        return perceivedEnvironment.get(loc) != null && perceivedEnvironment.get(loc).isAgent();
    }

    private static Location getNextLocation(Agent agent, int sign){
        Location agentLoc = agent.getLocation();
        Location target = agent.getState().waypoint;

        if (agent.getState().waypoint != agent.getState().longTermGoal){
            if (target.y != agentLoc.y){
                if (target.y < agentLoc.y) sign = -1*sign;
                return new Location(agentLoc.x,agentLoc.y+sign);
            } else {
                if (target.x < agentLoc.x) sign = -1*sign;
                return new Location(agentLoc.x+sign,agentLoc.y);
            }

        }else if ( target.x != agentLoc.x){
            if (target.x < agentLoc.x) sign = -1*sign;
            return new Location(agentLoc.x+sign,agentLoc.y);
        } else {
            if (target.y < agentLoc.y) sign = -1*sign;
            return new Location(agentLoc.x,agentLoc.y+sign);
        }
    }

    public static Location getBackwardLocation(Agent agent){
        return getNextLocation(agent,-1);
    }

    public static Location getForwardLocation(Agent agent){
        return getNextLocation(agent,1);
    }

    public static boolean canMoveOn(Location location, Agent a){
        Env perceivedEnvironment = a.getState().partialEnvironment;
        Stack<InteractiveEnvironmentObject> ieo = perceivedEnvironment.map.get(location);
        if (ieo == null) return false;
        if (ieo.peek().getAvailableActions() == null) return false;

        return location.y < perceivedEnvironment.size.height && location.x < perceivedEnvironment.size.width && ieo.peek().getAvailableActions().contains("WalkOn");
    }

    public static boolean canMoveBack(Agent a) {
        return canMoveOn(getBackwardLocation(a),a);
    }

    public static boolean canMoveForward(Agent a) {
        return canMoveOn(getForwardLocation(a),a);
    }

    public static Choice move(Location to, Agent a){
        return new Choice(Action.get("WalkOn"), a.getState().partialEnvironment.get(to));
    }

    public static Choice moveBack(Agent a){
        return move(getBackwardLocation(a),a);
    }

    public static Choice moveForward(Agent a){
        return move(getForwardLocation(a),a);
    }

    public static boolean canGet(Agent agent) {
        return !Decider.getChoicesInvolvingAction("Get", agent).isEmpty();
    }

    public static boolean canDump(Agent agent) {
        return !Decider.getChoicesInvolvingAction("Dump", agent).isEmpty();
    }

    public static boolean hasBox(Agent agent){
        return agent.getState().boxHeld != null;
    }

    public static Choice dump(Agent agent){
        return Decider.getChoicesInvolvingAction("Dump", agent).get(0);
    }

    public static Choice get(Agent agent){
        return Decider.getChoicesInvolvingAction("Get", agent).get(0);
    }

    public static Choice getRandomMove(Agent agent){
        List<Choice> choices = Decider.getChoicesInvolvingAction("WalkOn", agent);
        if (choices.size() == 0) return null;
        double f = Math.random();
        Double d = f*choices.size();

        return choices.get(d.intValue());
    }

    public static boolean canMove(Agent agent){
         return !Decider.getChoicesInvolvingAction("WalkOn", agent).isEmpty();
    }

    public static boolean isSurroundedByWalls(Agent a){
        Env env = a.getState().partialEnvironment;
        Location loc = new Location(a.getLocation().x,a.getLocation().y);
        loc.x ++;
        if (env.get(loc) != null && !env.get(loc).is("Wall")) return false;
        loc.x--;
        loc.y ++;
        if (env.get(loc) != null && !env.get(loc).is("Wall")) return false;
        loc.y --;
        loc.x --;
        if (env.get(loc) != null && !env.get(loc).is("Wall")) return false;
        loc.x ++;
        loc.y --;
        return env.get(loc) != null && env.get(loc).is("Wall");
    }

    public static boolean isBoxHeld(Agent a){
        return a.getState().boxHeld != null;
    }

    public static void setLongTermObjectiveToStorage(Agent a){
        a.getState().longTermGoal = a.getState().storage;
    }

    public static void setLongTermObjectiveToDepot(Agent a){
        a.getState().longTermGoal = a.getState().depot;
    }

    // cheating a bit on the rule system.
    public static void setWayPoint (Agent a){
        Location wp = null;
        if (isBoxHeld(a)){
             if (a.getState().wayToDepot != null){
                 int xa = a.getLocation().x;
                 int xo = a.getState().depot.x;
                 int xp = a.getState().wayToDepot.x;

                 if ((xa>xp && xp>xo) || (xa<xp && xp<xo)) {
                     wp = a.getState().wayToDepot;
                 }else {
                     wp = a.getState().depot;
                 }
             } else {
                 wp = a.getState().depot;
             }
        } else {
            if (a.getState().wayToStorage != null){
                int xa = a.getLocation().x;
                int xo = a.getState().storage.x;
                int xp = a.getState().wayToStorage.x;

                if ((xa>xp && xp>xo) || (xa<xp && xp<xo)) {
                    wp = a.getState().wayToStorage;
                } else {
                    wp = a.getState().storage;
                }
            } else {
                wp = a.getState().storage;
            }
        }
        a.getState().waypoint = wp;
    }

    public static boolean isOnWrongCorridor(Agent a){
        if (a.getState().boxHeld != null){
            if (a.getState().wayToStorage == null) return false;
            if (a.getLocation().x == a.getState().wayToStorage.x) return true;
        } else {
            if (a.getState().wayToDepot == null) return false;
            if (a.getLocation().x == a.getState().wayToDepot.x) return true;
        }
        return false;
    }
}
