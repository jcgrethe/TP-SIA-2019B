package ar.edu.itba.sia.gae.methods.selection;

import ar.edu.itba.sia.gae.helpers.Configuration;
import ar.edu.itba.sia.gae.models.GameCharacter;
import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class SelectionHelper {

    public static List<GameCharacter> selectionWrapperWithTwoMethodsB(List<GameCharacter> population,
                                                                       Configuration configuration, long generation, double populationSize){
        SelectionMethod methodA = configuration.getReplacementMethodA();
        SelectionMethod methodB = configuration.getReplacementMethodB();
        Integer sizeA = (int) (configuration.getReplacementMethodAPercentage() * populationSize);
        Integer sizeB = (int) ((1 - configuration.getReplacementMethodAPercentage()) * populationSize);
        return selectionWrapperAux(methodA, methodB, population, sizeA, sizeB, configuration, generation);
    }

    public static List<GameCharacter> selectionWrapperWithTwoMethodsA(List<GameCharacter> population,
                                                                      Configuration configuration, long generation, double populationSize){
        SelectionMethod methodA = configuration.getSelectionMethodA();
        SelectionMethod methodB = configuration.getSelectionMethodB();
        Integer sizeA = (int) (configuration.getSelectionMethodAPercentage() * populationSize);
        Integer sizeB = (int) ((1 - configuration.getSelectionMethodAPercentage()) * populationSize);
        return selectionWrapperAux(methodA, methodB, population, sizeA, sizeB, configuration, generation);
    }

    private static List<GameCharacter> selectionWrapperAux(SelectionMethod methodA, SelectionMethod methodB, List<GameCharacter> population,
                                                           Integer sizeA, Integer sizeB, Configuration configuration, long generation){
        List<GameCharacter> finalSelection = new LinkedList<>();
        finalSelection.addAll(methodA.select(population,sizeA,generation, configuration.getBolztmann(), configuration.getTournamentsM()));
        finalSelection.addAll(methodB.select(population,sizeB,generation, configuration.getBolztmann(), configuration.getTournamentsM()));
        return finalSelection;

    }

    public static List<Double> getCumulativeFitnesses(List<GameCharacter> population, Boolean isBolztmann, long generation){
        final List<Double> cummulatives = new LinkedList<>();
        final List<Double> bolztmannValues;
        if (isBolztmann){
            bolztmannValues = boltzmannValues(population, generation);
        } else {
            bolztmannValues = null;
        }
        IntStream.range(0, population.size()).forEach(i -> {
            if (isBolztmann){
                cummulatives.add(
                        bolztmannValues.get(i) + (i != 0?cummulatives.get(i-1):0)
                );
            } else {
                cummulatives.add(
                        population.get(i).getFitness() + (i != 0?cummulatives.get(i-1):0)
                );
            }
        });
        final Double cummulative = cummulatives.get(cummulatives.size() - 1);
        return cummulatives.stream().map(c -> c /= cummulative).collect(Collectors.toList());
    }

    public static List<Double> generateNRandomNumbers(Integer n){
        List<Double> randoms = new LinkedList<>();
        Random rand = ThreadLocalRandom.current();
        IntStream.range(0, n).forEach(i -> randoms.add(rand.nextDouble()));
        return randoms;
    }

    public static List<GameCharacter> getByRouletteMetodology(List<Double> randoms, List<Double> cumulatives, List<GameCharacter> population){
        final List<GameCharacter> selection = new LinkedList<>();
        randoms.stream().forEach(random -> {
            Double lastCumulative = 0d, currentCumulative;
            for(int i = 0 ; i < cumulatives.size() ; i++){
                currentCumulative = cumulatives.get(i);
                if (random > lastCumulative && random < currentCumulative){
                    selection.add(population.get(i));
                    break;
                }
                lastCumulative = currentCumulative;
            }
        });
        return selection;
    }

    public static List<Double> boltzmannValues(List<GameCharacter> population, long generations){
        Double temperature = calculateTemperature(generations);
        List<Double> boltzmannValues = population.stream().
                map(GameCharacter::getFitness).
                map(f -> Math.exp(f / temperature)).
                collect(Collectors.toList());
        final Double average = boltzmannValues.stream().mapToDouble(Double::doubleValue).average().orElse(Double.NaN);
        boltzmannValues = boltzmannValues.stream().parallel().map(expF -> expF / average).collect(Collectors.toList());
        return boltzmannValues;
    }

    private static double calculateTemperature(long generations){
        return 100d / (generations + 1);
    }
}
