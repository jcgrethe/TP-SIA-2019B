package ar.edu.itba.sia.gae.selectionMethods;

import ar.edu.itba.sia.gae.Configuration;
import ar.edu.itba.sia.gae.models.GameCharacter;
import ar.edu.itba.sia.gae.replacementMethods.ReplacementMethod;
import ar.edu.itba.sia.gae.selectionMethods.SelectionMethod;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class SelectionHelper {

    public static List<GameCharacter> selectionWrapperWithTwoMethodsB(List<GameCharacter> population,
                                                                       Configuration configuration, long generation, double populationSize){
        SelectionMethod methodA = configuration.getReplacementMethodA();
        SelectionMethod methodB = configuration.getReplacementMethodB();
        Integer sizeA = (int) (configuration.getReplacementMethodAPercentage() * populationSize);
        Integer sizeB = (int) ((1 - configuration.getReplacementMethodAPercentage()) * populationSize);
        List<GameCharacter> finalSelection = new LinkedList<>();
        finalSelection.addAll(methodA.select(population,sizeA,generation));
        finalSelection.addAll(methodB.select(population,sizeB,generation));
        return finalSelection;
    }

    public static List<GameCharacter> selectionWrapperWithTwoMethodsA(List<GameCharacter> population,
                                                                      Configuration configuration, long generation, double populationSize){
        SelectionMethod methodA = configuration.getSelectionMethodA();
        SelectionMethod methodB = configuration.getSelectionMethodB();
        Integer sizeA = (int) (configuration.getSelectionMethodAPercentage() * populationSize);
        Integer sizeB = (int) ((1 - configuration.getSelectionMethodAPercentage()) * populationSize);
        List<GameCharacter> finalSelection = new LinkedList<>();
        finalSelection.addAll(methodA.select(population,sizeA,generation));
        finalSelection.addAll(methodB.select(population,sizeB,generation));
        return finalSelection;
    }
}
