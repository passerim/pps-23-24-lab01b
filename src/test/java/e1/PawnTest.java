package e1;

import e1.pieces.AbstractPiece;
import e1.pieces.Pawn;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;

class PawnTest extends PieceTest {

    @Override
    AbstractPiece createPiece(Pair<Integer, Integer> startingPosition) {
        return new Pawn(startingPosition);
    }

    @Test
    void performIllegalMove() {
        final int movementX = 1;
        final int movementY = 2;
        assertFalse(piece.move(movementX, movementY));
        assertFalse(piece.isIn(movementX, movementY));
    }

    @Test
    void performAnotherIllegalMove() {
        final int movementX = 2;
        final int movementY = 3;
        assertFalse(piece.move(movementX, movementY));
        assertFalse(piece.isIn(movementX, movementY));
    }
}
