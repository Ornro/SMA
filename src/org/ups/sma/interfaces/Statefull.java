package org.ups.sma.interfaces;

import org.ups.sma.domain.State;

public interface Statefull {
	public State getState();
	public State getPublicState();
}
