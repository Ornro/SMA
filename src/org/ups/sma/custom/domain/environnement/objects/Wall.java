package org.ups.sma.custom.domain.environnement.objects;

import org.ups.sma.domain.Action;
import org.ups.sma.domain.environnement.InteractiveEnvironmentObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Example of a wall object
 */
public class Wall extends InteractiveEnvironmentObject {

    protected Wall(List<String> availableActions) {
        super(availableActions);
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
