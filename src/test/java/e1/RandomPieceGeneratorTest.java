package e1;

import e1.pieces.Knight;
import e1.pieces.Pawn;
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
        final Pawn pawn = pieceGenerator.getPawn();
        final int x = pawn.getPosition().getX();
        final int y = pawn.getPosition().getY();
        assertFalse(x < 0 || y < 0 || x >= SIZE || y >= SIZE);
    }

    @Test
    void testCreateKnight() {
        final Pawn pawn = pieceGenerator.getPawn();
        final Knight knight = pieceGenerator.getKnight();
        final int x = knight.getPosition().getX();
        final int y = knight.getPosition().getY();
        assertFalse(x < 0 || y < 0 || x >= SIZE || y >= SIZE);
        assertNotEquals(knight.getPosition(), pawn.getPosition());
    }
}
