package sudoku;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class SudokuMain extends JFrame {
    private static final long serialVersionUID = 1L;
    public static int input;

    private int secondsPassed;

    private GameBoardPanel board;
    private JButton btnNewGame;
    private JButton btnHint;
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

        // Create a button to start a new game
        btnNewGame = new JButton("New Game");
        btnHint = new JButton("Hint");
        btnNewGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                secondsPassed = 0;
                updateTimerLabel();
                board.newGame();
            }
        });
//        btnHint.addActionListener(e -> {
//            Random r = new Random();
//            int row = 0, col = 0;
//            for(int i = 0; i < 9; i++){
//                for(int j = 0; j < 9; j++){
//                    if(!board.getPuzzle().isGiven[i][j]){
//                        row = i;
//                        col = j;
//                        break;
//                    }
//                }
//                if(!board.getPuzzle().isGiven[row][col]){
//                    break;
//                }
//            }
//            board.getCells()[row][col].setText("" + board.getPuzzle().numbers[row][col]);
//            board.getCells()[row][col].paint();
//            if (board.isSolved()) {
//                timer.stop();
//                JOptionPane.showMessageDialog(null, "Congratulations! You've solved the puzzle!");
//            }
//        });
        cp.add(btnNewGame, BorderLayout.SOUTH);
//        cp.add(btnHint, BorderLayout.LINE_END);

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