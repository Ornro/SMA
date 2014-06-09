package org.ups.sma.domain.environment;

import org.ups.sma.custom.domain.environment.Location;
import org.ups.sma.impl.environment.EnvironmentManager;

/**
 * Created by Ben on 24/05/14.
 */
public abstract class LocalizableEnvironmentObject {
    protected Location location;

    public LocalizableEnvironmentObject(Location location){
        this.location = location;
    }

    public Location getLocation(){
        return this.location;
    }

    public void setLocation ( Location location ){
        this.location = location;
    }
}
