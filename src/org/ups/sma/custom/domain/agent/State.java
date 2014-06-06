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

    public Location storage;
    public Location depot;
    public Location wayToStorage;
    public Location wayToDepot;

    public boolean goingNorth;
    public boolean goingSouth;

    public boolean bestIsNorth;

}
