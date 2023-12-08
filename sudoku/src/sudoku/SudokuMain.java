package sudoku;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SudokuMain extends JFrame {
    private static final long serialVersionUID = 1L;
    public static int input;

    private int secondsPassed;

    private GameBoardPanel board;
    private InputBar inputBar;
    private JButton btnNewGame;
    private Timer timer;
    private JLabel timerLabel;

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
        inputBar = new InputBar();

        // Create a button to start a new game
        btnNewGame = new JButton("New Game");
        btnNewGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                secondsPassed = 0;
                updateTimerLabel();
                board.newGame();
            }
        });
        cp.add(btnNewGame, BorderLayout.SOUTH);

        // Add the GameBoardPanel and InputBar to the center of the layout
        cp.add(board, BorderLayout.CENTER);
        //cp.add(inputBar, BorderLayout.PAGE_START);

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