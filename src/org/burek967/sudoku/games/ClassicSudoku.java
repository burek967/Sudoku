package org.burek967.sudoku.games;

import org.burek967.sudoku.Field;
import org.burek967.sudoku.Grid;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;

public class ClassicSudoku extends JFrame {

    private static final int data[][] = {
            {2,0,0,3,8,4,6,0,0},
            {0,0,0,0,0,0,0,0,0},
            {3,0,0,0,0,5,4,9,0},
            {0,5,0,2,0,0,0,0,0},
            {1,0,0,0,5,0,0,0,9},
            {0,0,0,1,0,3,0,6,0},
            {8,4,6,0,0,0,2,0,0},
            {0,0,0,0,0,0,0,0,5},
            {0,0,2,8,7,1,3,0,0},
    };

    private void initGrid(){
        Grid g = new Grid(9);
        for(int i=0;i<9;++i)
            for(int j=0;j<9;++j){
                Field f;
                if(data[i][j] == 0)
                    f = new Field();
                else
                    f = new Field(data[i][j]);
                f.setFont(new Font("Arial", Font.BOLD, 25));
                f.setBorder(new MatteBorder(
                        i == 0 ? 3 : 0,
                        j == 0 ? 3 : 0,
                        i % 3 == 2 ? 3 : 1,
                        j % 3 == 2 ? 3 : 1,
                        new Color(0x000000)));
                f.setGroup(i/3 * 3 + j/3);
                add(f);
                g.setField(i, j, f);
            }
    }

    public ClassicSudoku() {
        super("Classic Sudoku");
        initGrid();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setSize(500,500);
        setLayout(new GridLayout(9,9,0,0));
        ((JPanel)getContentPane()).setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        setVisible(true);
    }

    public static void main(String[] args) {
        new ClassicSudoku();
    }
}
