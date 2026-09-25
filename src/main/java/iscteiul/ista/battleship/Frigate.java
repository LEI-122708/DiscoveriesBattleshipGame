package iscteiul.ista.battleship;

/**
 * Represents a Frigate ship in the Battleship game.
 * A Frigate is a linear ship that occupies 4 consecutive positions 
 * on the board, depending on its bearing.
 */
public class Frigate extends Ship {
    private static final Integer SIZE = 4;
    private static final String NAME = "Fragata";

    /**
     * Constructs a new Frigate at the specified position and bearing.
     *
     * @param bearing the direction the ship is facing (NORTH, SOUTH, EAST, or WEST)
     * @param pos     the starting position of the ship on the board
     * @throws IllegalArgumentException if the provided bearing is invalid or null
     */
    public Frigate(Compass bearing, IPosition pos) throws IllegalArgumentException {
        super(Frigate.NAME, bearing, pos);
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
                throw new IllegalArgumentException("ERROR! invalid bearing for the frigate");
        }
    }

    /**
     * Retrieves the size of the Frigate.
     *
     * @return the number of positions this ship occupies (4)
     */
    @Override
    public Integer getSize() {
        return Frigate.SIZE;
    }
}