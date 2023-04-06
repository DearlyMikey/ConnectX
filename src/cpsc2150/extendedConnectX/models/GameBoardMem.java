package cpsc2150.extendedConnectX.models;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

/**
 *
 *
 *
 *
 */
public class GameBoardMem extends AbsGameBoard {

    private int row;
    private int col;
    private int numToWin;
    private Map<Character, List<BoardPosition>> gameMap;

    public GameBoardMem(int r, int c, int num_To_Win) {
        row = r;
        col = c;
        numToWin = num_To_Win;
        gameMap = new HashMap<Character, List<BoardPosition>>();
    }

    public void placeToken(char p, int c) {
        gameMap.putIfAbsent(p, new ArrayList<>());
        BoardPosition pos = new BoardPosition(0, c);
        for(int i = 0; whatsAtPos(pos) != ' '; i++) {
            pos = new BoardPosition(i, c);
        }
        gameMap.get(p).add(pos);
    }

    public char whatsAtPos(BoardPosition pos) {
        for(Map.Entry<Character, List<BoardPosition>> m : gameMap.entrySet()) {
            if(m.getValue().contains(pos)) {
                return m.getKey();
            }
        }
        return ' ';
    }

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

    public int getNumRows() {
        return row;
    }

    public int getNumColumns() {
        return col;
    }

    public int getNumToWin() {
        return numToWin;
    }

}
