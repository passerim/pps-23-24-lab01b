package e1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

abstract class PieceGeneratorTest {

    public static final int SIZE = 5;
    protected PieceGenerator pieceGenerator;

    abstract PieceGenerator createPieceGenerator(int size);

    @BeforeEach
    void setupPieceGenerator() {
        this.pieceGenerator = createPieceGenerator(SIZE);
    }

    @Test
    void testNegativeSizeGrid() {
        assertThrows(IllegalArgumentException.class, () -> createPieceGenerator(-1));
    }

    @Test
    abstract void testCreatePawn();

    @Test
    abstract void testCreateKnight();
}
