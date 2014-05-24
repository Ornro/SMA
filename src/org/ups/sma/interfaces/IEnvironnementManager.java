package org.ups.sma.interfaces;

import org.ups.sma.domain.custom.agent.Coord;
import org.ups.sma.domain.environnement.Env;
import org.ups.sma.impl.agent.Agent;

public interface IEnvironnementManager {
	/**
	 * Prends en paramétre les coordonnées de l'acteur et son champ de vision
	 * et retourne l'environnement partiel lis à la demande
	 */
    // to edit
	public Env getEnvironnement(Agent agent);
	public Env getFullEnvironnement();
	public void notifyStateChange(Statefull s);
	
}
