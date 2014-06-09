package org.ups.sma.domain.environment;


import org.ups.sma.custom.domain.environment.Location;
import org.ups.sma.custom.domain.environment.Type;
import org.ups.sma.domain.Action;
import org.ups.sma.impl.agent.Agent;
import org.ups.sma.impl.environment.EnvironmentManager;

import java.util.ArrayList;
import java.util.List;

public abstract class InteractiveEnvironmentObject extends LocalizableEnvironmentObject{
    protected List<String> availableActions = new ArrayList<String>();
    protected long id;
    protected Type type;

    protected InteractiveEnvironmentObject(List<String> availableActions, Location location) {
        super(location);
        this.availableActions = availableActions;

        EnvironmentManager.getInstance().addObject(this.location, this);
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

    public void setType(Type t) { this.type = t; }

    public long getId(){
        return this.id;
    }

    /**
     * Must be implemented, it is used to destroy
     * environment elements when replacing them by others.
     */
    public abstract void destroy();

    public boolean isAgent() {
        return this instanceof Agent;
    }

    public boolean is(String object){
        try {
            Class<Action> c = (Class<Action>) Class.forName("org.ups.sma.custom.domain.environment.objects."+object);
            return c.isInstance(this);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        return this.getClass().isInstance(o);
    }
}
