package ar.edu.itba.sia.gae.methods.finish;

import ar.edu.itba.sia.gae.helpers.Configuration;
import ar.edu.itba.sia.gae.models.GameCharacter;

import java.util.List;

public class Finished {

    private ByGenerations byGenerations = new ByGenerations();
    private ByOptimalFitness byOptimalFitness = new ByOptimalFitness();
    private FitnessUnchanged fitnessUnchanged = new FitnessUnchanged();
    private PopulationUnchanged populationUnchanged = new PopulationUnchanged();

    public Boolean isFinished(long g, List<GameCharacter> p, Configuration c){
        if(byGenerations.isFinished(g, p, c) || byOptimalFitness.isFinished(g, p, c) ||
                fitnessUnchanged.isFinished(g, p, c) || populationUnchanged.isFinished(g, p, c) )
            return true;
        return false;
    }



}
