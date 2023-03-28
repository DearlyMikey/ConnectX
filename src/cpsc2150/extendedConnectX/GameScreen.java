package cpsc2150.extendedConnectX;

import cpsc2150.extendedConnectX.models.GameBoard;

import java.util.Scanner;

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
        boolean playAgain = true;

        while(playAgain == true) {
            GameBoard Game = new GameBoard();
            String TheBoard = Game.toString();
            char currPlayer = 'X';


            System.out.println(TheBoard);
            boolean endGame = false;
            while (endGame == false) {
                Scanner input = new Scanner(System.in);
                System.out.println("Player " + currPlayer +
                        " what column do you want to place your token?");

                int userInput = input.nextInt();

                //checks if the number they chose is in a spot that's unavailable
                while (userInput > Game.getNumColumns() || userInput < 0) {
                    System.out.println("Unavailable location, choose another VALID column");
                    userInput = input.nextInt();
                }
                while (Game.checkIfFree(userInput) == false) {
                    System.out.println("This column isn't free, please choose another one");
                    userInput = input.nextInt();
                }

                Game.placeToken(currPlayer, userInput);
                TheBoard = Game.toString();
                System.out.println(TheBoard);

                if (Game.checkForWin(userInput) == true) {
                    break;
                }
                if (Game.checkTie() == true) {
                    break;
                }

                if (currPlayer == 'X') {
                    currPlayer = 'O';
                } else {
                    currPlayer = 'X';
                }
            }
            if (Game.checkTie() == false) {
                System.out.println("Congrats!" +currPlayer);
            }
            System.out.println("Would you like to play again? Y/N");
            String userInput2;
            Scanner repeatIn = new Scanner(System.in);
            userInput2 = repeatIn.next();

            boolean repeat = true;
            while(repeat) {
                if (userInput2.equals("y")) {
                    endGame = true;
                    break;
                } else if (userInput2.equals("n")) {
                    endGame = false;
                    break;
                }
                System.out.println("Would you like to play again? Y/N");
                userInput2 = repeatIn.next();
            }
        }
    }

}


