package cpsc2150.extendedConnectX.models;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

/**
 * This class makes a gameboard using a Map as well as places tokens and
 * keeps track of each player and their associated position of the board
 *
 * @invariant MINROWS <= height <= MAXROWS
 * @invariant MINCOLS <= width <= MAXCOLS
 * @invariant MINTOWIN <= numToWin <= MAXTOWIN
 *
 * @correspondence [number of rows] = height,
 *                 [number of columns] = width,
 *                 [number of tokens in a row to win] = numToWin,
 *                 self = gameMap
 */
public class GameBoardMem extends AbsGameBoard implements IGameBoard {

    private int height;
    private int width;
    private int numToWin;
    private Map<Character, List<BoardPosition>> gameMap;

    /**
     * Constructor that creates the gameboard
     * @pre [The player chooses memory efficient implementation]
     *
     * @param r number of rows in the board
     * @param c number of columns in the board
     * @param num_To_Win number of consecutive tokens needed to win
     *
     * @post [The board is rows x cols and empty]
     * @return an empty gameboard
     */
    public GameBoardMem(int r, int c, int num_To_Win) {
        height = r;
        width = c;
        numToWin = num_To_Win;
        gameMap = new HashMap<Character, List<BoardPosition>>();
    }

    /**
     * This method places a token in the lowest available row
     *
     * @param p player token
     * @param c column
     * @pre c >= 0 and [p is a valid character]
     * @post [position of token is stored in the map using key p]
     * @return n/a
     */
    public void placeToken(char p, int c) {
        gameMap.putIfAbsent(p, new ArrayList<>());
        BoardPosition pos = new BoardPosition(0, c);
        for(int i = 0; whatsAtPos(pos) != ' '; i++) {
            pos = new BoardPosition(i, c);
        }

        gameMap.get(p).add(pos);
    }

    /**
     * This method returns a char at the given position
     *
     * @param pos BoardPosition that holds [row][col] positions
     * @pre [BoardPosition is valid position]
     * @post [returns the key if occupied and ' ' if not]
     *
     * @return char key at position
     */
    public char whatsAtPos(BoardPosition pos) {
        for(Map.Entry<Character, List<BoardPosition>> m : gameMap.entrySet()) {
            if(m.getValue().contains(pos)) {
                return m.getKey();
            }
        }
        return ' ';
    }

    /**
     * This method checks if a player is at position pos on the board
     * @param pos BoardPosition that holds [row][col] positions
     * @param player char of player token
     * @pre [char player is a valid player] and [BoardPosition is a valid pos]
     * @post [true if the given player matches with it's position, false otherwise]
     *
     * @return [true if player is at pos, false otherwise]
     */
    @Override
    public boolean isPlayerAtPos(BoardPosition pos, char player) {
        if (!gameMap.containsKey(player)) {
            return false;
        }

        for (Map.Entry<Character, List<BoardPosition>> m : gameMap.entrySet()) {
            if (m.getKey().equals(player) && m.getValue().contains(pos)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Gets number of rows
     * @return height
     */
    public int getNumRows() {
        return height;
    }

    /**
     * Gets number of columns
     * @return width
     */
    public int getNumColumns() {
        return width;
    }

    /**
     * Gets number to win
     * @return number to win
     */
    public int getNumToWin() {
        return numToWin;
    }

}
