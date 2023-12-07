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
                SudokuMain.input = val;
                buttons[val - 1].setBackground(Color.GREEN);
                buttons[currInput - 1].setBackground(Color.WHITE);
                currInput = val;
            });
            add(buttons[i - 1]);
        }
        currInput = 1;
        buttons[0].setBackground(Color.GREEN);
    }
}
