package ar.edu.itba.sia.gae.methods.finish;

import ar.edu.itba.sia.gae.helpers.Configuration;
import ar.edu.itba.sia.gae.models.GameCharacter;

import java.util.List;

public class ByGenerations implements Finished {

    @Override
    public boolean isFinished(long generation, List<GameCharacter> population, Configuration config) {
        if (generation >= config.getMaxGenerations()){
            return true;
        }
        return false;
    }

    public String toString(Configuration configuration){
        return "until " + configuration.getMaxGenerations() + " generations. ";
    }
}
