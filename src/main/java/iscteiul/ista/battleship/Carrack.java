/**
 *
 */
package iscteiul.ista.battleship;

/**
 * A carrack ("Nau"), a ship that occupies three consecutive cells.
 * <p>
 * Starting from the given position, the carrack extends downwards (increasing
 * row) when heading {@link Compass#NORTH NORTH} or {@link Compass#SOUTH SOUTH},
 * and to the right (increasing column) when heading {@link Compass#EAST EAST}
 * or {@link Compass#WEST WEST}.
 *
 * @see Ship
 */
public class Carrack extends Ship {
    /** Number of board cells occupied by a carrack. */
    private static final Integer SIZE = 3;
    /** Category name of this ship, as used by {@link IShip#getCategory()}. */
    private static final String NAME = "Nau";

    /**
     * Creates a carrack and computes the cells it occupies.
     *
     * @param bearing the bearing where the carrack heads to
     * @param pos     the upper-left position of the carrack
     * @throws NullPointerException     if {@code bearing} is {@code null}
     * @throws IllegalArgumentException if {@code bearing} is not one of
     *                                  {@code NORTH}, {@code SOUTH},
     *                                  {@code EAST} or {@code WEST}
     */
    public Carrack(Compass bearing, IPosition pos) throws IllegalArgumentException {
        super(Carrack.NAME, bearing, pos);
        switch (bearing) {
            case NORTH:
            case SOUTH:
                for (int r = 0; r < SIZE; r++)
                    getPositions().add(new Position(pos.getRow() + r, pos.getColumn()));
                break;
            case EAST:
            case WEST:
                for (int c = 0; c < SIZE; c++)
                    getPositions().add(new Position(pos.getRow(), pos.getColumn() + c));
                break;
            default:
                throw new IllegalArgumentException("ERROR! invalid bearing for the carrack");
        }
    }

    /**
     * Returns the number of cells occupied by a carrack.
     *
     * @return always {@code 3}
     */
    @Override
    public Integer getSize() {
        return Carrack.SIZE;
    }

}
