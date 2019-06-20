package com.pavelk.cells;

import java.util.ArrayList;
import java.util.List;


public class ResultCell extends Cell {
    public static List<ResultCell> getResultCellList() {
        return resultCellList;
    }

    public static void addToResultCellList(ResultCell resultCell) {
        if (resultCell != null)
            resultCellList.add(resultCell);
    }

    private static List<ResultCell> resultCellList = new ArrayList<>();

    private Cell cell;
    private External3GCell externalCell;


    public ResultCell(Cell cell, External3GCell externalCell) {
        this.cell = cell;
        this.externalCell = externalCell;
    }

    public Cell getCell() {
        return cell;
    }

    public External3GCell getExternalCell() {
        return externalCell;
    }
}
