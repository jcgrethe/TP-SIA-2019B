package ar.edu.itba.sia.gae.helpers;

import ar.edu.itba.sia.gae.crossOverMethods.crossOver;
import ar.edu.itba.sia.gae.crossOverMethods.onePointCross;
import ar.edu.itba.sia.gae.models.CharacterType;
import ar.edu.itba.sia.gae.models.Item;
import ar.edu.itba.sia.gae.models.ItemType;
import ar.edu.itba.sia.gae.mutationMethods.*;
import ar.edu.itba.sia.gae.replacementMethods.ReplacementMethod;
import ar.edu.itba.sia.gae.replacementMethods.ReplacementMethod1;
import ar.edu.itba.sia.gae.replacementMethods.ReplacementMethod2;
import ar.edu.itba.sia.gae.selectionMethods.Elite;
import ar.edu.itba.sia.gae.selectionMethods.SelectionMethod;

import java.io.IOException;
import java.util.*;

import static java.lang.System.exit;

public class Configuration {

    // Methods
    private final SelectionMethod selectionMethodA;
    private final SelectionMethod selectionMethodB;
    private final SelectionMethod replacementMethodA;
    private final SelectionMethod replacementMethodB;
    private double selectionMethodAPercentage;
    private double replacementMethodAPercentage;
    private final crossOver crossOverMethod;
    private final MutationMethod mutationMethod;
    private final ReplacementMethod replacementMethod;
    private double nextGenerationPercentage;
    private final long initialSize;
    private final CharacterType type;
    private final double minHeight;
    private final double maxHeight;

    // Data
    Map items;

    // Defined Values
    long maxGenerations;
    double mutationUniformProbability;

    public Configuration(Properties properties){
        try{
            
        } catch (NullPointerException e){
            
        }
        this.selectionMethodA = getSelectionMethod(Optional.ofNullable(properties.get("selectionMethodA"))
                .orElseThrow(() -> new IllegalArgumentException("selectionMethodA")).toString());
        this.selectionMethodB = getSelectionMethod(Optional.ofNullable(properties.get("selectionMethodB"))
                .orElseThrow(() -> new IllegalArgumentException("selectionMethodB")).toString());
        this.replacementMethodA = getSelectionMethod(Optional.ofNullable(properties.get("replacementMethodA"))
                .orElseThrow(() -> new IllegalArgumentException("replacementMethodA")).toString());
        this.replacementMethodB = getSelectionMethod(Optional.ofNullable(properties.get("replacementMethodB"))
                .orElseThrow(() -> new IllegalArgumentException("replacementMethodB")).toString());
        this.crossOverMethod = getCrossOverMethod(Optional.ofNullable(properties.get("crossOverMethod"))
                .orElseThrow(() -> new IllegalArgumentException("crossOverMethod")).toString());
        this.mutationMethod = getMutationMethod(Optional.ofNullable(properties.get("mutationMethod"))
                .orElseThrow(() -> new IllegalArgumentException("mutationMethod")).toString());
        this.replacementMethod = getReplacementMethod(Optional.ofNullable(properties.get("replacementMethod"))
                .orElseThrow(() -> new IllegalArgumentException("replacementMethod")).toString());
        this.maxGenerations = Long.valueOf(Optional.ofNullable(properties.get("maxGenerations"))
                .orElseThrow(() -> new IllegalArgumentException("maxGenerations")).toString());
        this.mutationUniformProbability = Double.valueOf(Optional.ofNullable(properties.get("mutationUniformProbability"))
                .orElseThrow(() -> new IllegalArgumentException("mutationUniformProbability")).toString());
        this.selectionMethodAPercentage = Double.valueOf(Optional.ofNullable(properties.get("selectionMethodAPercentage"))
                .orElseThrow(() -> new IllegalArgumentException("selectionMethodAPercentage")).toString());
        this.replacementMethodAPercentage = Double.valueOf(Optional.ofNullable(properties.get("replacementMethodAPercentage"))
                .orElseThrow(() -> new IllegalArgumentException("replacementMethodAPercentage")).toString());
        this.initialSize = Long.valueOf(Optional.ofNullable(properties.get("initialSize"))
                .orElseThrow(() -> new IllegalArgumentException("initialSize")).toString());
        this.type = getCharacterType(Optional.ofNullable(properties.get("type"))
                .orElseThrow(() -> new IllegalArgumentException("type")).toString());
        this.minHeight = Double.valueOf(Optional.ofNullable(properties.get("minHeight"))
                .orElseThrow(() -> new IllegalArgumentException("minHeight")).toString());
        this.maxHeight = Double.valueOf(Optional.ofNullable(properties.get("maxHeight"))
                .orElseThrow(() -> new IllegalArgumentException("maxHeight")).toString());
        this.nextGenerationPercentage = Double.valueOf(Optional.ofNullable(properties.get("nextGenerationPercentage"))
                .orElseThrow(() -> new IllegalArgumentException("nextGenerationPercentage")).toString());
        initItems(properties);
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

    public double getNextGenerationPercentage() {
        return nextGenerationPercentage;
    }

    public SelectionMethod getReplacementMethodA() {
        return replacementMethodA;
    }

    public SelectionMethod getReplacementMethodB() {
        return replacementMethodB;
    }

    public double getReplacementMethodAPercentage() {
        return replacementMethodAPercentage;
    }

    public ReplacementMethod getReplacementMethod() {
        return replacementMethod;
    }

    private SelectionMethod getSelectionMethod(String selection){
        switch (selection.toLowerCase()){
            case "elite": return new Elite();
//            case "roulette": return new Roulette();
//            case "universal": return new Universal();
//            case "boltzmann": return new Boltzmann();
//            case "tournaments": return new Tournaments();
//            case "ranking": return new Ranking();
            default: throw new IllegalArgumentException("Invalid selection method.");
        }
    }

    private ReplacementMethod getReplacementMethod(String selection){
        switch (selection.toLowerCase()){
            case "1": return new ReplacementMethod1();
            case "2": return new ReplacementMethod2();
//            case "3": return new ReplacementMethod3();
            default: throw new IllegalArgumentException("Invalid replacement method.");
        }
    }

    private MutationMethod getMutationMethod(String selection){
        switch (selection.toLowerCase()){
            case "genuniform": return new GenUniform();
            case "gennonuniform": return new GenNonUniform();
            case "multigenuniform": return new MultiGenUniform();
            case "multigennonuniform": return new MultiGenNonUniform();
            default: throw new IllegalArgumentException("Invalid mutation method.");
        }
    }

    private crossOver getCrossOverMethod(String selection){
        switch (selection.toLowerCase()){
            case "onepointcross": return new onePointCross();
//            case "twopointcross": return new GenNonUniform();
//            case "uniformcross": return new MultiGenUniform();
//            case "annularcross": return new MultiGenNonUniform();
            default: throw new IllegalArgumentException("Invalid cross over method.");
        }
    }

    private CharacterType getCharacterType(String selection){
        switch (selection.toLowerCase()){
            case "archer": return CharacterType.ARCHER;
            case "warrior": return CharacterType.WARRIOR;
            case "defender": return CharacterType.DEFENDER;
            case "assassin": return CharacterType.ASSASSIN;
            default: throw new IllegalArgumentException("Invalid cross over method.");
        }
    }

    private void initItems(Properties properties){
        try {
            items = TSVHelper.initData(properties);
        } catch (IOException e) {
            throw new IllegalStateException("There was an error initializing the data.");
        }
    }
}
