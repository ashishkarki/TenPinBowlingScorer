package test;

import main.processors.RollProcessor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BowlingMainTest {
    RollProcessor rollProcessor;
    final String rolls20FilePath = "src/resources/rolls20.txt";

    @BeforeEach
    void setUp() {
        rollProcessor = new RollProcessor();
    }

    @Test
    void readRollsFromFile() {
        List<String> rollsList = rollProcessor.readRollsFromFile(rolls20FilePath);
        assertEquals(rollsList.size(), 20);
    }
}