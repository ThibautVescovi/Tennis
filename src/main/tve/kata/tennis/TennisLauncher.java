package main.tve.kata.tennis;

import main.tve.kata.tennis.core.Game;

import java.util.Scanner;

/**
 * This is the main class to launch a tennis match
 * @author Thibaut VESCOVI
 */
public class TennisLauncher {

    public static void main(String[] args) {
        System.out.println("Player one name: ");
        String playerOneName = new Scanner(System.in).nextLine();
        System.out.println("Player two name: ");
        String playerTwoName = new Scanner(System.in).nextLine();
        System.out.println("Press enter to start the game...");
        new Scanner(System.in).nextLine();

        Game game = new Game(playerOneName, playerTwoName);
        game.run();
    }
}
