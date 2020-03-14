public class Battle {

    private Planet attacking;
    private Planet underAttack;
    private Army attackingArmy;

    Battle between(Planet attacking, Planet underAttack) {
        this.attacking = attacking;
        this.underAttack = underAttack;
        return this;
    }

    public Battle withAttackingArmy(Army army) {
        this.attackingArmy = army;
        return this;
    }

    public String result() {
        return underAttack.handle(this.attackingArmy);
    }
}
