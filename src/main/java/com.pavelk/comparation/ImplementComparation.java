package com.pavelk.comparation;

import com.pavelk.cells.Cell;
import com.pavelk.cells.External3GCell;
import com.pavelk.cells.ResultCell;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

//import static com.pavelk.cells.Cell.cellList;
//import static com.pavelk.cells.External3GCell.external3GCells;

public class ImplementComparation implements Comparation{
    private static final Logger LOGGER = LoggerFactory.getLogger(ImplementComparation.class.getSimpleName());
    private List<ResultCell> pscExternal3GIncorrect;

    public List<ResultCell> getPscExternal3GIncorrect() {
        return pscExternal3GIncorrect;
    }

    @Override
    public List<ResultCell> pscExternal3GComparation() {
        pscExternal3GIncorrect = new ArrayList<>();
        for (int i = 0; i < External3GCell.getExternal3GCells().size() ; i++) {
        //    LOGGER.info("check " + external3GCells.get(i).getCellName());
            for (int j = 0; j < Cell.getCellList().size() ; j++) {
                if (External3GCell.getExternal3GCells().get(i).getCellName().equals(Cell.getCellList().get(j).getCellName())) {
                    if (External3GCell.getExternal3GCells().get(i).getPsc()!= Cell.getCellList().get(j).getPsc()) {
                        pscExternal3GIncorrect.add(new ResultCell(Cell.getCellList().get(j), External3GCell.getExternal3GCells().get(i)));
                        LOGGER.info("incorrect: " + External3GCell.getExternal3GCells().get(i).getCellName());
                    }
                }
            }
        }
        LOGGER.info("pscExternal3GIncorrect SIZE  : " + pscExternal3GIncorrect.size());
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
