package org.ups.sma.domain;

import org.ups.sma.domain.environnement.InteractiveEnvironmentObject;

/**
 * Created by Ben on 07/06/14.
 */
public class Choice {
    private Action action;
    private InteractiveEnvironmentObject object;

    public Choice(Action action, InteractiveEnvironmentObject object){
        this.action = action;
        this.object = object;
    }

    /**
     * Return if the action is the given action.
     * @param action
     *
     * @return
     */
    public boolean isAction(String action){
        return this.action.getClass().getSimpleName().equals(action);
    }

    public InteractiveEnvironmentObject getObject(){
        return this.object;
    }

    public Action getAction(){
        return this.action;
    }

    protected boolean isObjectInvolved( String object){
        try {
            Class<?> c = Class.forName("org.ups.sma.custom.domain.objects."+object);
            return c.isInstance(this.object);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }


}
