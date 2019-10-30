package ar.edu.itba.sia.gae.methods.replacement;

import ar.edu.itba.sia.gae.helpers.Configuration;
import ar.edu.itba.sia.gae.methods.selection.SelectionHelper;
import ar.edu.itba.sia.gae.models.GameCharacter;

import java.util.ArrayList;
import java.util.List;

public class ReplacementMethod3 extends ReplacementMethod {
    @Override
    public List<GameCharacter> replace(Configuration configuration, List<GameCharacter> population, long generation) {

        List<GameCharacter> nextGeneration = new ArrayList<>();

        int childrenNextGenerationSize = (int) (configuration.getNextGenerationPercentage() * population.size());
        List<GameCharacter> children = getChildren(configuration, population, generation, childrenNextGenerationSize);

        //Selection A
        List<GameCharacter> childrenParentsPopulation = new ArrayList<>();
        childrenParentsPopulation.addAll(population);
        childrenParentsPopulation.addAll(children);
        List<GameCharacter> selection = SelectionHelper.selectionWrapperWithTwoMethodsB(childrenParentsPopulation,
                configuration, generation, childrenNextGenerationSize);
        nextGeneration.addAll(selection);


        // B
        int parentsNextGenerationSize = population.size() - childrenNextGenerationSize;
        selection = SelectionHelper.selectionWrapperWithTwoMethodsB(population, configuration,generation, parentsNextGenerationSize);
        nextGeneration.addAll(selection);

        return nextGeneration;
    }
}
