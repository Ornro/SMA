package org.ups.sma.custom.domain.agent.rules;

import org.ups.sma.domain.Choice;
import org.ups.sma.domain.environment.Rule;
import org.ups.sma.impl.agent.Agent;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ben on 07/06/14.
 */
public class GetRule extends Rule {
    @Override
    public boolean condition(Agent a) {
        return DecisionUtils.canGet(a) && !DecisionUtils.hasBox(a);
    }

    @Override
    public List<Choice> choices(Agent a) {
        System.out.println("get Rule");
        List<Choice> choices = new ArrayList<Choice>();
        choices.add(DecisionUtils.get(a));
        return choices;
    }
}
