package org.ups.sma.domain;

import java.util.List;
import java.util.Map;

import org.ups.sma.domain.custom.EnvironnementObjects;
import org.ups.sma.interfaces.Statefull;

public class Env {
	public int width;
	public int height;
	public Map<Coord,EnvironnementObjects> map;
	public List<Statefull> statefullObject;
}
