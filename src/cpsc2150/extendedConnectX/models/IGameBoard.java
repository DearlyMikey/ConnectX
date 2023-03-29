package cpsc2150.extendedConnectX.models;

/**
 * Interface for GameBoard classes that contains the default and primary methods
 *
 * Initialization ensures:
 *                      Every space in the GameBoard is a blank character/empty and the board is
 *                      MAXROWS x MAXCOLS
 *
 *  Defines: number_of_rows: Z
 *           number_of_columns: Z
 *           number_to_win: Z
 *
 * Constraints: 0 < number_of_rows <= MAXROWS
 *              0 < number_of_cols <= MAXCOLS
 */
public interface IGameBoard {
    public static final int MINROWS = 3;
    public static final int MINCOLS = 3;
    public static final int MINTOWIN = 3;
    public static final int MAXROWS = 100;
    public static final int MAXCOLS = 100;
    public static final int MAXTOWIN = 25;
    public static final int MINPLAYERS = 2;
    public static final int MAXPLAYERS = 10;


    /**
     * This method returns the number of rows in the GameBoard
     *
     * @post getNumRows() = MAXROWS
     *
     * @return Number of rows in the GameBoard
     */
    public int getNumRows();

    /**
     * This method returns the number of columns in the GameBoard
     *
     * @post getNumColumns() = MAXCOLS
     *
     * @return Number of columns in the GameBoard
     */
    public int getNumColumns();

    /**
     * This method returns the number of tokens in a row needed to win the game
     *
     * @post getNumToWin() = NUMTOWIN
     *
     * @return An int representing the number of consecutive tokens needed to win
     */
    public int getNumToWin();

    /**
     * This method checks if the column can take another token.
     *
     * @param c column number
     *
     * @return true if the column is free, otherwise false
     *
     * @pre 0 <= c <= MAXCOLS
     *
     * @post [true if the column has space for another token, false otherwise] AND
     *       self = #self
     *
     */
    public default boolean checkIfFree(int c) {
        for(int i = 0; i < getNumRows(); i++){
            BoardPosition pos = new BoardPosition(i,c);
            if(whatsAtPos(pos) == ' '){
                return true;
            }
        }
        return false;
    }

    /**
     * This method places the player token into
     * the lowest available row in column c
     *
     * @param p player token
     * @param c column
     *
     * @pre 0 <= c <= MAXCOLS AND
     * checkIfFree() == true AND p != null
     *
     * @post [token placed in lowest available row]
     *
     */
    public void placeToken(char p, int c);

