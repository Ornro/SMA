package org.ups.sma.custom.impl.agent;

import org.ups.sma.custom.domain.agent.State;
import org.ups.sma.custom.domain.environnement.Location;
import org.ups.sma.domain.Action;
import org.ups.sma.domain.environnement.Env;
import org.ups.sma.domain.environnement.InteractiveEnvironmentObject;
import org.ups.sma.impl.agent.Agent;
import org.ups.sma.impl.agent.interfaces.Decider;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Ben on 24/05/14.
 */
public class Decide extends Decider {

    @Override
    public Map<Action, InteractiveEnvironmentObject> getNextMove(Agent agent, Env perceivedEnvironment) {
        if (isCorridorEntrance(agent.getLocation(),perceivedEnvironment)){
            return getInCorridorDecision(agent, perceivedEnvironment);
        } else if (isCorridor(agent.getLocation(),perceivedEnvironment)){
            return corridorDecision(agent, perceivedEnvironment);
        } else {
            Map<Action, InteractiveEnvironmentObject> dumpOrGet;
            if ( agent.getState().boxHolded != null ){
                dumpOrGet = canDump(agent, perceivedEnvironment);
            } else {
                dumpOrGet = canGet(agent, perceivedEnvironment);
            }

            if ( dumpOrGet == null ) {
                return notInCorridorDecision(agent, perceivedEnvironment);
            }else {
                return dumpOrGet;
            }
        }
    }

    private Map<Action, InteractiveEnvironmentObject> canGet(Agent agent, Env perceivedEnvironment) {
        Map<Action, InteractiveEnvironmentObject> action = new HashMap<Action, InteractiveEnvironmentObject>();

        List<InteractiveEnvironmentObject> gettableObjects =  whereIsActionAvailable("Get",getAvailableMoves(agent,perceivedEnvironment));
        if (gettableObjects != null){
            action.put(getAction("Get"),gettableObjects.get(0));
            return action;
        }
        return null;
    }

    private Map<Action, InteractiveEnvironmentObject> canDump(Agent agent, Env perceivedEnvironment) {
        Map<Action, InteractiveEnvironmentObject> action = new HashMap<Action, InteractiveEnvironmentObject>();

        List<InteractiveEnvironmentObject> dumpableObjects =  whereIsActionAvailable("Dump",getAvailableMoves(agent,perceivedEnvironment));
        if (dumpableObjects != null){
            action.put(getAction("Dump"),dumpableObjects.get(0));
            return action;
        }
        return null;
    }

    private Map<Action, InteractiveEnvironmentObject> getInCorridorDecision(Agent agent, Env perceivedEnvironment){
        Map<Action, InteractiveEnvironmentObject> action = new HashMap<Action, InteractiveEnvironmentObject>();
        Location next = getForwardLocation(agent);
        State agentState = agent.getState();

        if (canMoveOn(next,perceivedEnvironment)) {
            action.put(getAction("WalkOn"),perceivedEnvironment.get(next));

            if (agentState.boxHolded != null){
                agentState.wayToDepot = agent.getLocation();
            }else {
                agentState.wayToStorage = agent.getLocation();
            }

            return action;

        } else {
            if (agentState.wayToDepot == agent.getLocation()) {
                agentState.wayToDepot = null;
            } if (agentState.wayToStorage == agent.getLocation()) {
                agentState.wayToStorage = null;
            }
            return notInCorridorDecision(agent, perceivedEnvironment);
        }
    }

    private Map<Action, InteractiveEnvironmentObject> notInCorridorDecision(Agent agent, Env perceivedEnvironment){
        Map<Action, InteractiveEnvironmentObject> action = new HashMap<Action, InteractiveEnvironmentObject>();
        Location next = getForwardLocation(agent);
        State agentState = agent.getState();

        if (canMoveOn(next,perceivedEnvironment)) {
            action.put(getAction("WalkOn"),perceivedEnvironment.get(next));
            return action;

        } else {
            if (agentState.bestIsNorth){
                agentState.goingSouth = true;
            } else if (!agent.getState().goingNorth && !agent.getState().goingSouth) {
                agentState.goingNorth = true;
            }

            if (agentState.goingNorth){
                next = agent.getLocation();
                next.y--;
                if (canMoveOn(next,perceivedEnvironment)) {
                    action.put(getAction("WalkOn"),perceivedEnvironment.get(next));
                    agentState.bestIsNorth = false;
                    return action;
                } else {
                    agentState.goingNorth = false;
                    agentState.goingSouth = true;
                }
            }
            if (agentState.goingSouth) { // north is not an option
                next = agent.getLocation();
                next.y++;
                if (canMoveOn(next,perceivedEnvironment)) {
                    action.put(getAction("WalkOn"),perceivedEnvironment.get(next));
                    agentState.bestIsNorth = false;
                    return action;
                } else {
                    agentState.goingSouth = false;
                }
            }
            if (agentState.bestIsNorth){ // may move forward
                return action; // we wait
            }
            return move(getBackwardLocation(agent),perceivedEnvironment);
        }
    }

