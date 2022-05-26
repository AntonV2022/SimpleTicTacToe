package org.example;

import java.util.Scanner;

public class Main {

    private static String[][] GRID = new String[3][3];
    private static String FIGURE_X = "X";
    private static String FIGURE_O = "O";
    private static boolean CHECK_WINNER = true;

    static void printGrid() {
        System.out.println("---------");
        System.out.println("|" + " " + GRID[0][0] + " " + GRID[0][1] + " " + GRID[0][2] + " " + "|");
        System.out.println("|" + " " + GRID[1][0] + " " + GRID[1][1] + " " + GRID[1][2] + " " + "|");
        System.out.println("|" + " " + GRID[2][0] + " " + GRID[2][1] + " " + GRID[2][2] + " " + "|");
        System.out.println("---------");
    }

    static void fillGrid() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                GRID[i][j] = " ";
            }
        }
    }

    static void makeMove() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the coordinates: ");
        try {
            int userInputRow = scanner.nextInt();
            int userInputColumn = scanner.nextInt();
            if ((userInputRow > 3 || userInputRow < 1) || (userInputColumn > 3 || userInputColumn < 1)) {
                System.out.println("Coordinates should be from 1 to 3!");
                makeMove();
            } else if (!GRID[userInputRow - 1][userInputColumn - 1].equals("X") && !GRID[userInputRow - 1][userInputColumn - 1].equals("O")) {
                whoMove(GRID, userInputRow, userInputColumn);
            } else {
                System.out.println("This cell is occupied! Choose another one!");
                makeMove();
            }
        } catch (Exception e) {
            System.out.println("You should enter numbers!");
            makeMove();
        }
    }

    static void checkWinner(String[][] grid) {
        if (isTheWinner(grid, FIGURE_X)) {
            printGrid();
            System.out.println("X wins");
            CHECK_WINNER = false;
        } else if (isTheWinner(grid, FIGURE_O)) {
            printGrid();
            System.out.println("O wins");
            CHECK_WINNER = false;
        } else if (isEmpty(grid)) {
            printGrid();
            System.out.println("Draw");
            CHECK_WINNER = false;
        }
    }

    static void whoMove(String[][] grid, int userInputRow, int userInputColumn) {
        int xCount = 0;
        int oCount = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (grid[i][j].equals("X")) {
                    xCount++;
                } else if (grid[i][j].equals("O")) {
                    oCount++;
                }
            }
        }
        if (xCount == oCount) {
            grid[userInputRow - 1][userInputColumn - 1] = "X";
        } else if (xCount > oCount) {
            grid[userInputRow - 1][userInputColumn - 1] = "O";
        }
    }

    static boolean isTheWinner(String[][] grid, String symbol) {
        return (grid[0][0].equals(symbol) && grid[0][1].equals(symbol) && grid[0][2].equals(symbol))
                || (grid[1][0].equals(symbol) && grid[1][1].equals(symbol) && grid[1][2].equals(symbol))
                || (grid[2][0].equals(symbol) && grid[2][1].equals(symbol) && grid[2][2].equals(symbol))
                || (grid[0][0].equals(symbol) && grid[1][0].equals(symbol) && grid[2][0].equals(symbol))
                || (grid[0][1].equals(symbol) && grid[1][1].equals(symbol) && grid[2][1].equals(symbol))
                || (grid[0][2].equals(symbol) && grid[1][2].equals(symbol) && grid[2][2].equals(symbol))
                || (grid[0][0].equals(symbol) && grid[1][1].equals(symbol) && grid[2][2].equals(symbol))
                || (grid[0][2].equals(symbol) && grid[1][1].equals(symbol) && grid[2][0].equals(symbol));
    }

    static boolean isEmpty(String[][] grid) {
        int count = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (grid[i][j].equals("X") || grid[i][j].equals("O")) {
                    count++;
                }
            }
        }
        return count == 9;
    }

    public static void main(String[] args) {
        fillGrid();
        while (CHECK_WINNER) {
            printGrid();
            makeMove();
            checkWinner(GRID);
        }
    }
}