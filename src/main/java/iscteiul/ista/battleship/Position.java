package iscteiul.ista.battleship;

import java.util.Objects;

/**
 * Represents a position (cell) on the Battleship game board.
 * Each position is defined by its row and column coordinates,
 * and maintains states for whether it is occupied by a ship or has been hit by a shot.
 *
 * @author Battleship Team
 * @version 1.0
 */
public class Position implements IPosition {

    /** The row coordinate on the board. */
    private int row;

    /** The column coordinate on the board. */
    private int column;

    /** Indicates whether the position is occupied by a ship. */
    private boolean isOccupied;

    /** Indicates whether the position has been hit by a shot. */
    private boolean isHit;

    /**
     * Constructs a new position with the specified row and column coordinates.
     * By default, the position is initialized as not occupied and not hit.
     *
     * @param row    the row index of the position on the board
     * @param column the column index of the position on the board
     */
    public Position(int row, int column) {
        this.row = row;
        this.column = column;
        this.isOccupied = false;
        this.isHit = false;
    }

    /**
     * Retrieves the row number of this position.
     *
     * @return the row index
     */
    @Override
    public int getRow() {
        return row;
    }

    /**
     * Retrieves the column number of this position.
     *
     * @return the column index
     */
    @Override
    public int getColumn() {
        return column;
    }

    /**
     * Computes the hash code value for this position based on its attributes.
     *
     * @return the calculated hash code
     */
    @Override
    public int hashCode() {
        return Objects.hash(column, isHit, isOccupied, row);
    }

    /**
     * Compares this position to another object for equality.
     * Two positions are considered equal if they share the same row and column coordinates.
     *
     * @param otherPosition the object to be compared with this position
     * @return {@code true} if the object is an instance of {@link IPosition} and has
     *         matching row and column coordinates; {@code false} otherwise
     */
    @Override
    public boolean equals(Object otherPosition) {
        if (this == otherPosition)
            return true;
        if (otherPosition instanceof IPosition) {
            IPosition other = (IPosition) otherPosition;
            return (this.getRow() == other.getRow() && this.getColumn() == other.getColumn());
        } else {
            return false;
        }
    }

    /**
     * Checks whether this position is adjacent to another given position (including diagonals).
     *
     * @param other the position to check for adjacency against
     * @return {@code true} if both row and column differences are at most 1 unit; {@code false} otherwise
     */
    @Override
    public boolean isAdjacentTo(IPosition other) {
        return (Math.abs(this.getRow() - other.getRow()) <= 1 && Math.abs(this.getColumn() - other.getColumn()) <= 1);
    }

    /**
     * Marks this position as occupied by a ship.
     */
    @Override
    public void occupy() {
        isOccupied = true;
    }

    /**
     * Registers a shot targeting this position, marking it as hit.
     */
    @Override
    public void shoot() {
        isHit = true;
    }

    /**
     * Checks whether this position is currently occupied by a ship.
     *
     * @return {@code true} if occupied; {@code false} otherwise
     */
    @Override
    public boolean isOccupied() {
        return isOccupied;
    }

    /**
     * Checks whether this position has been hit by a shot.
     *
     * @return {@code true} if it has been hit; {@code false} otherwise
     */
    @Override
    public boolean isHit() {
        return isHit;
    }

    /**
     * Returns a string representation of this position.
     *
     * @return a {@link String} containing the row and column of the position
     */
    @Override
    public String toString() {
        return ("Linha = " + row + " Coluna = " + column);
    }
}