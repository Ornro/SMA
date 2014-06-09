package org.ups.sma.custom.domain.agent.rules;

import org.ups.sma.domain.Choice;
import org.ups.sma.domain.environment.Rule;
import org.ups.sma.impl.agent.Agent;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ben on 07/06/14.
 */
public class DumpRule extends Rule {
    @Override
    public boolean condition(Agent a) {
        return DecisionUtils.canDump(a) && DecisionUtils.hasBox(a);
    }

    @Override
    public List<Choice> choices(Agent a) {
        List<Choice> choices = new ArrayList<Choice>();
        choices.add(DecisionUtils.dump(a));
        return choices;
    }
}
