package main.processors;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Read the rolls from file and get their actual scores etc
 */
public class RollProcessor {
    public List<Integer> getScoreFor(List<String> rawRolls) {
        List<Integer> scoredRolls = new ArrayList<>();

        for (int i = 0; i < rawRolls.size(); i++) {
            scoredRolls.add(getRollScore(rawRolls, i));
        }

        return scoredRolls;
    }

    public Integer getRollScore(List<String> rawRolls, int rollIndex) {
        int scoreValue = -1;

        final String roll = rawRolls.get(rollIndex);
        if (roll.equals("X")) { // strike
            scoreValue = 10;
        } else if (roll.equals("/")) { // spare
            scoreValue = 10 - getRollScore(rawRolls, rollIndex - 1);
        } else if (roll.equals("-")) { // miss
            scoreValue = 0;
        } else {
            scoreValue = Integer.valueOf(roll);
        }

        return scoreValue;
    }

    public List<String> readRollsFromFile(String filePath) {
        Scanner scanner = null;
        List<String> rolls = new ArrayList<>();

        try {
            scanner = new Scanner(new File(filePath));
            scanner.useDelimiter(",");

            while (scanner.hasNext()) {
                rolls.add(scanner.next().trim());
            }
        } catch (FileNotFoundException fnfe) {
            System.out.println("Bad File name: " + fnfe.getMessage());
        } finally {
            // close the scanner
            if (scanner != null) {
                scanner.close();
            }
        }

        return rolls;
    }
}
