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

        if (horsesNeeded > this.army.getHorses()) {
            double extraHorsesNeeded = horsesNeeded - this.army.getHorses();
            double extraElephantsNeeded = Math.ceil(extraHorsesNeeded / strength);

            if(extraElephantsNeeded + elephantsNeeded <= this.army.getElephants()){
                elephantsNeeded += extraElephantsNeeded;
                horsesNeeded -= extraHorsesNeeded;
            }else{
                win = false;
                horsesNeeded = this.army.getHorses();
            }
        }
        if (elephantsNeeded > this.army.getElephants()) {
            double extraElephantsNeeded = elephantsNeeded - this.army.getElephants();
            double extraHorsesNeeded = extraElephantsNeeded * strength;
            double extraTanksNeeded = Math.ceil(extraElephantsNeeded / strength);

            if(extraHorsesNeeded + horsesNeeded <= this.army.getHorses()){
                horsesNeeded += extraHorsesNeeded;
                elephantsNeeded -= extraElephantsNeeded;
            }
            else if(extraTanksNeeded + tanksNeeded <= this.army.getTanks()){
                tanksNeeded += extraTanksNeeded;
                elephantsNeeded -= extraElephantsNeeded;
            }else{
                win = false;
                elephantsNeeded = this.army.getElephants();
            }
        }
        if (tanksNeeded > this.army.getTanks()) {
            double extraTanksNeeded = tanksNeeded - this.army.getTanks();
            double extraElephantsNeeded = extraTanksNeeded * strength;
            double extraSlingGunsNeeded = Math.ceil(extraTanksNeeded / strength);

            if(extraElephantsNeeded + elephantsNeeded < this.army.getElephants()){
                elephantsNeeded += extraElephantsNeeded;
                tanksNeeded -= extraTanksNeeded;
            }else if(extraSlingGunsNeeded + slingGunsNeeded <= this.army.getSlingGuns()){
                slingGunsNeeded += extraSlingGunsNeeded;
                tanksNeeded -= extraTanksNeeded;
            }else{
                win = false;
                tanksNeeded = this.army.getTanks();
            }
        }
        if (slingGunsNeeded > this.army.getSlingGuns()) {
            double extraSlingGunsNeeded = slingGunsNeeded - this.army.getSlingGuns();
            double extraTanksNeeded = extraSlingGunsNeeded * strength;

            if(tanksNeeded + extraTanksNeeded <= this.army.getTanks()){
                tanksNeeded += extraTanksNeeded;
                slingGunsNeeded -= extraSlingGunsNeeded;
            }else{
                win = false;
                slingGunsNeeded = this.army.getSlingGuns();
            }
        }

        if(win){
            return formattedResult("WINS", horsesNeeded, elephantsNeeded, tanksNeeded, slingGunsNeeded);
        }

        return formattedResult("LOSES", horsesNeeded, elephantsNeeded, tanksNeeded, slingGunsNeeded);
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
