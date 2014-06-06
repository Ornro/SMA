package org.ups.sma.custom.domain.agent;

import org.ups.sma.custom.domain.environnement.Location;
import org.ups.sma.custom.domain.environnement.objects.Box;

/**
 * The agent state
 */
public class State {
    @Public
	public Location position;
    public boolean isAlive;

    @Public
    public Box boxHolded;
}
