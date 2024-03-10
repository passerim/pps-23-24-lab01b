package e1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class RandomPieceGeneratorTest extends PieceGeneratorTest {
    @Override
    PieceGenerator createPieceGenerator(int size) {
        return new RandomPieceGenerator(size);
    }

    @Test
    void testCreatePawn() {
        final Pair<Integer, Integer> pawn = pieceGenerator.getPawn();
        final int x = pawn.getX();
        final int y = pawn.getY();
        assertFalse(x < 0 || y < 0 || x >= SIZE || y >= SIZE);
    }

    @Test
    void testCreateKnight() {
        final Pair<Integer, Integer> pawn = pieceGenerator.getPawn();
        final Pair<Integer, Integer> knight = pieceGenerator.getKnight();
        final int x = knight.getX();
        final int y = knight.getY();
        assertFalse(x < 0 || y < 0 || x >= SIZE || y >= SIZE);
        assertNotEquals(knight, pawn);
    }
}
