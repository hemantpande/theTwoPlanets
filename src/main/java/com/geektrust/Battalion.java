package com.geektrust;

public class Battalion {

    protected double needed;
    protected double total;
    protected double extraNeeded;

    public Battalion(final double total) {
        this.total = total;
    }

    public double getTotal() {
        return total;
    }

    public void setNeeded(final double needed) {
        this.needed = needed;
    }

    public boolean hasExceeded() {
        return this.needed > this.total;
    }

    public void setMax() {
        if (this.needed > this.total)
            this.needed = this.total;
    }
}
