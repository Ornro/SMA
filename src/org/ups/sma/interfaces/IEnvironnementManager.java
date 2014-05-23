package org.ups.sma.interfaces;

import org.ups.sma.domain.Coord;
import org.ups.sma.domain.Env;

public interface IEnvironnementManager {
	/**
	 * Prends en param�tre les coordonn�s de l'acteur et son champ de vision
	 * et retourne l'environnement partiel li� � la demande
	 */
	public Env getEnvironnement(Coord c, int range);
	public Env getFullEnvironnement();
	public void notifyStateChange(Statefull s);
	
}
