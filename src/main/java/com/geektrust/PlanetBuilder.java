package com.geektrust;

public class PlanetBuilder {

    private String name;
    private double horses;
    private double elephants;
    private double tanks;
    private double slingGuns;
    private int strength = 0;

    public PlanetBuilder withName(final String name) {
        this.name = name;
        return this;
    }

    public PlanetBuilder withHorses(double horses){
        this.horses = horses;
        return this;
    }

    public PlanetBuilder withElephants(double elephants){
        this.elephants = elephants;
        return this;
    }

    public PlanetBuilder withTanks(double tanks){
        this.tanks = tanks;
        return this;
    }

    public PlanetBuilder withSlingGuns(double slingGuns){
        this.slingGuns = slingGuns;
        return this;
    }

    public PlanetBuilder withStrength(final int strength) {
        this.strength = strength;
        return this;
    }

    public Planet build() {
        return new Planet(this.name, new Army(horses, elephants, tanks, slingGuns, strength));
    }
}
