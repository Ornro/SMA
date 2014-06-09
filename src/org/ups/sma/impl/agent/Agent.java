package org.ups.sma.impl.agent;


import org.ups.sma.custom.domain.environment.Location;
import org.ups.sma.domain.Action;
import org.ups.sma.custom.domain.agent.Public;
import org.ups.sma.custom.domain.agent.State;
import org.ups.sma.domain.Choice;
import org.ups.sma.domain.environment.Env;
import org.ups.sma.domain.environment.Filter;
import org.ups.sma.domain.environment.InteractiveEnvironmentObject;
import org.ups.sma.impl.actionengine.ActionEngine;
import org.ups.sma.impl.agent.impl.Decider;
import org.ups.sma.impl.agent.impl.Effector;
import org.ups.sma.impl.agent.impl.Perceiver;
import org.ups.sma.interfaces.ActionManager;
import org.ups.sma.interfaces.Actor;
import org.ups.sma.interfaces.Savable;
import org.ups.sma.interfaces.Stateful;

import java.lang.reflect.Field;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by Ben on 24/05/14.
 */

public class Agent extends InteractiveEnvironmentObject implements Stateful, Actor, Savable {
    private State state;
    private Effector effector;
    private Perceiver perceiver;
    private Decider decider;
    private Logger LOGGER = Logger.getLogger(Agent.class.getName() + id);

    public Agent(State state, Effector effector, Perceiver perceiver, Decider dec, List<String> availableActions, Location location) {
        super(availableActions, location);
        this.state = state;
        this.effector = effector;
        this.perceiver = perceiver;
        this.decider = dec;

        /**
         * The agent registers himself to the action manager
         * that will call him whenever he wants him to act.
         */
        ActionManager manager = ActionEngine.getInstance();
        manager.register(this);
    }

    public Decider getDecider(){
        return this.decider;
    }

    public List<String> getAbilities(){ return effector.getAbilities(); }

    public Filter getRange(){ return effector.getRange(); }

    @Override
    public State getState() { return this.state; }

    @Override
    public State getPublicState(){
        State s = new State();
        Field[] fields = state.getClass().getFields();
        for (Field field : fields){
            if (field.getAnnotation(Public.class) != null){
                try {
                    field.set(s,field.get(this.state));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return s;
    }

    @Override
    public void act() {
        System.out.println("Agent is acting");
        Env perceivedEnvironment = perceiver.getInformation(this);

        this.state.partialEnvironment.merge(perceivedEnvironment);

        List<Choice> actionsToExecute = decider.getNextMove(this);
        logAgentActions(actionsToExecute);
        effector.execute(actionsToExecute,this);
    }

    private void logAgentActions(List<Choice> choices){
        for (Choice c : choices){
            Action a = c.getAction();
            InteractiveEnvironmentObject object = c.getObject();
            LOGGER.info("Agent "+id+" will execute "+a+" on "+object.getType());
        }
    }

    @Override
    public void destroy() {
        ActionManager manager = ActionEngine.getInstance();
        manager.unregister(this);

        //TODO: fill this method
    }

    @Override
    public String saveAsString() {
        return null;
    }

    @Override
    public Savable InstantiateFromString(String s) {
        return null;
    }
}

