package src.main.tve.kata.tennis.core;

public enum Score {
    LOVE("0"),
    FIFTEEN("15"),
    THIRTY("30"),
    FORTY("40"),
    DEUCE("DEUCE"),
    ADVANTAGE("ADV");

    private String scoreLabel;

    Score(String scoreLabel) {
        this.scoreLabel = scoreLabel;
    }

    @Override
    public String toString() {
        return scoreLabel;
    }


}
