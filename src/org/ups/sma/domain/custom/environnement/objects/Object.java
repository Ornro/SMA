package org.ups.sma.domain.custom.environnement.objects;

import org.ups.sma.domain.Action;
import org.ups.sma.domain.environnement.InteractiveEnvironmentObject;
import org.ups.sma.impl.agent.Agent;

/**
 * Custom implementation of an Interactive Environment Object
 * Don't forget to define all the possible actions by an agent
 * in the list as follows in this sample
 */
public class Object extends InteractiveEnvironmentObject {

    public Object() {
        super();

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
