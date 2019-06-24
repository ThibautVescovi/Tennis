package src.main.tve.kata.tennis.core;

import java.util.HashMap;
import java.util.Map;

/**
 * Match class manage all match including Game, Set and TieBreak
 */
public class Match {

    private Player playerOne, playerTwo;

    // This map will manage Player and their game score
    private Map<Player, Score> scores = new HashMap<>();

    // This map will manage Player and their set
    private Map<Player, Integer> sets = new HashMap<>();

    // This map will manage Player and their tie break score
    private Map<Player, Integer> ties = new HashMap<>();

    private Game game;

    private Set set;

    private TieBreak tie;

    private boolean endMatch = false;

    //Constructor
    public Match(String playerNameOne, String playerNameTwo) {
        initMatch(playerNameOne, playerNameTwo);
    }

    /**
     * Init score map with player and score set to 0
     *
     * @param playerNameOne player one name
     * @param playerNameTwo player two name
     */
    private void initMatch(String playerNameOne, String playerNameTwo) {
        playerOne = new Player(playerNameOne);
        playerTwo = new Player(playerNameTwo);
        initGame();
        initSet();
    }

    /**
     * Tennis Game Manager
     */
    public void run() {
        while (!endMatch) {
            displaySetScore();
            displayGameScore();
            game.changeScore(game.getPointWinner());
            if (game.isWinner()) {
                endMatch = set.addPoint(game.getWinner());
                if (set.isTieBreak()) {
                    initTie();
                    tie.run();
                    break;
                }
                initGame();
            }
        }
        displayWinScore();

    }


    /**
     * Display current game score
     */
    private void displayGameScore() {

        scores.forEach((player, score) -> {
            System.out.println(player + " [" + score + "]");

        });
        System.out.println(" ------- ");
    }

    /**
     * Display current set score
     */
    private void displaySetScore() {

        System.out.println("Score : " + sets.get(playerOne) + " / " + sets.get(playerTwo));
    }

    /**
     * Display current set score
     */
    private void displayWinScore() {

        System.out.println("Winner is : " + set.getWinnerSet().getName() + " - Score final :  " + sets.get(playerOne) + " / " + sets.get(playerTwo));
    }

    /**
     * Init a GAME
     */
    public void initGame() {
        scores.put(playerOne, Score.LOVE);
        scores.put(playerTwo, Score.LOVE);
        playerOne.setWinnerGame(false);
        playerTwo.setWinnerGame(false);
        game = new Game(playerOne, playerTwo, scores);
    }

    /**
     * Init a SET
     */
    public void initSet() {
        sets.put(playerOne, 0);
        sets.put(playerTwo, 0);
        set = new Set(playerOne, playerTwo, sets);
    }

    /**
     * Init a TIE BREAK
     */
    public void initTie() {
        ties.put(playerOne, 0);
        ties.put(playerTwo, 0);
        tie = new TieBreak(playerOne, playerTwo, ties);
    }

    // GETTERS & SETTERS

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Set getSet() {
        return set;
    }

    public void setSet(Set set) {
        this.set = set;
    }

    public TieBreak getTie() {
        return tie;
    }

    public void setTie(TieBreak tie) {
        this.tie = tie;
    }
}
