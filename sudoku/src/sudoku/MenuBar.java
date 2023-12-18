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

public class MenuBar extends JMenuBar{
    JMenu file = new JMenu("File");
    JMenuItem newGame = new JMenuItem("New Game");
    JMenuItem resetGame = new JMenuItem("Reset Game");
    JMenuItem exitGame = new JMenuItem("Exit Game");
    JMenu option = new JMenu("Options");
    ButtonGroup difficulties = new ButtonGroup();
    JRadioButtonMenuItem easy = new JRadioButtonMenuItem("Easy");
    JRadioButtonMenuItem medium = new JRadioButtonMenuItem("Medium");
    JRadioButtonMenuItem hard = new JRadioButtonMenuItem("Hard");
    ButtonGroup puzzleSource = new ButtonGroup();
    JRadioButtonMenuItem generator = new JRadioButtonMenuItem("Puzzle Generator");
    JRadioButtonMenuItem template = new JRadioButtonMenuItem("Puzzle Template");
    JMenu help = new JMenu("Help");
    JMenuItem about = new JMenuItem("About");

    public MenuBar(){
        exitGame.addActionListener(e -> {
            System.exit(0);
        });

        file.add(newGame);
        file.add(resetGame);
        file.addSeparator();
        file.add(exitGame);

        easy.setSelected(true);

        difficulties.add(easy);
        difficulties.add(medium);
        difficulties.add(hard);
        option.add(easy);
        option.add(medium);
        option.add(hard);
        option.addSeparator();
        puzzleSource.add(generator);
        puzzleSource.add(template);
        generator.setSelected(true);
        option.add(generator);
        option.add(template);

        about.addActionListener(e -> {
            SwingUtilities.invokeLater(AboutFrame::new);
//            JOptionPane.showMessageDialog(null, "Sudoku & Tic Tac Toe built with Java & Swing\nVersion 1.0\n\nCreated by: Tito Virgiawan, Maureen Ghassani, Mutiara Noor", "About", JOptionPane.INFORMATION_MESSAGE);
        });

        help.add(about);

        add(file);
        add(option);
        add(help);
    }
}