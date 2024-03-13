package e1;

import e1.pieces.AbstractPiece;
import e1.pieces.Knight;
import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

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

    private boolean isLegalMovement(int row, int col) {
        int x = row - piece.getPosition().getX();
        int y = col - piece.getPosition().getY();
        return (x != 0) && (y != 0) && ((Math.abs(x) + Math.abs(y)) == 3);
    }

    @Test
    void testAllLegalMovesFromTheStartingPosition() {
        final int size = 5;
        final Pair<Integer, Integer> startingPosition = piece.getPosition();
        IntStream.range(0, size)
                 .forEach(row -> IntStream.range(0, size).filter(col -> isLegalMovement(row, col)).forEach(col -> {
                     assertTrue(piece.move(row, col));
                     assertTrue(piece.isIn(row, col));
                     piece.move(startingPosition.getX(), startingPosition.getY());
                 }));
    }

    @Test
    void testAllIllegalMovesFromTheStartingPosition() {
        final int size = 5;
        IntStream.range(0, size).forEach(row -> IntStream.range(0, size).filter(col -> !isLegalMovement(row, col))
                                                         .forEach(col -> assertFalse(piece.move(row, col))));
    }
}
