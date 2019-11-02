package ar.edu.itba.sia.gae.methods.finish;

import ar.edu.itba.sia.gae.helpers.Configuration;
import ar.edu.itba.sia.gae.models.GameCharacter;

import java.util.*;

public class PopulationUnchanged implements Finished {

    private List<GameCharacter> lastPopulation = Collections.emptyList();
    private long unchangedGenerationCount = 0;
    @Override
    public boolean isFinished(long generation, List<GameCharacter> population, Configuration configuration) {
        List<GameCharacter> common = new ArrayList<>(lastPopulation);
        common.retainAll(population);
        double populationUnchangedPercentage = 1 - ((double)common.size())/population.size();
        System.out.println(populationUnchangedPercentage);
        if(configuration.getFitnessEpsilon() > populationUnchangedPercentage){
            unchangedGenerationCount++;
            if(unchangedGenerationCount>configuration.getMaxGenerations())
                return true;
        }else {
            unchangedGenerationCount = 0;
        }
        lastPopulation = new LinkedList<>(population);
        return false;
    }

    @Override
    public String toString(Configuration configuration) {
        return " until " + configuration.getMaxGenerations() + " generations with relevant population unchanged" ;
    }
}
