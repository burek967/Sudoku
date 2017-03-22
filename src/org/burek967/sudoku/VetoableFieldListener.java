package org.burek967.sudoku;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;

public class VetoableFieldListener implements VetoableChangeListener {
    private final int maxval;

    public VetoableFieldListener(int range){
        super();
        maxval = range;
    }

    @Override
    public void vetoableChange(PropertyChangeEvent evt) throws PropertyVetoException {
        SudokuFieldChangeEvent e = (SudokuFieldChangeEvent) evt;
        if(e.getVal().isEmpty())
            return;
        int newval;
        try {
            newval = Integer.parseInt(e.getVal());
        } catch(NumberFormatException ex) {
            throw new PropertyVetoException("Not a number", evt);
        }
        if(newval <= 0 || newval > maxval)
            throw new PropertyVetoException("Number out of range", evt);
        Field f = e.getField();
        for(Field x : f.getGrid().getNeighbouringFields(f))
            if (x.isBlocked() && !x.isEmpty() && x.getValue() == newval)
                throw new PropertyVetoException("Field is in conflict with a fixed one: " + x, evt);
    }
}
