package e1;

import java.util.Random;

public class RandomPieceGenerator implements PieceGenerator {

    private final Random random = new Random();
    private final int size;
    private final Pair<Integer, Integer> pawn;
    private final Pair<Integer, Integer> knight;

    public RandomPieceGenerator(int size) {
        if (size <= 0) {
            throw new IllegalArgumentException("Grid size should be greater than zero!");
        }
        this.size = size;
        this.pawn = randomEmptyPosition();
        this.knight = randomEmptyPosition();
    }

    private Pair<Integer, Integer> randomEmptyPosition() {
        Pair<Integer, Integer> pos = new Pair<>(this.random.nextInt(size), this.random.nextInt(size));
        // the recursive call below prevents clash with an existing pawn
        return this.pawn != null && this.pawn.equals(pos) ? randomEmptyPosition() : pos;
    }

    @Override
    public Pair<Integer, Integer> getPawn() {
        return pawn;
    }

    @Override
    public Pair<Integer, Integer> getKnight() {
        return knight;
    }
}
