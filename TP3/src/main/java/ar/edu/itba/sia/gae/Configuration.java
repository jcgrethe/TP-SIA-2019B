package ar.edu.itba.sia.gae;

import ar.edu.itba.sia.gae.crossOverMethods.crossOver;
import ar.edu.itba.sia.gae.models.Item;
import ar.edu.itba.sia.gae.models.ItemType;
import ar.edu.itba.sia.gae.mutationMethods.MutationMethod;
import ar.edu.itba.sia.gae.replacementMethods.ReplacementMethod;
import ar.edu.itba.sia.gae.selectionMethods.SelectionMethod;

import java.util.List;
import java.util.Map;

public class Configuration {

    // Methods
    SelectionMethod selectionMethodA;
    SelectionMethod selectionMethodB;
    crossOver crossOverMethod;
    MutationMethod mutationMethod;
    ReplacementMethod replacementMethodA;
    ReplacementMethod replacementMethodB;

    // Data
    Map<ItemType, List<Item>> items;

    // Defined Values
    long maxGenerations;
    double mutationUniformProbability;
    double selectionMethodAPercentage;
    double replacementMethodAPercentage;


    public Configuration(SelectionMethod selectionMethodA, SelectionMethod selectionMethodB, crossOver crossOverMethod,
                         MutationMethod mutationMethod, ReplacementMethod replacementMethodA, ReplacementMethod replacementMethodB,
                         Map<ItemType, List<Item>> items, long maxGenerations, double mutationUniformProbability,
                         double selectionMethodAPercentage, double replacementMethodAPercentage) {
        this.selectionMethodA = selectionMethodA;
        this.selectionMethodB = selectionMethodB;
        this.crossOverMethod = crossOverMethod;
        this.mutationMethod = mutationMethod;
        this.replacementMethodA = replacementMethodA;
        this.replacementMethodB = replacementMethodB;
        this.items = items;
        this.maxGenerations = maxGenerations;
        this.mutationUniformProbability = mutationUniformProbability;
        this.selectionMethodAPercentage = selectionMethodAPercentage;
        this.replacementMethodAPercentage = replacementMethodAPercentage;
    }

    public SelectionMethod getSelectionMethodA() {
        return selectionMethodA;
    }

    public SelectionMethod getSelectionMethodB() {
        return selectionMethodB;
    }

    public crossOver getCrossOverMethod() {
        return crossOverMethod;
    }

    public MutationMethod getMutationMethod() {
        return mutationMethod;
    }

    public ReplacementMethod getReplacementMethodA() {
        return replacementMethodA;
    }

    public ReplacementMethod getReplacementMethodB() {
        return replacementMethodB;
    }

    public Map<ItemType, List<Item>> getItems() {
        return items;
    }

    public long getMaxGenerations() {
        return maxGenerations;
    }

    public double getMutationUniformProbability() {
        return mutationUniformProbability;
    }

    public double getSelectionMethodAPercentage() {
        return selectionMethodAPercentage;
    }

    public double getReplacementMethodAPercentage() {
        return replacementMethodAPercentage;
    }
}
