package iscteiul.ista.battleship;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Abstract class representing a generic ship in the Battleship game.
 * Defines the core structure of a vessel, including its category, bearing,
 * reference position, and the set of board positions it occupies.
 *
 * @author Battleship Team
 * @version 1.0
 */
public abstract class Ship implements IShip {

    /** Constant identifying the Galleon ship type. */
    private static final String GALEAO = "galeao";

    /** Constant identifying the Frigate ship type. */
    private static final String FRAGATA = "fragata";

    /** Constant identifying the Carrack ship type. */
    private static final String NAU = "nau";

    /** Constant identifying the Caravel ship type. */
    private static final String CARAVELA = "caravela";

    /** Constant identifying the Barge ship type. */
    private static final String BARCA = "barca";

    /** Category/name of the vessel. */
    private String category;

    /** Orientation/bearing of the vessel on the board. */
    private Compass bearing;

    /** Initial or reference position of the vessel. */
    private IPosition pos;

    /** List of all board positions occupied by this vessel. */
    protected List<IPosition> positions;

    /**
     * Factory method that constructs and instantiates a concrete ship instance matching the requested type.
     *
     * @param shipKind the category name of the ship (e.g., "barca", "caravela", "nau", "fragata", "galeao")
     * @param bearing  the orientation/bearing of the ship
     * @param pos      the starting position of the ship
     * @return a concrete instance of {@link Ship}, or {@code null} if the type is unknown
     */
    static Ship buildShip(String shipKind, Compass bearing, Position pos) {
        Ship s;
        switch (shipKind) {
            case BARCA:
                s = new Barge(bearing, pos);
                break;
            case CARAVELA:
                s = new Caravel(bearing, pos);
                break;
            case NAU:
                s = new Carrack(bearing, pos);
                break;
            case FRAGATA:
                s = new Frigate(bearing, pos);
                break;
            case GALEAO:
                s = new Galleon(bearing, pos);
                break;
            default:
                s = null;
        }
        return s;
    }

    /**
     * Base constructor for concrete ship subclasses.
     *
     * @param category the category/name of the ship
     * @param bearing  the orientation/bearing of the ship (must not be {@code null})
     * @param pos      the initial/reference position of the ship (must not be {@code null})
     */
    public Ship(String category, Compass bearing, IPosition pos) {
        assert bearing != null;
        assert pos != null;

        this.category = category;
        this.bearing = bearing;
        this.pos = pos;
        positions = new ArrayList<>();
    }

    /**
     * Retrieves the category name of the ship.
     *
     * @return the ship category
     */
    @Override
    public String getCategory() {
        return category;
    }

    /**
     * Retrieves the list of board positions occupied by the ship.
     *
     * @return a list of {@link IPosition} objects
     */
    public List<IPosition> getPositions() {
        return positions;
    }

    /**
     * Retrieves the initial/reference position of the ship.
     *
     * @return the reference {@link IPosition}
     */
    @Override
    public IPosition getPosition() {
        return pos;
    }

    /**
     * Retrieves the orientation/bearing of the ship.
     *
     * @return the bearing as a {@link Compass} enum
     */
    @Override
    public Compass getBearing() {
        return bearing;
    }

    /**
     * Checks whether the ship is still floating (i.e., has at least one position
     * that has not been hit yet).
     *
     * @return {@code true} if at least one position remains unhit; {@code false} if the ship is sunk
     */
    @Override
    public boolean stillFloating() {
        for (int i = 0; i < getSize(); i++)
            if (!getPositions().get(i).isHit())
                return true;
        return false;
    }

    /**
     * Retrieves the topmost row coordinate (smallest row index) occupied by the ship.
     *
     * @return the minimum row index among the ship's positions
     */
    @Override
    public int getTopMostPos() {
        int top = getPositions().get(0).getRow();
        for (int i = 1; i < getSize(); i++)
            if (getPositions().get(i).getRow() < top)
                top = getPositions().get(i).getRow();
        return top;
    }

    /**
     * Retrieves the bottommost row coordinate (largest row index) occupied by the ship.
     *
     * @return the maximum row index among the ship's positions
     */
    @Override
    public int getBottomMostPos() {
        int bottom = getPositions().get(0).getRow();
        for (int i = 1; i < getSize(); i++)
            if (getPositions().get(i).getRow() > bottom)
                bottom = getPositions().get(i).getRow();
        return bottom;
    }

    /**
     * Retrieves the leftmost column coordinate (smallest column index) occupied by the ship.
     *
     * @return the minimum column index among the ship's positions
     */
    @Override
    public int getLeftMostPos() {
        int left = getPositions().get(0).getColumn();
        for (int i = 1; i < getSize(); i++)
            if (getPositions().get(i).getColumn() < left)
                left = getPositions().get(i).getColumn();
        return left;
    }

    /**
     * Retrieves the rightmost column coordinate (largest column index) occupied by the ship.
     *
     * @return the maximum column index among the ship's positions
     */
    @Override
    public int getRightMostPos() {
        int right = getPositions().get(0).getColumn();
        for (int i = 1; i < getSize(); i++)
            if (getPositions().get(i).getColumn() > right)
                right = getPositions().get(i).getColumn();
        return right;
    }

    /**
     * Checks if the ship occupies the specified position.
     *
     * @param pos the position to test (must not be {@code null})
     * @return {@code true} if the position is occupied by this ship; {@code false} otherwise
     */
    @Override
    public boolean occupies(IPosition pos) {
        assert pos != null;

        for (int i = 0; i < getSize(); i++)
            if (getPositions().get(i).equals(pos))
                return true;
        return false;
    }

    /**
     * Checks if this ship is placed too close (adjacent) to another ship.
     *
     * @param other the other ship to check against (must not be {@code null})
     * @return {@code true} if any position of this ship is adjacent to the other ship; {@code false} otherwise
     */
    @Override
    public boolean tooCloseTo(IShip other) {
        assert other != null;

        Iterator<IPosition> otherPos = other.getPositions().iterator();
        while (otherPos.hasNext())
            if (tooCloseTo(otherPos.next()))
                return true;

        return false;
    }

    /**
     * Checks if this ship is placed too close (adjacent) to a given target position.
     *
     * @param pos the position to test
     * @return {@code true} if any position of this ship is adjacent to the target position; {@code false} otherwise
     */
    @Override
    public boolean tooCloseTo(IPosition pos) {
        for (int i = 0; i < this.getSize(); i++)
            if (getPositions().get(i).isAdjacentTo(pos))
                return true;
        return false;
    }

    /**
     * Fires a shot at a target position. If the ship occupies this position,
     * that specific position is marked as hit.
     *
     * @param pos the position targeted by the shot (must not be {@code null})
     */
    @Override
    public void shoot(IPosition pos) {
        assert pos != null;

        for (IPosition position : getPositions()) {
            if (position.equals(pos))
                position.shoot();
        }
    }

    /**
     * Returns a string representation of the essential ship attributes.
     *
     * @return a {@link String} describing the category, bearing, and reference position
     */
    @Override
    public String toString() {
        return "[" + category + " " + bearing + " " + pos + "]";
    }
}