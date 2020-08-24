package test.processors;

import main.processors.RollProcessor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class RollProcessorTest {
    final String rolls20FilePath = "src/resources/rolls20.txt";
    final String rollsAllStrikesFilePath = "src/resources/rollsAllStrikes.txt";
    final String rolls9AndMissesFilePath = "src/resources/rolls9AndMisses.txt";
    final String rolls5AndSparesFilePath = "src/resources/rolls5AndSpares.txt";

    RollProcessor rollProcessor;

    @BeforeEach
    void setUp() {
        rollProcessor = new RollProcessor();
    }

    @Test
    void getScoreFor_gets_integer_scores() {
        final List<String> sampleRawRolls = new ArrayList<>(
                List.of("X", "8", "/", "-", "9", "1")
        );
        final List<Integer> expectedScoredRolls = new ArrayList<>(
                List.of(10, 8, 2, 0, 9, 1)
        );

        List<Integer> scoredRolls = rollProcessor.getScoreFor(sampleRawRolls);

        assertArrayEquals(expectedScoredRolls.toArray(), scoredRolls.toArray());
    }

    @Test
    void readRollsFromFile_correctly_reads_inputs() {
        List<String> rolls20 = rollProcessor.readRollsFromFile(rolls20FilePath);
        assertEquals(rolls20.size(), 20);
        assertEquals(rolls20.get(0), "-");

        List<String> rollsAllStrikes = rollProcessor.readRollsFromFile(rollsAllStrikesFilePath);
        assertEquals(rollsAllStrikes.size(), 12);
        assertEquals(rollsAllStrikes.get(0), "X");

        List<String> rolls5AndSpares = rollProcessor.readRollsFromFile(rolls5AndSparesFilePath);
        assertEquals(rolls5AndSpares.size(), 21);
        assertEquals(rolls5AndSpares.get(0), "5");

        List<String> rolls9AndMisses = rollProcessor.readRollsFromFile(rolls9AndMissesFilePath);
        assertEquals(rolls9AndMisses.size(), 20);
        assertEquals(rolls9AndMisses.get(0), "9");
    }
}