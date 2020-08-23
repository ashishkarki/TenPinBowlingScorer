package main.entities;

/**
 * One frame out of 10 or how many number of roles
 */
public class RollFrame {
    private final String EMPTY_STR_NAME = "No Value";

    private String roll1;
    private String roll2;

    public RollFrame(String roll1) {
        this.roll1 = roll1;
        this.roll2 = EMPTY_STR_NAME;
    }

    public RollFrame(String roll1, String roll2) {
        this.roll1 = roll1;
        this.roll2 = roll2;
    }

    public String printFrame() {
        if (roll2 == EMPTY_STR_NAME) {
            return roll1;
        } else {
            return roll1 + ", " + roll2;
        }
    }
}