package ar.edu.itba.sia.gae.methods.finish;

import ar.edu.itba.sia.gae.helpers.Configuration;
import ar.edu.itba.sia.gae.models.GameCharacter;

import java.util.Collections;
import java.util.List;

public class FitnessUnchanged implements Finished {

    private long unchangedGenerationCount = 0;
    private double lastFitness = 0;

    @Override
    public boolean isFinished(long generation, List<GameCharacter> population, Configuration configuration) {
        double currentFitness = Collections.max(population).getFitness();
        if(currentFitness > lastFitness){
            unchangedGenerationCount = 0;
            lastFitness = currentFitness;
        }else {
            unchangedGenerationCount++;
        }
        if (unchangedGenerationCount > configuration.getMaxGenerations())
            return true;
        return false;
    }

    @Override
    public String toString(Configuration configuration) {
        return " until " + configuration.getMaxGenerations() + " generations with max fitness unchanged";
    }
}
