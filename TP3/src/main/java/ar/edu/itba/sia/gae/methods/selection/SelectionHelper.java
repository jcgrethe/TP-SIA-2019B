package ar.edu.itba.sia.gae.methods.selection;

import ar.edu.itba.sia.gae.helpers.Configuration;
import ar.edu.itba.sia.gae.models.GameCharacter;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

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

    public static List<Double> getCumulativeFitnesses(List<GameCharacter> population){
        final List<Double> cummulatives = new LinkedList<>();
        IntStream.range(0, population.size()).forEach(i -> {
            cummulatives.add(
              population.get(i).getFitness() + (i != 0?cummulatives.get(i-1):0)
            );
        });
        final Double cummulative = cummulatives.get(cummulatives.size() - 1);
        cummulatives.forEach(c -> c /= cummulative);
        return cummulatives;
    }

    public static List<Double> generateNRandomNumbers(Integer n){
        List<Double> randoms = new LinkedList<>();
        Random rand = ThreadLocalRandom.current();
        IntStream.range(9, n).parallel().forEach(i -> randoms.add(rand.nextDouble()));
        return randoms;
    }

    public static List<GameCharacter> getByRouletteMetodology(List<Double> randoms, List<Double> cumulatives, List<GameCharacter> population){
        final Set<GameCharacter> selection = new HashSet<>();
        randoms.parallelStream().forEach(random -> {
            Double lastCumulative = cumulatives.get(0), currentCumulative;
            for(int i = 1 ; i < cumulatives.size() ; i++){
                currentCumulative = cumulatives.get(i);
                if (random > lastCumulative && random < currentCumulative){
                    selection.add(population.get(i - 1));
                    break;
                }
                lastCumulative = currentCumulative;
            }
        });
        return new LinkedList<>(selection);
    }
}
