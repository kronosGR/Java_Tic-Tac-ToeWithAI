package tictactoe;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

class TicTacToe {

    final String SpaceRead = " ";
    private String[] initialBoard;
    private Scanner sc;
    private String turn = "X";

//    private String[] menuOptions = new String[]{"user", "easy" };

    private int[][] winningRows = {
            {0, 1, 2},
            {3, 4, 5},
            {6, 7, 8},
            {0, 3, 6},
            {1, 4, 7},
            {2, 5, 8},
            {0, 4, 8},
            {6, 4, 2}
    };

    private String[] getInitialBoard() {
        return initialBoard;
    }

    private void setInitialBoard(String[] initialBoard) {
        this.initialBoard = initialBoard;
    }


    public TicTacToe() {
        sc = new Scanner(System.in);
        this.resetBoard();
    }

    private void resetBoard() {
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

    private int checkForTwo(String player) {
        int index = -1;
        for (int i = 0; i < winningRows.length; i++) {
            int count = 0;
            for (int x = 0; x < winningRows[i].length; x++) {
                if (this.initialBoard[winningRows[i][x]].equals(player)) {
                    count++;
                }
            }
            if (count == 2) {
                index = i;
                break;
            }
        }

        return index;
    }

    private void AIMedium() {
        // check for possible win
        String enemy = "";
        if (this.turn.equals("X")) {
            enemy = "O";
        } else {
            enemy = "X";
        }

        int empty = -1;
        if (this.checkForTwo(this.turn) > -1) {
            // find the empty from the winningRows[index] and play this one
            int[] winArray = winningRows[this.checkForTwo(this.turn)];
            for (int i = 0; i < winArray.length; i++) {
                if (this.initialBoard[winArray[i]].equals(SpaceRead)) {
                    this.initialBoard[winArray[i]] = this.turn;
                    break;
                }
            }
        } else if (this.checkForTwo(enemy) > -1) {
            // find the empty for the opponent like above and play this one
            int[] winArray = winningRows[this.checkForTwo(enemy)];
            for (int i = 0; i < winArray.length; i++) {
                if (this.initialBoard[winArray[i]].equals(SpaceRead)) {
                    this.initialBoard[winArray[i]] = this.turn;
                    break;
                }
            }
        } else {
            // play random
            this.AIEasy();
        }
    }

    private void AIEasy() {
        // find empty
        Random random = new Random();
        // repeat until is not occupied
        while (true) {
            int possible = random.nextInt(9);
            if (initialBoard[possible].equals(SpaceRead)) {
                initialBoard[possible] = turn;
                break;
            }
        }
    }

    private void AIturn(String level) {
        switch (level) {
            case "easy":
                this.AIEasy();
                System.out.println("Making move level \"easy\"");
                break;
            case "medium":
                this.AIMedium();
                System.out.println("Making move level \"medium\"");
                break;
        }
        printBoard();
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
            String line = sc.nextLine();
            String[] lineA = line.split(" ");

            String cmd1 = lineA[0];
            if (cmd1.equals("exit")) {
                break;
            }

            if (lineA.length < 3) {
                System.out.println("Bad parameters!");
                continue;
            }

            String cmd2 = lineA[1];
            String cmd3 = lineA[2];
            String level = "";

            if (cmd2.equals("easy") || cmd2.equals("medium")) {
                level = cmd2;
            }
            if (cmd3.equals("easy") || cmd3.equals("medium")) {
                level = cmd3;
            }

            boolean pl1 = false;
            boolean pl2 = false;
            if (cmd2.equals("user")) pl1 = true;
            if (cmd3.equals("user")) pl2 = true;


            play(pl1, pl2, level);

        }
    }

    private void humanPlay(String turn) {
        System.out.println("Enter the coordinates: ");
        String location = sc.nextLine();
        String[] locationArray = location.split(" ");

        if (!isNumber(locationArray[0]) || !isNumber(locationArray[1])) {
            System.out.println("You should enter numbers!");
            return;
        }

        int y = Integer.parseInt(locationArray[0]);
        int x = Integer.parseInt(locationArray[1]);

        if (x < 1 || x > 3 || y < 1 || y > 3) {
            System.out.println("Coordinates should be from 1 to 3!");
            return;
        }
        int index = x - 1 + ((y - 1) * 3);
        if (!this.initialBoard[index].equals(SpaceRead)) {
            System.out.println("This cell is occupied! Choose another one!");
            return;
        }


        this.initialBoard[index] = turn;
        printBoard();
    }

    /**
     * Start the game, Main game loop
     *
     * @param player1 if true is human
     * @param player2 if true is human
     */
    private void play(boolean player1, boolean player2, String level) {

        this.resetBoard();
        this.printBoard();
        while (true) {
            if (player1) {
                humanPlay(this.turn);
            } else {
                this.AIturn(level);
            }
            if (checkGame()) break;
            changeTurn();

            if (player2) {
                humanPlay(this.turn);
            } else {
                this.AIturn(level);
            }
            if (checkGame()) break;
            changeTurn();
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
