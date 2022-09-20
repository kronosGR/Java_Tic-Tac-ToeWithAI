package tictactoe;

import java.util.Scanner;

class TicTacToe{
    private String[] initialBoard;
    private Scanner sc;

    public String[] getInitialBoard() {
        return initialBoard;
    }

    public void setInitialBoard(String[] initialBoard) {
        this.initialBoard = initialBoard;
    }


    public TicTacToe(){
        sc = new Scanner(System.in);
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

    public void askBoard(){
        System.out.print("Enter the cells: ");
        String board = sc.nextLine();
    }
}
public class Main {
    public static void main(String[] args) {
        // write your code here
        TicTacToe ticTacToe = new TicTacToe();
        ticTacToe.askBoard();

    }
}
