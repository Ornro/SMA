package org.ups.sma.impl.environment;

import org.ups.sma.custom.domain.environment.Location;
import org.ups.sma.custom.domain.environment.Size;
import org.ups.sma.custom.domain.environment.Type;
import org.ups.sma.custom.domain.environment.objects.Default;
import org.ups.sma.domain.environment.Env;
import org.ups.sma.domain.environment.InteractiveEnvironmentObject;
import org.ups.sma.interfaces.IEnvironmentManager;

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

/**
 * Created by Ben on 24/05/14.
 */
public final class EnvironmentManager implements IEnvironmentManager {
    private Env environment;
    private static volatile EnvironmentManager instance;

    private EnvironmentManager(Size size){
        this.environment = new Env();
        this.environment.size = size;
    }

    private EnvironmentManager(Env env){
        environment = env;
    }

    public static EnvironmentManager initialize(Size size){
        if (instance == null) // we try to avoid using synchronized
        {
            synchronized (EnvironmentManager.class) {
                if (instance == null)
                    instance = new EnvironmentManager(size);
            }
        }
        return instance;
    }

    public static EnvironmentManager initialize(Env env){
        if (instance == null) // we try to avoid using synchronized
        {
            synchronized (EnvironmentManager.class) {
                if (instance == null)
                    instance = new EnvironmentManager(env);
            }
        }
        return instance;
    }

    public static EnvironmentManager getInstance(){
        if (instance == null) // we try to avoid using synchronized
        {
            synchronized (EnvironmentManager.class) {
                if (instance == null)
                    instance = null;
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

    public void addObject(Location location, InteractiveEnvironmentObject object){
        if (environment.map.get(location) == null){
            environment.map.put(location,new Stack<InteractiveEnvironmentObject>());
        }
        environment.map.get(location).push(object);
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

    public void setEnvironment(Env env) {
        this.environment = env;
    }
}
