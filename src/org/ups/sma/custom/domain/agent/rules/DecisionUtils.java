package org.ups.sma.custom.domain.agent.rules;

import org.ups.sma.custom.domain.environment.Location;
import org.ups.sma.domain.Action;
import org.ups.sma.domain.Choice;
import org.ups.sma.domain.environnement.Env;
import org.ups.sma.impl.agent.Agent;
import org.ups.sma.impl.agent.impl.Decider;

/**
 * Created by Ben on 07/06/14.
 */
public class DecisionUtils {

    public static boolean isInCorridor(Agent a){
        Env perceivedEnvironment = a.getState().partialEnvironment;
        Location loc = new Location(a.getLocation().x,a.getLocation().y);
        loc.y++;
        if (perceivedEnvironment.get(loc).is("Wall") || loc.y>perceivedEnvironment.size.height){
            loc.y-=2;
            if (perceivedEnvironment.get(loc).is("Wall") || loc.y<perceivedEnvironment.size.height){
                return true;
            }
        }
        return false;
    }

    public static boolean isRobotInFront(Agent a){
        Env perceivedEnvironment = a.getState().partialEnvironment;
        Location loc = getForwardLocation(a);
        return perceivedEnvironment.get(loc).isAgent();
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

        return !(location.y > perceivedEnvironment.size.height || location.y < perceivedEnvironment.size.height || location.x < perceivedEnvironment.size.width || location.x > perceivedEnvironment.size.width) && perceivedEnvironment.map.get(location).peek().getAvailableActions().contains("WalkOn");
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
        return Decider.getChoicesInvolvingAction("WalkOn", agent).get(0);

    }

    public static boolean canMove(Agent agent){
         return !Decider.getChoicesInvolvingAction("WalkOn", agent).isEmpty();
    }

    public static boolean isSurroundedByWalls(Agent a){
        Env env = a.getState().partialEnvironment;
        Location loc = a.getLocation();
        loc.x ++;
        if (!env.get(loc).is("Wall")) return false;
        loc.y ++;
        if (!env.get(loc).is("Wall")) return false;
        loc.x -= 2;
        if (!env.get(loc).is("Wall")) return false;
        loc.y -= 2;
        return env.get(loc).is("Wall");
    }

    public static boolean isBoxHeld(Agent a){
        return a.getState().boxHeld != null;
    }

    public static void setLongTermObjectiveToStorage(Agent a){
        a.getState().longTermGoal = a.getState().wayToStorage;
    }

    public static void setLongTermObjectiveToDepot(Agent a){
        a.getState().longTermGoal = a.getState().wayToDepot;
    }

    public static boolean isWaypointSet(Agent a) {
        boolean b = a.getState().boxHeld != null && a.getState().wayToDepot != null;
        boolean b1 = a.getState().boxHeld == null && a.getState().wayToStorage != null;

        return b || b1;
    }

    // cheating a bit on the rule system.
    public static void setWayPoint (Agent a){
        Location wp = null;
        if (a.getState().boxHeld != null && a.getState().wayToDepot != null) wp = a.getState().wayToDepot;
        if (a.getState().boxHeld == null && a.getState().wayToStorage != null) wp = a.getState().wayToStorage;
        a.getState().waypoint = wp;
    }
}
