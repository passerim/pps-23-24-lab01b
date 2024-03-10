package e2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CellTest {

    private static final Pair<Integer, Integer> DEFAULT_POSITION = new Pair<>(0, 0);
    private List<Cell> cellSiblings;
    private int numberOfAdjacentMines;
    private Cell cell;

    List<Cell> createSiblings() {
        final List<Cell> siblings = new ArrayList<>();
        final Cell sibling1 = new Cell(new Pair<>(0, 1));
        final Cell sibling2 = new Cell(new Pair<>(1, 1));
        final Cell sibling3 = new Cell(new Pair<>(1, 0));
        final Cell sibling4 = new Cell(new Pair<>(0, 0));
        sibling2.placeMine();
        sibling4.placeMine();
        siblings.add(sibling1);
        siblings.add(sibling2);
        siblings.add(sibling3);
        siblings.add(sibling4);
        return siblings;
    }

    @BeforeEach
    void setupTests() {
        this.cell = new Cell(DEFAULT_POSITION);
        this.cellSiblings = createSiblings();
        this.numberOfAdjacentMines = (int) cellSiblings.stream().filter(Cell::hasMine).count();
        this.cellSiblings.forEach(this.cell::addSibling);
    }

    @Test
    void testAddSiblings() {
        cellSiblings.forEach(cell::addSibling);
        assertEquals(cellSiblings.size(), cell.getSiblings().size());
    }

    @Test
    void testAddSiblingTwoTimes() {
        final Cell sibling = new Cell(new Pair<>(1, 1));
        cell.addSibling(sibling);
        cell.addSibling(sibling);
        assertEquals(cellSiblings.size() + 1, cell.getSiblings().size());
    }

    @Test
    void testGetPosition() {
        assertEquals(DEFAULT_POSITION, cell.getPosition());
    }

    @Test
    void testGetCounter() {
        cellSiblings.forEach(cell::addSibling);
        assertEquals(numberOfAdjacentMines, cell.getCounter());
    }

    @Test
    void testGetSiblings() {
        cellSiblings.forEach(cell::addSibling);
        assertEquals(cellSiblings, cell.getSiblings());
    }

    @Test
    void testIsNotSweptOnCreation() {
        assertFalse(cell.isSwept());
    }

    @Test
    void testNoFlagOnCreation() {
        assertFalse(cell.hasFlag());
    }

    @Test
    void testNoMineOnCreation() {
        assertFalse(cell.hasMine());
    }

    @Test
    void testPlaceMine() {
        cell.placeMine();
        assertTrue(cell.hasMine());
    }

    @Test
    void testSweep() {
        cell.sweep();
        assertTrue(cell.isSwept());
    }

    @Test
    void testPutFlag() {
        cell.toggleFlag();
        assertTrue(cell.hasFlag());
    }

    @Test
    void testRemoveFlag() {
        cell.toggleFlag();
        cell.toggleFlag();
        assertFalse(cell.hasFlag());
    }
}
