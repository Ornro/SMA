package org.ups.sma.interfaces;

import org.ups.sma.domain.environnement.Env;
import org.ups.sma.domain.environnement.Filter;
import org.ups.sma.domain.environnement.InteractiveEnvironmentObject;

public interface IEnvironmentManager {
	/**
	 * Prends en paramétre les coordonnées de l'acteur et son champ de vision
	 * et retourne l'environment partiel lis à la demande
	 */
    // to edit
	public Env getFullEnvironment();
	public void update(InteractiveEnvironmentObject s);
}
