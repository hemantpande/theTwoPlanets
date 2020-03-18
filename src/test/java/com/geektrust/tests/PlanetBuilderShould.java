package com.geektrust.tests;

import com.geektrust.Army;
import com.geektrust.Constants;
import com.geektrust.Planet;
import com.geektrust.PlanetBuilder;
import org.junit.Assert;
import org.junit.Test;

public class PlanetBuilderShould {

    @Test
    public void construct_planet(){
        Planet planet = new PlanetBuilder()
                .withHorses(10)
                .withElephants(11)
                .withTanks(12)
                .withSlingGuns(13)
                .withStrength(3)
                .withName(Constants.PLANET_LENGABURU)
                .build();

        Assert.assertEquals(planet.getArmy(), new Army(10,11,12, 13, 3));
        Assert.assertEquals(planet.getName(), Constants.PLANET_LENGABURU);
    }


}
