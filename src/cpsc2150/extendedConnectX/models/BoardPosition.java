package cpsc2150.extendedConnectX.models;

/**
 * <p>The class keeps track of the position of
 * an individual cell for a board</p>
 *
 * @author Michael Gonzales
 * @version 1.0
 *
 * @invariant row {@code >=} 0 AND row {@code <=} 8 AND
 * col {@code >=} 0 AND col {@code <=} 6
 *
 */
public class BoardPosition {

    private final int row;
    private final int col;
    /**
     * This is the constructor for this class that initializes
     * the board position
     *
     * @param r The row number of the cell
     * @param c The column number of the cell
     *
     * @pre r {@code >=} 0 AND r {@code <=} 8 AND
     * c {@code >=} 0 AND col {@code <=} 6
     *
     * @post row = r AND col = c
     */
    public BoardPosition(int r, int c) {
        row = r;
        col = c;
    }
    /**
     * This method returns the row number
     *
     * @return row number on board
     *
     * @pre row {@code >=} 0 AND row {@code <=} 8
     *
     * @post getRow = #row
     *
     * @return row
     */
    public int getRow() {
        return row;
    }
    /**
     * This method returns the column number
     *
     * @return column number on board
     *
     * @pre col {@code >=} 0 AND col {@code <=} 6
     *
     * @post getCol = #col
     *
     * @return col
     */
    public int getCol() {
        return col;
    }
    /**
     * This method overrides the toString() method to
     * create a string in the format "<row>,<col>"
     *
     * @return string in format "<row>,<col>"
     *
     */
    @Override
    public String toString() {
        return getRow() + "," + getCol();
    }
    /**
     * This method overrides the equals() method inherited from the
     * Object class
     *
     * @param obj Object class
     *
     */
    @Override
    public boolean equals(Object obj) {
        final BoardPosition current = (BoardPosition) obj;
        return this.getRow() == current.getRow() && (this.getCol() == current.getCol());
    }

}
