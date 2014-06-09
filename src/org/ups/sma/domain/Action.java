package org.ups.sma.domain;

import org.ups.sma.domain.environment.InteractiveEnvironmentObject;
import org.ups.sma.impl.agent.Agent;

/**
 * Created by Ben on 24/05/14.
 */
public abstract class Action {

    /**
     * Method that must be overriden
     * @param a
     * @param o
     */

    public abstract void execute(Agent a, InteractiveEnvironmentObject o);

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }

    @Override
    public boolean equals(Object other){
        return (this.getClass().getSimpleName()).equals(other.getClass().getSimpleName());
    }

    public static Action get(String action){
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
