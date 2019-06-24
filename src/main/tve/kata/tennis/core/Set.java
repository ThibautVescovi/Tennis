package src.main.tve.kata.tennis.core;

import java.util.HashMap;
import java.util.Map;

public class Set {

    // This map will manage Player and their score
    private Map<Player, Integer> sets = new HashMap<>();

    //Players
    private Player playerOne, playerTwo;

    //Tie break
    private boolean tieBreak;

    // Constructor
    public Set(Player playerOne,Player playerTwo,Map<Player, Integer> sets){
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
        this.sets = sets;
    }

    /**
     * Add a set point to a player
     * @param player the player who win the set
     */
    public boolean addPoint(Player player){
        sets.put(player, sets.get(player) + 1);
        return checkTieBreakOrWin(player);
    }

    /**
     * Tell if there is a winner for this set
     * @return true if there is a set winner
     */
    public boolean isWinnerSet(){
        return playerOne.isWinnerSet() || playerTwo.isWinnerSet();
    }

    /**
     * Get the player winnner
     * @return The player who win the game
     */
    public Player getWinnerSet(){
        return playerOne.isWinnerSet() ? playerOne : playerTwo;
    }

    /**
     * Get a player, return his opponent
     * @param player the player
     * @return the opponent
     */
    public Player opponent(Player player) {
        return player.equals(playerOne) ? playerTwo : playerOne;
    }

    /**
     * Check if the set is done or if a tie break is needed
     * @param player
     */
    private boolean checkTieBreakOrWin(Player player){
        if(sets.get(player) == 6){
            if(sets.get(opponent(player)) <= 4){
                player.setWinnerSet(true);
                return true;
            }
        }else if(sets.get(player) == 7){
            player.setWinnerSet(true);
            return true;
        }
        return false;
    }

    /**
     * Tell if there is a tiebreak
     * @return
     */
    public boolean isTieBreak() {
        return tieBreak;
    }

    public Map<Player, Integer> getSets() {
        return sets;
    }

    public void setSets(Map<Player, Integer> sets) {
        this.sets = sets;
    }

}
