package com.pavelk.cells;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class CellTest {

    private Cell cell1;
    private Cell cell2;
    private Cell cell3;

    @Before
    public void setUp() throws Exception {
        cell1 = new Cell("cell1",1,206,200,123);
        cell2 = new Cell("cell2",2,206,201,124);
        cell3 = new Cell("cell3",3,206,202,125);
    }

    @org.junit.Test
    public void getCellList() {
        List<Cell> expected = Cell.getCellList();

        List<Cell> actual = new ArrayList<>();
        actual.add(cell1);
        actual.add(cell2);
        actual.add(cell3);

        Assert.assertEquals(expected,actual);
    }

    @org.junit.Test
    public void getCellName() {
    }

    @org.junit.Test
    public void getCellId() {
    }

    @org.junit.Test
    public void getRnc() {
    }

    @org.junit.Test
    public void getLac() {
    }

    @org.junit.Test
    public void getPsc() {
    }

    @Test
    public void addToCellList() {

    }

    @Test
    public void getCellList_NO_NULL() {
        //добавим проверку на null
        List<Cell> expected = Cell.getCellList();
        Assert.assertNotNull(expected);
    }
}