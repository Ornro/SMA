package org.ups.sma.interfaces;

import org.ups.sma.domain.Coord;
import org.ups.sma.domain.Env;

public interface IEnvironnementManager {
	/**
	 * Prends en paramètre les coordonnés de l'acteur et son champ de vision
	 * et retourne l'environnement partiel lié à la demande
	 */
	public Env getEnvironnement(Coord c, int range);
	public Env getFullEnvironnement();
	public void notifyStateChange(Statefull s);
	
}
