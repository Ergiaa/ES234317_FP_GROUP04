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

public class Mistake extends JTextField {
    private int i = 0;
    public Mistake(){
        setText("0/3 Mistake");
        setEditable(false);
        setForeground(Color.BLACK);
        setHorizontalAlignment(JTextField.CENTER);
    }
    public void change(){
        i++;
        setText(i + "/3 Mistake");
    }
    public void reset(){
        i = 0;
        setText(i + "/3 Mistake");
    }

    public int getMistake() {
        return i;
    }
}
