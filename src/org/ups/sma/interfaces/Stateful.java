package org.ups.sma.interfaces;

import org.ups.sma.custom.domain.agent.State;

public interface Stateful {
	public State getState();
	public State getPublicState();
}
