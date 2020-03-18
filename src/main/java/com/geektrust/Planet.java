package com.geektrust;

public class Planet {

    private final Army army;
    private final String name;

    public Planet(final String name, final Army army) {
        this.army = army;
        this.name = name;
    }

    public Army getArmy() {
        return army;
    }

    public String getName() {
        return name;
    }

    public String handleAttack(Army attackingArmy) {
        return army.handleAttack(attackingArmy);
    }
}
