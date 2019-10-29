package ar.edu.itba.sia.gae.methods.mutation;

import ar.edu.itba.sia.gae.models.GameCharacter;

/*
    One of the gens mutates. Always same probability.
*/
public class GenUniform implements MutationMethod {
    @Override
    public void mutate(GameCharacter gameCharacter, long generations) {
        MutationHelper.mutateOneGen(gameCharacter, MutationHelper.MutationProbabilityMethod.UNIFORM, generations, null);
    }
}
