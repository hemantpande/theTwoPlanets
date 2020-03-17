package com.geektrust;

public class PlanetBuilder {

    private String name;
    private Army army;

    public PlanetBuilder withName(final String name) {
        this.name = name;
        return this;
    }

    public PlanetBuilder withArmy(final Army army) {
        this.army = army;
        return this;
    }

    public Planet build() {
        return new Planet(this.name, this.army);
    }

    public PlanetBuilder withStrength(final int strength) {
        this.army.setStrength(strength);
        return this;
    }
}
