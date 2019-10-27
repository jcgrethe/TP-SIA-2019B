package ar.edu.itba.sia.gae.mutationMethods;

import ar.edu.itba.sia.gae.models.GameCharacter;
import ar.edu.itba.sia.gae.models.Item;
import ar.edu.itba.sia.gae.models.ItemType;

import java.util.List;
import java.util.Map;

public interface MutationMethod {
    void mutate(GameCharacter gameCharacter, long generations);
}
