package cpsc2150.extendedConnectX.models;
import static org.junit.Assert.*;
import org.junit.Test;

public class TestGameBoard {

    private IGameBoard MakeGameBoard(int rows, int cols, int wins) {
        return new GameBoard(rows, cols, wins);
    }

    private String testArray(char[][] arr, int rows, int cols) {
        String board = "";
        for (int i = 0; i < cols; i++) {
            if(i <= 9) {
                board += "| " + i;
            }
            else {
                board += "|" + i;
            }
        }

        board += "|";
        board += "\n";

        for (int i = rows - 1; i >= 0; i--) {
            board += "|";
            for (int j = 0; j < cols; j++) {
                board += arr[i][j] + " |";
            }
            board += "\n";
        }
        return board;
    }

    @Test
    public void testConstructor_5_5_4() {
        int row = 5;
        int col = 5;
        int numToWin = 4;
        char[][] expBoard = new char[row + 1][col + 1];
        for(int i = 0; i < row; i++) {
            for(int j = 0; j < col; j++) {
                expBoard[i][j] = ' ';
            }
        }
        IGameBoard board = MakeGameBoard(row,col,numToWin);
        assertEquals(testArray(expBoard,row,col), board.toString());
    }

    @Test
    public void testConstructor_min_size() {
        int row = 3;
        int col = 3;
        int numToWin = 3;
        char[][] expBoard = new char[row + 1][col + 1];
        for(int i = 0; i < row; i++) {
            for(int j = 0; j < col; j++) {
                expBoard[i][j] = ' ';
            }
        }
        IGameBoard board = MakeGameBoard(row,col,numToWin);
        assertEquals(testArray(expBoard,row,col), board.toString());
    }

    @Test
    public void testConstructor_max_size() {
        int row = 100;
        int col = 100;
        int numToWin = 25;
        char[][] expBoard = new char[row + 1][col + 1];
        for(int i = 0; i < row; i++) {
            for(int j = 0; j < col; j++) {
                expBoard[i][j] = ' ';
            }
        }
        IGameBoard board = MakeGameBoard(row,col,numToWin);
        assertEquals(testArray(expBoard,row,col), board.toString());
    }

    @Test
    public void testCheckIfFree_free_emptyCol() {
        int row = 5;
        int col = 5;
        int numToWin = 4;
        IGameBoard board = MakeGameBoard(row,col,numToWin);
        assertTrue(board.checkIfFree(1));
    }

    @Test
    public void testCheckIfFree_full_fullCol() {
        int row = 5;
        int col = 5;
        int numToWin = 4;
        IGameBoard board = MakeGameBoard(row,col,numToWin);
        board.placeToken('X', 1);
        for(int i = 1; i < row; i++) {
            if (i != 2) {
                board.placeToken('X', 1);
            }
            if (i == 2) {
                board.placeToken('O', 1);
            }
        }
        assertFalse(board.checkIfFree(1));
    }

    @Test
    public void testCheckIfFree_free_one_spot() {
        int row = 5;
        int col = 5;
        int numToWin = 4;
        IGameBoard board = MakeGameBoard(row,col,numToWin);
        board.placeToken('X', 1);
        for(int i = 1; i < row - 1; i++) {
            if (i != 2) {
                board.placeToken('X', 1);
            }
            if (i == 2) {
                board.placeToken('O', 1);
            }
        }
        assertTrue(board.checkIfFree(1));
    }

    @Test
    public void testCheckHorizWin_win_last_marker_left() {
        int row = 5;
        int col = 5;
        int numToWin = 4;
        IGameBoard board = MakeGameBoard(row,col,numToWin);
        board.placeToken('O',0);
        board.placeToken('O',1);
        board.placeToken('O',2);
        board.placeToken('X',3);
        board.placeToken('O',4);
        board.placeToken('X',3);
        board.placeToken('X',2);
        board.placeToken('X',1);
        board.placeToken('X',0);
        BoardPosition pos = new BoardPosition(1,0);
        assertTrue(board.checkHorizWin(pos,'X'));
    }

