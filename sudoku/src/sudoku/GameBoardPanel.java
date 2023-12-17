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
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;

public class GameBoardPanel extends JPanel {
    private static final long serialVersionUID = 1L;
    public static final int CELL_SIZE = 60;
    public static final int BOARD_WIDTH = CELL_SIZE * SudokuConstants.GRID_SIZE;
    public static final int BOARD_HEIGHT = CELL_SIZE * SudokuConstants.GRID_SIZE;

    private Cell[][] cells = new Cell[SudokuConstants.GRID_SIZE][SudokuConstants.GRID_SIZE];
    private Puzzle puzzle = new Puzzle();
    private int difficulties = 0;
    private Timer timer;
    private JLabel timerLabel;
    private Mistake mistake;
    private GameStatus status;

    public GameBoardPanel(Timer timer) {
        super.setLayout(new BorderLayout());

        // Create a panel for the top area containing inputBar and timerLabel
        JPanel topPanel = new JPanel(new BorderLayout());

        // Create a label to display the timer
        timerLabel = new JLabel("Timer: 0 seconds");

        InputBar inputBar = new InputBar();
        mistake = new Mistake();
        topPanel.add(inputBar, BorderLayout.SOUTH);

        super.add(topPanel, BorderLayout.PAGE_START);

        JPanel boardPanel = new JPanel();
        boardPanel.setLayout(new GridLayout(SudokuConstants.GRID_SIZE, SudokuConstants.GRID_SIZE));

        for (int row = 0; row < SudokuConstants.GRID_SIZE; ++row) {
            for (int col = 0; col < SudokuConstants.GRID_SIZE; ++col) {
                cells[row][col] = new Cell(row, col);
                boardPanel.add(cells[row][col]);
            }
        }

        super.add(boardPanel, BorderLayout.CENTER);

        CellInputListener listener = new CellInputListener();

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (cells[i][j].isEnabled()) {
                    cells[i][j].addActionListener(listener);
                }
            }
        }

        super.setPreferredSize(new Dimension(BOARD_WIDTH, BOARD_HEIGHT));
        this.timer = timer;

        newGame();
    }

    public void newGame() {
        puzzle.newPuzzle(difficulties);

        for (int row = 0; row < SudokuConstants.GRID_SIZE; ++row) {
            for (int col = 0; col < SudokuConstants.GRID_SIZE; ++col) {
                cells[row][col].newGame(puzzle.numbers[row][col], puzzle.isGiven[row][col]);
            }
        }

        // Reset timer display
        timerLabel.setText("Timer: 0 seconds");
        timer.start();
        mistake.reset();
        status = GameStatus.RUNNING;
    }

    public boolean isSolved() {
        for (int row = 0; row < SudokuConstants.GRID_SIZE; ++row) {
            for (int col = 0; col < SudokuConstants.GRID_SIZE; ++col) {
                if (status != GameStatus.FAILED && cells[row][col].status == CellStatus.TO_GUESS || cells[row][col].status == CellStatus.WRONG_GUESS) {
                    status = GameStatus.SUCCESS;
                    return false;
                }
            }
        }
        return true;
    }
    public void solve(){
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if(cells[i][j].status == CellStatus.TO_GUESS || cells[i][j].status == CellStatus.WRONG_GUESS) {
                    cells[i][j].setText("" + puzzle.numbers[i][j]);
                    cells[i][j].status = CellStatus.CORRECT_GUESS;
                    cells[i][j].paint();
                    if (isSolved()) {
                        timer.stop();
                        JOptionPane.showMessageDialog(null, "Here is the solved puzzle!");
                    }
                }
            }
        }
    }
    public boolean giveHint(){
        int[] nums = {0,1,2,3,4,5,6,7,8};
        ArrayList<Integer> rows = new ArrayList<>(9);
        ArrayList<Integer> cols = new ArrayList<>(9);
        for(int i : nums){
            rows.add(i);
            cols.add(i);
        }
        Collections.shuffle(rows);
        for(int i : rows){
            Collections.shuffle(cols);
            for(int j : cols){
                if(cells[i][j].status == CellStatus.TO_GUESS || cells[i][j].status == CellStatus.WRONG_GUESS){
                    cells[i][j].setText("" + puzzle.numbers[i][j]);
                    cells[i][j].status = CellStatus.CORRECT_GUESS;
                    cells[i][j].paint();
                    return true;
                }
            }
        }
        return false;
    }
    public void endGame(){
        timer.stop();
        status = GameStatus.FAILED;
        for (int row = 0; row < SudokuConstants.GRID_SIZE; ++row) {
            for (int col = 0; col < SudokuConstants.GRID_SIZE; ++col) {
                cells[row][col].endGame();
            }
        }
        JOptionPane.showMessageDialog(null, "Sorry! You make too much mistake, try again!");
    }
    public void resetGame(){
        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                if(cells[i][j].status == CellStatus.CORRECT_GUESS || cells[i][j].status == CellStatus.WRONG_GUESS) {
                    cells[i][j].setText("");
                    cells[i][j].status = CellStatus.TO_GUESS;
                    cells[i][j].paint();
                }
                cells[i][j].setEnabled(true);
            }
        }
        mistake.reset();
    }

    private class CellInputListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Cell sourceCell = (Cell) e.getSource();
            if (sourceCell.status == CellStatus.TO_GUESS || sourceCell.status == CellStatus.WRONG_GUESS) {
                int numberIn = Integer.parseInt(sourceCell.getText());
                System.out.println("You entered " + numberIn);
                if (numberIn == sourceCell.getNumber()) {
                    sourceCell.setStatus(CellStatus.CORRECT_GUESS);
                } else {
                    sourceCell.setStatus(CellStatus.WRONG_GUESS);
                }
                sourceCell.paint();
                if(sourceCell.status == CellStatus.WRONG_GUESS) mistake.change();
                if(mistake.getMistake() == 3) endGame();
                if (isSolved() && status == GameStatus.SUCCESS || status == GameStatus.RUNNING) {
                    timer.stop();
                    JOptionPane.showMessageDialog(null, "Congratulations! You've solved the puzzle!");
                }
            }
        }
    }

    public void setDifficulties(int difficulties) {
        this.difficulties = difficulties;
    }

    public Puzzle getPuzzle() {
        return puzzle;
    }
    public Cell[][] getCells() {
        return cells;
    }
    public Mistake getMistake(){
        return mistake;
    }
}
