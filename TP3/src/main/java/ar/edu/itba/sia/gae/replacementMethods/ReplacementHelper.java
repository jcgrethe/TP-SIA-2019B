package ar.edu.itba.sia.gae.replacementMethods;

import ar.edu.itba.sia.gae.models.GameCharacter;
import ar.edu.itba.sia.gae.selectionMethods.SelectionMethod;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class ReplacementHelper {
    public static List<GameCharacter> replacementWrapperWithTwoMethods(List<GameCharacter> population, Double methodAPercentage,
                                                              ReplacementMethod methodA, ReplacementMethod methodB){
        if (!Optional.ofNullable(methodB).isPresent()){
            return methodA.replace(population);
        }
        if (methodAPercentage > 1d || methodAPercentage < 0d) throw new IllegalArgumentException("Illegal percentage");
        int populationSize = population.size();
        Integer sizeA = (int) (methodAPercentage * (double) populationSize);
        Integer sizeB = (int) ((1 - methodAPercentage) * (double) populationSize);
        List<GameCharacter> finalSelection = new LinkedList<>();
        finalSelection.addAll(methodA.replace(population));
        finalSelection.addAll(methodB.replace(population));
        return finalSelection;
    }
}
