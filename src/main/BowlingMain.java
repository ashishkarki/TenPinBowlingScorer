package main;

import main.processors.RollProcessor;
import main.processors.ScoreProcessor;

import java.util.List;

public class BowlingMain {

    public static void main(String[] args) {
        RollProcessor rollProcessor = new RollProcessor();
        ScoreProcessor scoreProcessor = new ScoreProcessor();

        // first read 1-line of roll from a file
        // including strikes ('X'), spares ('/') and misses ('-')
        List<String> rawRolls = rollProcessor.readRollsFromFile(args[0]);
        List<Integer> scoredRolls = rollProcessor.getScoreFor(rawRolls);

        System.out.println("| f1 | f2 | f3 | f4 | f5 | f6 | f7 | f8 | f9 | f10 |");
        System.out.print("| ");

        final int rollSize = rawRolls.size();
        int whereTo = rollSize; // where does the last frame start at
        if (scoredRolls.get(rollSize - 2 - 1) == 10 ||
                (scoredRolls.get(rollSize - 3) + scoredRolls.get(rollSize - 2)) == 10) {
            // if there is a strike at 3rd last position, that is where the last frame starts
            // if the 3rd + 2nd last score = 10 i.e. spare, then that is where the last frame starts
            whereTo = rollSize - 3;
        }
        for (int i = 0; i < whereTo; i++) {
            String rawRoll = rawRolls.get(i);

            if (rawRoll.equals("X")) {
                System.out.print(rawRoll + " | ");
            } else {
                System.out.print(rawRoll + ", " + rawRolls.get(i + 1) + " | ");
                i += 1;
            }
        }
        for (int i = whereTo; i < rollSize; i++) {
            System.out.print(rawRolls.get(i) + " | ");
        }

        System.out.println("\nscore: " + scoreProcessor.calculateTotalScore(scoredRolls, whereTo));
    }
}

