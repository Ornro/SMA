package org.ups.sma.domain.environnement;


import org.ups.sma.custom.domain.environnement.Type;

import java.util.ArrayList;
import java.util.List;

public abstract class InteractiveEnvironmentObject extends LocalizableEnvironmentObject{
    protected List<String> availableActions = new ArrayList<String>();
    protected long id;
    protected Type type;

    protected InteractiveEnvironmentObject(List<String> availableActions) {
        super();
        this.availableActions = availableActions;
    }

    /**
     * If null is returned then the object is not drawable
     * @return
     */
    public List<String> getAvailableActions(){
        return availableActions;
    }

    public boolean canBeDone( String action ){
        return this.getAvailableActions().contains(action);
    }

    public Type getType(){
        return this.type;
    }

    public long getId(){
        return this.id;
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
