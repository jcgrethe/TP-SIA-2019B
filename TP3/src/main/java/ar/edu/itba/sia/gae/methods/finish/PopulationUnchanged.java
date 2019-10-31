package ar.edu.itba.sia.gae.methods.finish;

import ar.edu.itba.sia.gae.helpers.Configuration;
import ar.edu.itba.sia.gae.models.GameCharacter;

import java.util.*;

public class PopulationUnchanged implements Finished {

    private List<GameCharacter> lastPopulation = Collections.emptyList();
    private long unchangedGenerationCount = 0;
    @Override
    public boolean isFinished(long generation, List<GameCharacter> population, Configuration configuration) {
        Set<GameCharacter> aux = new HashSet<>();
        aux.addAll(lastPopulation);
        aux.addAll(population);
        double totalSize = lastPopulation.size() + population.size();
        double populationUnchangedPercentage = aux.size()/totalSize;
        if(configuration.getFitnessEpsilon() > populationUnchangedPercentage){
            unchangedGenerationCount++;
            if(unchangedGenerationCount>configuration.getMaxGenerations())
                return true;
        }else {
            unchangedGenerationCount = 0;
        }
        lastPopulation = population;
        return false;
    }

    @Override
    public String toString(Configuration configuration) {
        return " until " + configuration.getMaxGenerations() + " generations with relevant population unchanged" ;
    }
}
