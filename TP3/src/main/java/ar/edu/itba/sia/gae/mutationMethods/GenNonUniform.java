package ar.edu.itba.sia.gae.mutationMethods;

import ar.edu.itba.sia.gae.models.GameCharacter;
import ar.edu.itba.sia.gae.models.Item;
import ar.edu.itba.sia.gae.models.ItemType;

import java.util.List;
import java.util.Map;

/*
    One of the gens mutates. Probability of mutation changes.
*/
public class GenNonUniform implements MutationMethod {
    @Override
    public void mutate(GameCharacter gameCharacter, long generations) {
        MutationHelper.mutateOneGen(gameCharacter, MutationHelper.MutationProbabilityMethod.NON_UNIFORM, generations, null);
    }
}
