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

public class AboutFrame extends JFrame {
    public AboutFrame() {
            setSize(400, 600);
            setLocationRelativeTo(null);
            
            ImageIcon image = new ImageIcon("sudoku/src/sudoku/img/dev.png");

            JLabel label = new JLabel("<html><br><center>Sudoku Game</center><center>Version 1.0</center><br><center>Developed by Group 4:</center><br>"
            + "<center>5026221096 - Viera Tito Virgiawan</center>"
            + "<center>5026221045 - Mutiara Noor Fauzia</center>"
            + "<center>5026221193 - Maureen Ghassani Fadhliphya</center><br>"
            + "<center>© Final Project ASD A 2023/2024</center></html>");
            label.setIcon(image);
            label.setHorizontalTextPosition(JLabel.CENTER);
            label.setVerticalTextPosition(JLabel.BOTTOM);
            label.setIconTextGap(10);
            label.setVerticalAlignment(JLabel.CENTER);
            label.setHorizontalAlignment(JLabel.CENTER);
            //label.setBounds(100, 100, 400, 600);

            add(label);
            setTitle("About");
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            setVisible(true);
    }
}
