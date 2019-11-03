package ar.edu.itba.sia.gae.methods.finish;

import ar.edu.itba.sia.gae.helpers.Configuration;
import ar.edu.itba.sia.gae.models.GameCharacter;

import java.util.Collections;
import java.util.List;

public class FitnessUnchanged {

    private long unchangedGenerationCount = 0;
    private double lastFitness = 0;

    public boolean isFinished(long generation, List<GameCharacter> population, Configuration configuration) {
        double currentFitness = Collections.max(population).getFitness();
        if(currentFitness > lastFitness){
            unchangedGenerationCount = 0;
            lastFitness = currentFitness;
        }else {
            unchangedGenerationCount++;
        }
        if (unchangedGenerationCount > configuration.getMaxGenerationFitness()) {
            System.out.println("Finished by fitness unchanged");
            return true;
        }
        return false;
    }
}
