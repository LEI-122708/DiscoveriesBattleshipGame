package iscteiul.ista.battleship;

/**
 * Represents a Galleon ship in the Battleship game.
 * A Galleon occupies 5 positions and has a specific non-linear, 
 * block-like geometric shape depending on its bearing.
 */
public class Galleon extends Ship {
    private static final Integer SIZE = 5;
    private static final String NAME = "Galeao";

    /**
     * Constructs a new Galleon at the specified position and bearing.
     *
     * @param bearing the direction the ship is facing, which determines its shape orientation
     * @param pos     the anchor position used to generate the ship's coordinates
     * @throws IllegalArgumentException if the provided bearing is invalid
     * @throws NullPointerException     if the provided bearing is null
     */
    public Galleon(Compass bearing, IPosition pos) throws IllegalArgumentException {
        super(Galleon.NAME, bearing, pos);

        if (bearing == null)
            throw new NullPointerException("ERROR! invalid bearing for the galleon");

        switch (bearing) {
            case NORTH:
                fillNorth(pos);
                break;
            case EAST:
                fillEast(pos);
                break;
            case SOUTH:
                fillSouth(pos);
                break;
            case WEST:
                fillWest(pos);
                break;

            default:
                throw new IllegalArgumentException("ERROR! invalid bearing for the galleon");
        }
    }

    /**
     * Retrieves the size of the Galleon.
     *
     * @return the number of positions this ship occupies (5)
     */
    @Override
    public Integer getSize() {
        return Galleon.SIZE;
    }

    /**
     * Populates the positions of the Galleon when facing North.
     *
     * @param pos the starting anchor position
     */
    private void fillNorth(IPosition pos) {
        for (int i = 0; i < 3; i++) {
            getPositions().add(new Position(pos.getRow(), pos.getColumn() + i));
        }
        getPositions().add(new Position(pos.getRow() + 1, pos.getColumn() + 1));
        getPositions().add(new Position(pos.getRow() + 2, pos.getColumn() + 1));
    }

    /**
     * Populates the positions of the Galleon when facing South.
     *
     * @param pos the starting anchor position
     */
    private void fillSouth(IPosition pos) {
        for (int i = 0; i < 2; i++) {
            getPositions().add(new Position(pos.getRow() + i, pos.getColumn()));
        }
        for (int j = 2; j < 5; j++) {
            getPositions().add(new Position(pos.getRow() + 2, pos.getColumn() + j - 3));
        }
    }

    /**
     * Populates the positions of the Galleon when facing East.
     *
     * @param pos the starting anchor position
     */
    private void fillEast(IPosition pos) {
        getPositions().add(new Position(pos.getRow(), pos.getColumn()));
        for (int i = 1; i < 4; i++) {
            getPositions().add(new Position(pos.getRow() + 1, pos.getColumn() + i - 3));
        }
        getPositions().add(new Position(pos.getRow() + 2, pos.getColumn()));
    }

    /**
     * Populates the positions of the Galleon when facing West.
     *
     * @param pos the starting anchor position
     */
    private void fillWest(IPosition pos) {
        getPositions().add(new Position(pos.getRow(), pos.getColumn()));
        for (int i = 1; i < 4; i++) {
            getPositions().add(new Position(pos.getRow() + 1, pos.getColumn() + i - 1));
        }
        getPositions().add(new Position(pos.getRow() + 2, pos.getColumn()));
    }
}