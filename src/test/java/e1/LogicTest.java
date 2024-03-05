package e1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LogicTest {

    private final int SIZE = 3;

    @Test
    public void testRandomEmptyPosition() {
        Logics logics = new LogicsImpl(SIZE);
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                assertFalse(logics.hasKnight(i, j) && logics.hasPawn(i, j));
            }
        }
    }

    @Test
    public void testHitNegativeBoundaries() {
        Logics logics = new LogicsImpl(SIZE);
        assertThrows(IndexOutOfBoundsException.class, () -> logics.hit(-1, 0));
        assertThrows(IndexOutOfBoundsException.class, () -> logics.hit(0, -1));
        assertThrows(IndexOutOfBoundsException.class, () -> logics.hit(-1, -1));
    }

    @Test
    public void testHitPositiveBoundaries() {
        Logics logics = new LogicsImpl(SIZE);
        assertThrows(IndexOutOfBoundsException.class, () -> logics.hit(SIZE, SIZE - 1));
        assertThrows(IndexOutOfBoundsException.class, () -> logics.hit(SIZE - 1, SIZE));
        assertThrows(IndexOutOfBoundsException.class, () -> logics.hit(SIZE, SIZE));
    }

    @Test
    public void testLegalMove() {
        final Pair<Integer, Integer> pawnPosition = new Pair<>(1, 2);
        final Pair<Integer, Integer> knightPosition = new Pair<>(0, 0);
        Logics logics = new LogicsImpl(SIZE, pawnPosition, knightPosition);
        assertFalse(logics.hit(2, 1));
        assertTrue(logics.hasKnight(2, 1));
    }

    @Test
    public void testIllegalMove() {
        final Pair<Integer, Integer> pawnPosition = new Pair<>(1, 2);
        final Pair<Integer, Integer> knightPosition = new Pair<>(0, 0);
        Logics logics = new LogicsImpl(SIZE, pawnPosition, knightPosition);
        assertFalse(logics.hit(0, 0));
    }

    @Test
    public void testHit() {
        final Pair<Integer, Integer> pawnPosition = new Pair<>(1, 2);
        final Pair<Integer, Integer> knightPosition = new Pair<>(0, 0);
        Logics logics = new LogicsImpl(SIZE, pawnPosition, knightPosition);
        assertTrue(logics.hit(1, 2));
    }

    @Test
    public void testHasPawn() {
        final Pair<Integer, Integer> pawnPosition = new Pair<>(SIZE / 2, SIZE / 2);
        final Pair<Integer, Integer> knightPosition = new Pair<>(0, 0);
        Logics logics = new LogicsImpl(SIZE, pawnPosition, knightPosition);
        assertTrue(logics.hasPawn(pawnPosition.getX(), pawnPosition.getY()));
    }

    @Test
    public void testHasNotPawn() {
        final Pair<Integer, Integer> pawnPosition = new Pair<>(SIZE / 2, SIZE / 2);
        final Pair<Integer, Integer> knightPosition = new Pair<>(0, 0);
        Logics logics = new LogicsImpl(SIZE, pawnPosition, knightPosition);
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (i != pawnPosition.getX() && j != pawnPosition.getY()) {
                    assertFalse(logics.hasPawn(i, j));
                }
            }
        }
    }

    @Test
    public void testHasKnight() {
        final Pair<Integer, Integer> pawnPosition = new Pair<>(0, 0);
        final Pair<Integer, Integer> knightPosition = new Pair<>(SIZE / 2, SIZE / 2);
        Logics logics = new LogicsImpl(SIZE, pawnPosition, knightPosition);
        assertTrue(logics.hasPawn(pawnPosition.getX(), pawnPosition.getY()));
    }

    @Test
    public void testHasNotKnight() {
        final Pair<Integer, Integer> pawnPosition = new Pair<>(0, 0);
        final Pair<Integer, Integer> knightPosition = new Pair<>(SIZE / 2, SIZE / 2);
        Logics logics = new LogicsImpl(SIZE, pawnPosition, knightPosition);
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (i != knightPosition.getX() && j != knightPosition.getY()) {
                    assertFalse(logics.hasKnight(i, j));
                }
            }
        }
    }
}
