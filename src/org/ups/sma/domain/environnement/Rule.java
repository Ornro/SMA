package org.ups.sma.domain.environnement;

import org.ups.sma.domain.Choice;
import org.ups.sma.impl.agent.Agent;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ben on 07/06/14.
 */
public abstract class Rule {

    public abstract boolean condition (Agent a);

    public abstract List<Choice> choices (Agent a);

    public List<Choice> apply(Agent a){
        if (condition(a)) return choices(a);
        return new ArrayList<Choice>();
    }
}
