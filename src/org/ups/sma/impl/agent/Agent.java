package org.ups.sma.impl.agent;


import java.lang.reflect.Field;

import org.ups.sma.domain.custom.agent.Public;
import org.ups.sma.domain.custom.agent.State;
import org.ups.sma.impl.actionengine.ActionEngine;
import org.ups.sma.impl.agent.impl.Act;
import org.ups.sma.impl.agent.interfaces.Decider;
import org.ups.sma.impl.agent.interfaces.Effector;
import org.ups.sma.impl.agent.interfaces.Perciever;
import org.ups.sma.impl.custom.agent.Decide;
import org.ups.sma.impl.custom.agent.Perceive;

import org.ups.sma.domain.Action;
import org.ups.sma.domain.environnement.Env;
import org.ups.sma.domain.environnement.InteractiveEnvironmentObject;
import org.ups.sma.domain.custom.agent.Public;
import org.ups.sma.domain.custom.agent.State;
import org.ups.sma.impl.actionengine.ActionEngine;
import org.ups.sma.impl.agent.interfaces.*;

import org.ups.sma.interfaces.ActionManager;
import org.ups.sma.interfaces.Actor;
import org.ups.sma.interfaces.Stateful;


import java.lang.reflect.Field;
import java.util.List;

/**
 * Created by Ben on 24/05/14.
 */
public class Agent extends InteractiveEnvironmentObject implements Stateful, Actor {
    private State state;
    private Effector effector;
    private Perciever perciever;
    private Decider decider;
    private Env partialEnvironment;

    public Agent(State state, Effector effector, Perciever perciever, Decider decider, Env partialEnvironment) {
        this.state = state;
        this.effector = effector;
        this.perciever = perciever;
        this.decider = decider;
        this.partialEnvironment = partialEnvironment;

        /**
         * The agent registers himself to the action manager
         * that will call him whenever he wants him to act.
         */
        ActionManager manager = ActionEngine.getInstance();
        manager.register(this);
    }

    @Override
    public State getState() {
        return state;
    }

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
        //TODO: add logger here to save the environment and the action
        Env perceivedEnvironment = perciever.getInformation(this);

        partialEnvironment.merge(perceivedEnvironment);

        List<Action> actionsToExecute = decider.getNextMove(this);
        effector.execute(actionsToExecute,this);
    }

    @Override
    public void destroy() {
        //TODO: fill this method
    }

    @Override
    public InteractiveEnvironmentObject clone() {
        return null;
    }
}
