package com.geektrust.tests;

import com.geektrust.Army;
import com.geektrust.Constants;
import com.geektrust.Planet;
import org.junit.Assert;
import org.junit.Test;

public class PlanetShould {

    @Test
    public void handle_attack() {
        final Army army = new Army(10, 10, 10, 10, 2);
        Planet planet = new Planet(Constants.PLANET_LENGABURU, army);

        final String actual = planet.handleAttack(new Army(10, 10, 10, 10));

        Assert.assertEquals("WINS 5H 5E 5AT 5SG", actual);
    }
}
