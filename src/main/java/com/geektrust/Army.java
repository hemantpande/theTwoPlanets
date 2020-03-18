package com.geektrust;

public class Army {

    private final Battalion horses;
    private final Battalion elephants;
    private final Battalion tanks;
    private final Battalion slingGuns;

    public Army(final double horses, final double elephants, final double tanks, final double slingGuns, int strength) {
        this.horses = new Battalion(horses, strength);
        this.elephants = new Battalion(elephants, strength);
        this.tanks = new Battalion(tanks, strength);
        this.slingGuns = new Battalion(slingGuns, strength);
    }

    public Army(final double horses, final double elephants, final double tanks, final double slingGuns) {
        this(horses, elephants, tanks, slingGuns, Constants.DEFAULT_STRENGTH_FACTOR);
    }

    public Battalion getHorses() {
        return horses;
    }

    public Battalion getElephants() {
        return elephants;
    }

    public Battalion getTanks() {
        return tanks;
    }

    public Battalion getSlingGuns() {
        return slingGuns;
    }

    @Override
    public boolean equals(Object obj) {
        Army compareTo = (Army) obj;
        return this.horses.total() == compareTo.getHorses().total() &&
                this.elephants.total() == compareTo.elephants.total() &&
                this.tanks.total() == compareTo.tanks.total() &&
                this.slingGuns.total() == compareTo.slingGuns.total();
    }

    public String handleAttack(Army attackingArmy) {

        calculateArmyNeededToCounter(attackingArmy);

        tryToBalanceBattalions(null, this.horses, this.elephants);
        tryToBalanceBattalions(this.horses, this.elephants, this.tanks);
        tryToBalanceBattalions(this.elephants, this.tanks, this.slingGuns);
        tryToBalanceBattalions(this.tanks, this.slingGuns, null);

        if (hasAnyOfTheBattalionsExceededItsForces()) {
            trySetBattalionsToMax();
            return formattedResultFor(Outcome.LOSS);
        }

        return formattedResultFor(Outcome.WIN);
    }

    private void calculateArmyNeededToCounter(Army attackingArmy) {
        this.horses.neededFor(attackingArmy.getHorses());
        this.elephants.neededFor(attackingArmy.getElephants());
        this.tanks.neededFor(attackingArmy.getTanks());
        this.slingGuns.neededFor(attackingArmy.getSlingGuns());
    }

    private void tryToBalanceBattalions(Battalion previous, Battalion current, Battalion next) {
        current.tryToBalancePrevious(previous);
        current.tryToBalanceNext(next);
    }

    private void trySetBattalionsToMax() {
        this.horses.trySetMax();
        this.elephants.trySetMax();
        this.tanks.trySetMax();
        this.slingGuns.trySetMax();
    }

    private boolean hasAnyOfTheBattalionsExceededItsForces() {
        return this.horses.hasExceeded() ||
                this.elephants.hasExceeded() ||
                this.tanks.hasExceeded() ||
                this.slingGuns.hasExceeded();
    }

    private String formattedResultFor(final Outcome result) {
        return String.format(Constants.OUTPUT_FORMAT, result.value(), (int) this.horses.needed(),
                (int) this.elephants.needed(), (int) this.tanks.needed(), (int) this.slingGuns.needed());
    }
}
