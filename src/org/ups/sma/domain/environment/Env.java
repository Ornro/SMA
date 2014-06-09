package org.ups.sma.domain.environment;

import org.ups.sma.custom.domain.environment.Location;
import org.ups.sma.custom.domain.environment.Size;
import org.ups.sma.impl.agent.Agent;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

public class Env {
    public Size size;
	public Map<Location,Stack<InteractiveEnvironmentObject>> map;

    /**
     * Default constructor may not be safe if the user has not implemented
     * the necessary methods.
     */
    public Env() {
        this.map = new HashMap<Location,Stack<InteractiveEnvironmentObject>>();
        this.size = new Size();
    }

    // Constructor that is safe to use.
    public Env(Size size, Map<Location, Stack<InteractiveEnvironmentObject>> map) {
        this.size = size;
        this.map = map;
    }

    // Constructor that is safe to use.
    public Env(Size size) {
        this.size = size;
        this.map = new HashMap<Location, Stack<InteractiveEnvironmentObject>>();
    }

    /**
     * Merges the given environment to this one.
     * We suppose this one is the old one and if any conflict is met
     * this environment will be overwritten.
     *
     * @param other the new part of environment
     * @return true if the merge was done false if not. It
     * may happen that the environments are not merged if their
     * size is different.
     *
     * NOTE: an environment can have a big size and yet only
     * contain information for a tiny part of it. The check only
     * ensures that the environment won't be bigger than the old one
     * without forcing the user to implement a method telling which
     * of two sizes is bigger which may be complicated.
     */
    public boolean merge(Env other){
        if (this.size.equals(other)){
            for (Location l : other.map.keySet()) {
                Stack current = this.map.get(l);

                while (!current.empty()){
                    InteractiveEnvironmentObject e = (InteractiveEnvironmentObject) current.pop();
                    e.destroy();
                }

                this.map.put(l,other.map.get(l));
            }
            return true;
        }
        return false;
    }

    public Env applyFilter(Filter filter, Agent agent) {
        Env filteredEnv = new Env(this.size);
        Set<Map.Entry<Location,Stack<InteractiveEnvironmentObject>>> entries = this.map.entrySet();
        for (Map.Entry<Location,Stack<InteractiveEnvironmentObject>> entry : entries) {
            if(filter.isAcceptable(entry.getKey(), agent)){
              filteredEnv.map.put(entry.getKey(),entry.getValue());
            }
        }
        return filteredEnv;
    }

    public InteractiveEnvironmentObject get(Location location){
        return this.map.get(location).peek();
    }
}