package org.ups.sma.impl.agent.impl;

import org.ups.sma.domain.Choice;
import org.ups.sma.domain.environment.Filter;
import org.ups.sma.impl.agent.Agent;

import java.util.List;

/**
 * Created by Ben on 24/05/14.
 */
public class Effector {
    private Filter range;
    private List<String> abilities;

    public Effector(Filter range, List<String> abilities) {
        this.range = range;
        this.abilities = abilities;
    }

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
