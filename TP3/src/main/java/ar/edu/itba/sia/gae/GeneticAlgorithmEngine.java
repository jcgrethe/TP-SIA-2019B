package ar.edu.itba.sia.gae;

import ar.edu.itba.sia.gae.crossOverMethods.crossOver;
import ar.edu.itba.sia.gae.models.Item;
import ar.edu.itba.sia.gae.models.ItemType;
import ar.edu.itba.sia.gae.mutationMethods.mutation;
import ar.edu.itba.sia.gae.replacementMethods.replacement;
import ar.edu.itba.sia.gae.selectionMethods.selection;

import java.util.*;

public class GeneticAlgorithmEngine {

    public final selection selectionMethod;
    public final crossOver crossOverMethod;
    public final mutation mutationMethod;
    public final replacement replacementMethod;
    public final Map<ItemType, List<Item>> items;

    public GeneticAlgorithmEngine(selection selectionMethod, crossOver crossOverMethod, mutation mutationMethod, replacement replacementMethod, Map<ItemType, List<Item>> items) {
        this.selectionMethod = selectionMethod;
        this.crossOverMethod = crossOverMethod;
        this.mutationMethod = mutationMethod;
        this.replacementMethod = replacementMethod;
        this.items = items
    }

    public void calculate(){
        Long generation = 0L;
        List<Character> population = initPopulation();
        while(true){   // TODO Conditions

            // Selection


            // CrossOver

            // Mutation

            // Replacement

            // Next Generation ++
        }

    }

    private List<Character> initPopulation() {
        List<Character> population = new LinkedList<>();

    }
}
