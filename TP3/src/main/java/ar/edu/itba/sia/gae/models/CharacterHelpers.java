package ar.edu.itba.sia.gae.models;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class CharacterHelpers {
    private static ThreadLocalRandom random = ThreadLocalRandom.current();

    public static Double getRandomHeight(){
        return random.nextDouble(1.3,2);
    }
}
