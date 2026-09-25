/**
 *
 */
package iscteiul.ista.battleship;

import java.util.ArrayList;
import java.util.List;

/**
 * A fleet of ships placed on a {@value IFleet#BOARD_SIZE}x{@value IFleet#BOARD_SIZE} board.
 * <p>
 * Ships are only accepted if they fit entirely inside the board and do not
 * touch (or overlap) any ship already in the fleet. The fleet also offers
 * queries by category and floating state, and helpers to print its status.
 *
 * @see IFleet
 * @see IShip
 */
public class Fleet implements IFleet {
    /**
     * Prints each of the given ships to standard output, one per line.
     *
     * @param ships the list of ships to print
     */
    static void printShips(List<IShip> ships) {
        for (IShip ship : ships)
            System.out.println(ship);
    }

    // -----------------------------------------------------

    /** The ships that make up this fleet, in the order they were added. */
    private List<IShip> ships;

    /**
     * Creates an empty fleet.
     */
    public Fleet() {
        ships = new ArrayList<>();
    }

    /**
     * Returns the ships in this fleet.
     *
     * @return the live list of ships; changes to it affect the fleet
     */
    @Override
    public List<IShip> getShips() {
        return ships;
    }

    /**
     * Adds a ship to the fleet if it can be legally placed.
     * <p>
     * The ship is added only if the fleet is not over capacity, the ship lies
     * entirely inside the board, and it is not adjacent to or overlapping any
     * ship already in the fleet.
     *
     * @param s the ship to add
     * @return {@code true} if the ship was added, {@code false} otherwise
     */
    @Override
    public boolean addShip(IShip s) {
        boolean result = false;
        if ((ships.size() <= FLEET_SIZE) && (isInsideBoard(s)) && (!colisionRisk(s))) {
            ships.add(s);
            result = true;
        }
        return result;
    }

    /**
     * Returns the ships of a given category.
     *
     * @param category the category name to match exactly, such as
     *                 {@code "Nau"} or {@code "Caravela"}
     * @return a new list with the matching ships; empty if there are none
     */
    @Override
    public List<IShip> getShipsLike(String category) {
        List<IShip> shipsLike = new ArrayList<>();
        for (IShip s : ships)
            if (s.getCategory().equals(category))
                shipsLike.add(s);

        return shipsLike;
    }

    /**
     * Returns the ships that have not been sunk yet.
     *
     * @return a new list with the ships that are still floating
     */
    @Override
    public List<IShip> getFloatingShips() {
        List<IShip> floatingShips = new ArrayList<>();
        for (IShip s : ships)
            if (s.stillFloating())
                floatingShips.add(s);

        return floatingShips;
    }

    /**
     * Returns the ship occupying the given position.
     *
     * @param pos the board position to check
     * @return the ship at {@code pos}, or {@code null} if the cell is empty
     */
    @Override
    public IShip shipAt(IPosition pos) {
        for (int i = 0; i < ships.size(); i++)
            if (ships.get(i).occupies(pos))
                return ships.get(i);
        return null;
    }

    /**
     * Checks whether a ship lies entirely inside the board.
     *
     * @param s the ship to check
     * @return {@code true} if all of the ship's cells are on the board
     */
    private boolean isInsideBoard(IShip s) {
        return (s.getLeftMostPos() >= 0 && s.getRightMostPos() <= BOARD_SIZE - 1 && s.getTopMostPos() >= 0
                && s.getBottomMostPos() <= BOARD_SIZE - 1);
    }

    /**
     * Checks whether a ship would touch or overlap any ship in the fleet.
     *
     * @param s the ship to check
     * @return {@code true} if {@code s} is too close to an existing ship
     */
    private boolean colisionRisk(IShip s) {
        for (int i = 0; i < ships.size(); i++) {
            if (ships.get(i).tooCloseTo(s))
                return true;
        }
        return false;
    }


    /**
     * Prints the state of the fleet: all ships, the ships still floating, and
     * then the ships of each category, from galleons down to barges.
     */
    public void printStatus() {
        printAllShips();
        printFloatingShips();
        printShipsByCategory("Galeao");
        printShipsByCategory("Fragata");
        printShipsByCategory("Nau");
        printShipsByCategory("Caravela");
        printShipsByCategory("Barca");
    }

    /**
     * Prints all the ships of the fleet that belong to a given category.
     *
     * @param category the category of ships of interest; must not be
     *                 {@code null}
     */
    public void printShipsByCategory(String category) {
        assert category != null;

        printShips(getShipsLike(category));
    }

    /**
     * Prints all the ships of the fleet that have not been sunk yet.
     */
    public void printFloatingShips() {
        printShips(getFloatingShips());
    }

    /**
     * Prints all the ships of the fleet.
     */
    void printAllShips() {
        printShips(ships);
    }

}
