package iscteiul.ista.battleship;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a single session of the Battleship game.
 * This class tracks the fleet of ships, processes the shots fired by the player,
 * and maintains statistics such as the number of hits, sinks, and invalid shots.
 */
public class Game implements IGame {
    private IFleet fleet;
    private List<IPosition> shots;

    private Integer countInvalidShots;
    private Integer countRepeatedShots;
    private Integer countHits;
    private Integer countSinks;

    /**
     * Constructs a new Game with a specific fleet.
     * Initializes shot tracking and statistical counters.
     *
     * @param fleet the fleet of ships deployed on the board
     */
    public Game(IFleet fleet) {
        shots = new ArrayList<>();
        countInvalidShots = 0;
        countRepeatedShots = 0;
        this.countHits = 0;
        this.countSinks = 0;
        this.fleet = fleet;
    }

    /**
     * Processes a single shot at the specified position.
     * Validates the shot, checks for duplicates, and updates ship status
     * and game statistics accordingly.
     *
     * @param pos the position on the board to fire at
     * @return the ship that was sunk by this shot, or {@code null} if no ship was sunk
     */
    @Override
    public IShip fire(IPosition pos) {
        if (!validShot(pos))
            countInvalidShots++;
        else { // valid shot!
            if (repeatedShot(pos))
                countRepeatedShots++;
            else {
                shots.add(pos);
                IShip s = fleet.shipAt(pos);
                if (s != null) {
                    s.shoot(pos);
                    countHits++;
                    if (!s.stillFloating()) {
                        countSinks++;
                        return s;
                    }
                }
            }
        }
        return null;
    }

    /**
     * Retrieves the list of all valid, non-repeated shots fired so far.
     *
     * @return a list of positions representing the valid shots
     */
    @Override
    public List<IPosition> getShots() {
        return shots;
    }

    /**
     * Retrieves the total number of repeated shots fired.
     *
     * @return the count of repeated shots
     */
    @Override
    public int getRepeatedShots() {
        return this.countRepeatedShots;
    }

    /**
     * Retrieves the total number of invalid shots fired (e.g., out of bounds).
     *
     * @return the count of invalid shots
     */
    @Override
    public int getInvalidShots() {
        return this.countInvalidShots;
    }

    /**
     * Retrieves the total number of successful hits on ships.
     *
     * @return the count of hits
     */
    @Override
    public int getHits() {
        return this.countHits;
    }

    /**
     * Retrieves the total number of ships that have been completely sunk.
     *
     * @return the count of sunk ships
     */
    @Override
    public int getSunkShips() {
        return this.countSinks;
    }

    /**
     * Retrieves the number of ships in the fleet that are still afloat.
     *
     * @return the count of remaining floating ships
     */
    @Override
    public int getRemainingShips() {
        List<IShip> floatingShips = fleet.getFloatingShips();
        return floatingShips.size();
    }

    /**
     * Verifies if a shot is within the valid boundaries of the game board.
     *
     * @param pos the position to check
     * @return {@code true} if the position is within bounds, {@code false} otherwise
     */
    private boolean validShot(IPosition pos) {
        return (pos.getRow() >= 0 && pos.getRow() <= Fleet.BOARD_SIZE && pos.getColumn() >= 0
                && pos.getColumn() <= Fleet.BOARD_SIZE);
    }

    /**
     * Verifies if a shot has already been fired at the given position.
     *
     * @param pos the position to check
     * @return {@code true} if the position was already targeted, {@code false} otherwise
     */
    private boolean repeatedShot(IPosition pos) {
        for (int i = 0; i < shots.size(); i++)
            if (shots.get(i).equals(pos))
                return true;
        return false;
    }

    /**
     * Renders a text-based representation of the board to the console, 
     * marking specific positions with a given character.
     *
     * @param positions a list of positions to mark on the board
     * @param marker    the character used to denote the given positions
     */
    public void printBoard(List<IPosition> positions, Character marker) {
        char[][] map = new char[Fleet.BOARD_SIZE][Fleet.BOARD_SIZE];

        for (int r = 0; r < Fleet.BOARD_SIZE; r++)
            for (int c = 0; c < Fleet.BOARD_SIZE; c++)
                map[r][c] = '.';

        for (IPosition pos : positions)
            map[pos.getRow()][pos.getColumn()] = marker;

        for (int row = 0; row < Fleet.BOARD_SIZE; row++) {
            for (int col = 0; col < Fleet.BOARD_SIZE; col++)
                System.out.print(map[row][col]);
            System.out.println();
        }
    }

    /**
     * Prints the game board to the console, highlighting all valid shots 
     * that have been fired with an 'X'.
     */
    public void printValidShots() {
        printBoard(getShots(), 'X');
    }

    /**
     * Prints the game board to the console, showing the complete layout 
     * of the fleet using the '#' character.
     */
    public void printFleet() {
        List<IPosition> shipPositions = new ArrayList<IPosition>();

        for (IShip s : fleet.getShips())
            shipPositions.addAll(s.getPositions());

        printBoard(shipPositions, '#');
    }
}