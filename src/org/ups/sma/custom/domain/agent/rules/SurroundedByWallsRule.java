package org.ups.sma.custom.domain.agent.rules;

import org.ups.sma.custom.impl.actions.Suicide;
import org.ups.sma.domain.Choice;
import org.ups.sma.domain.environment.Rule;
import org.ups.sma.impl.agent.Agent;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ben on 08/06/14.
 */
public class SurroundedByWallsRule extends Rule {
    @Override
    public boolean condition(Agent a) {
        return DecisionUtils.isSurroundedByWalls(a);
    }

    @Override
    public List<Choice> choices(Agent a) {
        System.out.println("Surr by walls Rule");
        List<Choice> choices = new ArrayList<Choice>();
        choices.add(new Choice(new Suicide(),a));
        return choices;
    }
}