    @Test
    public void testCheckHorizWin_win_last_marker_middle() {
        int row = 5;
        int col = 5;
        int numToWin = 4;
        IGameBoard board = MakeGameBoard(row,col,numToWin);
        board.placeToken('O',0);
        board.placeToken('O',1);
        board.placeToken('O',2);
        board.placeToken('X',3);
        board.placeToken('O',4);
        board.placeToken('X',3);
        board.placeToken('X',1);
        board.placeToken('X',0);
        board.placeToken('X',2);
        BoardPosition pos = new BoardPosition(1,2);
        assertTrue(board.checkHorizWin(pos,'X'));
    }

    @Test
    public void testCheckHorizWin_win_last_marker_right() {
        int row = 5;
        int col = 5;
        int numToWin = 4;
        IGameBoard board = MakeGameBoard(row,col,numToWin);
        board.placeToken('O',0);
        board.placeToken('O',1);
        board.placeToken('O',2);
        board.placeToken('X',3);
        board.placeToken('O',4);
        board.placeToken('X',1);
        board.placeToken('X',2);
        board.placeToken('X',3);
        board.placeToken('X',4);
        BoardPosition pos = new BoardPosition(1,4);
        assertTrue(board.checkHorizWin(pos, 'X'));
    }

    @Test
    public void testCheckHorizWin_false_last_marker_wrong() {
        int row = 5;
        int col = 5;
        int numToWin = 4;
        IGameBoard board = MakeGameBoard(row, col, numToWin);
        board.placeToken('O',0);
        board.placeToken('X',1);
        board.placeToken('X',2);
        board.placeToken('X',3);
        board.placeToken('O',1);
        board.placeToken('X',3);
        board.placeToken('O',2);
        board.placeToken('O',3);
        board.placeToken('X', 0);
        BoardPosition pos = new BoardPosition(1,0);
        assertFalse(board.checkHorizWin(pos, 'X'));
    }

    @Test
    public void testCheckVertWin_win_top() {
        int row = 5;
        int col = 5;
        int numToWin = 4;
        IGameBoard board = MakeGameBoard(row, col, numToWin);
        board.placeToken('X',1);
        board.placeToken('X',1);
        board.placeToken('X',1);
        board.placeToken('X',1);
        BoardPosition pos = new BoardPosition(3,1);
        assertTrue(board.checkVertWin(pos, 'X'));
    }

    @Test
    public void testCheckVertWin_win_interrupted_markers() {
        int row = 6;
        int col = 5;
        int numToWin = 4;
        IGameBoard board = MakeGameBoard(row, col, numToWin);
        board.placeToken('X',1);
        board.placeToken('O',1);
        board.placeToken('X',1);
        board.placeToken('X',1);
        board.placeToken('X',1);
        board.placeToken('X',1);
        BoardPosition pos = new BoardPosition(5,1);
        assertTrue(board.checkVertWin(pos, 'X'));
    }

    @Test
    public void testCheckVertWin_false_interrupted_markers() {
        int row = 6;
        int col = 5;
        int numToWin = 4;
        IGameBoard board = MakeGameBoard(row, col, numToWin);
        board.placeToken('X',1);
        board.placeToken('X',1);
        board.placeToken('O',1);
        board.placeToken('X',1);
        BoardPosition pos = new BoardPosition(3,1);
        assertFalse(board.checkVertWin(pos, 'X'));
    }

    @Test
    public void testCheckVertWin_win_bottom_marker_opposite() {
        int row = 6;
        int col = 5;
        int numToWin = 4;
        IGameBoard board = MakeGameBoard(row, col, numToWin);
        board.placeToken('O',1);
        board.placeToken('X',1);
        board.placeToken('X',1);
        board.placeToken('X',1);
        board.placeToken('X',1);
        BoardPosition pos = new BoardPosition(4,1);
        assertTrue(board.checkVertWin(pos, 'X'));
    }

