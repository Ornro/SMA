package org.ups.sma.domain.environment;

import org.ups.sma.custom.domain.environment.Location;

/**
 * Created by Ben on 24/05/14.
 */
public abstract class LocalizableEnvironmentObject {
    protected Location location;

    public Location getLocation(){
        return this.location;
    }

    public void setLocation ( Location location ){
        this.location = location;
    }
}
