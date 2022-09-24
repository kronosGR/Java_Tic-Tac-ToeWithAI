package tictactoe;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

class TicTacToe {

    final String SpaceRead = " ";
    final String SpaceWrite = " ";
    private String[] initialBoard;
    private Scanner sc;
    private String turn = "X";

    private String[] menuOptions = new String[]{"user", "easy"};

    private String[] getInitialBoard() {
        return initialBoard;
    }

    private void setInitialBoard(String[] initialBoard) {
        this.initialBoard = initialBoard;
    }


    public TicTacToe() {
        sc = new Scanner(System.in);
        initialBoard = new String[]{" ", " ", " ", " ", " ", " ", " ", " ", " "};
    }

    public void printBoard() {
        System.out.println("---------");

        for (int x = 0; x < this.initialBoard.length; x++) {
            if (x == 0 || x == 3 || x == 6) {
                System.out.print("| ");
            }
            System.out.print(this.initialBoard[x].toUpperCase() + " ");

            if (x == 2 || x == 5 || x == 8) {
                System.out.print("|");
                System.out.println();
            }
        }

        System.out.println("---------");
    }

//    public void askBoard() {
//        System.out.print("Enter the cells: ");
//        initialBoard = sc.nextLine().split("");
//    }

    public static boolean isNumber(String str) {
        if (str == null) {
            return false;
        }
        try {
            int d = Integer.parseInt(str);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    public int countP(String player) {
        int total = 0;
        for (int i = 0; i < this.initialBoard.length; i++) {
            if (this.initialBoard[i].equals(player)) total++;
        }
        return total;
    }

    public boolean hasSpace() {
        for (int i = 0; i < this.initialBoard.length; i++) {
            if (this.initialBoard[i].equals(this.SpaceRead)) return true;
        }
        return false;
    }

    public boolean hasWon(String player) {
        if (this.initialBoard[0].equals(player) && this.initialBoard[1].equals(player) && this.initialBoard[2].equals(player))
            return true;
        else if (this.initialBoard[3].equals(player) && this.initialBoard[4].equals(player) && this.initialBoard[5].equals(player))
            return true;
        else if (this.initialBoard[6].equals(player) && this.initialBoard[7].equals(player) && this.initialBoard[8].equals(player))
            return true;
        else if (this.initialBoard[0].equals(player) && this.initialBoard[3].equals(player) && this.initialBoard[6].equals(player))
            return true;
        else if (this.initialBoard[1].equals(player) && this.initialBoard[4].equals(player) && this.initialBoard[7].equals(player))
            return true;
        else if (this.initialBoard[2].equals(player) && this.initialBoard[5].equals(player) && this.initialBoard[8].equals(player))
            return true;
        else if (this.initialBoard[0].equals(player) && this.initialBoard[4].equals(player) && this.initialBoard[8].equals(player))
            return true;
        else if (this.initialBoard[6].equals(player) && this.initialBoard[4].equals(player) && this.initialBoard[2].equals(player))
            return true;
        else return false;
    }

    private void AIturn() {
        // find empty
        Random random = new Random();
        // repeat until is not occupied
        while (true) {
            int possible = random.nextInt(9);
            if (initialBoard[possible] != "X" || initialBoard[possible] != " ") {
                initialBoard[possible] = turn;
                System.out.println("Making move level \"easy\"");
                break;
            }
        }
    }

    public boolean checkGame() {
        if (hasSpace()) {
            // check X if has 3
            if (hasWon("X") && !hasWon("O")) {
                System.out.println("X wins");
                return true;
            }
            // check O if has 3
            else if (hasWon("O") && !hasWon("X")) {
                System.out.println("O wins");
                return true;
            }
            // else game not finished
            // else if (countP("X") - countP("O") >= 0) System.out.println("Game not finished");
            // else System.out.println("Impossible");
        } else {
            // check X if has 3
            if (hasWon("X")) {
                System.out.println("X wins");
                return true;
            }
            // check O if has 3
            else if (hasWon("O")) {
                System.out.println("O wins");
                return true;
            }
            // draw+
//            else if ((countP("X") == 3 && countP("O") == 3)
//                    || (countP("X") - countP("O") > 1)
//                    || (countP("O") - countP("X") > 1)) System.out.println("Impossible");
            else {
                System.out.println("Draw");
                return true;
            }
        }
        return false;
    }

    private void changeTurn() {
        if (turn.equals("X")) {
            turn = "O";
        } else {
            turn = "X";
        }
    }

    public void start() {
        while (true) {
            System.out.println("Input command: ");
            String cmd1 = sc.next();

            if (cmd1.equals("exit")) {
                break;
            }

            String cmd2 = sc.next();
            String cmd3 = sc.next();


            if (!Arrays.stream(menuOptions).toList().contains(cmd2) && !Arrays.stream(menuOptions).toList().contains(cmd3)) {
                System.out.println("Bad parameters!");
            }


        }
    }

    private void play() {
        while (true) {
            System.out.println("Enter the coordinates: ");
            String location = sc.nextLine();
            String[] locationArray = location.split(" ");

            if (!isNumber(locationArray[0]) || !isNumber(locationArray[1])) {
                System.out.println("You should enter numbers!");
                continue;
            }

            int y = Integer.parseInt(locationArray[0]);
            int x = Integer.parseInt(locationArray[1]);

            if (x < 1 || x > 3 || y < 1 || y > 3) {
                System.out.println("Coordinates should be from 1 to 3!");
                continue;
            }


            int index = x - 1 + ((y - 1) * 3);
            if (!this.initialBoard[index].equals(SpaceRead)) {
                System.out.println("This cell is occupied! Choose another one!");
                continue;
            }


            this.initialBoard[index] = this.turn;
            printBoard();
            changeTurn();

            this.AIturn();
            changeTurn();

            printBoard();
            if (checkGame()) break;
        }

    }


}

public class Main {
    public static void main(String[] args) {
        // write your code here
        TicTacToe ticTacToe = new TicTacToe();
        ticTacToe.start();

    }
}
