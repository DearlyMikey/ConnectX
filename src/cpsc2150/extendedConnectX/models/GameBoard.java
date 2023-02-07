package cpsc2150.extendedConnectX.models;

/**
 * This class represents the game board used
 * in the game by the players
 *
 * @author Michael Gonzales
 * @version 1.0
 *
 * @invariant p != null
 */
public class GameBoard {
    private char[][] board;
    public final static int maxCol = 6;
    public final static int maxRow = 8;

    /**
     *
     * This is the constructor for the class that initializes
     * the game board
     * @pre
     * @post board = new char[row][col]
     */
    public GameBoard() {}

    /**
     * This method checks if the column can take another token.
     *
     * @param c column number
     *
     * @return true if the column is free, otherwise false
     *
     * @pre c >= 0 AND c <= 6
     *
     * @post [if true, column has space for another token]
     *
     */
    public boolean checkIfFree(int c) {}

    /**
     * This method places the player token into
     * the lowest available row in column c
     *
     * @param p player token
     * @param c column
     *
     * @pre c >= 0 AND c <= 6 AND
     * checkIfFree() == true AND p != null
     *
     * @post [token placed in lowest available row]
     *
     */
    public void placeToken(char p, int c) {}

    /**
     * This method checks if the latest token placed
     * results in a win
     *
     * @param c column number
     *
     * @pre c >= 0 AND c <= 6
     *
     * @post iff checkForWin(int c) == true, [game is won by latest player]
     *
     * @return true or false depending on if latest token
     * placed results in a win
     */
    public boolean checkForWin(int c) {}

    /**
     * This methods checks whether the game has resulted in a tie
     *
     * @return true or false depending on if latest taken placed
     * results in a tie
     *
     */
    public boolean checkTie() {}

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
     * @pre p != null AND [BoardPosition is valid]
     * @post [true is a player win, false is not a win]
     */
    public boolean checkHorizWin(BoardPosition pos, char p) {}

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
     * @pre p != null AND [BoardPosition is valid]
     *
     * @post [true is a player win, false is not a win]
     */
    public boolean checkVertWin(BoardPosition pos, char p) {}

    /**
     * This method checks if the last token placed (which was
     * placed in position pos by player p) resulted in 5 in a row diagonally
     *
     * @param pos BoardPosition that holds [row][col] positions
     * @param p player token
     *
     * @return true if player has 5 token in a row diagonally, otherwise false
     *
     * @pre p != null AND [BoardPosition is valid]
     *
     * @post [true is a player win, false is not a win]
     */
    public boolean checkDiagWin(BoardPosition pos, char p) {}

    /**
     * Returns what is in the GameBoard at position pos.
     * If no marker is there, it returns a blank space char.
     *
     * @param pos BoardPosition that holds [row][col] positions
     *
     * @return char at position pos, if no marker, returns a blank space " "
     *
     * @pre [BoardPosition is valid]
     *
     * @post [char in position is found]
     */
    public char whatsAtPos(BoardPosition pos) {}

    /**
     * This method returns true if a player taken is in the given position,
     * otherwise returns false
     *
     * @param pos BoardPosition that holds [row][col] positions
     * @param player char of player token
     *
     * @return true if the player is at pos; otherwise, returns false
     *
     * @pre [BoardPosition is valid] and player != null
     *
     * @post [true if given player and position match, false otherwise]
     *
     */
    public boolean isPlayerAtPos(BoardPosition pos, char player) {}

    /**
     * This methods override toString to return a string that shows
     * the entire game board.
     *
     * @param obj
     *
     * @pre board != null
     *
     * @post toString = [current game board]
     *
     * @return string that shows entire game board
     */
    @Override
    public String toString(Object obj) {}




}
