package com.geektrust;

public class Army {

    private final Battalion horses;
    private final Battalion elephants;
    private final Battalion tanks;
    private final Battalion slingGuns;
    private double strength;

    public Army(double horses, double elephants, double tanks, double slingGuns) {
        this.horses = new Battalion(horses);
        this.elephants = new Battalion(elephants);
        this.tanks = new Battalion(tanks);
        this.slingGuns = new Battalion(slingGuns);
    }

    public void setStrength(double strength) {
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

    public void setAttackingArmy(Army attackingArmy) {
        this.horses.setNeeded(Math.ceil(attackingArmy.getHorses() / strength));
        this.elephants.setNeeded(Math.ceil(attackingArmy.getElephants() / strength));
        this.tanks.setNeeded(Math.ceil(attackingArmy.getTanks() / strength));
        this.slingGuns.setNeeded(Math.ceil(attackingArmy.getSlingGuns() / strength));
    }

    public String handleAttack() {
        boolean balanced = true;
        balanced = tryToBalanceBattalions(null, this.horses, this.elephants);
        balanced = tryToBalanceBattalions(this.horses, this.elephants, this.tanks);
        balanced = tryToBalanceBattalions(this.elephants, this.tanks, this.slingGuns);
        balanced = tryToBalanceBattalions(this.tanks, this.slingGuns, null);

        if(balanced){
            return formattedResult("WINS");
        }

        return formattedResult("LOSES");
    }

    private boolean tryToBalanceBattalions(Battalion previous, Battalion current, Battalion next){
        boolean balanced = true;
        if (current.needed > current.total) {
            current.extraNeeded = current.needed - current.total;

            if(previous != null &&
                    ((current.extraNeeded * strength) + previous.needed <= previous.total)){
                previous.extraNeeded = current.extraNeeded * strength;
                previous.needed += previous.extraNeeded;
                current.needed -= current.extraNeeded;
            }
            else if(next != null &&
                    ((Math.ceil(current.extraNeeded / strength)) + next.needed <= next.total)){
                next.extraNeeded = Math.ceil(current.extraNeeded / strength);
                next.needed += next.extraNeeded;
                current.needed -= current.extraNeeded;
            }else{
                balanced = false;
                current.needed = current.total;
            }
        }

        return balanced;
    }

    private String formattedResult(String result) {
        return String.format("%s %sH %sE %sAT %sSG", result, (int) this.horses.needed,
                (int) this.elephants.needed, (int) this.tanks.needed, (int) this.slingGuns.needed);
    }
}
