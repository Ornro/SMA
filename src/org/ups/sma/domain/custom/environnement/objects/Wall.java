package org.ups.sma.domain.custom.environnement.objects;

import org.ups.sma.domain.environnement.InteractiveEnvironmentObject;

/**
 * Example of a wall object
 */
public class Wall extends InteractiveEnvironmentObject {

    public Wall() {
        super();

        // uncomment and fill to add an action that any
        // agent will be able to perform.
        // this.availableActions.add();
    }

    @Override
    public void destroy() {

    }

    @Override
    public InteractiveEnvironmentObject clone() {
        return null;
    }
}
