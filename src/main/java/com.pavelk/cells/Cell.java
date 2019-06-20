package com.pavelk.cells;

import java.util.ArrayList;
import java.util.List;

public class Cell {
    public static List<Cell> getCellList() {
        return cellList;
    }

    public static void addToCellList(Cell cell) {
        if (cell != null)
            cellList.add(cell);
    }

    private static List<Cell> cellList = new ArrayList<>();

    private String cellName;
    private int cellId;
    private int rnc;
    private int lac;
    private int psc;

    public Cell() {

    }

    public Cell(String cellName, int cellId, int rnc, int lac, int psc) {
        this.cellName = cellName;
        this.cellId = cellId;
        this.rnc = rnc;
        this.lac = lac;
        this.psc = psc;
    }

    public String getCellName() {
        return cellName;
    }

    public int getCellId() {
        return cellId;
    }

    public int getRnc() {
        return rnc;
    }

    public int getLac() {
        return lac;
    }

    public int getPsc() {
        return psc;
    }
}
