package ar.edu.itba.sia.gae.models;

import java.util.Objects;
import java.util.function.Supplier;

public abstract class Character implements Comparable {
    private final CharacterType type;
    private final double height;

    private final Item VEST;
    private final Item GLOVES;
    private final Item HELMET;
    private final Item BOOTS;
    private final Item WEAPON;

    private final double forceMultiplier;
    private final double agilityMultiplier;
    private final double expertiseMultiplier;
    private final double resistanceMultiplier;
    private final double vitalityMultiplier;

    private final double attackMultiplier;
    private final double defenseMultiplier;

    public Character(CharacterType type, double height, Item VEST, Item GLOVES, Item HELMET, Item BOOTS, Item WEAPON,
                     double forceMultiplier, double agilityMultiplier, double expertiseMultiplier,
                     double resistanceMultiplier, double vitalityMultiplier) {

        if (height < 1.3 || height > 2) throw new IllegalArgumentException("Invalid Height");
        this.type = type;
        this.height = height;
        this.VEST = VEST;
        this.GLOVES = GLOVES;
        this.HELMET = HELMET;
        this.BOOTS = BOOTS;
        this.WEAPON = WEAPON;
        this.forceMultiplier = forceMultiplier;
        this.agilityMultiplier = agilityMultiplier;
        this.expertiseMultiplier = expertiseMultiplier;
        this.resistanceMultiplier = resistanceMultiplier;
        this.vitalityMultiplier = vitalityMultiplier;
        this.attackMultiplier = calculateATM();
        this.defenseMultiplier = calculateDEM();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Character character = (Character) o;
        return Double.compare(character.height, height) == 0 &&
                Double.compare(character.forceMultiplier, forceMultiplier) == 0 &&
                Double.compare(character.agilityMultiplier, agilityMultiplier) == 0 &&
                Double.compare(character.expertiseMultiplier, expertiseMultiplier) == 0 &&
                Double.compare(character.resistanceMultiplier, resistanceMultiplier) == 0 &&
                Double.compare(character.vitalityMultiplier, vitalityMultiplier) == 0 &&
                Double.compare(character.attackMultiplier, attackMultiplier) == 0 &&
                Double.compare(character.defenseMultiplier, defenseMultiplier) == 0 &&
                Objects.equals(VEST, character.VEST) &&
                Objects.equals(GLOVES, character.GLOVES) &&
                Objects.equals(HELMET, character.HELMET) &&
                Objects.equals(BOOTS, character.BOOTS) &&
                Objects.equals(WEAPON, character.WEAPON);
    }

    @Override
    public int hashCode() {
        return Objects.hash(height, VEST, GLOVES, HELMET, BOOTS, WEAPON, forceMultiplier, agilityMultiplier, expertiseMultiplier, resistanceMultiplier, vitalityMultiplier, attackMultiplier, defenseMultiplier);
    }

    public double getFitness(){
        switch (type){
            case WARRIOR:   return 0.6 * calculateAttack() + 0.4 * calculateDefense();
            case ARCHER:    return 0.9 * calculateAttack() + 0.1 * calculateDefense();
            case DEFENDER:  return 0.1 * calculateAttack() + 0.9 * calculateDefense();
            case ASSASIN:   return 0.7 * calculateAttack() + 0.3 * calculateDefense();
            default: return -1;
        }
    }

    @Override
    public int compareTo(Object o) {
        if (!(o instanceof Character)) return 0;
        Character other = (Character) o;
        return (int) (other.getFitness() - this.getFitness());
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////                        Helper Functions                             /////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////////

    private double calculateATM(){
        return 0.5 - Math.pow(3d*this.height - 5d, 4d) + Math.pow(3d * this.height - 5, 2d) + this.height/2d;
    }

    private double calculateDEM(){
        return 2d + Math.pow(3d*this.height - 5d, 4d) - Math.pow(3d * this.height - 5, 2d) - this.height/2d;
    }

    private double calculateAttack(){
        return (calculateAgility() + calculateExpertise()) * calculateForce() * attackMultiplier;
    }
    private double calculateDefense(){
        return (calculateResistance() + calculateExpertise()) * calculateVitality() * defenseMultiplier;
    }

    private double calculateAgility(){
        return Math.tanh(
            0.01 * agilityMultiplier * (
                VEST.getAgility() + HELMET.getAgility() + GLOVES.getAgility() +
                        BOOTS.getAgility() + WEAPON.getAgility()
            )
        );
    }
    private double calculateForce(){
        return 100d * Math.tanh(
                0.01 * forceMultiplier * (
                        VEST.getForce() + HELMET.getForce() + GLOVES.getForce() +
                                BOOTS.getForce() + WEAPON.getForce()
                )
        );
    }
    private double calculateExpertise(){
        return 0.6 * Math.tanh(
                0.01 * expertiseMultiplier * (
                        VEST.getExpertise() + HELMET.getExpertise() + GLOVES.getExpertise() +
                                BOOTS.getExpertise() + WEAPON.getExpertise()
                )
        );
    }
    private double calculateResistance(){
        return Math.tanh(
                0.01 * resistanceMultiplier * (
                        VEST.getResistance() + HELMET.getResistance() + GLOVES.getResistance() +
                                BOOTS.getResistance() + WEAPON.getResistance()
                )
        );
    }
    private double calculateVitality(){
        return 100d * Math.tanh(
                0.01 * vitalityMultiplier * (
                        VEST.getVitality() + HELMET.getVitality() + GLOVES.getVitality() +
                                BOOTS.getVitality() + WEAPON.getVitality()
                )
        );
    }

}
