package main.java.student;

import main.java.ias.Terminal;
import main.java.ias.TextAdventure;
import main.java.ias.TextAdventureException;

/**
 * Factory class with functions to generate a game and terminal.
 */
public final class Factory {
    
    private Factory() { }

    /**
     * Creates a new game-instance with an empty board of the given size.
     * @param name The name of the game
     * @param boardWidth the width of the board
     * @param boardHeight the height of the board
     * @return a fresh game instance
     * @throws TextAdventureException Think about the cases in which an exception is useful and implement it.
     */
    public static TextAdventure getGame(String name, int boardWidth, int boardHeight)
            throws TextAdventureException {
        TextAdventure textAdventure = new MyTextAdventure(name, boardWidth, boardHeight);
        return textAdventure;
    }

    /**
     * Creates a new terminal-instance to read user input and prompt messages.
     * @return a terminal instance
     */ 
    public static Terminal getTerminal() {
        Terminal terminal = new MyTerminal();
        return terminal;
    }
}
