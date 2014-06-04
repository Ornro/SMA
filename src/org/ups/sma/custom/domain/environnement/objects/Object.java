package org.ups.sma.custom.domain.environnement.objects;

import org.ups.sma.domain.Action;
import org.ups.sma.domain.environnement.InteractiveEnvironmentObject;

import java.util.ArrayList;

/**
 * Custom implementation of an Interactive Environment Object
 * Don't forget to define all the possible actions by an agent
 * in the list as follows in this sample
 */
public class Object extends InteractiveEnvironmentObject {

    public Object() {
        // generate available action
        super(new ArrayList<Action>());

        // uncomment and fill to add an action that any
        // agent will be able to perform.
        // this.availableActions.add();
    }

    @Override
    public void destroy() {
        // must be set
    }

    @Override
    public InteractiveEnvironmentObject clone() {
        return null;
    }
}
