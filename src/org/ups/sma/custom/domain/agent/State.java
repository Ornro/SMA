package org.ups.sma.custom.domain.agent;

import org.ups.sma.custom.domain.environment.Location;
import org.ups.sma.custom.domain.environment.objects.Box;
import org.ups.sma.domain.environnement.Env;

/**
 * The agent state
 */
public class State {

    public Env partialEnvironment;

    public Location storage;
    public Location depot;
    public Location wayToStorage;
    public Location wayToDepot;

    @Public
    public Box boxHeld;

    public Location longTermGoal;
    public Location waypoint;

    public boolean goingNorth;
    public boolean goingSouth;
    public boolean bestIsNorth;

}
