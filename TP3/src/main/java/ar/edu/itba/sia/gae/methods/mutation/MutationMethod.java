package ar.edu.itba.sia.gae.methods.mutation;

import ar.edu.itba.sia.gae.models.GameCharacter;

public interface MutationMethod {
    void mutate(GameCharacter gameCharacter, long generations);
}
