package org.ups.sma.impl.agent.impl;

import org.ups.sma.custom.domain.environment.Location;
import org.ups.sma.domain.Action;
import org.ups.sma.domain.Choice;
import org.ups.sma.domain.environment.Env;
import org.ups.sma.domain.environment.InteractiveEnvironmentObject;
import org.ups.sma.domain.environment.Rule;
import org.ups.sma.impl.agent.Agent;

import java.util.*;

/**
 * Created by Ben on 24/05/14.
 */
public class Decider {
    private List<Rule> rules;

    public Decider (List<Rule> rules){
        this.rules = rules;
    }

    public List<Choice> getNextMove(Agent agent){
        List<Choice> choices = new ArrayList<Choice>();
        List<Rule> rules = this.rules;
        for (Rule rule : rules){
            if (rule.condition(agent)){
                choices.addAll(rule.choices(agent));
            }
        }
        return choices;
    }

    /**
     * Returns the list of the possible choices that an agent can do knowing his perceived environment.
     * It uses the agent range filter to narrow the results.
     *
     * @param agent the agent
     * @return the list of optional actions an agent can perform
     */
    public List<Choice> getAvailableChoices(Agent agent){
        Env env = getReachableEnvironment(agent);
        List<Choice> moves = new ArrayList<Choice>();
        Set<Map.Entry<Location,Stack<InteractiveEnvironmentObject>>> entries = env.map.entrySet();

        for (Map.Entry<Location,Stack<InteractiveEnvironmentObject>> entry : entries ){
            InteractiveEnvironmentObject object = entry.getValue().peek();
            List<Action> actions = joinAndInstantiate(agent.getAbilities(), object.getAvailableActions());
            for (Action action : actions){
                if (agent.getAbilities().contains(action.toString())){
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
     *
     * @return the list of choices that are involving the given action
     */
    public static List<Choice> getChoicesInvolvingAction(String actionName, Agent agent){
        List<Choice> availableChoices = agent.getDecider().getAvailableChoices(agent);
        List<Choice> narrowedChoices = new ArrayList<Choice>();
        for (Choice c : availableChoices){
            if (c.isAction(actionName)){
                narrowedChoices.add(c);
            }
        }
        return narrowedChoices;
    }

    private static Env getReachableEnvironment(Agent agent){
        return agent.getState().partialEnvironment.applyFilter(agent.getRange(), agent);
    }

    private static List<Action> joinAndInstantiate(List<String> agentActions, List<String> objectActions){
        if (objectActions == null || objectActions.isEmpty() || agentActions == null || agentActions.isEmpty()){
            return new ArrayList<Action>();
        }
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


