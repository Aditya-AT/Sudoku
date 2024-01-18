public class SudokuSolver {

    public static void main(String[] args) {
        int[][] board = {
            {0,8,5,4,0,0,1,2,0},
            {6,0,0,0,7,5,0,0,9},
            {0,0,0,6,0,1,0,7,8},
            {0,0,7,0,4,0,0,6,0},
            {2,0,1,0,5,0,9,3,0},
            {0,0,4,0,6,0,0,0,5},
            {0,7,0,3,0,0,0,1,2},
            {1,2,0,0,0,7,4,0,0},
            {3,4,0,2,0,6,0,5,7}
        };

        System.out.println("The initial Board\n");
        printBoard(board);

        if (solveSudoku(board)) {
            System.out.println("\nThe Final Board using Back Tracking\n");
            printBoard(board);
        } else {
            System.out.println("No solution exists");
        }
    }

    // This method attempts to solve the Sudoku puzzle using backtracking.
    public static boolean solveSudoku(int[][] board) {
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[0].length; col++) {
                if (board[row][col] == 0) {
                    for (int num = 1; num <= 9; num++) {
                        if (isValid(board, num, row, col)) {
                            board[row][col] = num;
                            if (solveSudoku(board)) {
                                return true;
                            }
                            board[row][col] = 0; // undo the move
                        }
                    }
                    return false; // trigger backtracking
                }
            }
        }
        return true; // puzzle solved
    }

    // This method checks whether it is valid to place a number in a given position.
    public static boolean isValid(int[][] board, int num, int row, int col) {
        // Check row
        for (int i = 0; i < board[0].length; i++) {
            if (board[row][i] == num && i != col) {
                return false;
            }
        }

        // Check column
        for (int i = 0; i < board.length; i++) {
            if (board[i][col] == num && i != row) {
                return false;
            }
        }

        // Check 3x3 box
        int boxRow = row - row % 3;
        int boxCol = col - col % 3;
        for (int i = boxRow; i < boxRow + 3; i++) {
            for (int j = boxCol; j < boxCol + 3; j++) {
                if (board[i][j] == num && (i != row || j != col)) {
                    return false;
                }
            }
        }

        return true;
    }

    // This method prints the current state of the board.
    public static void printBoard(int[][] board) {
        for (int i = 0; i < board.length; i++) {
            if (i % 3 == 0 && i != 0) {
                System.out.println("---------------------");
            }
            for (int j = 0; j < board[0].length; j++) {
                if (j % 3 == 0 && j != 0) {
                    System.out.print("|");
                }
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }
}
