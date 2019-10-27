package ar.edu.itba.sia.gae.mutationMethods;

import ar.edu.itba.sia.gae.models.GameCharacter;

/*
    All the gens mutates. Probability of mutation changes.
*/
public class MultiGenNonUniform implements MutationMethod {
    @Override
    public void mutate(GameCharacter gameCharacter, long generations) {
        MutationHelper.mutateMultiGens(gameCharacter, MutationHelper.MutationProbabilityMethod.NON_UNIFORM, generations);
    }
}
