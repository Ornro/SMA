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
public class UpdateStorageWayRule extends Rule{

    @Override
    public boolean condition(Agent a) {
        return !DecisionUtils.isBoxHeld(a) && DecisionUtils.isCorridorEntrance(a) && !DecisionUtils.wasInCorridor(a);
    }

    @Override
    public List<Choice> choices(Agent a) {
        System.out.println("update StorageWay");
        a.getState().wayToStorage = new Location(a.getLocation().x,a.getLocation().y);
        return new ArrayList<Choice>();
    }
}
