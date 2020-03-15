package com.geektrust;

public class Planet {

    private final String name;
    private final Army army;

    public Planet(String name, Army army, double strength) {
        this.name = name;
        this.army = army;
        this.army.setStrength(strength);
    }

    public String handle(Army attackingArmy) {

        army.setAttackingArmy(attackingArmy);

        return army.handleAttack();
    }
}
