package org.ups.sma.custom.impl.agent;

/**
 * Created by Ben on 24/05/14.
 */
public class Decide {
/*
    public List<Choice> getNextMove(Agent agent) {
        List<Choice> finalDecision = new ArrayList<Choice>();
        if (isInCorridorEntrance(agent)){
            finalDecision.add(getInCorridorDecision(agent));
            return finalDecision;
        } else if (isInCorridor(agent)){
            finalDecision.add(corridorDecision(agent));
            return finalDecision;
        } else {
            Choice dumpOrGet;
            if ( agent.getState().boxHeld != null ){
                dumpOrGet = canDump(agent);
            } else {
                dumpOrGet = canGet(agent);
            }

            if ( dumpOrGet == null ) {
                finalDecision.add(notInCorridorDecision(agent));
                return finalDecision;
            }else {
                finalDecision.add(dumpOrGet);
                return finalDecision;
            }
        }
    }

    private Choice canGet(Agent agent) {
        Choice choice = null;
        List<Choice> gettable =  getChoicesInvolvingAction("Get", getAvailableChoices(agent));

        if (gettable != null){
            choice = gettable.get(0);
        }

        return choice;
    }

    private Choice canDump(Agent agent) {
        Choice choice = null;
        List<Choice> dumpable =  getChoicesInvolvingAction("Dump", getAvailableChoices(agent));

        if (dumpable != null){
            choice = dumpable.get(0);
        }

        return choice;
    }


    private Choice getInCorridorDecision(Agent agent){
        Env perceivedEnvironment = agent.getState().partialEnvironment;
        Choice action = null;
        Location next = getForwardLocation(agent);
        State agentState = agent.getState();

        if (canMoveOn(next,perceivedEnvironment)) {
            action = new Choice(Action.get("WalkOn"),perceivedEnvironment.get(next));

            if (agentState.boxHeld != null){
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
            return notInCorridorDecision(agent);
        }
    }

    private Choice notInCorridorDecision(Agent agent){
        Env perceivedEnvironment = agent.getState().partialEnvironment;
        Choice action = null;
        Location next = getForwardLocation(agent);
        State agentState = agent.getState();

        if (canMoveOn(next,perceivedEnvironment)) {
            return new Choice(Action.get("WalkOn"),perceivedEnvironment.get(next));

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
                    action = new Choice(Action.get("WalkOn"),perceivedEnvironment.get(next));
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
                    action = new Choice(Action.get("WalkOn"),perceivedEnvironment.get(next));
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

    private boolean isInCorridor(Agent a){
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

    private boolean isInCorridorEntrance(Agent a){
        Location loc = new Location(a.getLocation().x,a.getLocation().y);
        loc.x++;
        if (a.getState().partialEnvironment.get(loc).is("Wall")){
            return false;
        }
        if (isInCorridor(a)){
            return true;
        }else {
            loc.x-=2;
            return !a.getState().partialEnvironment.get(loc).is("Wall") && isInCorridor(a);
        }

    }
    private Choice corridorDecision(Agent agent){
        Env perceivedEnvironment = agent.getState().partialEnvironment;
        Choice action = null;
        Location next = getForwardLocation(agent);
        if ( perceivedEnvironment.get(next).isAgent() ) {
            next = getBackwardLocation(agent);

            return move(next,perceivedEnvironment);
        } else {
            if (canMoveOn(next,perceivedEnvironment)){
                action = new Choice(Action.get("WalkOn"),perceivedEnvironment.get(next));
                return action;
            } else {
                // can't move no agent in front
                agent.destroy();
                return null;
            }

        }
    }

    private Choice move(Location to, Env perceivedEnvironment){
        Choice action = null;
        if (canMoveOn(to,perceivedEnvironment)) action = new Choice(Action.get("WalkOn"), perceivedEnvironment.get(to));

        return action;
    }

    private Location getForwardLocation(Agent agent){
        Location agentLoc = agent.getLocation();
        Location target;

        if (agent.getState().boxHeld != null){
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

        if (agent.getState().boxHeld != null){
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
    }*/
}
