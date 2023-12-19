/**
 * ES234317-Algorithm and Data Structures
 * Semester Ganjil, 2023/2024
 * Group Capstone Project
 * Group #4
 * 1 - 5026221045 - Mutiara Noor Fauzia
 * 2 - 5026221096 - Viera Tito Virgiawan
 * 3 - 5026221193 - Maureen Ghassani Fadhliphya
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
/**
 * Tic-Tac-Toe: Two-player Graphic version with better OO design.
 * The Board and Cell classes are separated in their own classes.
 */
public class GameMain extends JPanel {
    private static final long serialVersionUID = 1L; // to prevent serializable warning

    // Define named constants for the drawing graphics
    public static final String TITLE = "Tic Tac Toe";
    public static final Color COLOR_BG = new Color(241, 250, 238);
    public static final Color COLOR_BG_STATUS = new Color(29, 53, 87);
    public static final Color COLOR_CROSS = new Color(230, 57, 70);
    public static final Color COLOR_NOUGHT = new Color(168, 218, 220);
    public static final Font FONT_STATUS = new Font("NUNITO", Font.PLAIN, 14);

    // Define game objects
    private Board board;         // the game board
    private State currentState;  // the current state of the game
    private Seed currentPlayer;  // the current player
    private JLabel statusBar;    // for displaying status message
    private JButton restartButton; // for restarting the game
    private JButton aboutButton;   // for dev information
    private JButton helpButton; // for displaying game rules

    /** Constructor to setup the UI and game components */
    public GameMain() {

        // This JPanel fires MouseEvent
        super.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {  // mouse-clicked handler
                int mouseX = e.getX();
                int mouseY = e.getY();
                // Get the row and column clicked
                int row = mouseY / Cell.SIZE;
                int col = mouseX / Cell.SIZE;

                if (currentState == State.PLAYING) {
                    if (row >= 0 && row < Board.ROWS && col >= 0 && col < Board.COLS
                            && board.cells[row][col].content == Seed.NO_SEED) {
                        // Update cells[][] and return the new game state after the move
                        currentState = board.stepGame(currentPlayer, row, col);
                        // Switch player
                        currentPlayer = (currentPlayer == Seed.CROSS) ? Seed.NOUGHT : Seed.CROSS;
                    }
                } else {        // game over
                    newGame();  // restart the game
                }
                // Refresh the drawing canvas
                repaint();  // Callback paintComponent().
            }
        });

        JPanel tbPanel = new JPanel();
        tbPanel.setLayout(new BorderLayout());

        // Setup the status bar (JLabel) to display status message
        statusBar = new JLabel();
        statusBar.setFont(FONT_STATUS);
        statusBar.setBackground(COLOR_BG_STATUS);
        statusBar.setOpaque(true);
        statusBar.setPreferredSize(new Dimension(300, 30));
        statusBar.setHorizontalAlignment(JLabel.CENTER);
        statusBar.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 12));

        JPanel tPanel = new JPanel();
        tPanel.setLayout(new BorderLayout());
        tPanel.add(statusBar, BorderLayout.PAGE_START);

        JPanel bPanel = new JPanel();
        bPanel.setLayout(new BorderLayout());

        restartButton = new JButton("Restart");
        restartButton.setPreferredSize(new Dimension(150, 30));
        restartButton.setHorizontalAlignment(JButton.CENTER);
        restartButton.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));

        restartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                newGame();
                repaint();
            }
        });

        helpButton = new JButton("Help");
        helpButton.setPreferredSize(new Dimension(150, 30));
        helpButton.setHorizontalAlignment(JButton.CENTER);
        helpButton.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

        helpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showHelp(); // Display help information
            }
        });

        aboutButton = new JButton("About");
        aboutButton.setPreferredSize(new Dimension(150, 30));
        aboutButton.setHorizontalAlignment(JButton.CENTER);
        aboutButton.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));

        aboutButton.addActionListener(e -> {
            SwingUtilities.invokeLater(About::new);
        });

        bPanel.add(restartButton, BorderLayout.LINE_START);
        bPanel.add(aboutButton, BorderLayout.LINE_END);
        bPanel.add(helpButton, BorderLayout.CENTER);

        tbPanel.add(tPanel, BorderLayout.NORTH);
        tbPanel.add(bPanel, BorderLayout.SOUTH);

        super.setLayout(new BorderLayout());
        //super.add(tPanel, BorderLayout.PAGE_START);
        super.add(tbPanel, BorderLayout.PAGE_END);
        super.setPreferredSize(new Dimension(Board.CANVAS_WIDTH, Board.CANVAS_HEIGHT + 60));
        super.setBorder(BorderFactory.createLineBorder(COLOR_BG_STATUS, 0, false));

        // Set up Game
        initGame();
        newGame();
    }

    /** Initialize the game (run once) */
    public void initGame() {
        board = new Board();  // allocate the game-board
    }

    /** Reset the game-board contents and the current-state, ready for new game */
    public void newGame() {
        for (int row = 0; row < Board.ROWS; ++row) {
            for (int col = 0; col < Board.COLS; ++col) {
                board.cells[row][col].content = Seed.NO_SEED; // all cells empty
            }
        }
        currentPlayer = Seed.CROSS;    // cross plays first
        currentState = State.PLAYING;  // ready to play
    }

    /** Custom painting codes on this JPanel */
    @Override
    public void paintComponent(Graphics g) {  // Callback via repaint()
        super.paintComponent(g);
        setBackground(COLOR_BG); // set its background color

        board.paint(g);  // ask the game board to paint itself

        // Print status-bar message
        if (currentState == State.PLAYING) {
            statusBar.setForeground(new Color(241, 250, 238));
            statusBar.setText((currentPlayer == Seed.CROSS) ? "X's Turn" : "O's Turn");
        } else if (currentState == State.DRAW) {
            statusBar.setForeground(Color.RED);
            statusBar.setText("It's a Draw! Click to play again.");
        } else if (currentState == State.CROSS_WON) {
            statusBar.setForeground(new Color(241, 250, 238));
            statusBar.setText("'X' Won! Click to play again.");
        } else if (currentState == State.NOUGHT_WON) {
            statusBar.setForeground(new Color(241, 250, 238));
            statusBar.setText("'O' Won! Click to play again.");
        }
    }
    private void showHelp() {
        String helpMessage = "Welcome to Tic-Tac-Toe!\n\n" +
                "How to play:\n" +
                "- Click on an empty cell to make a move.\n" +
                "- The game is won by the player who succeeds in placing three of their marks in a horizontal, vertical, or diagonal row.\n" +
                "- If all cells are filled and no player has won, the game is a draw.\n\n" +
                "Enjoy playing!";

        JOptionPane.showMessageDialog(this, helpMessage, "Game Help", JOptionPane.INFORMATION_MESSAGE);
    }

    /** The entry "main" method */
    public static void main(String[] args) {
        // Run GUI construction codes in Event-Dispatching thread for thread safety
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JFrame frame = new JFrame(TITLE);
                // Set the content-pane of the JFrame to an instance of main JPanel
                frame.setContentPane(new GameMain());
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.pack();
                frame.setLocationRelativeTo(null); // center the application window
                frame.setVisible(true);            // show it
            }
        });
    }
}