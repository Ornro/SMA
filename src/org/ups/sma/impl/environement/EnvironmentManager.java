package org.ups.sma.impl.environement;

import org.ups.sma.custom.domain.environnement.Location;
import org.ups.sma.domain.environnement.Env;
import org.ups.sma.domain.environnement.Filter;
import org.ups.sma.domain.environnement.InteractiveEnvironmentObject;
import org.ups.sma.interfaces.IEnvironmentManager;

import java.util.Map;
import java.util.Set;
import java.util.Stack;

/**
 * Created by Ben on 24/05/14.
 */
public final class EnvironmentManager implements IEnvironmentManager {
    private Env environment;
    private static volatile EnvironmentManager instance;

    public static EnvironmentManager getInstance(){
        if (instance == null) // we try to avoid using synchronized
        {
            synchronized (EnvironmentManager.class) {
                if (instance == null)
                    instance = new EnvironmentManager();
            }
        }
        return instance;
    }

    @Override
    public Env getFullEnvironment() {
        return environment;
    }

    @Override
    public void update(InteractiveEnvironmentObject s) {

    }

    public void removeAllObjects(Location location){
       this.environment.map.remove(location);
    }

    public void removeObject (InteractiveEnvironmentObject object){
        this.environment.map.get(object.getLocation()).remove(object);
    }

    public void moveObject(InteractiveEnvironmentObject object, Location to){
        this.environment.map.get(object.getLocation()).remove(object);
        this.environment.map.get(to).push(object);
        object.setLocation(to);
    }

    public void replace(Location location, Stack<InteractiveEnvironmentObject> object){
        this.environment.map.put(location,object);
    }

    public long getNextId(){
        long id = 0l;
        Set<Map.Entry<Location,Stack<InteractiveEnvironmentObject>>> entries = this.environment.map.entrySet();
        for (Map.Entry<Location,Stack<InteractiveEnvironmentObject>> entry : entries){
            long tmp = getHighestId(entry.getValue());
            if (id < tmp) id = tmp;
        }

        return id;
    }

    private long getHighestId(Stack<InteractiveEnvironmentObject> objects){
        long id = 0l;
        for (int i=0;i<objects.size();i++){
            long tmp = objects.get(i).getId();
            if ( id < tmp) id = tmp;
        }
        return id;
    }

}
