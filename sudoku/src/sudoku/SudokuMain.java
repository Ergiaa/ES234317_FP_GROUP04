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
import java.lang.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SudokuMain extends JFrame {
    private static final long serialVersionUID = 1L;
    public static int input = 1;

    private int secondsPassed;

    private GameBoardPanel board;
    private JButton btnNewGame;
    private JButton btnHint;
    private JButton btnSolve;
    private JButton btnReset;
    private JPanel btnPanel;
    private JPanel btnSubPanel0;
    private JPanel btnSubPanel1;
    private JComboBox<String> difficulties;
    private JTextField mistake;
    private Timer timer;
    private JLabel timerLabel;
    private String[] choices = {"Easy","Medium","Hard"};
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
        btnPanel = new JPanel(new GridLayout(2,1));
        btnSubPanel0 = new JPanel(new GridLayout());
        btnSubPanel1 = new JPanel(new GridLayout());

        // Create a button to start a new game
        btnNewGame = new JButton("New Game");
        btnHint = new JButton("Hint");
        btnSolve = new JButton("Solve");
        btnReset = new JButton("Reset");
        difficulties = new JComboBox<>(choices);
        MenuBar menu = new MenuBar();
        setMenu(menu);
        setJMenuBar(menu);

        mistake = new JTextField();
        mistake.setEnabled(false);
        mistake.setHorizontalAlignment(JTextField.CENTER);
        mistake.setForeground(Color.black);
        mistake.setText("0/3 Mistake");
        btnNewGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                secondsPassed = 0;
                hintCount = 0;
                updateTimerLabel();
                board.newGame();
            }
        });
        btnHint.addActionListener(e -> {
            if(hintCount < 3 && board.giveHint()){
                hintCount++;
                if(board.isSolved()) {
                       timer.stop();
                       JOptionPane.showMessageDialog(null, "Congratulations! You've solved the puzzle!");
                }
            } else if(hintCount == 3){
                JOptionPane.showMessageDialog(null,"You have used all of your hints");
            }
        });
        btnSolve.addActionListener(e -> {
            board.solve();
        });
        btnReset.addActionListener(e -> {
            board.resetGame();
            timerLabel.setText("Timer: 0 seconds");
            secondsPassed = 0;
            timer.start();
            hintCount = 0;
        });
        difficulties.addActionListener(e -> {
            JComboBox<String> choice = (JComboBox<String>) e.getSource();
            int difficulties = choice.getSelectedIndex();

            if(difficulties == 0)menu.easy.setSelected(true);
            if(difficulties == 1)menu.medium.setSelected(true);
            if(difficulties == 2)menu.hard.setSelected(true);

            board.setDifficulties(difficulties);
            System.out.println(difficulties);
        });
        btnSubPanel0.add(btnHint, BorderLayout.NORTH);
        btnSubPanel0.add(btnNewGame, BorderLayout.NORTH);
        btnSubPanel0.add(btnReset, BorderLayout.NORTH);
        btnSubPanel0.add(btnSolve, BorderLayout.NORTH);
        btnSubPanel1.add(difficulties, BorderLayout.NORTH);
        btnSubPanel1.add(board.getMistake(),BorderLayout.SOUTH);
        btnPanel.add(btnSubPanel0);
        btnPanel.add(btnSubPanel1);
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

    public void setMenu(MenuBar menu){
        menu.newGame.addActionListener(e -> {
            secondsPassed = 0;
            hintCount = 0;
            updateTimerLabel();
            board.newGame();
        });
        menu.resetGame.addActionListener(e -> {
            board.resetGame();
            timerLabel.setText("Timer: 0 seconds");
            secondsPassed = 0;
            timer.start();
            hintCount = 0;
        });
        menu.easy.addActionListener(e -> {
            int level = 0;
            board.setDifficulties(level);
            System.out.println(level);
            difficulties.setSelectedIndex(0);
        });
        menu.medium.addActionListener(e -> {
            int level = 1;
            board.setDifficulties(level);
            System.out.println(level);
            difficulties.setSelectedIndex(1);
        });
        menu.hard.addActionListener(e -> {
            int level = 2;
            board.setDifficulties(level);
            System.out.println(level);
            difficulties.setSelectedIndex(2);
        });
    }
}
