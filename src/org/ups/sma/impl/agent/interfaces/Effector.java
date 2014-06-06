package org.ups.sma.impl.agent.interfaces;

import org.ups.sma.domain.Action;
import org.ups.sma.domain.environnement.Filter;
import org.ups.sma.domain.environnement.InteractiveEnvironmentObject;
import org.ups.sma.impl.agent.Agent;

import java.util.List;
import java.util.Map;

/**
 * Created by Ben on 24/05/14.
 */
public abstract class Effector {
    private Filter range;

    public abstract void execute(Map<Action,InteractiveEnvironmentObject> toDo, Agent agent);

    public Filter getRange(){ return this.range; }
    public void setRange(Filter range) {this.range=range;}
}
