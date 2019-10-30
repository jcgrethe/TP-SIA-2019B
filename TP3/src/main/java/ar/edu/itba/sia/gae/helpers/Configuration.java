package ar.edu.itba.sia.gae.helpers;

import ar.edu.itba.sia.gae.methods.crossover.*;
import ar.edu.itba.sia.gae.methods.selection.Roulette;
import ar.edu.itba.sia.gae.methods.selection.Universal;
import ar.edu.itba.sia.gae.methods.selection.*;
import ar.edu.itba.sia.gae.models.CharacterType;
import ar.edu.itba.sia.gae.models.Item;
import ar.edu.itba.sia.gae.models.ItemType;
import ar.edu.itba.sia.gae.methods.mutation.*;
import ar.edu.itba.sia.gae.methods.replacement.ReplacementMethod;
import ar.edu.itba.sia.gae.methods.replacement.ReplacementMethod1;
import ar.edu.itba.sia.gae.methods.replacement.ReplacementMethod2;

import java.io.IOException;
import java.util.*;

public class Configuration {

    // Methods
    private final SelectionMethod selectionMethodA;
    private final SelectionMethod selectionMethodB;
    private final SelectionMethod replacementMethodA;
    private final SelectionMethod replacementMethodB;
    private double selectionMethodAPercentage;
    private double replacementMethodAPercentage;
    private final CrossOver crossOverMethod;
    private final double crossOverProbability;
    private final MutationMethod mutationMethod;
    private final ReplacementMethod replacementMethod;
    private double nextGenerationPercentage;
    private final long initialSize;
    private final CharacterType type;
    private final double minHeight;
    private final double maxHeight;
    private final Boolean isBolztmann;
    private final int tournamentsM;

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
        this.crossOverProbability = Double.valueOf(Optional.ofNullable(properties.get("crossOverProbability"))
                .orElseThrow(() -> new IllegalArgumentException("crossOverProbability")).toString());
        this.isBolztmann = Boolean.valueOf(Optional.ofNullable(properties.get("isBolztmann"))
                .orElseThrow(() -> new IllegalArgumentException("isBolztmann")).toString());
        this.tournamentsM = Integer.valueOf(Optional.ofNullable(properties.get("tournamentsM"))
                .orElseThrow(() -> new IllegalArgumentException("tournamentsM")).toString());
        initItems(properties);

    }

    public SelectionMethod getSelectionMethodA() {
        return selectionMethodA;
    }

    public SelectionMethod getSelectionMethodB() {
        return selectionMethodB;
    }

    public CrossOver getCrossOverMethod() {
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

    public Boolean getBolztmann() {
        return isBolztmann;
    }

    public int getTournamentsM() {
        return tournamentsM;
    }

    private SelectionMethod getSelectionMethod(String selection){
        switch (selection.toLowerCase()){
            case "elite": return new Elite();
            case "roulette": return new Roulette();
            case "universal": return new Universal();
            case "tournamentsDeterministic": return new TournamentsDeterministic();
            case "tournamentsProbabilistic": return new TournamentsProbabilistic();
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

    private CrossOver getCrossOverMethod(String selection){
        switch (selection.toLowerCase()){
            case "onepointcross": return new OnePointCross();
            case "twopointcross": return new TwoPointCross();
            case "uniformcross": return new UniformCross();
            case "annularcross": return new AnularCross();
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

    public double getCrossOverProbability() {
        return crossOverProbability;
    }
}
