package sudoku;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameBoardPanel extends JPanel {
    private static final long serialVersionUID = 1L;
    public static final int CELL_SIZE = 60;
    public static final int BOARD_WIDTH = CELL_SIZE * SudokuConstants.GRID_SIZE;
    public static final int BOARD_HEIGHT = CELL_SIZE * SudokuConstants.GRID_SIZE;

    private Cell[][] cells = new Cell[SudokuConstants.GRID_SIZE][SudokuConstants.GRID_SIZE];
    private Puzzle puzzle = new Puzzle();
    private Timer timer;
    private JLabel timerLabel;

    public GameBoardPanel(Timer timer) {
        super.setLayout(new BorderLayout());

        // Create a panel for the top area containing inputBar and timerLabel
        JPanel topPanel = new JPanel(new BorderLayout());

        // Create a label to display the timer
        timerLabel = new JLabel("Timer: 0 seconds");

        InputBar inputBar = new InputBar();
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
        puzzle.newPuzzle(2);

        for (int row = 0; row < SudokuConstants.GRID_SIZE; ++row) {
            for (int col = 0; col < SudokuConstants.GRID_SIZE; ++col) {
                cells[row][col].newGame(puzzle.numbers[row][col], puzzle.isGiven[row][col]);
            }
        }

        // Reset timer display
        timerLabel.setText("Timer: 0 seconds");
    }

    public boolean isSolved() {
        for (int row = 0; row < SudokuConstants.GRID_SIZE; ++row) {
            for (int col = 0; col < SudokuConstants.GRID_SIZE; ++col) {
                if (cells[row][col].status == CellStatus.TO_GUESS || cells[row][col].status == CellStatus.WRONG_GUESS) {
                    return false;
                }
            }
        }
        return true;
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
                if (isSolved()) {
                    timer.stop();
                    JOptionPane.showMessageDialog(null, "Congratulations! You've solved the puzzle!");
                }
            }
        }
    }
}