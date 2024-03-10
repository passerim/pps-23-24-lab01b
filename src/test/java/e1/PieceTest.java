package e1;

import e1.pieces.AbstractPiece;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

abstract class PieceTest {

    private static final Pair<Integer, Integer> STARTING_POSITION = new Pair<>(0, 0);
    protected AbstractPiece piece;

    abstract AbstractPiece createPiece(Pair<Integer, Integer> startingPosition);

    @BeforeEach
    void setupPiece() {
        this.piece = createPiece(STARTING_POSITION);
    }

    @Test
    void testIsIn() {
        assertTrue(piece.isIn(STARTING_POSITION.getX(), STARTING_POSITION.getY()));
    }

    @Test
    void testGetPosition() {
        assertEquals(STARTING_POSITION, piece.getPosition());
    }
}
