package com.geektrust;

public class Planet {

    private final Army army;
    private final String name;

    public Planet(final String name, final Army army) {
        this.army = army;
        this.name = name;
    }

    public String handleAttack(Army attackingArmy) {

        return army.setAttackingArmy(attackingArmy)
                .handleAttack();
    }
}
