package ar.edu.itba.sia.gae.methods.finish;

import ar.edu.itba.sia.gae.helpers.Configuration;
import ar.edu.itba.sia.gae.models.GameCharacter;

import java.util.List;

public class ByGenerations {

    public boolean isFinished(long generation, List<GameCharacter> population, Configuration config) {
        if (generation >= config.getMaxGenerations()){
            System.out.println("Finished by generations");
            return true;
        }
        return false;
    }
}