    @Test
    public void testDiagWin_win_top_right_to_left() {
        int row = 5;
        int col = 5;
        int numToWin = 4;
        IGameBoard board = MakeGameBoard(row, col, numToWin);
        board.placeToken('X',0);
        board.placeToken('O',0);
        board.placeToken('O',1);
        board.placeToken('O',2);
        board.placeToken('O',3);
        board.placeToken('X',1);
        board.placeToken('X',2);
        board.placeToken('O',3);
        board.placeToken('X',2);
        board.placeToken('X',3);
        board.placeToken('X',3);
        BoardPosition pos = new BoardPosition(3,3);
        assertTrue(board.checkDiagWin(pos, 'X'));
    }

    @Test
    public void testDiagWin_win_bottom_left_to_right() {
        int row = 5;
        int col = 5;
        int numToWin = 4;
        IGameBoard board = MakeGameBoard(row, col, numToWin);
        board.placeToken('O',2);
        board.placeToken('O',3);
        board.placeToken('O',4);
        board.placeToken('X',2);
        board.placeToken('X',3);
        board.placeToken('O',4);
        board.placeToken('X',3);
        board.placeToken('O',4);
        board.placeToken('X',4);
        board.placeToken('X',1);
        BoardPosition pos = new BoardPosition(0,1);
        assertTrue(board.checkDiagWin(pos, 'X'));
    }

    @Test
    public void testDiagWin_win_top_left_to_right() {
        int row = 5;
        int col = 5;
        int numToWin = 4;
        IGameBoard board = MakeGameBoard(row, col, numToWin);
        board.placeToken('O',0);
        board.placeToken('O',0);
        board.placeToken('O',0);
        board.placeToken('X',0);
        board.placeToken('O',1);
        board.placeToken('O',1);
        board.placeToken('X',1);
        board.placeToken('O',2);
        board.placeToken('X',2);
        board.placeToken('X',3);
        BoardPosition pos = new BoardPosition(3,0);
        assertTrue(board.checkDiagWin(pos, 'X'));
    }

    @Test
    public void testDiagWin_win_bottom_right_to_left() {
        int row = 5;
        int col = 5;
        int numToWin = 4;
        IGameBoard board = MakeGameBoard(row, col, numToWin);
        board.placeToken('O',2);
        board.placeToken('O',2);
        board.placeToken('X',2);
        board.placeToken('O',3);
        board.placeToken('X',3);
        board.placeToken('X',4);
        board.placeToken('O',1);
        board.placeToken('O',1);
        board.placeToken('O',1);
        board.placeToken('X',1);
        BoardPosition pos = new BoardPosition(0,4);
        assertTrue(board.checkDiagWin(pos, 'X'));
    }

    @Test
    public void testDiagWin_false_not_enough_tokens() {
        int row = 5;
        int col = 5;
        int numToWin = 4;
        IGameBoard board = MakeGameBoard(row, col, numToWin);
        board.placeToken('X',0);
        board.placeToken('O',1);
        board.placeToken('X',1);
        board.placeToken('O',2);
        board.placeToken('O',2);
        board.placeToken('X',2);
        BoardPosition pos = new BoardPosition(2,2);
        assertFalse(board.checkDiagWin(pos,'X'));
    }

    @Test
    public void testDiagWin_false_interrupted_sequence() {
        int row = 5;
        int col = 5;
        int numToWin = 4;
        IGameBoard board = MakeGameBoard(row, col, numToWin);
        board.placeToken('X',0);
        board.placeToken('O',1);
        board.placeToken('O',1);
        board.placeToken('X',2);
        board.placeToken('O',2);
        board.placeToken('X',2);
        board.placeToken('X',3);
        board.placeToken('O',3);
        board.placeToken('O',3);
        board.placeToken('X',3);
        BoardPosition pos = new BoardPosition(3,3);
        assertFalse(board.checkDiagWin(pos, 'X'));
    }

