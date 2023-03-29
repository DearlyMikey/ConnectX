package cpsc2150.extendedConnectX.models;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

/**
 * This class represents the game board used
 * in the game by the players
 *
 * @author Michael Gonzales
 * @version 1.0
 *
 * @correspondence number_of_rows = height AND
 *                 number_of_columns = width AND
 *                 number_to_win = numToWin AND
 *                 self = board[0...height-1][0...width-1]
 *
 * @invariant board has no gaps between non-space tokens AND
 *            0 < width <= MAXCOLS
 *            0 < height <= MAXROWS
 *
 */
public class GameBoard extends AbsGameBoard implements IGameBoard{

    private int height;
    private int width;
    private char[][] board;
    private int numToWin;
    private Map<Character, List<BoardPosition>> Map;

    /**
     *
     * This is the constructor for the class that initializes
     * the game board
     *
     * @post board = new char[row][col] AND every space on board is empty
     *
     */
    public GameBoard(int row, int col, int num_To_Win) {
        height = row;
        width = col;
        numToWin = num_To_Win;
        board = new char[height+1][width+1];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                board[i][j] = ' ';
            }
        }
    }

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
    public void placeToken(char p, int c) {
        for(int i = 0; i < height; i++) {
            if (board[i][c] == ' ') {
                board[i][c] = p;
                return;
            }
        }
    }

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
    public char whatsAtPos(BoardPosition pos) {
        int row = pos.getRow();
        int col = pos.getCol();
        return board[row][col];
    }

    public int getNumRows() {
        return height;
    }

    public int getNumColumns() {
        return width;
    }

    public int getNumToWin() {
        return numToWin;
    }

}
