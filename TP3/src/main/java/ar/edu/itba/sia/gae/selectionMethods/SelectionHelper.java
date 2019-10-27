package ar.edu.itba.sia.gae.selectionMethods;

import ar.edu.itba.sia.gae.models.GameCharacter;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class SelectionHelper {
    public static List<GameCharacter> selectionWrapperWithTwoMethods(List<GameCharacter> population, Double methodAPercentage,
                                                              SelectionMethod methodA, SelectionMethod methodB, long generations){
        if (!Optional.ofNullable(methodB).isPresent()){
            return methodA.select(population, population.size(), generations);
        }
        if (methodAPercentage > 1d || methodAPercentage < 0d) throw new IllegalArgumentException("Illegal percentage");
        int populationSize = population.size();
        Integer sizeA = (int) (methodAPercentage * (double) populationSize);
        Integer sizeB = (int) ((1 - methodAPercentage) * (double) populationSize);
        List<GameCharacter> finalSelection = new LinkedList<>();
        finalSelection.addAll(methodA.select(population, sizeA, generations));
        finalSelection.addAll(methodB.select(population, sizeB, generations));
        return finalSelection;
    }
}
