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
    Random r = new Random();
    Scanner sc = new Scanner(Objects.requireNonNull(Main.class.getResourceAsStream("puzzleNums")));
    Scanner scTF = new Scanner(Objects.requireNonNull(Main.class.getResourceAsStream("trueFalse")));

    // Constructor
    public Puzzle() {
        super();
    }
    public void newPuzzle(int difficulties) {
        //Fill array numbers from file puzzleNums
        int[][] puzzle = new int[9][9];
        generatePuzzle(puzzle,0,0);
        printPuzzle(puzzle);

        //Since puzzle from puzzleNums is incomplete, complete using a solver
        numbers = puzzle;
        solver.solve(numbers);
//        solver.solveSudoku(numbers,0,0);

        isGiven = generateGiven(isGiven, difficulties);
    }
    public boolean generatePuzzle(int[][] puzzle, int row, int col){
        ArrayList<Integer> nums = new ArrayList<>();
        for(int i = 1; i <= 9; i++) nums.add(i);
        Collections.shuffle(nums);

        if(col == 9){
            col = 0;
            if(++row == 9){
                return true;
            }
        }
        boolean valid = false;
        for(int i : nums){
            if(solver.isSafe(puzzle,row,col,i)){
                puzzle[row][col] = i;
                if(generatePuzzle(puzzle, row, col + 1)) return true;
            }
        }
        puzzle[row][col] = 0;
        return false;
    }
    public boolean[][] generateGiven(boolean[][] isGiven, int difficulties){
        int given = 0;
        Random r = new Random();
        if(difficulties == 0) given = r.nextInt(15) + 35;
        if(difficulties == 1) given = r.nextInt(15) + 25;
        if(difficulties == 2) given = r.nextInt(15) + 15;

        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                isGiven[i][j] = false;
            }
        }

        int prevRow = 0, prevCol = 0;
        for(int i = 0; i <= given; i++) {
            int row = 0, col = 0;
            int count = 0, count2 = 0;
            boolean found = false;
            do {
                row = r.nextInt(9);
                while(row == prevRow) row = r.nextInt(9);
                count = 0;
                do {
                    col = r.nextInt(9);
                    while(col ==prevCol) col = r.nextInt(9);
                    count++;
                    if (!isGiven[row][col]) {
                        found = true;
                        break;
                    }
                    if (count > 9) break;
                } while (isGiven[row][col]);
                if (found) {
                    found = false;
                    break;
                }
                count2++;
                if (count2 > 9) break;
            } while (isGiven[row][col]);

            isGiven[row][col] = true;
            prevRow = row;
            prevCol = col;
        }
        return isGiven;
    }
    public void printPuzzle(int[][] arr){
        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
    }
}

