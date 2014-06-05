package org.ups.sma.custom.domain.environnement;

/**
 * Class used to allow the user to implement a custom size
 * object for it's environment.
 */
public class Size {

    // 2D example
    public int width;
    public int height;

    // default constructor is adviced but not mandatory.
    public Size() {
        this.height = 100;
        this.width = 100;
    }

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
}
