package cpsc2150.extendedConnectX.models;

public abstract class AbsGameBoard implements IGameBoard {

    /**
     * This methods override toString to return a string that shows
     * the entire game board.
     *
     *
     * @return [string that shows entire game board]
     *
     * @pre board != null
     *
     * @post [A formatted string of the board will return] AND
     *       board = #board
     */
    @Override
    public String toString() {
        String board = "";
        // print the column numbers on top of the board
        for (int i = 0; i < getNumColumns(); i++) {
                if(i <= 9) {
                    board += "| " + i;
                }
                else {
                    board += "|" + i;
                }
        }

        board += "|";
        board += "\n";

        // now, traverse the entire board and print out its contents
        for (int i = getNumRows() - 1; i >= 0; i--) {
            board += "|";
            for (int j = 0; j < getNumColumns(); j++) {
                BoardPosition curPos = new BoardPosition(i, j);
                board += whatsAtPos(curPos) + " |";
            }
            board += "\n";
        }
        return board;
    }
}
