package org.ups.sma.impl.agent.impl;

import org.ups.sma.domain.Action;
import org.ups.sma.domain.Choice;
import org.ups.sma.domain.environnement.Filter;
import org.ups.sma.domain.environnement.InteractiveEnvironmentObject;
import org.ups.sma.impl.agent.Agent;

import java.util.List;
import java.util.Map;

/**
 * Created by Ben on 24/05/14.
 */
public class Effector {
    private Filter range;
    private List<String> abilities;

    public void execute(List<Choice> choices, Agent agent) {
        for (Choice c : choices){
            c.getAction().execute(agent,c.getObject());
        }
    }

    public Filter getRange(){ return this.range; }

    public void setRange(Filter range) {this.range=range;}

    public List<String> getAbilities() {
        return abilities;
    }

    public void setAbilities(List<String> abilities) {
        this.abilities = abilities;
    }
}
