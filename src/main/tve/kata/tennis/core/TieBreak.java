package src.main.tve.kata.tennis.core;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class TieBreak {

    // This map will manage Player and their score
    private Map<Player, Integer> ties = new HashMap<>();

    //Players
    private Player playerOne, playerTwo;

    private boolean winnerTie;

    public TieBreak(Player playerOne,Player playerTwo ){
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
        this.winnerTie = false;
        ties.put(playerOne,0);
        ties.put(playerTwo,0);
    }

    public Player run(){
        Player player = null;
        while(!winnerTie){
            changeScore(getPointWinner());
            checkWinner();

        }

        return playerOne;
    }


    /**
     * Determine witch player win this point
     * @return point winner
     */
    public Player getPointWinner() {
        return new Random().nextInt(2) + 1 == 1 ? this.playerOne : this.playerTwo;
    }

    public void changeScore(Player player) {
        ties.put(player,ties.get(player) + 1);

    }

    public void checkWinner(){
       // if(ties.get(playerOne) == )
    }

    public boolean isWinnerTie() {
        return winnerTie;
    }

    public void setWinnerTie(boolean winnerTie) {
        this.winnerTie = winnerTie;
    }

}
