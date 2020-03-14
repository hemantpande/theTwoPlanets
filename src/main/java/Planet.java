public class Planet {

    private final String name;
    private final Army army;
    private double strength;

    public Planet(String name, Army army, double strength) {
        this.name = name;
        this.army = army;
        this.strength = strength;
    }

    public String handle(Army attackingArmy) {
        double horsesNeeded = Math.ceil(attackingArmy.getHorses() / strength);
        double elephantsNeeded = Math.ceil(attackingArmy.getElephants() / strength);
        double tanksNeeded = Math.ceil(attackingArmy.getTanks() / strength);
        double slingGunsNeeded = Math.ceil(attackingArmy.getSlingGuns() / strength);
        boolean win = true;

        Battalion horses = new Battalion(horsesNeeded, this.army.getHorses(), "horses");
        Battalion elephants = new Battalion(elephantsNeeded, this.army.getElephants(), "elephants");
        Battalion tanks = new Battalion(tanksNeeded, this.army.getTanks(), "tanks");
        Battalion slingGuns = new Battalion(slingGunsNeeded, this.army.getSlingGuns(), "slingGuns");

        win = decideWhichWayToGo(null, horses, elephants);
        win = decideWhichWayToGo(horses, elephants, tanks);
        win = decideWhichWayToGo(elephants, tanks, slingGuns);
        win = decideWhichWayToGo(tanks, slingGuns, null);
        
        if(win){
            return formattedResult("WINS", horses.needed, elephants.needed, tanks.needed, slingGuns.needed);
        }

        return formattedResult("LOSES", horses.needed, elephants.needed, tanks.needed, slingGuns.needed);
    }

    private boolean decideWhichWayToGo(Battalion previous, Battalion current, Battalion next){
        boolean win = true;
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
                win = false;
                current.needed = current.total;
            }
        }

        return win;
    }

    private String formattedResult(String result, double horses, double elephants, double tanks, double slingGuns) {
        return String.format("%s %sH %sE %sAT %sSG",
                result,
                (int) horses,
                (int) elephants,
                (int) tanks,
                (int) slingGuns);
    }
}
