package com.pavelk.comparation;

import com.pavelk.cells.ResultCell;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

import static com.pavelk.cells.Cell.cellList;
import static com.pavelk.cells.External3GCell.external3GCells;

public class ImplementComparation implements Comparation{
    private static final Logger LOGGER = LoggerFactory.getLogger(ImplementComparation.class.getSimpleName());
    private List<ResultCell> pscExternal3GIncorrect;

    public List<ResultCell> getPscExternal3GIncorrect() {
        return pscExternal3GIncorrect;
    }

    @Override
    public List<ResultCell> pscExternal3GComparation() {
        pscExternal3GIncorrect = new ArrayList<>();
        LOGGER.info("check incorrect PSC : ");
        for (int i = 0; i < external3GCells.size() ; i++) {
            LOGGER.info("check " + external3GCells.get(i).getCellName());
            for (int j = 0; j <cellList.size() ; j++) {
                if (external3GCells.get(i).getCellName().equals(cellList.get(j).getCellName())) {
                    if (external3GCells.get(i).getPsc()!= cellList.get(j).getPsc()) {
                        pscExternal3GIncorrect.add(new ResultCell(cellList.get(j), external3GCells.get(i)));
                        LOGGER.info("incorrect: " + external3GCells.get(i).getCellName());
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
