package ar.edu.itba.sia.gae.models;

import java.util.Objects;

public class GameCharacter implements Comparable {
    private final CharacterType type;
    private double height;

    private Item VEST;
    private Item GLOVES;
    private Item HELMET;
    private Item BOOTS;
    private Item WEAPON;

    private final double forceMultiplier;
    private final double agilityMultiplier;
    private final double expertiseMultiplier;
    private final double resistanceMultiplier;
    private final double vitalityMultiplier;

    private final double attackMultiplier;
    private final double defenseMultiplier;

    public GameCharacter(CharacterType type, double height, Item VEST, Item GLOVES, Item HELMET, Item BOOTS, Item WEAPON,
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
        GameCharacter gameCharacter = (GameCharacter) o;
        return Double.compare(gameCharacter.height, height) == 0 &&
                Double.compare(gameCharacter.forceMultiplier, forceMultiplier) == 0 &&
                Double.compare(gameCharacter.agilityMultiplier, agilityMultiplier) == 0 &&
                Double.compare(gameCharacter.expertiseMultiplier, expertiseMultiplier) == 0 &&
                Double.compare(gameCharacter.resistanceMultiplier, resistanceMultiplier) == 0 &&
                Double.compare(gameCharacter.vitalityMultiplier, vitalityMultiplier) == 0 &&
                Double.compare(gameCharacter.attackMultiplier, attackMultiplier) == 0 &&
                Double.compare(gameCharacter.defenseMultiplier, defenseMultiplier) == 0 &&
                Objects.equals(VEST, gameCharacter.VEST) &&
                Objects.equals(GLOVES, gameCharacter.GLOVES) &&
                Objects.equals(HELMET, gameCharacter.HELMET) &&
                Objects.equals(BOOTS, gameCharacter.BOOTS) &&
                Objects.equals(WEAPON, gameCharacter.WEAPON);
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
        if (!(o instanceof GameCharacter)) return 0;
        GameCharacter other = (GameCharacter) o;
        return (int) (other.getFitness() - this.getFitness());
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public Item getVEST() {
        return VEST;
    }

    public void setVEST(Item VEST) {
        this.VEST = VEST;
    }

    public Item getGLOVES() {
        return GLOVES;
    }

    public void setGLOVES(Item GLOVES) {
        this.GLOVES = GLOVES;
    }

    public Item getHELMET() {
        return HELMET;
    }

    public void setHELMET(Item HELMET) {
        this.HELMET = HELMET;
    }

    public Item getBOOTS() {
        return BOOTS;
    }

    public void setBOOTS(Item BOOTS) {
        this.BOOTS = BOOTS;
    }

    public Item getWEAPON() {
        return WEAPON;
    }

    public void setWEAPON(Item WEAPON) {
        this.WEAPON = WEAPON;
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////                        MutationHelper Functions                             /////////////////////
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
