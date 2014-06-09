package org.ups.sma.custom.domain.agent.rules;

import org.ups.sma.domain.Choice;
import org.ups.sma.domain.environment.Rule;
import org.ups.sma.impl.agent.Agent;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ben on 07/06/14.
 */
public class MoveForwardBlockedRule extends Rule {
    @Override
    public boolean condition(Agent a) {
        return !DecisionUtils.canMoveForward(a) && !DecisionUtils.isInCorridor(a) && !DecisionUtils.canDump(a) && !DecisionUtils.canGet(a) && DecisionUtils.canMove(a);
    }

    @Override
    public List<Choice> choices(Agent a) {
        System.out.println("move blocked Rule");
        List<Choice> choices = new ArrayList<Choice>();
        choices.add(DecisionUtils.getRandomMove(a));
        return choices;
    }
}
