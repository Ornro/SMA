package org.ups.sma.domain;

import org.ups.sma.impl.agent.Agent;

/**
 * Created by Ben on 24/05/14.
 */
public abstract class Action {
    public enum ActionType{
        PERCEPTIVE,
        ACTIVE
    }

    public ActionType type;

    /**
     * Method that must be overriden
     * @param a
     */
    public void execute(Agent a){

    }


}
