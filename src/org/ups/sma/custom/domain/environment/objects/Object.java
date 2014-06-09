package org.ups.sma.custom.domain.environment.objects;

import org.ups.sma.domain.environment.InteractiveEnvironmentObject;

import java.util.List;

/**
 * Custom implementation of an Interactive Environment Object
 * Don't forget to define all the possible actions by an agent
 * in the list as follows in this sample
 */
public class Object extends InteractiveEnvironmentObject {

    protected Object(List<String> availableActions) {
        super(availableActions);
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
