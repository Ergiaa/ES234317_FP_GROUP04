/**
 * ES234317-Algorithm and Data Structures
 * Semester Ganjil, 2023/2024
 * Group Capstone Project
 * Group #4
 * 1 - 5026221045 - Mutiara Noor Fauzia
 * 2 - 5026221096 - Viera Tito Virgiawan
 * 3 - 5026221193 - Maureen Ghassani Fadhliphya
 */
package sudoku;

import java.util.*;

public class Puzzle {
    // All variables have package access
    // The numbers on the puzzle
    int[][] numbers = new int[SudokuConstants.GRID_SIZE][SudokuConstants.GRID_SIZE];
    // The clues - isGiven (no need to guess) or need to guess
    boolean[][] isGiven = new boolean[SudokuConstants.GRID_SIZE][SudokuConstants.GRID_SIZE];
    Solver solver = new Solver();
    Scanner sc = new Scanner(Objects.requireNonNull(Main.class.getResourceAsStream("puzzleNums")));
    Scanner scTF = new Scanner(Objects.requireNonNull(Main.class.getResourceAsStream("trueFalse")));

    // Constructor
    public Puzzle() {
        super();
    }
    public void newPuzzle(int cellsToGuess) {
        //Fill array numbers from file puzzleNums
        String p = sc.nextLine();
        int i = 0;
        for (int row = 0; row < SudokuConstants.GRID_SIZE; ++row) {
            for (int col = 0; col < SudokuConstants.GRID_SIZE; ++col) {
                numbers[row][col] = Integer.parseInt("" + p.charAt(i));
                i++;
            }
        }
        //Since puzzle from puzzleNums is incomplete, complete using a solver
        solver.solve(numbers);
//        solver.solveSudoku(numbers,0,0);

        // Fill array isGiven from file trueFalse
        i = 0;
        String pTF = "";
        if(scTF.hasNext()) pTF = scTF.nextLine();
        for (int row = 0; row < SudokuConstants.GRID_SIZE; ++row) {
            for (int col = 0; col < SudokuConstants.GRID_SIZE; ++col) {
                isGiven[row][col] = Integer.parseInt("" + pTF.charAt(i)) != 0;
                i++;
            }
        }
    }
}

