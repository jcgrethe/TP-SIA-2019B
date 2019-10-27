package ar.edu.itba.sia.gae.models;

import java.util.Random;

public class CharacterHelpers {
    private static Random random = new Random();

    public static Double getRandomHeight(){
        return random.doubles(1.3, 2d)
                .findFirst().orElseThrow(IllegalStateException::new);
    }
}
