package test.processors;

import main.processors.ScoreProcessor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ScoreProcessorTest {

    ScoreProcessor scoreProcessor;

    @BeforeEach
    void setUp() {
        scoreProcessor = new ScoreProcessor();
    }

    @Test
    void calculateTotalScore_gets_correctScore() {
        final List<Integer> sampleScoredRolls1 = new ArrayList<>(
                List.of(7, 1, 5, 5, 2, 7, 4, 6, 0, 5, 8, 2, 8, 1, 4, 3, 2, 4, 5, 2)
        );
        final int whereTo1 = 18;
        final int expectedTotalScore1 = 91;

        final List<Integer> sampleScoredRolls2 = new ArrayList<>(
                List.of(0, 3, 5, 0, 9, 1, 2, 5, 3, 2, 4, 2, 3, 3, 4, 6, 10, 10, 2, 5)
        );
        final int whereTo2 = 17;
        final int expectedTotalScore2 = 103;

        assertTrue(
                scoreProcessor.calculateTotalScore(sampleScoredRolls1, whereTo1)
                        == expectedTotalScore1);
        assertTrue(
                scoreProcessor.calculateTotalScore(sampleScoredRolls2, whereTo2)
                        == expectedTotalScore2);
    }
}