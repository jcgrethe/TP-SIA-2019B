package ar.edu.itba.sia.gae.models;

import apple.laf.JRSUIUtils;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class GameCharacter implements Comparable{
    private final CharacterType type;
    private double height;

    private Item VEST;
    private Item GLOVES;
    private Item HELMET;
    private Item BOOTS;
    private Item WEAPON;

    private double forceMultiplier;
    private double agilityMultiplier;
    private double expertiseMultiplier;
    private double resistanceMultiplier;
    private double vitalityMultiplier;

    private final double attackMultiplier;
    private final double defenseMultiplier;

    private Image image;
    private int bWidth;
    private int bHeight;

    public GameCharacter(CharacterType type, double height, Item VEST, Item GLOVES, Item HELMET, Item BOOTS, Item WEAPON) {

        if (height < 1.3 || height > 2) throw new IllegalArgumentException("Invalid Height");
        this.type = type;
        this.height = height;
        this.VEST = VEST;
        this.GLOVES = GLOVES;
        this.HELMET = HELMET;
        this.BOOTS = BOOTS;
        this.WEAPON = WEAPON;
        setMultipliers(type);
        this.attackMultiplier = calculateATM();
        this.defenseMultiplier = calculateDEM();

        ImageIcon ii = new ImageIcon(JRSUIUtils.Images.class.getResource("/body1.png"));
        image = ii.getImage();
        bWidth = image.getWidth(null);
        bHeight = image.getHeight(null);
    }

    public Image getImage() {
        return image;
    }

    public int getImageWidth() {
        return bWidth;
    }

    public int getImageHeight() {
        return bHeight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GameCharacter gameCharacter = (GameCharacter) o;
        return Double.compare(gameCharacter.height, height) == 0 &&
                Objects.equals(VEST, gameCharacter.VEST) &&
                Objects.equals(GLOVES, gameCharacter.GLOVES) &&
                Objects.equals(HELMET, gameCharacter.HELMET) &&
                Objects.equals(BOOTS, gameCharacter.BOOTS) &&
                Objects.equals(WEAPON, gameCharacter.WEAPON);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, height, VEST, GLOVES, HELMET, BOOTS, WEAPON, forceMultiplier, agilityMultiplier,
                expertiseMultiplier, resistanceMultiplier, vitalityMultiplier, attackMultiplier, defenseMultiplier);
    }

    public double getFitness(){
        switch (type){
            case WARRIOR:   return 0.6 * calculateAttack() + 0.4 * calculateDefense();
            case ARCHER:    return 0.9 * calculateAttack() + 0.1 * calculateDefense();
            case DEFENDER:  return 0.1 * calculateAttack() + 0.9 * calculateDefense();
            case ASSASSIN:   return 0.7 * calculateAttack() + 0.3 * calculateDefense();
            default: return -1;
        }
    }

    public Item getItem(ItemType item){
        switch (item){
            case BOOTS: return BOOTS;
            case VEST: return VEST;
            case GLOVES: return GLOVES;
            case HELMET: return HELMET;
            case WEAPON: return WEAPON;
        }
        return null;
    }

    public void setItem(Item item){
        switch (item.getType()){
            case BOOTS: setBOOTS(item);
                break;
            case VEST: setVEST(item);
                break;
            case GLOVES: setGLOVES(item);
                break;
            case HELMET: setHELMET(item);
                break;
            case WEAPON: setWEAPON(item);
                break;
            default: throw new IllegalStateException("Invalid item type.");
        }
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
        if (VEST.getType() != ItemType.VEST)
            throw new IllegalStateException("Cannot put " + VEST.getType().name()  + " as VEST");
        this.VEST = VEST;
    }

    public Item getGLOVES() {
        return GLOVES;
    }

    public void setGLOVES(Item GLOVES) {
        if (GLOVES.getType() != ItemType.GLOVES)
            throw new IllegalStateException("Cannot put " + GLOVES.getType().name()  + " as GLOVES");
        this.GLOVES = GLOVES;
    }

    public Item getHELMET() {
        return HELMET;
    }

    public void setHELMET(Item HELMET) {
        if (HELMET.getType() != ItemType.HELMET)
            throw new IllegalStateException("Cannot put " + HELMET.getType().name()  + " as HELMET");
        this.HELMET = HELMET;
    }

    public Item getBOOTS() {
        return BOOTS;
    }

    public void setBOOTS(Item BOOTS) {
        if (BOOTS.getType() != ItemType.BOOTS) 
            throw new IllegalStateException("Cannot put " + BOOTS.getType().name()  + " as BOOTS");
        this.BOOTS = BOOTS;
    }

    public Item getWEAPON() {
        return WEAPON;
    }

    public void setWEAPON(Item WEAPON) {
        if (WEAPON.getType() != ItemType.WEAPON)
            throw new IllegalStateException("Cannot put " + WEAPON.getType().name()  + " as WEAPON");
        this.WEAPON = WEAPON;
    }

    public GameCharacter clone(){
        return new GameCharacter(type,height,VEST,GLOVES,HELMET,BOOTS,WEAPON);
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

    // Always character number 1
    private void setMultipliers(CharacterType type){
        switch (type){
            default:
            case WARRIOR:
                this.forceMultiplier = 1.1;
                this.agilityMultiplier = 0.9;
                this.expertiseMultiplier = 0.8;
                this.resistanceMultiplier = 1;
                this.vitalityMultiplier = 0.9;
                break;
            case ARCHER:
                this.forceMultiplier = 0.8;
                this.agilityMultiplier = 1.1;
                this.expertiseMultiplier = 1.1;
                this.resistanceMultiplier = 0.9;
                this.vitalityMultiplier = 0.7;
                break;
            case ASSASSIN:
                this.forceMultiplier = 0.8;
                this.agilityMultiplier = 1.2;
                this.expertiseMultiplier = 1.1;
                this.resistanceMultiplier = 1.0;
                this.vitalityMultiplier = 0.8;
                break;
            case DEFENDER:
                this.forceMultiplier = 1.0;
                this.agilityMultiplier = 0.9;
                this.expertiseMultiplier = 0.7;
                this.resistanceMultiplier = 1.2;
                this.vitalityMultiplier = 1.1;
                break;
        }
    }

    @Override
    public int compareTo(Object o) {
        if (o instanceof GameCharacter)
            return Double.compare(this.getFitness(), ((GameCharacter)o).getFitness());
        return -1;
    }

    @Override
    public String toString() {
        return "GameCharacter {\n" +
                "type=" + type +
                ", \n -height=" + height +
                ", \n\n -VEST=" + VEST +
                ", \n\n -GLOVES=" + GLOVES +
                ", \n\n -HELMET=" + HELMET +
                ", \n\n -BOOTS=" + BOOTS +
                ", \n\n -WEAPON=" + WEAPON +
                "\n}";
    }

    public Map<String, Long> equipment(){
        Map<String, Long> equipment = new HashMap<>();
        equipment.put("VEST", VEST.getId());
        equipment.put("GLOVES", GLOVES.getId());
        equipment.put("HELMET", HELMET.getId());
        equipment.put("BOOTS", BOOTS.getId());
        equipment.put("WEAPON", WEAPON.getId());
        return equipment;
    }
}
