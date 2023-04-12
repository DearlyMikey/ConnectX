package cpsc2150.extendedConnectX;
import cpsc2150.extendedConnectX.models.GameBoard;
import cpsc2150.extendedConnectX.models.GameBoardMem;
import cpsc2150.extendedConnectX.models.IGameBoard;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

/**
 * This class has our main function loads up the GameScreen for the game
 *
 *
 */
public class GameScreen {
    /**
     * The beginning of the game program
     *
     * @param args
     */
    public static void main(String[] args) {
        char playAgain = 'Y';
        Scanner input = new Scanner(System.in);

        while(playAgain == 'Y' || playAgain == 'y') {
            System.out.println("How many players?");
            int numPlayers = input.nextInt();
            input.nextLine();

            while(numPlayers < GameBoard.MINPLAYERS || numPlayers > GameBoard.MAXPLAYERS) {
                if(numPlayers < GameBoard.MINPLAYERS) {
                    System.out.println("The minimum amount of players is 2");
                }
                if (numPlayers > GameBoard.MAXPLAYERS) {
                    System.out.println("The maximum amount of players is 10");
                }
                System.out.println("How many players?");
                numPlayers = input.nextInt();
                input.nextLine();
            }

            List<Character> playerChar = new ArrayList<>();

            // Loop for players to choose their character
            for(int i = 1; i <= numPlayers; i++) {
                System.out.print(
                        "What character would you like, player " + i + "? ");
                String read = input.nextLine();
                char player = read.charAt(0);
                player = Character.toUpperCase(player);
                while(playerChar.contains(player)) {
                    System.out.println(
                            player + " is already taken!");
                    System.out.println(
                            "Please choose another token, player " + i);
                    read = input.nextLine();
                    player = read.charAt(0);
                    player = Character.toUpperCase(player);
                }
                playerChar.add(player);
            }

            System.out.println("How many rows do you want on the board?");
            int inputRows = input.nextInt();
            input.nextLine();
            while(inputRows > GameBoard.MAXROWS || inputRows < GameBoard.MINROWS) {
                if(inputRows > GameBoard.MAXROWS) {
                    System.out.println(
                            "The max amount of rows is " + GameBoard.MAXROWS);
                }
                if(inputRows < GameBoard.MINROWS) {
                    System.out.println(
                            "The min amount of rows is " + GameBoard.MINROWS);
                }
                System.out.println("How many rows do you want on the board?");
                inputRows = input.nextInt();
                input.nextLine();
            }

            System.out.println("How many columns do you want on the board?");
            int inputCols = input.nextInt();
            input.nextLine();
            while(inputCols > GameBoard.MAXCOLS || inputCols < GameBoard.MINCOLS) {
                if(inputCols > GameBoard.MAXCOLS) {
                    System.out.println(
                            "The max amount of columns is " + GameBoard.MAXCOLS);
                }
                if(inputCols < GameBoard.MINCOLS) {
                    System.out.println(
                            "The min amount of columns is " + GameBoard.MINCOLS);
                }
                System.out.println("How many columns do you want on the board?");
                inputCols = input.nextInt();
                input.nextLine();
            }

            System.out.println("How many in a row do you need to win?");
            int numToWin = input.nextInt();
            input.nextLine();
            while(numToWin > GameBoard.MAXTOWIN || numToWin < GameBoard.MINTOWIN || numToWin > inputCols || numToWin > inputRows) {
                if(numToWin > GameBoard.MAXTOWIN) {
                    System.out.println(
                            "The max tokens to win is " + GameBoard.MAXTOWIN);
                }
                if(numToWin < GameBoard.MINTOWIN) {
                    System.out.println(
                            "The min tokens to win is " + GameBoard.MINTOWIN);
                }
                if(numToWin > inputCols || numToWin > inputRows) {
                    System.out.println(
                            "It must be lesser than your inputted rows and columns");
                }
                System.out.println("How many in a row do you need to win?");
                numToWin = input.nextInt();
                input.nextLine();
            }

            System.out.println(
                    "Do you want a Fast Implementation (F/f) " +
                            "or a Memory Efficient Implementation (M/m)?");
            String type = input.nextLine();
            char inputType = type.charAt(0);

            while(inputType != 'F' && inputType != 'f' &&
                    inputType != 'M' && inputType != 'm') {
                System.out.println("Please enter either F/f or M/m");
                System.out.println(
                        "Do you want a Fast Implementation (F/f) " +
                                "or a Memory Efficient Implementation (M/m)?");
                type = input.nextLine();
                inputType = type.charAt(0);
            }

            IGameBoard Game;

            int currentPlayer;
            if(inputType == 'F' || inputType == 'f') {
                Game = new GameBoard(inputRows,inputCols,numToWin);
            } else
                Game = new GameBoardMem(inputRows,inputCols,numToWin);

            System.out.println("Starting the game!");
            String TheBoard = Game.toString();

            System.out.println(TheBoard);
            int userInput = -1;

            int count = -1;
            do {
                count++;
                System.out.println("Player " + playerChar.get(count % numPlayers) +
                        " what column do you want to place your token?");
                userInput = input.nextInt();
                input.nextLine();
                //checks if the number they chose is in a spot that's unavailable
                while (userInput > Game.getNumColumns() || userInput < 0) {
                    System.out.println("Unavailable location, choose another VALID column");
                    userInput = input.nextInt();
                    input.nextLine();
                }
                while (!Game.checkIfFree(userInput)) {
                    System.out.println("This column isn't free, please choose another one");
                    userInput = input.nextInt();
                    input.nextLine();
                }
                Game.placeToken(playerChar.get(count % numPlayers), userInput);
                TheBoard = Game.toString();
                System.out.println(TheBoard);
            } while(!Game.checkForWin(userInput) && !Game.checkTie());

            if (Game.checkTie()) {
                System.out.println("Congrats, player " + playerChar.get(count % numPlayers) + ", you won!");
            }
            else if (!Game.checkTie()) {
                System.out.println("The game has ended in a tie!");
            }

            System.out.println("Would you like to play again? Y/N");
            playAgain = input.next().charAt(0);

            while (playAgain != 'n' && playAgain != 'N' && playAgain != 'Y' && playAgain != 'y') {
                System.out.println("Invalid response, please input either Y or N");
                playAgain = input.next().charAt(0);
            }

            if (playAgain == 'n' || playAgain == 'N') {
                System.out.println("Bye bye! Thanks for playing!");
            }
        }
        input.close();
        System.exit(0);
    }
}


