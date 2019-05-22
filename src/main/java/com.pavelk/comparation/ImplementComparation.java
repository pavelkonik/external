package com.pavelk.comparation;

import com.pavelk.cells.ResultCell;

import java.util.ArrayList;
import java.util.List;

import static com.pavelk.cells.Cell.cellList;
import static com.pavelk.cells.External3GCell.external3GCells;
import static com.pavelk.cells.ResultCell.resultCellList;

public class ImplementComparation implements Comparation{
    private List<ResultCell> pscExternal3GIncorrect = new ArrayList<>();

    public List<ResultCell> getPscExternal3GIncorrect() {
        return pscExternal3GIncorrect;
    }

    @Override
    public List<ResultCell> pscExternal3GComparation() {
      //  pscExternal3GIncorrect = new ArrayList<>();
        for (int i = 0; i < external3GCells.size() ; i++) {
            for (int j = 0; j <cellList.size() ; j++) {
                if (external3GCells.get(i).getCellName().equals(cellList.get(j).getCellName())) {
                    if (external3GCells.get(i).getPsc()!= cellList.get(j).getPsc()) {
                        pscExternal3GIncorrect.add(new ResultCell(cellList.get(j), external3GCells.get(i)));
                    }
                }
            }
        }
        return pscExternal3GIncorrect;
    }

    @Override
    public List<ResultCell> pscExternal2GComparation() {
        return null;
    }

    @Override
    public List<ResultCell> pscSectorComparation() {
        return null;
    }
}
