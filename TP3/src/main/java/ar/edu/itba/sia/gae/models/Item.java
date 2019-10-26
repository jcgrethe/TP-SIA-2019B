package ar.edu.itba.sia.gae.models;

import java.util.Objects;

public class Item {
    private final long id;
    private final double force;
    private final double agility;
    private final double expertise;
    private final double resistance;
    private final double vitality;
    private final ItemType type;

    public Item(long id, double force, double agility, double expertise, double resistance, double vitality, ItemType type) {
        this.id = id;
        this.force = force;
        this.agility = agility;
        this.expertise = expertise;
        this.resistance = resistance;
        this.vitality = vitality;
        this.type = type;
    }

    public long getId() {
        return id;
    }

    public double getForce() {
        return force;
    }

    public double getAgility() {
        return agility;
    }

    public double getExpertise() {
        return expertise;
    }

    public double getResistance() {
        return resistance;
    }

    public double getVitality() {
        return vitality;
    }

    public ItemType getType() {
        return type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return id == item.id &&
                Double.compare(item.force, force) == 0 &&
                Double.compare(item.agility, agility) == 0 &&
                Double.compare(item.expertise, expertise) == 0 &&
                Double.compare(item.resistance, resistance) == 0 &&
                Double.compare(item.vitality, vitality) == 0 &&
                type == item.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, force, agility, expertise, resistance, vitality, type);
    }
}

