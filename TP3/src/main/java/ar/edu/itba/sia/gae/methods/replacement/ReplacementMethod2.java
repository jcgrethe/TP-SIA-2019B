package ar.edu.itba.sia.gae.methods.replacement;

import ar.edu.itba.sia.gae.helpers.Configuration;
import ar.edu.itba.sia.gae.models.GameCharacter;
import ar.edu.itba.sia.gae.methods.selection.SelectionHelper;
import java.util.ArrayList;
import java.util.List;

public class ReplacementMethod2 extends ReplacementMethod{

    @Override
    public List<GameCharacter> replace(Configuration configuration, List<GameCharacter> population, long generation) {
        List<GameCharacter> nextGeneration = new ArrayList<>();

        // A
        int childrenNextGenerationSize = (int) (configuration.getNextGenerationPercentage() * population.size());
        nextGeneration.addAll(getChildren(configuration, population, generation, childrenNextGenerationSize));

        // B
        int parentsNextGenerationSize = population.size() - childrenNextGenerationSize;
        List<GameCharacter> selection = SelectionHelper.selectionWrapperWithTwoMethodsB(population, configuration,generation, parentsNextGenerationSize);
        nextGeneration.addAll(selection);

        return nextGeneration;
    }

}
