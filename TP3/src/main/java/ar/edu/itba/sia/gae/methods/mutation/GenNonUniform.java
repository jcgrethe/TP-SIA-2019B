package ar.edu.itba.sia.gae.methods.mutation;

import ar.edu.itba.sia.gae.models.GameCharacter;

/*
    One of the gens mutates. Probability of mutation changes.
*/
public class GenNonUniform implements MutationMethod {
    @Override
    public void mutate(GameCharacter gameCharacter, long generations) {
        MutationHelper.mutateOneGen(gameCharacter, MutationHelper.MutationProbabilityMethod.NON_UNIFORM, generations, null);
    }
}
