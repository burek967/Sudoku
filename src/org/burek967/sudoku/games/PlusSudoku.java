package org.burek967.sudoku.games;

import org.burek967.sudoku.Field;
import org.burek967.sudoku.Grid;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;

public class PlusSudoku extends JFrame {

    private static final int data[][] = {
            {5,1,2,0,0},
            {0,0,5,0,0},
            {0,0,4,0,0},
            {0,0,3,0,0},
            {0,0,1,3,4},
    };

    private static final short groups[][] = {
            {1,1,1,2,2},
            {1,1,5,2,2},
            {4,5,5,5,2},
            {4,4,5,3,3},
            {4,4,3,3,3},
    };

    private void initGrid(){
        Grid g = new Grid(5);
        for(int i=0;i<5;++i)
            for(int j=0;j<5;++j){
                Field f;
                if(data[i][j] == 0)
                    f = new Field();
                else
                    f = new Field(data[i][j]);
                f.setFont(new Font("Arial", Font.BOLD, 40));
                f.setBorder(new MatteBorder(
                        i == 0 ? 5 : (groups[i-1][j] != groups[i][j] ? 2 : 0),
                        j == 0 ? 5 : (groups[i][j-1] != groups[i][j] ? 2 : 0),
                        i == 4 ? 5 : (groups[i+1][j] != groups[i][j] ? 3 : 1),
                        j == 4 ? 5 : (groups[i][j+1] != groups[i][j] ? 3 : 1),
                        new Color(0x000000)));
                f.setGroup(groups[i][j]);
                add(f);
                g.setField(i, j, f);
            }
    }

    public PlusSudoku() {
        super("\"Plus\" Sudoku");
        initGrid();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setSize(500,500);
        setLayout(new GridLayout(5,5,0,0));
        ((JPanel)getContentPane()).setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        setVisible(true);
    }

    public static void main(String[] args) {
        new PlusSudoku();
    }
}
