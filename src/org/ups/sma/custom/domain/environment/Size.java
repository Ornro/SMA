package org.ups.sma.custom.domain.environment;

/**
 * Class used to allow the user to implement a custom size
 * object for it's environment.
 */
public class Size {

    // 2D example
    public int width;
    public int height;

    // implement your own constructor
    public Size(int width, int height) {
        this.width = width;
        this.height = height;
    }

    // must implement the equals method
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Size)) return false;

        Size size = (Size) o;

        if (height != size.height) return false;
        if (width != size.width) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = width;
        result = 31 * result + height;
        return result;
    }

    @Override
    public String toString() {
        return "width="+width+", height="+height;
    }
}
