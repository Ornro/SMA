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
import org.ups.sma.impl.custom.agent.Percieve;
import org.ups.sma.interfaces.ActionManager;
import org.ups.sma.interfaces.Actor;
import org.ups.sma.interfaces.Statefull;

/**
 * Created by Ben on 24/05/14.
 */
public class Agent implements Statefull, Actor{
    private State state;
    private Effector effector;
    private Perciever perciever;
    private Decider decider;

    public Agent(){
        ActionManager manager = ActionEngine.getInstance();
        manager.register(this);
        this.effector = new Act();
        this.perciever = new Percieve();
        this.decider = new Decide();
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
        effector.execute(decider.getNextMove(perciever.getInformations(this),this.state));
    }
}
