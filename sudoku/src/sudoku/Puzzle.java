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

    // Constructor
    public Puzzle() {
        super();
    }
    public void newPuzzle(int difficulties, int sourcePuzzle) {
        if(sourcePuzzle == 0) {
            //Run Puzzle Generator if generator is chosen as Source of Puzzle
            generatePuzzle(numbers, 0, 0);
        } else if(sourcePuzzle == 1){
            //Take one line of Puzzle Template from file randomly if template is chosen as Source of Puzzle
            int random = r.nextInt(11);
            String template = "";
            for(int x = 0; x <= random; x++) template = sc.nextLine();

            //Put Puzzle Template into numbers array
            int chr = 0;
            for(int i = 0; i < 9; i++){
                for(int j = 0; j < 9; j++){
                    numbers[i][j] = Integer.parseInt("" + template.charAt(chr));
                    chr++;
                }
            }
        }
        //Solve incomplete puzzle with solver
        solver.solve(numbers);
//        solver.solveSudoku(numbers,0,0);

        //Generate Array consisting of boolean to decide which cell is given
        isGiven = generateGiven(isGiven, difficulties);
    }
    public boolean generatePuzzle(int[][] puzzle, int row, int col){
        //Create a collection of numbers from 1 to 9 then shuffle it
        ArrayList<Integer> nums = new ArrayList<>();
        for(int i = 1; i <= 9; i++) nums.add(i);
        Collections.shuffle(nums);

        //Check position of current cell
        if(col == 9){
            col = 0;
            if(++row == 9){
                return true;
            }
        }
        //Use collection of numbers in a cell and check if it is safe to be in that cell
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

