package com.geektrust;

public class Battalion {

    private double needed;
    private double extraNeeded;
    private final int strengthFactor;
    private final double total;

    public Battalion(final double total, int strength) {
        this.total = total;
        this.strengthFactor = strength;
    }

    public double total() {
        return total;
    }

    public double needed() {
        return needed;
    }

    public void neededFor(final Battalion battalion) {
        this.needed = Math.ceil(battalion.total() / strengthFactor);
    }

    public boolean hasExceeded() {
        return this.needed > this.total;
    }

    public void trySetMax() {
        if (this.needed > this.total)
            this.needed = this.total;
    }

    public void tryToBalanceNext(Battalion next) {
        if (next != null && hasExceeded()) {
            extraNeeded = needed - total;

            final double forceNeeded = Math.ceil(extraNeeded / strengthFactor);
            if (forceNeeded + next.needed < next.total) {
                next.needed += forceNeeded;
                needed -= extraNeeded;
            } else {
                final double howMuchCanWeGive = (next.total - next.needed) * strengthFactor;
                next.needed += Math.ceil(howMuchCanWeGive / strengthFactor);
                needed -= howMuchCanWeGive;
            }
        }
    }

    public void tryToBalancePrevious(Battalion previous) {
        if (previous != null && hasExceeded()) {
            extraNeeded = needed - total;

            final double forceNeeded = extraNeeded * strengthFactor;
            if (forceNeeded + previous.needed < previous.total) {
                previous.needed += forceNeeded;
                needed -= extraNeeded;
            } else {
                final double howMuchCanWeGive = Math.ceil((previous.total - previous.needed) / strengthFactor);
                previous.needed += howMuchCanWeGive * strengthFactor;
                needed -= howMuchCanWeGive;
            }
        }
    }
}
