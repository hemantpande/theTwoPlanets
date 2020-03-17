package com.geektrust;

public class Army {

    private final Battalion horses;
    private final Battalion elephants;
    private final Battalion tanks;
    private final Battalion slingGuns;
    private double strength;

    public Army(final double horses, final double elephants, final double tanks, final double slingGuns) {
        this.horses = new Battalion(horses);
        this.elephants = new Battalion(elephants);
        this.tanks = new Battalion(tanks);
        this.slingGuns = new Battalion(slingGuns);
    }

    public void setStrength(final double strength) {
        this.strength = strength;
    }

    public double getHorses() {
        return horses.getTotal();
    }

    public double getElephants() {
        return elephants.getTotal();
    }

    public double getTanks() {
        return tanks.getTotal();
    }

    public double getSlingGuns() {
        return slingGuns.getTotal();
    }

    public Army setAttackingArmy(final Army attackingArmy) {
        this.horses.setNeeded(Math.ceil(attackingArmy.getHorses() / strength));
        this.elephants.setNeeded(Math.ceil(attackingArmy.getElephants() / strength));
        this.tanks.setNeeded(Math.ceil(attackingArmy.getTanks() / strength));
        this.slingGuns.setNeeded(Math.ceil(attackingArmy.getSlingGuns() / strength));

        return this;
    }

    public String handleAttack() {

        tryToBalanceBattalions(null, this.horses, this.elephants);
        tryToBalanceBattalions(this.horses, this.elephants, this.tanks);
        tryToBalanceBattalions(this.elephants, this.tanks, this.slingGuns);
        tryToBalanceBattalions(this.tanks, this.slingGuns, null);

        if (hasAnyOfTheBattalionsExceededItsForces()) {
            setBattalionsToMax();
            return formattedResultFor(Outcome.LOSS);
        }

        return formattedResultFor(Outcome.WIN);
    }

    private void setBattalionsToMax() {
        this.horses.setMax();
        this.elephants.setMax();
        this.tanks.setMax();
        this.slingGuns.setMax();
    }

    private boolean hasAnyOfTheBattalionsExceededItsForces() {
        return this.horses.hasExceeded() ||
                this.elephants.hasExceeded() ||
                this.tanks.hasExceeded() ||
                this.slingGuns.hasExceeded();
    }

    private void tryToBalanceBattalions(Battalion previous, Battalion current, Battalion next) {

        if (previous != null && current.hasExceeded()) {
            current.extraNeeded = current.needed - current.total;

            final double forceNeeded = current.extraNeeded * strength;
            if (forceNeeded + previous.needed < previous.total) {
                previous.needed += forceNeeded;
                current.needed -= current.extraNeeded;
            } else {
                final double howMuchCanWeGive = Math.ceil((previous.total - previous.needed) / strength);
                previous.needed += howMuchCanWeGive * strength;
                current.needed -= howMuchCanWeGive;
            }
        }
        if (next != null && current.hasExceeded()) {
            current.extraNeeded = current.needed - current.total;

            final double forceNeeded = Math.ceil(current.extraNeeded / strength);
            if (forceNeeded + next.needed < next.total) {
                next.needed += forceNeeded;
                current.needed -= current.extraNeeded;
            } else {
                final double howMuchCanWeGive = (next.total - next.needed) * strength;
                next.needed += Math.ceil(howMuchCanWeGive / strength);
                current.needed -= howMuchCanWeGive;
            }
        }
    }

    private String formattedResultFor(final Outcome result) {
        return String.format("%s %sH %sE %sAT %sSG", result.value(), (int) this.horses.needed,
                (int) this.elephants.needed, (int) this.tanks.needed, (int) this.slingGuns.needed);
    }
}
