package main;

import main.processors.RollProcessor;
import main.processors.ScoreProcessor;

import java.util.List;

public class BowlingMain {
    private RollProcessor rollProcessor;
    private ScoreProcessor scoreProcessor;

    public BowlingMain(RollProcessor rp, ScoreProcessor sp) {
        rollProcessor = rp;
        scoreProcessor = sp;
    }

    public static void main(String[] args) {
        BowlingMain bowlingMain = new BowlingMain(new RollProcessor(), new ScoreProcessor());
        final String formattedOutput = bowlingMain.parseAndPrintFrames(args[0]);
        System.out.println(formattedOutput);
    }

    public String parseAndPrintFrames(String filePath) {
        StringBuilder stringBuilder = new StringBuilder();

        // first read 1-line of roll from a file including strikes ('X'), spares ('/') and misses ('-')
        List<String> rawRolls = rollProcessor.readRollsFromFile(filePath);
        List<Integer> scoredRolls = rollProcessor.getScoreFor(rawRolls);

        stringBuilder.append("| f1 | f2 | f3 | f4 | f5 | f6 | f7 | f8 | f9 | f10 |\n");
        stringBuilder.append("| ");

        int lastFrameStart = getLastFrameStartIndex(scoredRolls);

        for (int i = 0; i < lastFrameStart; i++) {
            String rawRoll = rawRolls.get(i);

            if (rawRoll.equals("X")) {
                stringBuilder.append(rawRoll + " | ");
            } else {
                stringBuilder.append(rawRoll + ", " + rawRolls.get(i + 1) + " | ");
                i += 1;
            }
        }

        for (int i = lastFrameStart; i < rawRolls.size(); i++) {
            stringBuilder.append(rawRolls.get(i));
            if (i != rawRolls.size() - 1) {
                stringBuilder.append(", ");
            }
        }

        if (lastFrameStart != rawRolls.size()) {
            stringBuilder.append(" |");
        }

        stringBuilder.append("\nscore: " + scoreProcessor.calculateTotalScore(scoredRolls, lastFrameStart));

        return stringBuilder.toString();
    }

    private int getLastFrameStartIndex(List<Integer> scoredRolls) {
        final int rollSize = scoredRolls.size();
        int lastFrameStart = rollSize; // where does the last frame start at
        if (scoredRolls.get(rollSize - 2 - 1) == 10 ||
                (scoredRolls.get(rollSize - 3) + scoredRolls.get(rollSize - 2)) == 10) {
            // if there is a strike at 3rd last position, that is where the last frame starts
            // if the 3rd + 2nd last score = 10 i.e. spare, then that is where the last frame starts
            lastFrameStart = rollSize - 3;
        }

        return lastFrameStart;
    }
}

