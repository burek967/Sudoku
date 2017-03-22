package org.burek967.sudoku;

import java.beans.PropertyChangeEvent;

public class SudokuFieldChangeEvent extends PropertyChangeEvent {
    private final Field field;
    private final String val;

    public Field getField() {
        return field;
    }

    public String getVal(){
        return val;
    }

    public SudokuFieldChangeEvent(Field f, String newval){
        super(f, null, f, newval);
        this.field = f;
        this.val = newval;
    }

}
