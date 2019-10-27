package ar.edu.itba.sia.gae;

import ar.edu.itba.sia.gae.crossOverMethods.crossOver;
import ar.edu.itba.sia.gae.models.GameCharacter;
import ar.edu.itba.sia.gae.models.Item;
import ar.edu.itba.sia.gae.models.ItemType;
import ar.edu.itba.sia.gae.mutationMethods.MutationHelper;
import ar.edu.itba.sia.gae.mutationMethods.MutationMethod;
import ar.edu.itba.sia.gae.replacementMethods.ReplacementMethod;
import ar.edu.itba.sia.gae.selectionMethods.SelectionMethod;

import java.util.*;

public class GeneticAlgorithmEngine {
    //Data
    public final Map<ItemType, List<Item>> items;

    // Methods
    public final SelectionMethod selectionMethod;
    public final crossOver crossOverMethod;
    public final MutationMethod mutationMethod;
    public final ReplacementMethod replacementMethod;

    //Conditions
    private final long maxGenerations;


    public GeneticAlgorithmEngine(SelectionMethod selectionMethod, crossOver crossOverMethod, MutationMethod mutationMethod,
                                  ReplacementMethod replacementMethod, Map<ItemType, List<Item>> items, long maxGenerations,
                                  double mutationUniformProbability) {
        this.selectionMethod = selectionMethod;
        this.crossOverMethod = crossOverMethod;
        this.mutationMethod = mutationMethod;
        this.replacementMethod = replacementMethod;
        this.items = items;
        this.maxGenerations = maxGenerations;
        MutationHelper.init(items, mutationUniformProbability, maxGenerations);
    }

    public void calculate(){
        Long generation = 0L;
        List<GameCharacter> population = initPopulation();
        while(generation < maxGenerations){   // TODO Add Conditions
            // Selection
            List<GameCharacter> selection = selectionMethod.select(population, population.size(), generation);

            // CrossOver
            List<GameCharacter> children = crossOverMethod.crossSelection(selection);

            // Mutation
            final long currentGeneration = generation;
            children.forEach(character -> mutationMethod.mutate(character, currentGeneration));

            // Replacement

            // Next Generation
            generation++;
        }

    }

    private List<GameCharacter> initPopulation() {
        List<GameCharacter> population = new LinkedList<>();
        return population;
    }
}
