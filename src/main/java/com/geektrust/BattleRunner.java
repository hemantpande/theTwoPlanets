
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
                .withName("Falicornia")
                .withArmy(new Army(300, 200, 40, 20))
                .build();

        Planet lengaburu = new PlanetBuilder()
                .withName("Lengaburu")
                .withArmy(new Army(100, 50, 10, 5))
                .withStrength(2)
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
                System.out.println("Input is not in expected format");
            }
        }
    }

    private static Army getArmyFromInputText(String input) {
        String[] partsOfInputString = input.split(" ");
        try {

            double horses = Double.parseDouble(partsOfInputString[1].replace("H", ""));
            double elephants = Double.parseDouble(partsOfInputString[2].replace("E", ""));
            double tanks = Double.parseDouble(partsOfInputString[3].replace("AT", ""));
            double slingGuns = Double.parseDouble(partsOfInputString[4].replace("SG", ""));

            return new Army(horses, elephants, tanks, slingGuns);
        } catch (IndexOutOfBoundsException | NumberFormatException exception) {
            // TODO : replace with Null-object pattern.
            return null;
        }
    }

    private static Scanner getScanner(String arg) {
        File inputFile = new File(arg);
        Scanner fileScanner = null;
        try {
            fileScanner = new Scanner(inputFile);
        } catch (FileNotFoundException e) {
            System.out.println("File is not found or is not accessible");
            System.exit(0);
        }
        return fileScanner;
    }

}