    @Test
    public void testDiagWin_win_last_marker_middle() {
        int row = 5;
        int col = 5;
        int numToWin = 4;
        IGameBoard board = MakeGameBoard(row, col, numToWin);
        board.placeToken('X',0);
        board.placeToken('O',1);
        board.placeToken('X',2);
        board.placeToken('O',2);
        board.placeToken('X',2);
        board.placeToken('X',3);
        board.placeToken('O',3);
        board.placeToken('O',3);
        board.placeToken('X',3);
        board.placeToken('X',1);
        BoardPosition pos = new BoardPosition(1,1);
        assertTrue(board.checkDiagWin(pos, 'X'));
    }

    @Test
    public void testCheckTie_true_full() {
        int row = 3;
        int col = 3;
        int numToWin = 3;
        IGameBoard board = MakeGameBoard(row, col, numToWin);
        board.placeToken('X',0);
        board.placeToken('X',0);
        board.placeToken('O',0);
        board.placeToken('O',1);
        board.placeToken('O',1);
        board.placeToken('X',1);
        board.placeToken('X',2);
        board.placeToken('X',2);
        board.placeToken('O',2);
        assertTrue(board.checkTie());
    }

    @Test
    public void testCheckTie_false_empty() {
        int row = 3;
        int col = 3;
        int numToWin = 3;
        IGameBoard board = MakeGameBoard(row, col, numToWin);
        assertFalse(board.checkTie());
    }

    @Test
    public void testCheckTie_false_almost_empty() {
        int row = 3;
        int col = 3;
        int numToWin = 3;
        IGameBoard board = MakeGameBoard(row, col, numToWin);
        board.placeToken('X',0);
        board.placeToken('X',0);
        board.placeToken('O',0);
        board.placeToken('O',1);
        board.placeToken('O',1);
        board.placeToken('X',1);
        board.placeToken('X',2);
        board.placeToken('X',2);
        assertFalse(board.checkTie());
    }

    @Test
    public void testCheckTie_false_last_token_winner() {
        int row = 4;
        int col = 4;
        int numToWin = 3;
        IGameBoard board = MakeGameBoard(row, col, numToWin);
        board.placeToken('X',0);
        board.placeToken('O',0);
        board.placeToken('X',0);
        board.placeToken('O',1);
        board.placeToken('X',1);
        board.placeToken('O',1);
        board.placeToken('X',2);
        board.placeToken('O',2);
        board.placeToken('X',2);
        assertFalse(board.checkTie());
    }

    @Test
    public void testWhatsAtPos_empty_space() {
        int row = 4;
        int col = 4;
        int numToWin = 3;
        IGameBoard board = MakeGameBoard(row, col, numToWin);
        BoardPosition pos = new BoardPosition(0,0);
        assertEquals(' ', board.whatsAtPos(pos));
    }

    @Test
    public void testWhatsAtPos_single_char() {
        int row = 4;
        int col = 4;
        int numToWin = 3;
        IGameBoard board = MakeGameBoard(row, col, numToWin);
        board.placeToken('X',2);
        BoardPosition pos = new BoardPosition(0,2);
        assertEquals('X', board.whatsAtPos(pos));
    }

    @Test
    public void testWhatsAtPos_empty_space_full_board() {
        int row = 4;
        int col = 4;
        int numToWin = 3;
        IGameBoard board = MakeGameBoard(row, col, numToWin);
        board.placeToken('X',0);
        board.placeToken('X',0);
        board.placeToken('O',0);
        board.placeToken('O',0);
        board.placeToken('O',1);
        board.placeToken('O',1);
        board.placeToken('O',1);
        board.placeToken('X',1);
        board.placeToken('O',3);
        board.placeToken('X',3);
        board.placeToken('O',3);
        board.placeToken('X',3);
        board.placeToken('X',2);
        board.placeToken('X',2);
        board.placeToken('X',2);
        BoardPosition pos = new BoardPosition(3,2);
        assertEquals(' ', board.whatsAtPos(pos));
    }

