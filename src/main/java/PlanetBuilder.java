public class PlanetBuilder {

    private String name;
    private Army army;
    private int strength = 1;

    public PlanetBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public PlanetBuilder withArmy(Army army) {
        this.army = army;
        return this;
    }

    public Planet build() {
        return new Planet(this.name, this.army, this.strength);
    }

    public PlanetBuilder withStrength(int strength) {
        this.strength = strength;
        return this;
    }
}
