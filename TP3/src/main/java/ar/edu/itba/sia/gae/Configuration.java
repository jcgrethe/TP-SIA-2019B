package ar.edu.itba.sia.gae;

import ar.edu.itba.sia.gae.crossOverMethods.crossOver;
import ar.edu.itba.sia.gae.models.CharacterType;
import ar.edu.itba.sia.gae.models.Item;
import ar.edu.itba.sia.gae.models.ItemType;
import ar.edu.itba.sia.gae.mutationMethods.MutationMethod;
import ar.edu.itba.sia.gae.replacementMethods.ReplacementMethod;
import ar.edu.itba.sia.gae.selectionMethods.SelectionMethod;

import java.util.List;
import java.util.Map;

public class Configuration {

    // Methods
    private final SelectionMethod selectionMethodA;
    private final SelectionMethod selectionMethodB;
    double selectionMethodAPercentage;

    private final crossOver crossOverMethod;

    private final MutationMethod mutationMethod;

    private final ReplacementMethod replacementMethod;
    private final SelectionMethod replacementMethodA;
    private final SelectionMethod replacementMethodB;
    double replacementMethodAPercentage;
    double nextGenerationPercentage;

    private final long initialSize;
    private final CharacterType type;
    private final double minHeight;
    private final double maxHeight;

    // Data
    Map<ItemType, List<Item>> items;

    // Defined Values
    long maxGenerations;
    double mutationUniformProbability;


    public Configuration(SelectionMethod selectionMethodA, SelectionMethod selectionMethodB, crossOver crossOverMethod,
                         MutationMethod mutationMethod, SelectionMethod replacementMethodA, SelectionMethod replacementMethodB, ReplacementMethod replacementMethod,
                         Map<ItemType, List<Item>> items, long maxGenerations, double mutationUniformProbability,
                         double selectionMethodAPercentage, double replacementMethodAPercentage, long initialSize, CharacterType type,
                         double minHeight, double maxHeight, double nextGenerationPercentage) {
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
        this.initialSize = initialSize;
        this.type = type;
        this.minHeight = minHeight;
        this.maxHeight = maxHeight;
        this.replacementMethod = replacementMethod;
        this.nextGenerationPercentage = nextGenerationPercentage;
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

    public SelectionMethod getReplacementMethodA() {
        return replacementMethodA;
    }

    public SelectionMethod getReplacementMethodB() {
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

    public long getInitialSize() {
        return initialSize;
    }

    public CharacterType getType() {
        return type;
    }

    public double getMinHeight() {
        return minHeight;
    }

    public double getMaxHeight() {
        return maxHeight;
    }

    public ReplacementMethod getReplacementMethod() {
        return replacementMethod;
    }

    public double getNextGenerationPercentage() {
        return nextGenerationPercentage;
    }
}
