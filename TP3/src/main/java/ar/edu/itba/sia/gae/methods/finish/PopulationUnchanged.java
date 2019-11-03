package ar.edu.itba.sia.gae.methods.finish;

import ar.edu.itba.sia.gae.helpers.Configuration;
import ar.edu.itba.sia.gae.models.GameCharacter;

import java.util.*;

public class PopulationUnchanged {

    private List<GameCharacter> lastPopulation = Collections.emptyList();
    private long unchangedGenerationCount = 0;

    public boolean isFinished(long generation, List<GameCharacter> population, Configuration configuration) {
        List<GameCharacter> common = new ArrayList<>(lastPopulation);
        common.retainAll(population);
        double populationUnchangedPercentage = 1 - ((double)common.size())/population.size();
        if(configuration.getPopulationEpsilon() > populationUnchangedPercentage){
            unchangedGenerationCount++;
            if(unchangedGenerationCount>configuration.getMaxGenerationPopulation()) {
                System.out.println("Finished by population unchanged");
                return true;
            }
        }else {
            unchangedGenerationCount = 0;
        }
        lastPopulation = new LinkedList<>(population);
        return false;
    }
}
