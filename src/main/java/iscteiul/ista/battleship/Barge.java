/**
 *
 */
package iscteiul.ista.battleship;

/**
 * A barge ("Barca"), the smallest ship in the fleet.
 * <p>
 * A barge occupies a single cell of the board, so its bearing does not
 * affect which positions it covers.
 *
 * @see Ship
 */
public class Barge extends Ship {
    /** Number of board cells occupied by a barge. */
    private static final Integer SIZE = 1;
    /** Category name of this ship, as used by {@link IShip#getCategory()}. */
    private static final String NAME = "Barca";

    /**
     * Creates a barge at the given position.
     *
     * @param bearing the barge bearing; kept for consistency with other ships,
     *                it does not change the occupied cell
     * @param pos     the position of the single cell occupied by the barge
     */
    public Barge(Compass bearing, IPosition pos) {
        super(Barge.NAME, bearing, pos);
        getPositions().add(new Position(pos.getRow(), pos.getColumn()));
    }

    /**
     * Returns the number of cells occupied by a barge.
     *
     * @return always {@code 1}
     */
    @Override
    public Integer getSize() {
        return SIZE;
    }

}
