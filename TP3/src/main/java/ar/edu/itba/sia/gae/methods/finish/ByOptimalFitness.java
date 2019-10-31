package ar.edu.itba.sia.gae.methods.finish;

import ar.edu.itba.sia.gae.helpers.Configuration;
import ar.edu.itba.sia.gae.models.GameCharacter;

import java.util.Collections;
import java.util.List;

public class ByOptimalFitness implements Finished {



    @Override
    public boolean isFinished(long generation, List<GameCharacter> population, Configuration configuration) {
        double currentBestFitness = Collections.max(population).getFitness();
        double optFitness = configuration.getOptimalFitness();
        if ((optFitness - currentBestFitness ) <= configuration.getFitnessEpsilon()){
            return true;
        }
        return false;
    }

    @Override
    public String toString(Configuration configuration) {
        return " until " + configuration.getOptimalFitness()+  " generations. ";
    }

}
