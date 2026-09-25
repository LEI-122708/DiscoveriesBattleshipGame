/**
 *
 */
package iscteiul.ista.battleship;

/**
 * A caravel ("Caravela"), a ship that occupies two consecutive cells.
 * <p>
 * Starting from the given position, the caravel extends downwards (increasing
 * row) when heading {@link Compass#NORTH NORTH} or {@link Compass#SOUTH SOUTH},
 * and to the right (increasing column) when heading {@link Compass#EAST EAST}
 * or {@link Compass#WEST WEST}.
 *
 * @see Ship
 */
public class Caravel extends Ship {
    /** Number of board cells occupied by a caravel. */
    private static final Integer SIZE = 2;
    /** Category name of this ship, as used by {@link IShip#getCategory()}. */
    private static final String NAME = "Caravela";

    /**
     * Creates a caravel and computes the cells it occupies.
     *
     * @param bearing the bearing where the caravel heads to
     * @param pos     the upper-left position of the caravel
     * @throws NullPointerException     if {@code bearing} is {@code null}
     * @throws IllegalArgumentException if {@code bearing} is not one of
     *                                  {@code NORTH}, {@code SOUTH},
     *                                  {@code EAST} or {@code WEST}
     */
    public Caravel(Compass bearing, IPosition pos) throws NullPointerException, IllegalArgumentException {
        super(Caravel.NAME, bearing, pos);

        if (bearing == null)
            throw new NullPointerException("ERROR! invalid bearing for the caravel");

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
                throw new IllegalArgumentException("ERROR! invalid bearing for the caravel");
        }

    }

    /**
     * Returns the number of cells occupied by a caravel.
     *
     * @return always {@code 2}
     */
    @Override
    public Integer getSize() {
        return SIZE;
    }

}
