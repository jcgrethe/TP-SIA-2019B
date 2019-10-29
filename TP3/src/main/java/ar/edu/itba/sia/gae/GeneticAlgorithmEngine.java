package ar.edu.itba.sia.gae;

import ar.edu.itba.sia.gae.helpers.Configuration;
import ar.edu.itba.sia.gae.models.GameCharacter;
import ar.edu.itba.sia.gae.models.ItemType;
import ar.edu.itba.sia.gae.mutationMethods.MutationHelper;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class GeneticAlgorithmEngine {

    private final Configuration config;

    public GeneticAlgorithmEngine(Configuration configuration) {
        this.config = configuration;
        MutationHelper.init(configuration.getItems(), configuration.getMutationUniformProbability(), configuration.getMaxGenerations());
    }

    public void calculate(){
        Long generation = 0L;
        List<GameCharacter> population = initPopulation();
        while(generation < config.getMaxGenerations()){   // TODO Add Conditions
            // Replacement
            population = config.getReplacementMethod().replace(config,population,generation);
            // Next Generation
            generation++;
            System.out.println("Generation " + generation + " | Max Fitness: " + Collections.max(population).getFitness());
        }

    }

    private List<GameCharacter> initPopulation() {
        List<GameCharacter> population = new LinkedList<>();
        ThreadLocalRandom random = ThreadLocalRandom.current();
        int vestSize = config.getItems().get(ItemType.VEST).size();
        int weaponSize = config.getItems().get(ItemType.WEAPON).size();
        int helmetSize = config.getItems().get(ItemType.HELMET).size();
        int bootSize = config.getItems().get(ItemType.BOOTS).size();
        int gloveSize = config.getItems().get(ItemType.GLOVES).size();


        for (int x = 0; x < config.getInitialSize(); x++){
            population.add(
                    new GameCharacter(
                            config.getType(),
                            random.nextDouble(config.getMinHeight(), config.getMaxHeight()),
                            config.getItems().get(ItemType.VEST).get(random.nextInt(0,vestSize)),
                            config.getItems().get(ItemType.GLOVES).get(random.nextInt(0,gloveSize)),
                            config.getItems().get(ItemType.HELMET).get(random.nextInt(0,helmetSize)),
                            config.getItems().get(ItemType.BOOTS).get(random.nextInt(0,bootSize)),
                            config.getItems().get(ItemType.WEAPON).get(random.nextInt(0,weaponSize))
                            )
            );
        }
        return population;
    }
}
