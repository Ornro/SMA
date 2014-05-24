package org.ups.sma.domain.environnement;

import java.util.List;
import java.util.Map;

import org.ups.sma.domain.custom.agent.Coord;
import org.ups.sma.domain.EnvironnementObject;
import org.ups.sma.interfaces.Statefull;

public class Env {
	public int width;
	public int height;
	public Map<Coord,EnvironnementObject> map;
}