    private boolean isCorridor(Location _loc, Env perceivedEnvironment){
        Location loc = new Location(_loc.x,_loc.y);
        loc.y++;
        if (is(perceivedEnvironment.get(loc),"Wall") || loc.y>perceivedEnvironment.size.height){
            loc.y-=2;
            if (is(perceivedEnvironment.get(loc),"Wall") || loc.y<perceivedEnvironment.size.height){
                return true;
            }
        }
        return false;
    }

    private boolean isCorridorEntrance(Location _loc, Env perceivedEnvironment){
        Location loc = new Location(_loc.x,_loc.y);
        loc.x++;
        if (is(perceivedEnvironment.get(loc),"Wall")){
            return false;
        }
        if (isCorridor(loc,perceivedEnvironment)){
            return true;
        }else {
            loc.x-=2;
            return !is(perceivedEnvironment.get(loc), "Wall") && isCorridor(loc, perceivedEnvironment);
        }

    }
    private Map<Action, InteractiveEnvironmentObject> corridorDecision(Agent agent, Env perceivedEnvironment){
        Map<Action, InteractiveEnvironmentObject> action = new HashMap<Action, InteractiveEnvironmentObject>();
        Location next = getForwardLocation(agent);
        if ( isAgent(perceivedEnvironment.get(next)) ) {
            next = getBackwardLocation(agent);

            return move(next,perceivedEnvironment);
        } else {
            if (canMoveOn(next,perceivedEnvironment)){
                action.put(getAction("WalkOn"),perceivedEnvironment.get(next));
                return action;
            } else {
                // can't move no agent in front
                agent.destroy();
                return null;
            }

        }
    }

    private Map<Action, InteractiveEnvironmentObject> move(Location to, Env perceivedEnvironment ){
        Map<Action, InteractiveEnvironmentObject> action = new HashMap<Action, InteractiveEnvironmentObject>();
        if (canMoveOn(to,perceivedEnvironment)){
            action.put(getAction("WalkOn"),perceivedEnvironment.get(to));
        }

        return action;
    }

    private Location getForwardLocation(Agent agent){
        Location agentLoc = agent.getLocation();
        Location target;

        if (agent.getState().boxHolded != null){
            if (agent.getState().wayToDepot != null && agent.getLocation() != agent.getState().wayToDepot){
                target = agent.getState().wayToDepot;
            } else {
                target=agent.getState().depot;
            }
        } else {
            if (agent.getState().wayToStorage != null  && agent.getLocation() != agent.getState().wayToStorage){
                target = agent.getState().wayToStorage;
            } else {
                target=agent.getState().storage;
            }
        }

       if ( target.x != agentLoc.x ){
            int sign = 1;
            if (target.x < agentLoc.x) sign = -1;
            return new Location(agentLoc.x*sign,agentLoc.y);
       } else {
            int sign = 1;
            if (target.y < agentLoc.y) sign = -1;
            agent.getState().bestIsNorth = true;
            return new Location(agentLoc.x,agentLoc.y*sign);
       }
    }

    private Location getBackwardLocation(Agent agent){
        Location agentLoc = agent.getLocation();
        Location target;

        if (agent.getState().boxHolded != null){
            if (agent.getState().wayToDepot != null){
                target = agent.getState().wayToDepot;
            } else {
                target=agent.getState().depot;
            }
        } else {
            if (agent.getState().wayToStorage != null){
                target = agent.getState().wayToStorage;
            } else {
                target=agent.getState().storage;
            }
        }

        if ( target.x != agentLoc.x ){
            int sign = 1;
            if (target.x < agentLoc.x) sign = -1;
            return new Location(agentLoc.x*-sign,agentLoc.y);
        } else {
            int sign = 1;
            if (target.y < agentLoc.y) sign = -1;
            agent.getState().bestIsNorth = true;
            return new Location(agentLoc.x,agentLoc.y*-sign);
        }
    }

    private boolean canMoveOn(Location location, Env perceivedEnvironment){
        if ( location.y>perceivedEnvironment.size.height || location.y<perceivedEnvironment.size.height
                || location.x<perceivedEnvironment.size.width || location.x>perceivedEnvironment.size.width){
            return false;
        }
        return perceivedEnvironment.map.get(location).peek().getAvailableActions().contains("WalkOn");
    }
}
