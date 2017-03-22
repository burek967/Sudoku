package org.burek967.sudoku;

import javax.swing.*;
import java.awt.*;
import java.io.Serializable;

public class Field extends JTextField implements Serializable {
    private boolean empty;
    private final boolean blocked;
    private boolean wrong;
    private int value;
    private int group;
    private int row;
    private int col;
    private Grid grid;

    public boolean isWrong() {
        return wrong;
    }

    public void setWrong(boolean wrong) {
        this.wrong = wrong;
        setBackground(new Color(wrong ? 0xFF0000 : 0xFFFFFF));
    }

    public boolean isBlocked() {
        return blocked;
    }

    public Grid getGrid() {
        return grid;
    }

    public void setGrid(Grid grid) {
        this.grid = grid;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public void setGroup(int group) {
        this.group = group;
    }

    public int getGroup() {
        return group;
    }

    public boolean isEmpty(){
        return this.empty;
    }

    public int getValue(){
        if(this.isEmpty())
            return -1;
        return value;
    }

    public void setValue(int value){
        this.empty = false;
        this.value = value;
        this.setText("" + value);
    }

    public void clear(){
        this.empty = true;
        this.setWrong(false);
        this.setText("");
        for(Field x : getGrid().getNeighbouringFields(this)) {
            if(x.isWrong()){
                boolean t = false;
                for(Field y : x.getGrid().getNeighbouringFields(x))
                    if(x.conflicts(y)){
                        t = true;
                        break;
                    }
                if(!t)
                    x.setWrong(false);
            }
        }
    }

    private void init(){
        setBackground(new Color(0xFFFFFF));
        setMargin(new Insets(0,0,0,0));
        setHorizontalAlignment(JTextField.CENTER);
    }

    public Field(int val){
        value = val;
        empty = false;
        blocked = true;
        setText("" + val);
        init();
        setEditable(false);
        setForeground(new Color(0x5a5a5a));
    }

    public Field(){
        value = 0;
        blocked = false;
        empty = true;
        init();
        setForeground(new Color(0x000000));
    }

    public boolean conflicts(Field another){
        if(isEmpty() || another.isEmpty() || this == another)
            return false;
        return this.getValue() == another.getValue();
    }

    public String toString(){
        return String.format("Field (%d,%d): %d", getRow(), getCol(), getValue());
    }

}