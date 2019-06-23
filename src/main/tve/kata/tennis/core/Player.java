package src.main.tve.kata.tennis.core;

public class Player {

    private String name;
    private boolean winner;

    public Player(String name) {
        this.setName(name);
        this.setWinner(false);
    }

    public boolean isWinner() {
        return winner;
    }

    public void setWinner(boolean winner) {
        this.winner = winner;
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
