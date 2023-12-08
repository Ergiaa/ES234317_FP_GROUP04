package sudoku;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class SudokuMain extends JFrame {
    private static final long serialVersionUID = 1L;
    public static int input = 1;

    private int secondsPassed;

    private GameBoardPanel board;
    private JButton btnNewGame;
    private JButton btnHint;
    private JButton btnSolve;
    private JPanel btnPanel;
    private Timer timer;
    private JLabel timerLabel;
    private int hintCount;

    public SudokuMain() {
        Container cp = getContentPane();
        cp.setLayout(new BorderLayout());

        // Create a timer
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                secondsPassed++;
                updateTimerLabel();
            }
        });

        // Create a label to display the timer
        timerLabel = new JLabel("Timer: 0 seconds");
        timerLabel.setHorizontalAlignment(JLabel.CENTER);
        cp.add(timerLabel, BorderLayout.NORTH);

        // Create instances of GameBoardPanel and InputBar
        board = new GameBoardPanel(timer);
        btnPanel = new JPanel(new GridLayout());

        // Create a button to start a new game
        btnNewGame = new JButton("New Game");
        btnHint = new JButton("Hint");
        btnSolve = new JButton("Solve");
        btnNewGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                secondsPassed = 0;
                hintCount = 0;
                updateTimerLabel();
                board.newGame();
            }
        });
        btnHint.addActionListener(e -> {
            if(hintCount < 3) {
                Random r = new Random();
                int row = 0, col = 0;
                boolean found = false;
                for (int i = 0; i < 9; i++) {
                    for (int j = 0; j < 9; j++) {
                        if (board.getCells()[i][j].status == CellStatus.TO_GUESS || board.getCells()[i][j].status == CellStatus.WRONG_GUESS) {
                            row = i;
                            col = j;
                            found = true;
                            break;
                        }
                    }
                    if (found) {
                        found = false;
                        break;
                    }
                }
                board.getCells()[row][col].setText("" + board.getPuzzle().numbers[row][col]);
                board.getCells()[row][col].status = CellStatus.CORRECT_GUESS;
                board.getCells()[row][col].paint();
                hintCount++;
                if (board.isSolved()) {
                    timer.stop();
                    JOptionPane.showMessageDialog(null, "Congratulations! You've solved the puzzle!");
                }
            }
        });
        btnSolve.addActionListener(e -> {
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    if(board.getCells()[i][j].status == CellStatus.TO_GUESS || board.getCells()[i][j].status == CellStatus.WRONG_GUESS) {
                        board.getCells()[i][j].setText("" + board.getPuzzle().numbers[i][j]);
                        board.getCells()[i][j].status = CellStatus.CORRECT_GUESS;
                        board.getCells()[i][j].paint();
                        if (board.isSolved()) {
                            timer.stop();
                            JOptionPane.showMessageDialog(null, "Here is the solved puzzle!");
                        }
                    }
                }
            }
        });
        btnPanel.add(btnNewGame);
        btnPanel.add(btnHint);
        btnPanel.add(btnSolve);
        cp.add(btnPanel, BorderLayout.SOUTH);

        // Add the GameBoardPanel and InputBar to the center of the layout
        cp.add(board, BorderLayout.CENTER);

        // Initialize the UI
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Sudoku");
        setVisible(true);
    }

    // Update the timer label text
    private void updateTimerLabel() {
        timerLabel.setText("Timer: " + secondsPassed + " seconds");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new SudokuMain());
    }
}