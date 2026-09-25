package iscteiul.ista;

import iscteiul.ista.battleship.Fleet;
import iscteiul.ista.battleship.Tasks;

/**
 * Main application class serving as the entry point for the Battleship game.
 * Controls the primary execution flow by triggering configured game tasks.
 *
 * @author britoeabreu
 * @author adrianolopes
 * @author miguelgoulao
 * @version 1.0
 */
public class App
{
    /**
     * Main method that launches the application.
     *
     * @param args command-line arguments passed during startup
     */
    public static void main( String[] args )
    {
        System.out.printf("\n***  Battleship Game ***\n");

        // Tasks.taskA();
        Tasks.taskB();
        // Tasks.taskC();
        // Tasks.taskD();
    }
}