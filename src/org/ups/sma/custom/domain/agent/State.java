package org.ups.sma.custom.domain.agent;

import org.ups.sma.custom.domain.environment.Location;
import org.ups.sma.custom.domain.environment.Size;
import org.ups.sma.custom.domain.environment.objects.Box;
import org.ups.sma.domain.environment.Env;

/**
 * The agent state
 */
public class State {

    public Env partialEnvironment = new Env();

    public Location storage = null;
    public Location depot= null;
    public Location wayToStorage= null;
    public Location wayToDepot= null;

    @Public
    public Box boxHeld = null;

    public Location longTermGoal = null;
    public Location waypoint = null;

    public boolean wasInCorridor = false;
    public boolean isGoingBack = false;
}
