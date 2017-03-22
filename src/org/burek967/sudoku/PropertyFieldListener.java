package org.burek967.sudoku;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class PropertyFieldListener implements PropertyChangeListener {
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        SudokuFieldChangeEvent e = (SudokuFieldChangeEvent) evt;
        Field f = e.getField();
        if(f.isEmpty())
            return;
        boolean conflict = false;
        for(Field x : f.getGrid().getNeighbouringFields(f)) {
            if(x.isWrong() && !f.conflicts(x)){
                boolean t = false;
                for(Field y : x.getGrid().getNeighbouringFields(x))
                    if(x.conflicts(y)){
                        t = true;
                        break;
                    }
                if(!t)
                    x.setWrong(false);
            } else if (f.conflicts(x)) {
                conflict = true;
                f.setWrong(true);
                x.setWrong(true);
            }
        }
        if(!conflict)
            f.setWrong(false);
    }
}
