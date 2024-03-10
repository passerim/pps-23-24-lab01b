package e2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class GridTest {
    private static final int SIZE = 7;
    private static final int MINES = 7;

    @Test
    void testMinesPlacement() {
        final Grid grid = new Grid(SIZE, MINES);
        int numberOfMines = 0;
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (grid.getCell(new Pair<>(i, j)).hasMine()) {
                    numberOfMines++;
                }
            }
        }
        assertEquals(MINES, numberOfMines);
    }

    @Test
    void testAllCellsCreated() {
        final Grid grid = new Grid(SIZE, MINES);
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                assertNotNull(grid.getCell(new Pair<>(i, j)));
            }
        }
    }

    @Test
    void testCorrectNumberOfSiblingInTheCenterOfTheGrid() {
        final Grid grid = new Grid(SIZE, MINES);
        for (int i = 1; i < SIZE - 1; i++) {
            for (int j = 1; j < SIZE - 1; j++) {
                assertEquals(8, grid.getCell(new Pair<>(i, j)).getSiblings().size());
            }
        }
    }

    @Test
    void testCorrectNumberOfSiblingOnTheBordersOfTheGrid() {
        final Grid grid = new Grid(SIZE, MINES);
        for (int i = 1; i < SIZE - 1; i++) {
            assertEquals(5, grid.getCell(new Pair<>(i, 0)).getSiblings().size());
            assertEquals(5, grid.getCell(new Pair<>(i, SIZE - 1)).getSiblings().size());
            assertEquals(5, grid.getCell(new Pair<>(0, i)).getSiblings().size());
            assertEquals(5, grid.getCell(new Pair<>(SIZE - 1, i)).getSiblings().size());
        }
    }

    @Test
    void testCorrectNumberOfSiblingInTheCornersOfTheGrid() {
        final Grid grid = new Grid(SIZE, MINES);
        assertEquals(3, grid.getCell(new Pair<>(0, 0)).getSiblings().size());
        assertEquals(3, grid.getCell(new Pair<>(0, SIZE - 1)).getSiblings().size());
        assertEquals(3, grid.getCell(new Pair<>(SIZE - 1, 0)).getSiblings().size());
        assertEquals(3, grid.getCell(new Pair<>(SIZE - 1, SIZE - 1)).getSiblings().size());
    }
}
