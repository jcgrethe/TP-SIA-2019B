package ar.edu.itba.sia.gae.mutationMethods;

import ar.edu.itba.sia.gae.models.GameCharacter;

/*
    All the gens mutates. Always same probability.
*/
public class MultiGenUniform implements MutationMethod {
    @Override
    public void mutate(GameCharacter gameCharacter, long generations) {
        MutationHelper.mutateMultiGens(gameCharacter, MutationHelper.MutationProbabilityMethod.UNIFORM, generations);
    }
}
