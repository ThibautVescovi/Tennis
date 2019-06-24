package src.main.tve.kata.tennis.core;

public class Player {

    private String name;
    private boolean winnerGame;
    private boolean winnerSet;

    public Player(String name) {
        this.setName(name);
        this.setWinnerGame(false);
        this.setWinnerSet(false);
    }

    public boolean isWinnerGame() {
        return winnerGame;
    }

    public void setWinnerGame(boolean winnerGame) {
        this.winnerGame = winnerGame;
    }

    public boolean isWinnerSet() {
        return winnerSet;
    }

    public void setWinnerSet(boolean winnerSet) {
        this.winnerSet = winnerSet;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!Player.class.isAssignableFrom(obj.getClass())) {
            return false;
        }
        final Player other = (Player) obj;
        if (this.name.equals(other.getName()))
            return true;
        return false;
    }
}
