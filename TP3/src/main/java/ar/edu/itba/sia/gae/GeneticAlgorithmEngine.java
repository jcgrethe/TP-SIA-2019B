package ar.edu.itba.sia.gae;

import ar.edu.itba.sia.gae.helpers.Configuration;
import ar.edu.itba.sia.gae.models.GameCharacter;
import ar.edu.itba.sia.gae.models.ItemType;
import ar.edu.itba.sia.gae.methods.mutation.MutationHelper;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

class GeneticAlgorithmEngine {

    private final Configuration config;

    GeneticAlgorithmEngine(Configuration configuration) {
        this.config = configuration;
        MutationHelper.init(configuration.getItems(), configuration.getMutationUniformProbability(), configuration.getMaxGenerations());
    }

    GameCharacter calculate(){
        Long generation = 0L;
        List<GameCharacter> population = initPopulation();
        System.out.println("Running " + config.getMaxGenerations() + " generations, " +
                "starting with fitness " + Collections.max(population).getFitness());
        while(generation < config.getMaxGenerations()){   // TODO Add Conditions
            population = config.getReplacementMethod().replace(config, population, generation);
            generation++;
            System.out.println("Generation " + generation + " | Max Fitness: " + Collections.max(population).getFitness()+
                    " | PromFitness: " + (population.stream().map(GameCharacter::getFitness).reduce(Double::sum).get()/population.size()));

        }
        return Collections.max(population);
    }

    private List<GameCharacter> initPopulation() {
        List<GameCharacter> population = new LinkedList<>();
        ThreadLocalRandom random = ThreadLocalRandom.current();
        int vestSize = config.getItems().get(ItemType.VEST).size();
        int weaponSize = config.getItems().get(ItemType.WEAPON).size();
        int helmetSize = config.getItems().get(ItemType.HELMET).size();
        int bootSize = config.getItems().get(ItemType.BOOTS).size();
        int gloveSize = config.getItems().get(ItemType.GLOVES).size();
        LongStream.range(0, config.getInitialSize()).forEach(l ->
            population.add(
                new GameCharacter(
                        config.getType(),
                        random.nextDouble(config.getMinHeight(), config.getMaxHeight()),
                        config.getItems().get(ItemType.VEST).get(random.nextInt(0, vestSize)),
                        config.getItems().get(ItemType.GLOVES).get(random.nextInt(0, gloveSize)),
                        config.getItems().get(ItemType.HELMET).get(random.nextInt(0, helmetSize)),
                        config.getItems().get(ItemType.BOOTS).get(random.nextInt(0, bootSize)),
                        config.getItems().get(ItemType.WEAPON).get(random.nextInt(0, weaponSize))
                )
        ));
        return population;
    }
}
