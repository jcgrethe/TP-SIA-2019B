package ar.edu.itba.sia.gae.models;

public abstract class Character {
    private final double height;

    private final Item VEST;
    private final Item GLOVES;
    private final Item HELMET;
    private final Item BOOTS;
    private final Item WEAPON;

    private final double baseForce;
    private final double baseAgility;
    private final double baseExpertise;
    private final double baseResistance;
    private final double baseVitality;

    private final double attackMultiplier;
    private final double defenseMultiplier;

    public Character(double height, Item VEST, Item GLOVES, Item HELMET, Item BOOTS, Item WEAPON, double baseForce,
                     double baseAgility, double baseExpertise, double baseResistance, double baseVitality,
                     double attackMultiplier, double defenseMultiplier) {
        this.height = height;
        this.VEST = VEST;
        this.GLOVES = GLOVES;
        this.HELMET = HELMET;
        this.BOOTS = BOOTS;
        this.WEAPON = WEAPON;
        this.baseForce = baseForce;
        this.baseAgility = baseAgility;
        this.baseExpertise = baseExpertise;
        this.baseResistance = baseResistance;
        this.baseVitality = baseVitality;
        this.attackMultiplier = attackMultiplier;
        this.defenseMultiplier = defenseMultiplier;
    }


}
