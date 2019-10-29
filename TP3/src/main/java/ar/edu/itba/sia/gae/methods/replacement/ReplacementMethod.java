package ar.edu.itba.sia.gae.methods.replacement;

import ar.edu.itba.sia.gae.helpers.Configuration;
import ar.edu.itba.sia.gae.models.GameCharacter;
import ar.edu.itba.sia.gae.methods.selection.SelectionHelper;

import java.util.List;

public abstract class ReplacementMethod {

    public abstract List<GameCharacter> replace(Configuration configuration, List<GameCharacter> population, long generation);

    protected List<GameCharacter> getChildren(Configuration config, List<GameCharacter> population, long generation, double size){

        // Selection
        List<GameCharacter> selection = SelectionHelper.selectionWrapperWithTwoMethodsA(population, config, generation, size);

        // CrossOver
        List<GameCharacter> children = config.getCrossOverMethod().crossSelection(selection);

        // Mutation
        final long currentGeneration = generation;
        children.forEach(character -> config.getMutationMethod().mutate(character, currentGeneration));

        // Fitness Evaluation
        population.forEach(character -> character.getFitness());

        return population;
    }


}
