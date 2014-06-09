package org.ups.sma.custom.impl.environment;

import org.ups.sma.custom.domain.environment.Location;
import org.ups.sma.custom.domain.environment.objects.Default;
import org.ups.sma.custom.domain.environment.objects.Wall;
import org.ups.sma.domain.environment.Env;
import org.ups.sma.impl.environment.EnvironmentManager;

import java.util.Random;

/**
 * Created by Business on 09/06/14.
 */
public class EnvironmentUtils {
    public static Location getRandomLocation(int x1, int x2, int y1, int y2) {
        Random rand = new Random();
        int randomX = rand.nextInt((x2 - x1) + 1) + x1;
        int randomY = rand.nextInt((y2 - y1) + 1) + y1;

        return new Location(randomX, randomY);
    }

    /**
     * Replace corridor whose y is given by a new one
     * @param yOldCorridor
     * @param yNewCorridor
     */
    public static void replaceCorridor(int yOldCorridor, int yNewCorridor){

        EnvironmentManager em = EnvironmentManager.getInstance();
        Env env = em.getFullEnvironment();

        // Suppression du couloir (ajout d'un wall)
        for(int i=0;i<env.size.width;i++){
            Location locationDessous = new Location(i, yOldCorridor+1);
            Location locationDessus = new Location(i, yOldCorridor-1);
            if( (yNewCorridor < env.size.height && env.get(locationDessous) instanceof Wall)
                || (yNewCorridor > 0 && env.get(locationDessous) instanceof Wall)){
                // On est dans un couloir
                em.removeAllObjects(new Location(i, yOldCorridor));
                em.addObject(new Location(i, yOldCorridor), new Default(new Location(i, yOldCorridor)));
                em.addObject(new Location(i, yOldCorridor), new Wall(new Location(i, yOldCorridor)));
            }
        }

        // Ajout d'un couloir (suppression de murs)
        for(int i=0;i<env.size.width;i++){
            Location location = new Location(i, yNewCorridor);
            if(env.get(location) instanceof Wall){
                // On est dans un couloir
                em.removeAllObjects(new Location(i, yNewCorridor));
                em.addObject(new Location(i, yNewCorridor), new Default(new Location(i, yNewCorridor)));
            }
        }
    }
}
