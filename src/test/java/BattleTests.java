import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class BattleTests {

    Planet falicornia;
    Planet lengaburu;

    @Before
    public void setup(){
        falicornia = new PlanetBuilder()
                .withName("Falicornia")
                .withArmy(new Army(300, 200, 40, 20))
                .build();

        lengaburu = new PlanetBuilder()
                .withName("Lengaburu")
                .withArmy(new Army(100, 50, 10, 5))
                .withStrength(2)
                .build();
    }

    @Test
    public void handleBasicAttack() {

        String result = new Battle()
                .between(falicornia, lengaburu)
                .withAttackingArmy(new Army(2,2,2,2))
                .result();

        Assert.assertEquals("WINS 1H 1E 1AT 1SG", result);
    }

    @Test
    public void handleAttack_with_ZeroTanks() {

        String result = new Battle()
                .between(falicornia, lengaburu)
                .withAttackingArmy(new Army(2,4,0,6))
                .result();

        Assert.assertEquals("WINS 1H 2E 0AT 3SG", result);
    }

    @Test
    public void handle_forward_substitution_rule_horses_elephants() {

        String result = new Battle()
                .between(falicornia, lengaburu)
                .withAttackingArmy(new Army(204,20,0,0))
                .result();

        Assert.assertEquals("WINS 100H 11E 0AT 0SG", result);
    }

    @Test
    public void handle_forward_substitution_rule_elephants_tanks() {

        String result = new Battle()
                .between(falicornia, lengaburu)
                .withAttackingArmy(new Army(200,104,0,0))
                .result();

        Assert.assertEquals("WINS 100H 50E 1AT 0SG", result);
    }

    @Test
    public void handle_reverse_substitution_rule_slingGuns_tanks() {

        String result = new Battle()
                .between(falicornia, lengaburu)
                .withAttackingArmy(new Army(0,0,14,12))
                .result();

        Assert.assertEquals("WINS 0H 0E 9AT 5SG", result);
    }

    @Test
    public void handle_reverse_substitution_rule_elephants_horses() {

        String result = new Battle()
                .between(falicornia, lengaburu)
                .withAttackingArmy(new Army(100,101,20,5))
                .result();

        Assert.assertEquals("WINS 52H 50E 10AT 3SG", result);
    }

    @Test
    public void handle_substitution_choice_rule_elephants_horses() {

        String result = new Battle()
                .between(falicornia, lengaburu)
                .withAttackingArmy(new Army(50,104,6,2))
                .result();

        Assert.assertEquals("WINS 29H 50E 3AT 1SG", result);
    }

    @Test
    public void handle_attack_sample_1() {

        String result = new Battle()
                .between(falicornia, lengaburu)
                .withAttackingArmy(new Army(100,101,20,5))
                .result();

        Assert.assertEquals("WINS 52H 50E 10AT 3SG", result);
    }

    // TODO : This assertion of this test, as suggested in problem statement is incorrect in my opinion.
    //  Reason being the tanks are being balanced in both it's neighbors, which is against the rule.
    @Test
    public void handle_attack_sample_2() {

        String result = new Battle()
                .between(falicornia, lengaburu)
                .withAttackingArmy(new Army(150,96,26,8))
                .result();

        Assert.assertEquals("WINS 75H 50E 10AT 5SG", result);
    }

    @Test
    public void handle_attack_sample_3() {

        String result = new Battle()
                .between(falicornia, lengaburu)
                .withAttackingArmy(new Army(250,50,20,15))
                .result();

        Assert.assertEquals("LOSES 100H 38E 10AT 5SG", result);
    }
}
