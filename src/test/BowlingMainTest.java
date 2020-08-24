package test;

import main.BowlingMain;
import main.processors.RollProcessor;
import main.processors.ScoreProcessor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BowlingMainTest {
    @Mock
    RollProcessor mockRollProcessor;

    @Mock
    ScoreProcessor mockScoreProcessor;

    @InjectMocks
    BowlingMain bowlingMain;

    @Test
    void parseAndPrintFrames_works_correctly() {
        final String rolls20FilePath = "src/resources/rolls20.txt";
        final List<String> mockRawRolls = new ArrayList<>(
                List.of("5", "/", "5", "/", "5", "/", "5", "/", "5", "/", "5", "/", "5", "/", "5", "/", "5", "/", "5", "/", "5")
        );
        final List<Integer> mockScoredRolls = new ArrayList<>(
                List.of(5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5)
        );

        when(mockRollProcessor.readRollsFromFile(Mockito.anyString()))
                .thenReturn(mockRawRolls);
        when(mockRollProcessor.getScoreFor(Mockito.anyList()))
                .thenReturn(mockScoredRolls);
        when(mockScoreProcessor.calculateTotalScore(Mockito.anyList(), Mockito.anyInt()))
                .thenReturn(150);

        final String expectedFramesAndScores = "| f1 | f2 | f3 | f4 | f5 | f6 | f7 | f8 | f9 | f10 |\n" +
                "| 5, / | 5, / | 5, / | 5, / | 5, / | 5, / | 5, / | 5, / | 5, / | 5, /, 5 |\n" +
                "score: 150";
        final String actualFramesAndScores = bowlingMain.parseAndPrintFrames(rolls20FilePath);

        assertEquals(expectedFramesAndScores, actualFramesAndScores);
    }
}