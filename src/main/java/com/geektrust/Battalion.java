package com.geektrust;

public class Battalion {

    protected double needed;
    protected double total;
    protected double extraNeeded;

    public Battalion(double needed, double total) {
        this.needed = needed;
        this.total = total;
    }

    public Battalion(double total) {
        this.total = total;
    }

    public double getNeeded() {
        return needed;
    }

    public double getTotal() {
        return total;
    }

    public double getExtraNeeded() {
        return needed - total;
    }

    public void setNeeded(double needed) {
        this.needed = needed;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public void setExtraNeeded(double extraNeeded) {
        this.extraNeeded = extraNeeded;
    }
}
