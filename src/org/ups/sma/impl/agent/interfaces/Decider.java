package org.ups.sma.impl.agent.interfaces;

import org.ups.sma.custom.domain.environnement.Location;
import org.ups.sma.domain.Action;
import org.ups.sma.domain.environnement.Env;
import org.ups.sma.domain.environnement.InteractiveEnvironmentObject;
import org.ups.sma.impl.agent.Agent;

import java.util.*;

/**
 * Created by Ben on 24/05/14.
 */
public abstract class Decider {
    public abstract Map<Action,InteractiveEnvironmentObject> getNextMove(Agent agent, Env perceivedEnvironment);

    protected Map<InteractiveEnvironmentObject,List<Action>> getAvailableMoves(Agent agent, Env perceivedEnvironment){
        Env env = getReachableEnvironment(agent,perceivedEnvironment);
        Map<InteractiveEnvironmentObject,List<Action>> moves = new HashMap<InteractiveEnvironmentObject,List<Action>>();
        Set<Map.Entry<Location,Stack<InteractiveEnvironmentObject>>> entries = env.map.entrySet();
        for (Map.Entry<Location,Stack<InteractiveEnvironmentObject>> entry : entries ){
            Stack<InteractiveEnvironmentObject> objects = entry.getValue();
            for (InteractiveEnvironmentObject object : objects){
                moves.put(object, joinAndInstantiate(agent.getAbilities(),object.getAvailableActions()));
            }
        }
        return moves;
    }

    private Env getReachableEnvironment(Agent agent, Env perceivedEnvironment){
        return perceivedEnvironment.applyFilter(agent.getRange());
    }

    private List<Action> joinAndInstantiate(List<String> agentActions, List<String> objectActions){
        List<Action> actions = new ArrayList<Action>();
        List<String> intersection = new ArrayList<String>();
        intersection.addAll(agentActions);
        intersection.retainAll(objectActions);

        for (String s : intersection){
            try {
                Class<Action> c = (Class<Action>) Class.forName("org.ups.sma.custom.impl.actions."+s);
                actions.add(c.newInstance());
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

        }
        return actions;
    }

    protected List<InteractiveEnvironmentObject> whereIsActionAvailable(String action, Map<InteractiveEnvironmentObject,List<Action>> options){
        List<InteractiveEnvironmentObject> objectsOnWhichActionIsAvailable = new ArrayList<InteractiveEnvironmentObject>();
        Set<Map.Entry<InteractiveEnvironmentObject,List<Action>>> entries = options.entrySet();

        for (Map.Entry<InteractiveEnvironmentObject,List<Action>> entry : entries){
            List<Action> actions = entry.getValue();
            InteractiveEnvironmentObject object = entry.getKey();
            try {
                Class<?> c = Class.forName("org.ups.sma.custom.impl.actions."+action);

                for (Action a : actions){
                    if (c.isInstance(a)){
                        objectsOnWhichActionIsAvailable.add(object);
                    }
                }
            }catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        }
        return objectsOnWhichActionIsAvailable;

    }

    protected boolean is(InteractiveEnvironmentObject ieo, String object){
        try {
            Class<?> c = Class.forName("org.ups.sma.custom.domain.objects."+object);
            return c.isInstance(ieo);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    protected boolean isAgent(InteractiveEnvironmentObject interactiveEnvironmentObject) {
        return interactiveEnvironmentObject instanceof Agent;
    }

    protected Action getAction(String action){
        try {
            Class<?> c = Class.forName("org.ups.sma.custom.impl.actions."+action);
            return (Action) c.newInstance();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }
}


