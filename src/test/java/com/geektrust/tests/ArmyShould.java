package com.geektrust.tests;

import com.geektrust.Army;
import org.junit.Assert;
import org.junit.Test;

public class ArmyShould {

    @Test
    public void handle_attack(){
        Army army = new Army(100, 50, 10, 5);

        final String actual = army.handleAttack(new Army(50, 20, 10, 1));

        Assert.assertEquals("WINS 50H 20E 10AT 1SG", actual);
    }
}
