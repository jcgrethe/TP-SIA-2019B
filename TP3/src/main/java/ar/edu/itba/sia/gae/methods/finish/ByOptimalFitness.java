package ar.edu.itba.sia.gae.methods.finish;

import ar.edu.itba.sia.gae.helpers.Configuration;
import ar.edu.itba.sia.gae.models.GameCharacter;

import java.util.Collections;
import java.util.List;

public class ByOptimalFitness {

    public boolean isFinished(long generation, List<GameCharacter> population, Configuration configuration) {
        double currentBestFitness = Collections.max(population).getFitness();
        double optFitness = configuration.getOptimalFitness();
        if ((optFitness - currentBestFitness ) <= configuration.getFitnessEpsilon()){
            System.out.println("Finished by optimal fitness");
            return true;
        }
        return false;
    }
}
