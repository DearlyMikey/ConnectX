package cpsc2150.extendedConnectX.models;

/**
 * This class represents the game board used
 * in the game by the players
 *
 * @author Michael Gonzales
 * @version 1.0
 *
 *
 */
public class GameBoard {
    private char[][] board;
    public final static int maxRow = 6;
    public final static int maxCol = 8;

    /**
     *
     * This is the constructor for the class that initializes
     * the game board
     *
     */
    public GameBoard() {}

    /**
     * This method checks if the column can take another token
     *
     * @param c column number
     *
     * @return true or false depending on if the column is free
     *
     * @pre c >= 0 AND c <= 6
     *
     * @post
     *
     */
    public boolean checkIfFree(int c) {}

    /**
     * This method places the player token into
     * the lowest available row in column c
     * @param p player token
     * @param c column
     *
     * @pre c >= 0 AND c <= 6 AND
     * checkIfFree() == true
     *
     * @post
     */
    public void placeToken(char p, int c) {}

    /**
     * This method checks if the latest token placed
     * results in a win
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

    public boolean checkHorizWin(BoardPosition pos, char p) {}

    public boolean checkVertWin(BoardPosition pos, char p) {}

    public boolean checkDiagWin(BoardPosition pos, char p) {}

    public char whatsAtPos(BoardPosition pos) {}

    public boolean isPlayerAtPos(BoardPosition pos, char player) {}

    @Override
    public String toString(Object obj) {}




}
