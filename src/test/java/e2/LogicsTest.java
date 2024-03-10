package e2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LogicsTest {

    private final int SIZE = 7;
    private final int MINES = 7;

    @Test
    void testMinesPlacement() {
        Logics logics = new LogicsImpl(SIZE, MINES);
        int numberOfMines = 0;
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (logics.aMineWasFound(new Pair<>(i, j))) {
                    numberOfMines++;
                }
            }
        }
        assertEquals(MINES, numberOfMines);
    }

    @Test
    void testAMineWasFound() {
        Logics logics = new LogicsImpl(1, 1);
        assertTrue(logics.aMineWasFound(new Pair<>(0, 0)));
    }

    @Test
    void testAMineWasNotFound() {
        Logics logics = new LogicsImpl(1, 0);
        assertFalse(logics.aMineWasFound(new Pair<>(0, 0)));
    }

    @Test
    void testPositionOutOfBounds() {
        Logics logics = new LogicsImpl(SIZE, MINES);
        Pair<Integer, Integer> position = new Pair<>(SIZE + 1, SIZE + 1);
        assertThrows(IndexOutOfBoundsException.class, () -> logics.aMineWasFound(position));
    }

    @Test
    void testNegativePositionOutOfBounds() {
        Logics logics = new LogicsImpl(SIZE, MINES);
        Pair<Integer, Integer> position = new Pair<>(-1, -1);
        assertThrows(IndexOutOfBoundsException.class, () -> logics.aMineWasFound(position));
    }

    @Test
    void testThereIsVictory() {
        Logics logics = new LogicsImpl(SIZE, SIZE * SIZE);
        assertTrue(logics.isThereVictory());
    }

    @Test
    void testThereIsNotVictory() {
        Logics logics = new LogicsImpl(SIZE, MINES);
        assertFalse(logics.isThereVictory());
    }

    @Test
    void testThereIsCounter() {
        Logics logics = new LogicsImpl(SIZE, 0);
        Pair<Integer, Integer> position = new Pair<>(0, 0);
        logics.aMineWasFound(position);
        assertTrue(logics.isThereCounter(position));
    }

    @Test
    void testThereIsNotCounterOnCellCreation() {
        Logics logics = new LogicsImpl(SIZE, 0);
        Pair<Integer, Integer> position = new Pair<>(0, 0);
        assertFalse(logics.isThereCounter(position));
    }

    @Test
    void testGetCounter() {
        Logics logics = new LogicsImpl(SIZE, 0);
        Pair<Integer, Integer> position = new Pair<>(0, 0);
        logics.aMineWasFound(position);
        assertEquals(0, logics.getCounter(position));
    }

    @Test
    void testThereIsFlag() {
        Logics logics = new LogicsImpl(SIZE, 0);
        Pair<Integer, Integer> position = new Pair<>(0, 0);
        logics.toggleFlag(position);
        assertTrue(logics.isThereFlag(position));
    }

    @Test
    void testThereIsNotFlagOnCellCreation() {
        Logics logics = new LogicsImpl(SIZE, 0);
        Pair<Integer, Integer> position = new Pair<>(0, 0);
        assertFalse(logics.isThereFlag(position));
    }

    @Test
    void testThereIsMine() {
        Logics logics = new LogicsImpl(SIZE, SIZE * SIZE);
        Pair<Integer, Integer> position = new Pair<>(0, 0);
        assertTrue(logics.isThereMine(position));
    }

    @Test
    void testThereIsNotMine() {
        Logics logics = new LogicsImpl(SIZE, 0);
        Pair<Integer, Integer> position = new Pair<>(0, 0);
        assertFalse(logics.isThereMine(position));
    }

    @Test
    void testToggleFlag() {
        Logics logics = new LogicsImpl(SIZE, SIZE * SIZE);
        Pair<Integer, Integer> position = new Pair<>(0, 0);
        assertFalse(logics.isThereFlag(position));
        logics.toggleFlag(position);
        assertTrue(logics.isThereFlag(position));
        logics.toggleFlag(position);
        assertFalse(logics.isThereFlag(position));
    }
}
