package org.ups.sma.impl.saver;

import org.ups.sma.domain.environnement.Env;
import org.ups.sma.impl.environement.EnvironmentManager;

/**
 * Created by Ben on 29/05/14.
 */
public class Saver {
    public static void saveCurrentStatus(){
        EnvironmentManager envManager = EnvironmentManager.getInstance();
        Env env = envManager.getFullEnvironment();


    }

}
