
package com.geektrust;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class BattleRunner {

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Please provide input file to process");
            System.exit(0);
        }

        Scanner fileScanner = getScanner(args[0]);

        Planet falicornia = new PlanetBuilder()
                .withName(Constants.PLANET_FALICORNIA)
                .withHorses(300)
                .withElephants(200)
                .withTanks(40)
                .withSlingGuns(20)
                .build();

        Planet lengaburu = new PlanetBuilder()
                .withName(Constants.PLANET_LENGABURU)
                .withHorses(100)
                .withElephants(50)
                .withTanks(10)
                .withSlingGuns(5)
                .withStrength(Constants.STRENGTH_MULTIPLYING_FACTOR)
                .build();

        while (fileScanner.hasNextLine()) {
            final String input = fileScanner.nextLine();
            Army attackingArmy = getArmyFromInputText(input);
            if(attackingArmy != null){
                String result = new Battle()
                        .between(falicornia, lengaburu)
                        .withAttackingArmy(attackingArmy)
                        .result();
                System.out.println(result);
            } else{
                System.out.println("Input not in expected format");
            }
        }
    }

    private static Army getArmyFromInputText(final String input) {
        String[] partsOfInputString = input.split(" ");
        try {

            double horses = Double.parseDouble(partsOfInputString[1].replace("H", ""));
            double elephants = Double.parseDouble(partsOfInputString[2].replace("E", ""));
            double tanks = Double.parseDouble(partsOfInputString[3].replace("AT", ""));
            double slingGuns = Double.parseDouble(partsOfInputString[4].replace("SG", ""));

            return new Army(horses, elephants, tanks, slingGuns, Constants.DEFAULT_STRENGTH_FACTOR);
        } catch (IndexOutOfBoundsException | NumberFormatException exception) {
            // TODO : replace with Null-object pattern.
            return null;
        }
    }

    private static Scanner getScanner(final String arg) {
        File inputFile = new File(arg);
        Scanner fileScanner = null;
        try {
            fileScanner = new Scanner(inputFile);
        } catch (FileNotFoundException e) {
            System.out.println("File not found or is not accessible");
            System.exit(0);
        }
        return fileScanner;
    }

}
