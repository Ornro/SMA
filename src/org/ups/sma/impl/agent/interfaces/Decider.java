package org.ups.sma.impl.agent.interfaces;

import org.ups.sma.custom.domain.environment.Location;
import org.ups.sma.domain.Action;
import org.ups.sma.domain.Choice;
import org.ups.sma.domain.environnement.Env;
import org.ups.sma.domain.environnement.InteractiveEnvironmentObject;
import org.ups.sma.impl.agent.Agent;

import java.util.*;

/**
 * Created by Ben on 24/05/14.
 */
public abstract class Decider {
    public abstract List<Choice> getNextMove(Agent agent);

    /**
     * Returns the list of the possible choices that an agent can do knowing his perceived environment.
     * It uses the agent range filter to narrow the results.
     *
     * @param agent the agent
     * @return the list of optional actions an agent can perform
     */
    public static List<Choice> getAvailableChoices(Agent agent){
        Env env = getReachableEnvironment(agent);
        List<Choice> moves = new ArrayList<Choice>();
        Set<Map.Entry<Location,Stack<InteractiveEnvironmentObject>>> entries = env.map.entrySet();

        for (Map.Entry<Location,Stack<InteractiveEnvironmentObject>> entry : entries ){
            Stack<InteractiveEnvironmentObject> objects = entry.getValue();
            for (InteractiveEnvironmentObject object : objects){
                List<Action> actions = joinAndInstantiate(agent.getAbilities(), object.getAvailableActions());
                for (Action action : actions){
                    moves.add(new Choice(action,object));
                }
            }
        }

        return moves;
    }

    /**
     * Return all the choices that are involving the given action
     *
     * @param actionName the action
     * @param availableChoices the list of all available choices
     *
     * @return the list of choices that are involving the given action
     */
    public static List<Choice> getChoicesInvolvingAction(String actionName, List<Choice> availableChoices){
        List<Choice> narrowedChoices = new ArrayList<Choice>();
        for (Choice c : availableChoices){
            if (c.isAction(actionName)){
                narrowedChoices.add(c);
            }
        }
        return narrowedChoices;
    }

    private static Env getReachableEnvironment(Agent agent){
        return agent.getState().partialEnvironment.applyFilter(agent.getRange());
    }

    private static List<Action> joinAndInstantiate(List<String> agentActions, List<String> objectActions){
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
}


