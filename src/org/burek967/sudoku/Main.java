package org.burek967.sudoku;

import org.burek967.sudoku.games.ClassicSudoku;
import org.burek967.sudoku.games.LSudoku;
import org.burek967.sudoku.games.PlusSudoku;

import javax.swing.*;

public class Main {
    private JButton classicSudokuButton;
    private JButton lSudokuButton;
    private JButton plusSudokuButton;
    private JPanel panel;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Main");
        frame.setContentPane(new Main().panel);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public Main() {
        classicSudokuButton.addActionListener(e -> new ClassicSudoku());
        plusSudokuButton.addActionListener(e -> new PlusSudoku());
        lSudokuButton.addActionListener(e -> new LSudoku());
    }
}