    /**
     * This method checks if the latest token placed
     * results in a win
     *
     * @param c column number
     *
     * @pre c == [column where latest token placed] AND
     *      c >= MINCOLS and c <= MAXCOLS
     *
     * @post checkforWin(int c) = true() iff (checkVertWin([latest token placed]) == true OR
     *           checkHorizWin([latest token placed]) == true OR
     *           checkDiagWin([latest token placed]) == true,
     *           [game is won by latest player]) AND
     *           self = #self
     *
     * @return true or false depending on if latest token
     * placed results in a win
     */
    public default boolean checkForWin(int c) {

        for(int i = getNumRows() - 1; i >= 0; i--) {
            BoardPosition pos = new BoardPosition(i,c);
            if (whatsAtPos(pos) != ' ') {
                if(checkHorizWin(pos, whatsAtPos(pos)) || checkVertWin(pos, whatsAtPos(pos)) ||
                checkDiagWin(pos, whatsAtPos(pos))) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * This methods checks whether the game has resulted in a tie
     *
     * @return true if latest token placed results in a tie, false otherwise
     *
     * @pre [checkIfFree == false on all columns] AND
     *      [game doesn't have winner yet]
     *
     * @post checkTie() = true iff [none of positions in GameBoard are empty] AND
     *       self = #self
     */
    public default boolean checkTie() {
        for(int i = 0; i < MAXCOLS; i++) {
            if (checkIfFree(i)) {
                return false;
            }
        }
        return true;
    }

    /**
     * This method checks if the last token placed (which was
     * placed in position pos by player p) resulted in 5 in a row horizontally
     *
     * @param pos BoardPosition that holds [row][col] positions
     * @param p player token
     *
     * @return true if player has 5 token in a row horizontally,
     * otherwise false
     *
     * @pre pos = [GameBoard position on latest play] AND
     *      p = [player token placed at position] AND
     *      p != null AND [BoardPosition is valid] AND
     *      0 <= pos.getRow() <= MAXROWS AND
     *      0<= pos.getCol() <= MAXCOLS
     *
     * @post checkHorizWin(pos, p) = true iff
     * ([placed marker is the last to make up the maximum number of
     * same markers needed to win horizontally]) AND
     * self = #self
     *
     */
    public default boolean checkHorizWin(BoardPosition pos, char p) {
        int count = 1;
        int row = pos.getRow();
        int col = pos.getCol();

        // checks to the right
        for(int i = col + 1; i < getNumColumns(); i++) {
            BoardPosition right = new BoardPosition(row, i);
            if (isPlayerAtPos(right, p)) {
                count++;
                if (count == getNumToWin()) {
                    return true;
                }
            } else {
                count = 0;
            }
        }

        // checks to the left
        for(int j = col - 1; j >= 0; j--) {
            BoardPosition left = new BoardPosition(row, j);
            if(isPlayerAtPos(left,p)) {
                count++;
                if(count == getNumToWin()) {
                    return true;
                }
            } else {
                count = 0;
            }
        }
        return false;
    }

    /**
     * This method checks if the last token placed (which was
     * placed in position pos by player p) resulted in 5 in a row vertically
     *
     * @param pos BoardPosition that holds [row][col] positions
     * @param p player token
     *
     * @return true if player has 5 tokens in a row vertically,
     * otherwise false
     *
     * @pre pos = [GameBoard position on latest play] AND
     *      p = [player token placed at position] AND
     *      p != null AND [BoardPosition is valid] AND
     *      0 <= pos.getRow() <= MAXROW AND
     *      0<= pos.getCol() <= MAXCOL
     *
     * @post checkVertWin(pos, p) = true iff
     * ([placed marker is the last to make up the maximum number of
     * same markers needed to win vertically]) AND
     * self = #self
     */
    public default boolean checkVertWin(BoardPosition pos, char p) {
        int count = 1;
        int row = pos.getRow();
        int col = pos.getCol();

        for(int i = row - 1; i >= 0; i--) {
            BoardPosition temp = new BoardPosition(i,col);
            if(isPlayerAtPos(temp, p)) {
                count++;
                if(count == getNumToWin()) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * This method checks if the last token placed (which was
     * placed in position pos by player p) resulted in 5 in a row diagonally
     *
     * @param pos BoardPosition that holds [row][col] positions
     * @param p player token
     *
     * @return true if player has 5 token in a row diagonally, otherwise false
     *
     * @pre pos = [GameBoard position on latest play] AND
     *      p = [player token placed at position] AND
     *      p != null AND [BoardPosition is valid] AND
     *      0 <= pos.getRow() <= MAXROW AND
     *      0<= pos.getCol() <= MAXCOL
     *
     * @post checkDiagWin(pos, p) = true iff
     * ([placed marker is the last to make up the maximum number of
     * same markers needed to win diagonally]) AND
     * self = #self
     */
    public default boolean checkDiagWin(BoardPosition pos, char p) {
        int row = pos.getRow();
        int col = pos.getCol();
        int count = 1;

        for(int i = row + 1, j = col - 1; i < getNumRows() && j >= 0; i++, j--) {
            BoardPosition topRightToLeft = new BoardPosition(i,j);
            if (isPlayerAtPos(topRightToLeft, p)) {
                count++;
                if (count == getNumToWin()) {
                    return true;
                }
            } else {
                break;
            }
        }

        for(int i = row - 1, j = col + 1; i >= 0 && j < getNumColumns(); i--, j++) {
            BoardPosition bottomLeftToRight = new BoardPosition(i,j);
            if (isPlayerAtPos(bottomLeftToRight, p)) {
                count++;
                if (count == getNumToWin()) {
                    return true;
                }
            }
            else {
                break;
            }
        }

        //reset count because we're checking a different diagonal
        count = 1;

        for(int i = row + 1, j = col + 1; i < getNumRows() && j < getNumColumns(); i++, j++) {
            BoardPosition bottomRightToLeft = new BoardPosition(i,j);
            if (isPlayerAtPos(bottomRightToLeft, p)) {
                count++;
                if (count == getNumToWin()) {
                    return true;
                }
            }
            else {
                break;
            }
        }

        for(int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
            BoardPosition bottomRightToLeft = new BoardPosition(i,j);
            if (isPlayerAtPos(bottomRightToLeft, p)) {
                count++;
                if (count == getNumToWin()) {
                    return true;
                }
            }
            else {
                break;
            }
        }
        return false;
    }

    /**
     * Returns what is in the GameBoard at position pos.
     * If no marker is there, it returns a blank space char.
     *
     * @param pos BoardPosition that holds [row][col] positions
     *
     * @return char at position pos, if no marker, returns a blank space " "
     *
     * @pre 0 <= pos.getRow() <= MAXROW AND
     *      0 <= pos.getCol() <= MAXCOL
     *
     * @post [char in position is found and returned]
     */
    public char whatsAtPos(BoardPosition pos);

    /**
     * This method returns true if a player taken is in the given position,
     * otherwise returns false
     *
     * @param pos BoardPosition that holds [row][col] positions
     * @param player char of player token
     *
     * @return true if the player is at pos; otherwise, returns false
     *
     * @pre [BoardPosition is valid] and player != null AND
     *      0 <= pos.getRow() <= MAXROW AND
     *      0<= pos.getCol() <= MAXCOL
     *
     * @post [true if player is at the board position] AND self = #self
     *
     */
    public default boolean isPlayerAtPos(BoardPosition pos, char player) {
        if(whatsAtPos(pos) == player) {
            return true;
        }
        return false;
    }
}
