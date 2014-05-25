package org.ups.sma.impl.environement;

import org.ups.sma.domain.custom.environnement.Location;
import org.ups.sma.domain.environnement.Env;
import org.ups.sma.domain.environnement.Filter;
import org.ups.sma.domain.environnement.InteractiveEnvironmentObject;
import org.ups.sma.interfaces.IEnvironmentManager;
import org.ups.sma.interfaces.Stateful;

import java.util.Map;
import java.util.Set;

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
    public Env getFilteredEnvironment(Filter filter) {
        Env filteredEnv = new Env(this.environment.size);
        Set<Map.Entry<Location,InteractiveEnvironmentObject>> entries = this.environment.map.entrySet();
        for (Map.Entry<Location,InteractiveEnvironmentObject> entry : entries) {
            if(filter.isAcceptable(entry.getKey(),entry.getValue())){
                filteredEnv.map.put(entry.getKey(),entry.getValue());
            }

        }
        return filteredEnv;
    }

    @Override
    public Env getFullEnvironment() {
        return environment;
    }

    @Override
    public void notifyStateChange(Stateful s) {

    }
}
