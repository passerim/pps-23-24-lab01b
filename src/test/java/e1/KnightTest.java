package e1;

import e1.pieces.AbstractPiece;
import e1.pieces.Knight;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class KnightTest extends PieceTest {

    @Override
    AbstractPiece createPiece(Pair<Integer, Integer> startingPosition) {
        return new Knight(startingPosition);
    }

    @Test
    void performLegalMove() {
        final int movementX = 1;
        final int movementY = 2;
        assertTrue(piece.move(movementX, movementY));
        assertTrue(piece.isIn(movementX, movementY));
    }

    @Test
    void performIllegalMove() {
        final int movementX = 2;
        final int movementY = 3;
        assertFalse(piece.move(movementX, movementY));
        assertFalse(piece.isIn(movementX, movementY));
    }
}
