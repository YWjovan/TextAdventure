package main.java.student;

import main.java.ias.Terminal;

import java.util.Scanner;

/**
 * Class which defines our design of terminal.
 */
public class MyTerminal implements Terminal {
    @Override
    public void promptInput(String input) {
        System.out.println(input);
    }

    @Override
    public String[] readInput() {
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        String[] res = str.split(" ");
        return res;
    }
}
