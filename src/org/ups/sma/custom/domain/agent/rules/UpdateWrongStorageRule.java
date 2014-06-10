package org.ups.sma.custom.domain.agent.rules;

import org.ups.sma.custom.domain.environment.Location;
import org.ups.sma.domain.Choice;
import org.ups.sma.domain.environment.Rule;
import org.ups.sma.impl.agent.Agent;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ben on 10/06/14.
 */
public class UpdateWrongStorageRule extends Rule {
    @Override
    public boolean condition(Agent a) {
        return !DecisionUtils.isBoxHeld(a) && DecisionUtils.isCorridorEntrance(a) && DecisionUtils.wasInCorridor(a);
    }

    @Override
    public List<Choice> choices(Agent a) {
        System.out.println("update wrong StorageWay");
        if (a.getState().isGoingBack){
            a.getState().wayToStorage = null;
            a.getState().wayToDepot = new Location(a.getLocation().x,a.getLocation().y);
        }
        return new ArrayList<Choice>();
    }
}
