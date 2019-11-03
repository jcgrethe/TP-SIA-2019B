package ar.edu.itba.sia.gae.helpers;

import ar.edu.itba.sia.gae.models.GameCharacter;

public class FittestChar {

    private static FittestChar instance;
    private GameCharacter character;

    private FittestChar(GameCharacter c){
        character = c;
    }

    public void setChar(GameCharacter c){
        character = c;
    }

    public GameCharacter getChar(){
        return character;
    }

    public static FittestChar getInstance(){
        if(instance == null){
            instance = new FittestChar(null);
        }
        return instance;
    }
}
