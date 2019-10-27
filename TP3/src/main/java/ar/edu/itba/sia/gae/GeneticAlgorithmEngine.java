package ar.edu.itba.sia.gae;

import ar.edu.itba.sia.gae.crossOverMethods.crossOver;
import ar.edu.itba.sia.gae.models.GameCharacter;
import ar.edu.itba.sia.gae.models.Item;
import ar.edu.itba.sia.gae.models.ItemType;
import ar.edu.itba.sia.gae.mutationMethods.MutationHelper;
import ar.edu.itba.sia.gae.mutationMethods.MutationMethod;
import ar.edu.itba.sia.gae.replacementMethods.ReplacementHelper;
import ar.edu.itba.sia.gae.replacementMethods.ReplacementMethod;
import ar.edu.itba.sia.gae.selectionMethods.SelectionHelper;
import ar.edu.itba.sia.gae.selectionMethods.SelectionMethod;

import java.util.*;

public class GeneticAlgorithmEngine {
    private final Configuration c;

    public GeneticAlgorithmEngine(Configuration configuration) {
        this.c = configuration;
        MutationHelper.init(configuration.getItems(), configuration.getMutationUniformProbability(), configuration.getMaxGenerations());
    }

    public void calculate(){
        Long generation = 0L;
        List<GameCharacter> population = initPopulation();
        while(generation < c.getMaxGenerations()){   // TODO Add Conditions
            // Selection
            List<GameCharacter> selection = SelectionHelper.selectionWrapperWithTwoMethods(population,
                    c.getSelectionMethodAPercentage(), c.getSelectionMethodA(), c.getSelectionMethodB(), generation);

            // CrossOver
            List<GameCharacter> children = c.getCrossOverMethod().crossSelection(selection);

            // Mutation
            final long currentGeneration = generation;
            children.forEach(character -> c.getMutationMethod().mutate(character, currentGeneration));

            // Fitness Evaluation
            population.forEach(character -> character.getFitness());    //TODO Something...

            // Replacement
            List<GameCharacter> replaced = ReplacementHelper.replacementWrapperWithTwoMethods(population,
                    c.getReplacementMethodAPercentage(), c.getReplacementMethodA(), c.getReplacementMethodB());

            // Next Generation
            generation++;
        }

    }

    private List<GameCharacter> initPopulation() {
        List<GameCharacter> population = new LinkedList<>();
        return population;
    }
}
