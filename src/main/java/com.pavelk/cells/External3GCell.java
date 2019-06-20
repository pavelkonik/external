package com.pavelk.cells;

import java.util.ArrayList;
import java.util.List;

public class External3GCell extends Cell {
    public static List<External3GCell> getExternal3GCells() {
        return external3GCells;
    }

    public static void addToExternal3GCells(External3GCell external3GCell) {
        if (external3GCell != null)
            external3GCells.add(external3GCell);
    }

    private static List<External3GCell> external3GCells = new ArrayList<>();

    private int nrnc;
    private Cell cell;

    public External3GCell() {
    }

    public External3GCell(int nrnc, Cell cell) {
        this.nrnc = nrnc;
        this.cell = cell;
    }

    public External3GCell(String cellName, int cellId, int rnc, int lac, int psc, int nrnc) {
        super(cellName, cellId, rnc, lac, psc);
        this.nrnc = nrnc;
    }

    public int getNrnc() {
        return nrnc;
    }
}
