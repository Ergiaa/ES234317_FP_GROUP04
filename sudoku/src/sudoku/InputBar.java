package sudoku;
import javax.swing.*;
public class InputBar extends JPanel {
    InputBar(){
        init();
    }
    public void init(){
        JButton btn_1 = new JButton("1");
        btn_1.addActionListener(e -> SudokuMain.input = 1);
        add(btn_1);
        JButton btn_2 = new JButton("2");
        btn_2.addActionListener(e -> SudokuMain.input = 2);
        add(btn_2);
        JButton btn_3 = new JButton("3");
        btn_3.addActionListener(e -> SudokuMain.input = 3);
        add(btn_3);
        JButton btn_4 = new JButton("4");
        btn_4.addActionListener(e -> SudokuMain.input = 4);
        add(btn_4);
        JButton btn_5 = new JButton("5");
        btn_5.addActionListener(e -> SudokuMain.input = 5);
        add(btn_5);
        JButton btn_6 = new JButton("6");
        btn_6.addActionListener(e -> SudokuMain.input = 6);
        add(btn_6);
        JButton btn_7 = new JButton("7");
        btn_7.addActionListener(e -> SudokuMain.input = 7);
        add(btn_7);
        JButton btn_8 = new JButton("8");
        btn_8.addActionListener(e -> SudokuMain.input = 8);
        add(btn_8);
        JButton btn_9 = new JButton("9");
        btn_9.addActionListener(e -> SudokuMain.input = 9);
        add(btn_9);
    }
}
