package ar.edu.itba.sia.gae.models;

public class Warrior1 extends Character {

    public Warrior1(double height, Item VEST, Item GLOVES, Item HELMET, Item BOOTS, Item WEAPON) {
        super(height, VEST, GLOVES, HELMET, BOOTS, WEAPON, 1.1, 0.9, 0.8, 1.0,
                0.9, 0.6, 0.4);
    }

    public Warrior1 clone(){
        return new Warrior1();
    }


}
