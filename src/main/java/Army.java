public class Army {

    private final double horses;
    private final double elephants;
    private final double tanks;
    private final double slingGuns;

    public double getHorses() {
        return horses;
    }

    public double getElephants() {
        return elephants;
    }

    public double getTanks() {
        return tanks;
    }

    public double getSlingGuns() {
        return slingGuns;
    }

    public Army(double horses, double elephants, double tanks, double slingGuns) {
        this.horses = horses;
        this.elephants = elephants;
        this.tanks = tanks;
        this.slingGuns = slingGuns;
    }
}
