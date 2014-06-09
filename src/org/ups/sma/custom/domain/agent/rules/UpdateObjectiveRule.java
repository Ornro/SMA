package org.ups.sma.custom.domain.agent.rules;

import org.ups.sma.domain.Choice;
import org.ups.sma.domain.environment.Rule;
import org.ups.sma.impl.agent.Agent;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ben on 08/06/14.
 */
public class UpdateObjectiveRule extends Rule {
    @Override
    public boolean condition(Agent a) {
        return DecisionUtils.isBoxHeld(a);
    }

    @Override
    public List<Choice> choices(Agent a) {
        DecisionUtils.setLongTermObjectiveToDepot(a);
        return new ArrayList<Choice>();
    }
}