    @Test
    public void testWhatsAtPos_full_board() {
        int row = 4;
        int col = 4;
        int numToWin = 3;
        IGameBoard board = MakeGameBoard(row, col, numToWin);
        board.placeToken('X',0);
        board.placeToken('O',0);
        board.placeToken('X',0);
        board.placeToken('O',0);
        board.placeToken('O',1);
        board.placeToken('X',1);
        board.placeToken('O',1);
        board.placeToken('X',1);
        board.placeToken('X',2);
        board.placeToken('O',2);
        board.placeToken('X',2);
        board.placeToken('O',2);
        board.placeToken('X',3);
        board.placeToken('O',3);
        board.placeToken('X',3);
        board.placeToken('O',3);
        BoardPosition pos = new BoardPosition(0,2);
        assertEquals('X', board.whatsAtPos(pos));
    }

    @Test
    public void testWhatsAtPos_four_different_chars() {
        int row = 4;
        int col = 4;
        int numToWin = 3;
        IGameBoard board = MakeGameBoard(row, col, numToWin);
        board.placeToken('A',0);
        board.placeToken('B',1);
        board.placeToken('C',2);
        board.placeToken('D',3);
        BoardPosition pos = new BoardPosition(0,3);
        assertEquals('D', board.whatsAtPos(pos));
    }

    @Test
    public void testIsPlayerAtPos_false_empty_board() {
        int row = 5;
        int col = 5;
        int numToWin = 4;
        IGameBoard board = MakeGameBoard(row, col, numToWin);
        BoardPosition pos = new BoardPosition(0,0);
        assertFalse(board.isPlayerAtPos(pos, 'X'));
    }

    @Test
    public void testIsPlayerAtPos_true_single_char() {
        int row = 5;
        int col = 5;
        int numToWin = 4;
        IGameBoard board = MakeGameBoard(row, col, numToWin);
        board.placeToken('X', 2);
        BoardPosition pos = new BoardPosition(0,2);
        assertTrue(board.isPlayerAtPos(pos, 'X'));
    }

    @Test
    public void testIsPlayerAtPos_false_empty_space_full_board() {
        int row = 4;
        int col = 4;
        int numToWin = 4;
        IGameBoard board = MakeGameBoard(row, col, numToWin);
        board.placeToken('X',0);
        board.placeToken('X',0);
        board.placeToken('O',0);
        board.placeToken('O',0);
        board.placeToken('O',1);
        board.placeToken('O',1);
        board.placeToken('O',1);
        board.placeToken('X',1);
        board.placeToken('X',2);
        board.placeToken('X',2);
        board.placeToken('X',2);
        board.placeToken('O',3);
        board.placeToken('X',3);
        board.placeToken('O',3);
        board.placeToken('X',3);
        BoardPosition pos = new BoardPosition(3,2);
        assertFalse(board.isPlayerAtPos(pos, 'X'));
    }

    @Test
    public void testIsPlayerAtPos_true_full_board() {
        int row = 4;
        int col = 4;
        int numToWin = 4;
        IGameBoard board = MakeGameBoard(row, col, numToWin);
        board.placeToken('X',0);
        board.placeToken('X',0);
        board.placeToken('O',0);
        board.placeToken('O',0);
        board.placeToken('O',1);
        board.placeToken('O',1);
        board.placeToken('O',1);
        board.placeToken('X',1);
        board.placeToken('X',2);
        board.placeToken('O',2);
        board.placeToken('X',2);
        board.placeToken('X',2);
        board.placeToken('O',3);
        board.placeToken('X',3);
        board.placeToken('O',3);
        board.placeToken('X',3);
        BoardPosition pos = new BoardPosition(0,2);
        assertTrue(board.isPlayerAtPos(pos, 'X'));
    }

