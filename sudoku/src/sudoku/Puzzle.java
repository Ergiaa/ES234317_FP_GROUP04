package sudoku;

import java.util.*;

public class Puzzle {
    // All variables have package access
    // The numbers on the puzzle
    int[][] numbers = new int[SudokuConstants.GRID_SIZE][SudokuConstants.GRID_SIZE];
    // The clues - isGiven (no need to guess) or need to guess
    boolean[][] isGiven = new boolean[SudokuConstants.GRID_SIZE][SudokuConstants.GRID_SIZE];
    Solver solver = new Solver();
    Scanner sc = new Scanner(Main.class.getResourceAsStream("easy"));
    Scanner scTF = new Scanner(Main.class.getResourceAsStream("trueFalse"));

    // Constructor
    public Puzzle() {
        super();
    }

    // Generate a new puzzle given the number of cells to be guessed, which can be used
    //  to control the difficulty level.
    // This method shall set (or update) the arrays numbers and isGiven
    public void newPuzzle(int cellsToGuess) {
        // I hardcode a puzzle here for illustration and testing.
//        numbers = puzzleCreator();
//        int[][] hardcodedNumbers =
//                {{5, 3, 4, 0, 7, 8, 9, 1, 2},
//                        {6, 7, 2, 1, 9, 5, 3, 4, 8},
//                        {1, 9, 8, 3, 4, 2, 5, 6, 7},
//                        {8, 5, 9, 7, 6, 1, 4, 2, 3},
//                        {4, 2, 6, 8, 5, 3, 7, 9, 1},
//                        {7, 1, 3, 9, 2, 4, 8, 5, 6},
//                        {9, 6, 1, 5, 0, 7, 2, 8, 4},
//                        {2, 8, 7, 4, 1, 9, 6, 3, 5},
//                        {3, 4, 5, 2, 8, 6, 1, 7, 9}};
//
//        solver.solveSudoku(hardcodedNumbers,0,0);

        // Copy from hardcodedNumbers into the array "numbers"
        String p = sc.nextLine();
        int i = 0;
        for (int row = 0; row < SudokuConstants.GRID_SIZE; ++row) {
            for (int col = 0; col < SudokuConstants.GRID_SIZE; ++col) {
                numbers[row][col] = Integer.parseInt("" + p.charAt(i));
                i++;
            }
        }
        solver.solveSudoku(numbers,0,0);

        // Need to use input parameter cellsToGuess!
        // Hardcoded for testing, only 2 cells of "8" is NOT GIVEN
        boolean[][] hardcodedIsGiven =
                {{true, true, true, true, true, false, true, true, true},
                        {true, true, true, true, true, true, true, true, false},
                        {true, true, false, true, true, true, true, true, true},
                        {true, true, true, true, true, true, true, true, true},
                        {true, false, true, true, true, true, true, true, true},
                        {true, true, true, true, true, true, true, true, true},
                        {true, true, true,   true, true, true, false, true, true},
                        {true, true, true, true, true, false, true, true, true},
                        {true, true, true, true, true, true, true, true, true}};

        // Copy from hardcodedIsGiven into array "isGiven
        i = 0;
        String pTF = "";
        if(scTF.hasNext()) pTF = scTF.nextLine();
        for (int row = 0; row < SudokuConstants.GRID_SIZE; ++row) {
            for (int col = 0; col < SudokuConstants.GRID_SIZE; ++col) {
                if(pTF.equals("")){
                    isGiven[row][col] = hardcodedIsGiven[row][col];
                } else{
                    isGiven[row][col] = Integer.parseInt("" + pTF.charAt(i)) != 0;
                    i++;
                }
            }
        }
    }
    public boolean check(int row, int col){
        for (int i = 0; i < 9; i++) {
            if (i != col && numbers[row][col] == numbers[row][i]) {
                return false;
            }
            if (i != row && numbers[row][col] == numbers[i][col]) {
                return false;
            }
        }
        for (int r = (row - 1) / 3 * 3 + 1; r <= (row + 2) / 3 * 3; r++) {
            for (int c = (col - 1) / 3 * 3 + 1; c <= (col + 2) / 3 * 3; c++) {
                if (r != row && c != col && numbers[row][col] == numbers[r][c]) {
                    return false;
                }
            }
        }
        return true;
    }
    public int[][] puzzleCreator(){
        Random r = new Random();
        for(int row = 0; row < 9; row++){
            for(int col = 0; col < 9; col++){
                do {
                    numbers[row][col] = r.nextInt(9) + 1;
                } while (!check(row, col));
            }
        }
        return numbers;
    }
    //(For advanced students) use singleton design pattern for this class
}

