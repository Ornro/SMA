package org.ups.sma.impl.agent;


import org.ups.sma.domain.Action;
import org.ups.sma.custom.domain.agent.Public;
import org.ups.sma.custom.domain.agent.State;
import org.ups.sma.domain.Choice;
import org.ups.sma.domain.environnement.Env;
import org.ups.sma.domain.environnement.Filter;
import org.ups.sma.domain.environnement.InteractiveEnvironmentObject;
import org.ups.sma.impl.actionengine.ActionEngine;
import org.ups.sma.impl.agent.interfaces.Decider;
import org.ups.sma.impl.agent.interfaces.Effector;
import org.ups.sma.impl.agent.interfaces.Perciever;
import org.ups.sma.interfaces.ActionManager;
import org.ups.sma.interfaces.Actor;
import org.ups.sma.interfaces.Savable;
import org.ups.sma.interfaces.Stateful;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

/**
 * Created by Ben on 24/05/14.
 */

public class Agent extends InteractiveEnvironmentObject implements Stateful, Actor, Savable {
    private State state;
    private Effector effector;
    private Perciever perciever;
    private Decider decider;
    private Logger LOGGER = Logger.getLogger(Agent.class.getName() + id);
    private List<String> abilities;
    private Filter range;

    public Agent(State state, Effector effector, Perciever perciever, Decider decider, List<String> availableActions ,List<String> abilities, Filter range) {
        super(availableActions);
        this.state = state;
        this.effector = effector;
        this.perciever = perciever;
        this.decider = decider;
        this.abilities = abilities;
        this.range = range;

        this.effector.setRange(this.range);

        /**
         * The agent registers himself to the action manager
         * that will call him whenever he wants him to act.
         */
        ActionManager manager = ActionEngine.getInstance();
        manager.register(this);
    }

    public List<String> getAbilities(){ return this.abilities; }

    public Filter getRange(){ return this.range; }

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
        Env perceivedEnvironment = perciever.getInformation(this);

        this.state.partialEnvironment.merge(perceivedEnvironment);

        List<Choice> actionsToExecute = decider.getNextMove(this);
        logAgentActions(actionsToExecute);

        effector.execute(actionsToExecute,this);
    }

    private void logAgentActions(List<Choice> choices){
        for (Choice c : choices){
            Action a = c.getAction();
            InteractiveEnvironmentObject object = c.getObject();
            LOGGER.info("Agent "+id+" will execute "+a+" on "+object);
        }
    }

    @Override
    public void destroy() {
        ActionManager manager = ActionEngine.getInstance();
        manager.unregister(this);

        //TODO: fill this method
    }

    @Override
    public InteractiveEnvironmentObject clone() {
        return null;
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
