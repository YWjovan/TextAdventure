package main.java.student;

import main.java.ias.Adventures;
import main.java.ias.GameStarter;
import main.java.ias.TextAdventure;
import main.java.ias.TextAdventureException;

/**
 * Main class, serves as an entry point to the program.
 */
public class Main {

    /**
     * Main method which serves as an entry to the program.
     * @param args terminal parameters
     */
    public static void main(String[] args) {
        try {
            // Add additional games to the Array
            TextAdventure[] games = {
                    Adventures.getFiremanGame(), Adventures.getLumberjackGame(), Adventures.getPokemonGame()
            };
            GameStarter starter = new GameStarter(games);
            starter.startGame();
        } catch (TextAdventureException e) {
            e.printStackTrace();
        }

    }
}
