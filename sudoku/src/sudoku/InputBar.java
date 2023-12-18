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
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InputBar extends JPanel {
    JButton[] buttons;
    int currInput = 0;
    InputBar(){
        buttons = new JButton[9];
        init();
    }
    public void init(){
        for(int i = 1; i <= 9; i++){
            buttons[i - 1] = new JButton("" + i);
            buttons[i - 1].setBackground(Color.WHITE);
            int val = i;
            buttons[i - 1].addActionListener(e -> {
                if(currInput != val) {
                    SudokuMain.input = val;
                    buttons[val - 1].setBackground(new Color(185, 250, 248));
                    buttons[currInput - 1].setBackground(Color.WHITE);
                    currInput = val;
                }
            });
            add(buttons[i - 1]);
        }
        currInput = 1;
        buttons[0].setBackground(new Color(185,250,248));
    }
}
