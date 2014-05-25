package org.ups.sma.domain.environnement;

import org.ups.sma.domain.Action;

import java.util.ArrayList;
import java.util.List;

public abstract class InteractiveEnvironmentObject extends LocalizableEnvironmentObject {
    protected List<Action> availableActions = new ArrayList<Action>();

	public List<Action> getAvailableActions(){
        return availableActions;
    }

    public List<Action> getPerceptiveActions(){
        List<Action> actions = new ArrayList<Action>();

        for (Action a : availableActions){
            if (a.type == Action.ActionType.PERCEPTIVE) actions.add(a);
        }

        return actions;
    }

    public List<Action> getActiveActions(){
        List<Action> actions = new ArrayList<Action>();

        for (Action a : availableActions){
            if (a.type == Action.ActionType.ACTIVE) actions.add(a);
        }

        return actions;
    }

    /**
     * Must be implemented, it is used to destroy
     * environment elements when replacing them by others.
     */
    public abstract void destroy();

    /**
     * Must be implemented, is used to generate different
     * instances of a specified object
     * e.g: when you want to change some environment
     * elements and replace them by some other you don't
     * want the replacing elements to be the same instance
     * of a single object but several identical instances.
     * This method is used when an environment is changed.
     *
     * @return The cloned object
     */
    public abstract InteractiveEnvironmentObject clone();
}
