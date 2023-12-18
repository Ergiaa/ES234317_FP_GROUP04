package sudoku;

import javax.swing.*;

public class AboutFrame extends JFrame {
    public AboutFrame(){
        setSize(400,600);
        JTextArea about = new JTextArea();
        about.setText("Sudoku & Tic Tac Toe built with Java & Swing\nVersion 1.0\n\nCreated by: Tito Virgiawan, Maureen Ghassani, Mutiara Noor");
        about.setEditable(false);

        add(about);
        setTitle("About");
        setVisible(true);
    }
}
