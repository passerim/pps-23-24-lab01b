package e1;

import e1.pieces.Knight;
import e1.pieces.Pawn;

import java.util.Random;

public class RandomPieceGenerator implements PieceGenerator {

    private final Random random = new Random();
    private final int size;
    private final Pawn pawn;
    private final Knight knight;

    public RandomPieceGenerator(int size) {
        if (size <= 0) {
            throw new IllegalArgumentException("Grid size should be greater than zero!");
        }
        this.size = size;
        this.pawn = new Pawn(randomEmptyPosition());
        this.knight = new Knight(randomEmptyPosition());
    }

    private Pair<Integer, Integer> randomEmptyPosition() {
        Pair<Integer, Integer> pos = new Pair<>(this.random.nextInt(size), this.random.nextInt(size));
        // the recursive call below prevents clash with an existing pawn
        return this.pawn != null && this.pawn.isIn(pos.getX(), pos.getY()) ? randomEmptyPosition() : pos;
    }

    @Override
    public Pawn getPawn() {
        return pawn;
    }

    @Override
    public Knight getKnight() {
        return knight;
    }
}
