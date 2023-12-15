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

import java.util.Stack;

public class Solver {
    public boolean solveSudoku(int grid[][], int row, int col)
    {
        if (row == SudokuConstants.GRID_SIZE - 1 && col == SudokuConstants.GRID_SIZE) return true;
        if (col == SudokuConstants.GRID_SIZE) {
            row++;
            col = 0;
        }
        if (grid[row][col] != 0) return solveSudoku(grid, row, col + 1);

        for (int num = 1; num < 10; num++) {
            if (isSafe(grid, row, col, num)) {
                grid[row][col] = num;
                if (solveSudoku(grid, row, col + 1)) return true;
            }
            grid[row][col] = 0;
        }
        return false;
    }
    public boolean solve(int grid[][]){
        Stack<Cell> stack = new Stack<>();
        boolean[][] isLocked = setLocked(grid);
        int curRow = 0;
        int curCol = 0;
        int curValue = 1;
        int time = 0 ;
        while(stack.size() < 81){
            time++;
            if(isLocked[curRow][curCol]){
                Cell lockedCell = new Cell(curRow, curCol);
                lockedCell.number = grid[curRow][curCol];
                stack.push(lockedCell);
                curRow = curRow + (curCol+1)/9;
                curCol = (curCol+1)%9;
                continue;
            }
            for (;curValue <= 9 ; curValue++){
                if (isSafe(grid, curRow, curCol, curValue)){
                    Cell cell = new Cell(curRow, curCol);
                    cell.number = curValue;
                    grid[curRow][curCol] = curValue;
                    stack.push(cell);
                    curRow = curRow + (curCol+1)/9;
                    curCol = (curCol+1)%9;
                    curValue = 1;
                    break;
                }
            }
            if(curValue > 9){
                if (!stack.isEmpty()) {
                    Cell cell = stack.pop();
                    while (isLocked[cell.row][cell.col]) {
                        if (!stack.isEmpty()) {
                            cell = stack.pop();
                        } else {
                            System.out.println("Number of steps: " + time);
                            return false;
                        }
                    }
                    curRow = cell.row;
                    curCol = cell.col;
                    curValue = cell.number + 1;
                    grid[curRow][curCol] =  0;
                } else {
                    System.out.println("Number of steps: " + time);
                    return false;
                }
            }
        }
        return true;
    }
    public boolean[][] setLocked(int[][] board){
        boolean[][] isLocked = new boolean[9][9];
        for(int r = 0 ; r < 9 ; r++){
            for(int c = 0 ; c < 9 ; c++){
                if(board[r][c]!=0){
                    isLocked[r][c] = true;
                }
            }
        }
        return isLocked;
    }
    public boolean isSafe(int[][] grid, int row, int col, int num)
    {
        for (int i = 0; i < 9; i++) {
            if (i != col && num == grid[row][i]) return false;
            if (i != row && num == grid[i][col]) return false;
        }
        int startRow = row - row % 3, startCol = col - col % 3;
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                if (r != row && c != col && num == grid[r + startRow][c + startCol]) return false;
            }
        }
        return true;
    }
}
