package ar.edu.itba.sia.gae;

import ar.edu.itba.sia.gae.crossOverMethods.crossOver;
import ar.edu.itba.sia.gae.models.Item;
import ar.edu.itba.sia.gae.models.ItemType;
import ar.edu.itba.sia.gae.mutationMethods.MutationMethod;
import ar.edu.itba.sia.gae.replacementMethods.ReplacementMethod;
import ar.edu.itba.sia.gae.selectionMethods.SelectionMethod;

import java.util.*;

public class GeneticAlgorithmEngine {

    public final SelectionMethod selectionMethod;
    public final crossOver crossOverMethod;
    public final MutationMethod mutationMethod;
    public final ReplacementMethod replacementMethod;
    public final Map<ItemType, List<Item>> items;

    public GeneticAlgorithmEngine(SelectionMethod selectionMethod, crossOver crossOverMethod, MutationMethod mutationMethod, ReplacementMethod replacementMethod, Map<ItemType, List<Item>> items) {
        this.selectionMethod = selectionMethod;
        this.crossOverMethod = crossOverMethod;
        this.mutationMethod = mutationMethod;
        this.replacementMethod = replacementMethod;
        this.items = items;
    }

    public void calculate(){
        Long generation = 0L;
        List<Character> population = initPopulation();
        while(true){   // TODO Conditions

            // Selection
            List<Character> selection = selectionMethod.select(population, population.size(), generation);

            // CrossOver
            List<Character> children = crossOverMethod.cross();


            // Mutation

            // Replacement

            // Next Generation ++
        }

    }

    private List<Character> initPopulation() {
        List<Character> population = new LinkedList<>();
        return population;
    }
}
