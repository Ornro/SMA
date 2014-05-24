package org.ups.sma.domain.agent;

import org.ups.sma.domain.custom.Action;
import org.ups.sma.domain.custom.agent.*;
import org.ups.sma.interfaces.EnvironnementObject;

public class Agent implements EnvironnementObject{
	public State state;

    @Override
    public Action getAviableActions() {
        return null;
    }
}
