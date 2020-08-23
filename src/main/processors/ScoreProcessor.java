package main.processors;

import java.util.List;

/**
 * Calculate scores
 */
public class ScoreProcessor {

    public int calculateTotalScore(List<Integer> scoredRolls, int whereTo) {
        int totalScoreSoFar = 0;
        int curFrameScore = 0;

        for (int i = 0; i < whereTo; i++) {
            final int curRollScore = scoredRolls.get(i);
            if (curRollScore == 10) { // strike
                // 10 for this strike + next 2 rolls' score
                curFrameScore = 10 + scoredRolls.get(i + 1) + scoredRolls.get(i + 2);
            } else if (curRollScore + scoredRolls.get(i + 1) == 10) { // spare
                curFrameScore = 10 + scoredRolls.get(i + 2);
                i += 1; // since we also accounted for second roll during this frame
            } else {
                curFrameScore = curRollScore + scoredRolls.get(i + 1);
                i += 1;
            }
            totalScoreSoFar += curFrameScore;
        }

        // deal with the last frame
        curFrameScore = 0;
        for (int i = whereTo; i < scoredRolls.size(); i++) {
            curFrameScore += scoredRolls.get(i);
        }
        totalScoreSoFar += curFrameScore;

        return totalScoreSoFar;
    }
}
