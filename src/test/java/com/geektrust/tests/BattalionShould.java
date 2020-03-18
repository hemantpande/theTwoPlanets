package com.geektrust.tests;

import com.geektrust.Battalion;
import com.geektrust.Constants;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class BattalionShould {

    @Test
    public void correctly_calculate_the_counter_forces_needed_when_they_are_enough() {
        Battalion angryBirds = new Battalion(100, 5);
        Battalion frogs = new Battalion(500, Constants.DEFAULT_STRENGTH_FACTOR);

        angryBirds.neededFor(frogs);

        Assert.assertTrue(100 == angryBirds.needed());
        Assert.assertFalse(angryBirds.hasExceeded());
    }

    @Test
    public void correctly_calculate_the_counter_forces_needed_when_they_are_not_enough() {
        Battalion angryBirds = new Battalion(99, 5);
        Battalion frogs = new Battalion(500, Constants.DEFAULT_STRENGTH_FACTOR);

        angryBirds.neededFor(frogs);

        Assert.assertTrue(100 == angryBirds.needed());
        Assert.assertTrue(angryBirds.hasExceeded());
    }

    @Test
    public void correctly_balance_the_previous_battalion() {
        Battalion horses = new Battalion(100, 2);
        Battalion elephants = new Battalion(50, 2);

        Battalion attacking_horses = new Battalion(100, Constants.DEFAULT_STRENGTH_FACTOR);
        Battalion attacking_elephants = new Battalion(101, Constants.DEFAULT_STRENGTH_FACTOR);

        horses.neededFor(attacking_horses);
        elephants.neededFor(attacking_elephants);

        elephants.tryToBalancePrevious(horses);

        Assert.assertTrue(52.0 == horses.needed());
        Assert.assertTrue((50.0 == elephants.needed()));
    }


    @Test
    public void correctly_balance_the_next_battalion() {
        Battalion horses = new Battalion(100, 2);
        Battalion elephants = new Battalion(50, 2);

        Battalion attacking_horses = new Battalion(204, Constants.DEFAULT_STRENGTH_FACTOR);
        Battalion attacking_elephants = new Battalion(20, Constants.DEFAULT_STRENGTH_FACTOR);

        horses.neededFor(attacking_horses);
        elephants.neededFor(attacking_elephants);

        horses.tryToBalanceNext(elephants);

        Assert.assertTrue(100.0 == horses.needed());
        Assert.assertTrue((11.0 == elephants.needed()));
    }

    @Test
    public void not_balance_the_previous_battalion_when_it_is_null() {
        Battalion horses = null;
        Battalion elephants = new Battalion(50, 2);

        Battalion attacking_elephants = new Battalion(101, Constants.DEFAULT_STRENGTH_FACTOR);

        elephants.neededFor(attacking_elephants);
        elephants.tryToBalancePrevious(horses);

        Assert.assertTrue((51.0 == elephants.needed()));
    }


    @Test
    public void not_balance_the_next_battalion_when_it_is_null() {
        Battalion horses = new Battalion(100, 2);
        Battalion elephants = null;

        Battalion attacking_horses = new Battalion(204, Constants.DEFAULT_STRENGTH_FACTOR);
        horses.neededFor(attacking_horses);
        horses.tryToBalanceNext(elephants);

        Assert.assertTrue(102.0 == horses.needed());
    }
}
