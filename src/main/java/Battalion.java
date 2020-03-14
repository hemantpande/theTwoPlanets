public class Battalion {

    public double needed;
    public double total;
    public double extraNeeded;
    public String name;

    public Battalion(double needed, double total, String name) {
        this.needed = needed;
        this.total = total;
        this.name = name;
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
}
