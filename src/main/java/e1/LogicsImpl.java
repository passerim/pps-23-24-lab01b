package e1;

import e1.pieces.Knight;
import e1.pieces.Pawn;

public class LogicsImpl implements Logics {

    private final Pawn pawn;
    private final int size;
    private final Knight knight;

    public LogicsImpl(int size) {
        final PieceGenerator pieceGenerator = new RandomPieceGenerator(size);
        this.size = size;
        this.pawn = pieceGenerator.getPawn();
        this.knight = pieceGenerator.getKnight();
    }

    public LogicsImpl(int size, Pair<Integer, Integer> pawnPosition, Pair<Integer, Integer> knightPosition) {
        if (size <= 0) {
            throw new IllegalArgumentException("Grid size should be greater than zero!");
        }
        this.size = size;
        this.pawn = new Pawn(pawnPosition);
        this.knight = new Knight(knightPosition);
        checkBounds(pawnPosition.getX(), pawnPosition.getY());
        checkBounds(knightPosition.getX(), knightPosition.getY());
    }

    private void checkBounds(int row, int col) {
        if (row < 0 || col < 0 || row >= size || col >= size) {
            throw new IndexOutOfBoundsException();
        }
    }

    @Override
    public boolean hit(int row, int col) {
        checkBounds(row, col);
        return knight.move(row, col) && knight.isIn(pawn.getPosition().getX(), pawn.getPosition().getY());
    }

    @Override
    public boolean hasKnight(int row, int col) {
        return this.knight.isIn(row, col);
    }

    @Override
    public boolean hasPawn(int row, int col) {
        return this.pawn.isIn(row, col);
    }
}
