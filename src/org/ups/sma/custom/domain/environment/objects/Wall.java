package org.ups.sma.custom.domain.environment.objects;

import org.ups.sma.custom.domain.environment.Location;
import org.ups.sma.domain.environnement.InteractiveEnvironmentObject;

import java.util.List;

/**
 * Example of a wall object
 */
public class Wall extends InteractiveEnvironmentObject {

    protected Wall(Location location, List<String> availableActions) {
        super(availableActions);
        this.location = location;
    }

    //   public Wall() {
        // this means no actions are available on a wall
        //super(new ArrayList<Action>());

        // uncomment and fill to add an action that any
        // agent will be able to perform.
        // this.availableActions.add();
   // }

    @Override
    public void destroy() {

    }

    @Override
    public InteractiveEnvironmentObject clone() {
        return null;
    }
}
