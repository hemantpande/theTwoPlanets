package com.geektrust.tests;

import com.geektrust.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BattleShould {

    private Planet falicornia;
    private Planet lengaburu;

    @Before
    public void initialize() {
        falicornia = new PlanetBuilder()
                .withName(Constants.PLANET_FALICORNIA)
                .withHorses(300)
                .withElephants(200)
                .withTanks(40)
                .withSlingGuns(20)
                .build();

        lengaburu = new PlanetBuilder()
                .withName(Constants.PLANET_LENGABURU)
                .withHorses(100)
                .withElephants(50)
                .withTanks(10)
                .withSlingGuns(5)
                .withStrength(Constants.STRENGTH_MULTIPLYING_FACTOR)
                .build();
    }

    @Test
    public void handleBasicAttack() {

        assertEquals("WINS 1H 1E 1AT 1SG",
                forAttackingArmy(new Army(2, 2, 2, 2)));
    }

    @Test
    public void handleAttack_with_zeroTanks() {

        assertEquals("WINS 1H 2E 0AT 3SG",
                forAttackingArmy(new Army(2, 4, 0, 6)));
    }

    @Test
    public void handle_forward_substitution_rule_between_horses_elephants() {

        assertEquals("WINS 100H 11E 0AT 0SG",
                forAttackingArmy(new Army(204, 20, 0, 0)));
    }

    @Test
    public void handle_forward_substitution_rule_between_elephants_tanks() {

        assertEquals("WINS 100H 50E 1AT 0SG",
                forAttackingArmy(new Army(200, 104, 0, 0)));
    }

    @Test
    public void handle_reverse_substitution_rule_between_slingGuns_tanks() {

        assertEquals("WINS 0H 0E 9AT 5SG",
                forAttackingArmy(new Army(0, 0, 14, 12)));
    }

    @Test
    public void handle_reverse_substitution_rule_between_elephants_horses() {

        assertEquals("WINS 52H 50E 10AT 3SG",
                forAttackingArmy(new Army(100, 101, 20, 5)));
    }

    @Test
    public void handle_substitution_choice_rule_between_elephants_horses() {

        assertEquals("WINS 29H 50E 3AT 1SG",
                forAttackingArmy(new Army(50, 104, 6, 2)));
    }

    @Test
    public void handle_attack_sample_1() {

        assertEquals("WINS 52H 50E 10AT 3SG",
                forAttackingArmy(new Army(100, 101, 20, 5)));
    }

    @Test
    public void handle_attack_sample_2() {

        assertEquals("WINS 75H 50E 10AT 5SG",
                forAttackingArmy(new Army(150, 96, 26, 8)));
    }

    @Test
    public void handle_attack_sample_3() {

        assertEquals("LOSES 100H 38E 10AT 5SG",
                forAttackingArmy(new Army(250, 50, 20, 15)));
    }

    @Test
    public void handle_attack_sample_4() {

        assertEquals("LOSES 100H 29E 10AT 5SG",
                forAttackingArmy(new Army(200, 58, 18, 12)));
    }

    private String forAttackingArmy(Army army) {
        return new Battle()
                .between(falicornia, lengaburu)
                .withAttackingArmy(army)
                .result();
    }
}
