package org.ups.sma.impl.agent;


import org.ups.sma.custom.domain.environnement.Location;
import org.ups.sma.domain.Action;
import org.ups.sma.custom.domain.agent.Public;
import org.ups.sma.custom.domain.agent.State;
import org.ups.sma.domain.environnement.Env;
import org.ups.sma.domain.environnement.InteractiveEnvironmentObject;
import org.ups.sma.impl.actionengine.ActionEngine;
import org.ups.sma.impl.agent.interfaces.Decider;
import org.ups.sma.impl.agent.interfaces.Effector;
import org.ups.sma.impl.agent.interfaces.Perciever;
import org.ups.sma.impl.environement.EnvironmentManager;
import org.ups.sma.interfaces.ActionManager;
import org.ups.sma.interfaces.Actor;
import org.ups.sma.interfaces.Savable;
import org.ups.sma.interfaces.Stateful;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

/**
 * Created by Ben on 24/05/14.
 */
public class Agent extends InteractiveEnvironmentObject implements Stateful, Actor, Savable {
    private State state;
    private Effector effector;
    private Perciever perciever;
    private Decider decider;
    private Env partialEnvironment;

    public Agent(State state, Effector effector, Perciever perciever, Decider decider, Env partialEnvironment, List<Action> availableActions) {
        super(availableActions);
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

        Map<Action,InteractiveEnvironmentObject> actionsToExecute = decider.getNextMove(this);
        effector.execute(actionsToExecute,this);
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

    public void moveTo(Location location){
        EnvironmentManager.getInstance().moveObject(this,location);
    }
}
