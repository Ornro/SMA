package org.ups.sma.custom.domain.environnement;

/**
 * Created by Ben on 24/05/14.
 */
public class Location {
    // custom object to be filled and used to locate an environment object

    // 2D example
    public int x;
    public int y;

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
}
