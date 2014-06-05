package org.ups.sma.interfaces;

/**
 * Created by Ben on 29/05/14.
 */
public interface Savable {
    public String saveAsString();
    public Savable InstantiateFromString(String s);
}
