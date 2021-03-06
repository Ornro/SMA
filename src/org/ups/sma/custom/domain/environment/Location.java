package org.ups.sma.custom.domain.environment;

import com.google.gson.Gson;

/**
 * Created by Ben on 24/05/14.
 */
public class Location {
    // custom object to be filled and used to locate an environment object

    // 2D example
    public int x;
    public int y;

    public Location (int x, int y) {
        this.x = x;
        this.y = y;
    }

    // it must implements the equals method
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Location)) return false;

        Location location = (Location) o;

        if (x != location.x) return false;
        if (y != location.y) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }

    @Override
    public String toString() {
        return x+"@"+y;
    }
}
