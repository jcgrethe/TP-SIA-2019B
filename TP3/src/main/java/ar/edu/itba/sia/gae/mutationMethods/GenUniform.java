package ar.edu.itba.sia.gae.mutationMethods;

import ar.edu.itba.sia.gae.models.Item;
import ar.edu.itba.sia.gae.models.GameCharacter;
import ar.edu.itba.sia.gae.models.ItemType;
import java.util.List;
import java.util.Map;

/*
    One of the gens mutates. Always same probability.
*/
public class GenUniform implements MutationMethod {
    @Override
    public void mutate(GameCharacter gameCharacter, long generations) {
        MutationHelper.mutateOneGen(gameCharacter, MutationHelper.MutationProbabilityMethod.UNIFORM, generations, null);
    }
}