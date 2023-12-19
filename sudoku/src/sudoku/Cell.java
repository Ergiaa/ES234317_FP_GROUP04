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

public class Cell extends JButton {
    private static final long serialVersionUID = 1L;  // to prevent serial warning

    // Define named constants for JTextField's colors and fonts
    //  to be chosen based on CellStatus
    public static final Color BG_GIVEN = new Color(29,53,87); // RGB
    public static final Color FG_GIVEN = new Color(241, 250, 238);
    public static final Color FG_NOT_GIVEN = new Color(45,30,47);
    public static final Color BG_TO_GUESS  = new Color(69, 123, 157);
    public static final Color BG_CORRECT_GUESS = new Color(168, 218, 220);
    public static final Color BG_WRONG_GUESS   = new Color(230, 57, 70);
    public static final Font FONT_NUMBERS = new Font("ROBOTO", Font.PLAIN, 28);

    // Define properties (package-visible)
    /** The row and column number [0-8] of this cell */
    int row, col;
    /** The puzzle number [1-9] for this cell */
    int number;
    /** The status of this cell defined in enum CellStatus */
    CellStatus status;

    /** Constructor */
    public Cell(int row, int col) {
        super();   // JTextField
        this.row = row;
        this.col = col;
        // Inherited from JTextField: Beautify all the cells once for all
        super.setHorizontalAlignment(JTextField.CENTER);
        super.setFont(FONT_NUMBERS);
        setCellBorder(row,col);
    }
    private void setCellBorder(int row, int col) {
        int top = (row % 3 == 0) ? 3 : 1;
        int left = (col % 3 == 0) ? 3 : 1;
        int bottom = (row % 3 == 2) ? 3 : 1;
        int right = (col % 3 == 2) ? 3 : 1;

        setBorder(BorderFactory.createMatteBorder(top, left, bottom, right, Color.BLACK));
    }
    /** Reset this cell for a new game, given the puzzle number and isGiven */
    public void newGame(int number, boolean isGiven) {
        this.number = number;
        setEnabled(true);
        status = isGiven ? CellStatus.GIVEN : CellStatus.TO_GUESS;
        paint();    // paint itself
    }
    public void endGame(){
        super.setEnabled(false);
    }

    /** This Cell (JTextField) paints itself based on its status */
    public void paint() {

//        if ((row / 3 + col / 3) % 2 == 0) {
//            super.setBackground(BG_GIVEN);
//        } else {
//            super.setBackground(BG_TO_GUESS);
//        }

        if (status == CellStatus.GIVEN) {
            super.setText(number + "");
            super.setBackground(BG_GIVEN);
            super.setForeground(FG_GIVEN);
        } else if (status == CellStatus.TO_GUESS) {
            super.setText("");
            super.addActionListener(e -> {
                if(status == CellStatus.TO_GUESS || status == CellStatus.WRONG_GUESS){
                    setText("" + SudokuMain.input);
                }
            });
            super.setBackground(BG_TO_GUESS);
            super.setForeground(FG_NOT_GIVEN);
        } else if (status == CellStatus.CORRECT_GUESS) {  // from TO_GUESS
            super.setBackground(BG_CORRECT_GUESS);
        } else if (status == CellStatus.WRONG_GUESS) {    // from TO_GUESS
            super.setBackground(BG_WRONG_GUESS);
        }
    }
    public int getNumber(){
        return number;
    }
    public void setStatus(CellStatus status) {
        this.status = status;
    }
}
