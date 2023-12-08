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
