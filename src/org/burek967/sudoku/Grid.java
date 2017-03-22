package org.burek967.sudoku;

import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.beans.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class Grid implements Serializable {

    private final int size;
    private final Field[][] state;
    private final PropertyChangeSupport mPcs = new PropertyChangeSupport(this);
    private final VetoableChangeSupport mVcs = new VetoableChangeSupport(this);

    public Grid(int s){
        size = s;
        state = new Field[size][size];
        mVcs.addVetoableChangeListener(new VetoableFieldListener(s));
        mPcs.addPropertyChangeListener(new PropertyFieldListener());
    }

    public void setField(int i, int j, Field f) {
        if(i<0 || i>=size || j<0 || j>=size)
            return;
        f.setRow(i);
        f.setCol(j);
        f.setGrid(this);
        f.addActionListener(e -> {
            try {
                mVcs.fireVetoableChange(new SudokuFieldChangeEvent(f, e.getActionCommand()));
            } catch(PropertyVetoException ex) {
                if(f.isEmpty())
                    f.setText("");
                else
                    f.setText("" + f.getValue());
                return;
            }
            if(e.getActionCommand().isEmpty())
                f.clear();
            else
                f.setValue(Integer.parseInt(e.getActionCommand()));
            mPcs.firePropertyChange(new SudokuFieldChangeEvent(f, e.getActionCommand()));
        });
        f.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                super.focusLost(e);
                f.getActionListeners()[0].actionPerformed(new ActionEvent(f, ActionEvent.ACTION_PERFORMED, f.getText()));
            }
        });
        state[i][j] = f;
    }

    private Collection<Field> getRow(int row){
        ArrayList<Field> ret = new ArrayList<>();
        for(int i=0;i<size;++i)
            if(state[row][i] != null)
                ret.add(state[row][i]);
        return ret;
    }

    private Collection<Field> getColumn(int column){
        ArrayList<Field> ret = new ArrayList<>();
        for(int i=0;i<size;++i)
            if(state[i][column] != null)
                ret.add(state[i][column]);
        return ret;
    }

    private Collection<Field> getGroup(int group){
        ArrayList<Field> ret = new ArrayList<>();
        for(int i=0;i<size;++i)
            for(int j=0;j<size;++j)
                if(state[i][j] != null && state[i][j].getGroup() == group)
                    ret.add(state[i][j]);
        return ret;
    }

    public Collection<Field> getNeighbouringFields(Field f) {
        Set<Field> ret = new HashSet<>();
        for(Field x : getRow(f.getRow()))
            if(x != f)
                ret.add(x);
        for(Field x : getColumn(f.getCol()))
            if(x != f)
                ret.add(x);
        for(Field x : getGroup(f.getGroup()))
            if(x != f)
                ret.add(x);
        return ret;
    }
}