    @Test
    public void testIsPlayerAtPos_true_four_different_chars() {
        int row = 4;
        int col = 4;
        int numToWin = 3;
        IGameBoard board = MakeGameBoard(row, col, numToWin);
        board.placeToken('A',0);
        board.placeToken('B',1);
        board.placeToken('C',2);
        board.placeToken('D',3);
        BoardPosition pos = new BoardPosition(0,3);
        assertTrue(board.isPlayerAtPos(pos,'D'));
    }

    @Test
    public void testPlaceToken_empty_board() {
        int row = 4;
        int col = 4;
        int numToWin = 4;
        IGameBoard board = MakeGameBoard(row, col, numToWin);
        char[][] expBoard = new char[row+1][col+1];
        for(int i = 0; i < row; i++) {
            for(int j = 0; j < col; j++) {
                expBoard[i][j] = ' ';
            }
        }
        expBoard[0][0] = 'X';
        board.placeToken('X',0);
        assertEquals(testArray(expBoard,row,col), board.toString());
    }

    @Test
    public void testPlaceToken_full_board() {
        int row = 4;
        int col = 4;
        int numToWin = 4;
        IGameBoard board = MakeGameBoard(row, col, numToWin);
        char[][] expBoard = new char[row+1][col+1];
        for(int i = 0; i < row; i++) {
            for(int j = 1; j < col; j++) {
                expBoard[i][j] = 'X';
            }
        }
        expBoard[0][0] = 'A';
        expBoard[1][0] = 'B';
        expBoard[2][0] = 'C';
        expBoard[3][0] = 'D';
        for (int i = 0; i < row; i++) {
            for (int j = 1; j < col; j++) {
                board.placeToken('X', j);
            }
        }
        board.placeToken('A', 0);
        board.placeToken('B', 0);
        board.placeToken('C', 0);
        board.placeToken('D', 0);
        assertEquals(testArray(expBoard,row,col), board.toString());
    }

    @Test
    public void testPlaceToken_almost_full_board() {
        int row = 4;
        int col = 4;
        int numToWin = 4;
        IGameBoard board = MakeGameBoard(row, col, numToWin);
        char[][] expBoard = new char[row+1][col+1];
        for(int i = 0; i < row; i++) {
            for(int j = 0; j < col; j++) {
                expBoard[i][j] = ' ';
            }
        }
        expBoard[0][0] = 'X';
        expBoard[1][0] = 'X';
        expBoard[2][0] = 'X';
        board.placeToken('X',0);
        board.placeToken('X',0);
        board.placeToken('X',0);
        assertEquals(testArray(expBoard,row,col),board.toString());
    }

    @Test
    public void testPlaceToken_col_not_empty() {
        int row = 4;
        int col = 4;
        int numToWin = 4;
        IGameBoard board = MakeGameBoard(row, col, numToWin);
        char[][] expBoard = new char[row+1][col+1];
        for(int i = 0; i < row; i++) {
            for(int j = 0; j < col; j++) {
                expBoard[i][j] = ' ';
            }
        }
        expBoard[0][0] = 'X';
        expBoard[1][0] = 'X';
        expBoard[2][0] = 'X';
        board.placeToken('X',0);
        board.placeToken('X',0);
        board.placeToken('X',0);
        assertEquals(testArray(expBoard,row,col), board.toString());
    }

    @Test
    public void testPlaceToken_row_not_empty() {
        int row = 5;
        int col = 5;
        int numToWin = 5;
        IGameBoard board = MakeGameBoard(row, col, numToWin);
        char[][] expBoard = new char[row+1][col+1];
        for(int i = 0; i < row; i++) {
            for(int j = 0; j < col; j++) {
                expBoard[i][j] = ' ';
            }
        }
        expBoard[0][0] = 'X';
        expBoard[0][1] = 'X';
        expBoard[0][3] = 'X';
        expBoard[0][4] = 'X';
        expBoard[0][2] = 'X';
        board.placeToken('X',0);
        board.placeToken('X',1);
        board.placeToken('X',3);
        board.placeToken('X',4);
        board.placeToken('X',2);
        assertEquals(testArray(expBoard,row,col), board.toString());
    }
}

