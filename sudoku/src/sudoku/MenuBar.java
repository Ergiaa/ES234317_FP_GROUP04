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

        add(file);
        add(option);
    }
}