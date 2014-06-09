package org.ups.sma.custom.impl.environment;

import org.ups.sma.custom.domain.environment.Location;

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
}
