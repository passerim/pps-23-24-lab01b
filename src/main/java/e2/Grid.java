package e2;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.function.BiConsumer;
import java.util.stream.IntStream;

public class Grid {

    private static final int NEIGHBOR_DISTANCE = 1;
    private final Map<Pair<Integer, Integer>, Cell> cells = new HashMap<>();
    private final Random random = new Random();
    private final int size;

    public Grid(int size, int mines) {
        this.size = size;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                final Pair<Integer, Integer> position = new Pair<>(i, j);
                final Cell cell = new Cell(position);
                cells.put(position, cell);
            }
        }
        cells.values().forEach(cell -> cells.forEach(addSiblingsToCell(cell)));
        IntStream.range(0, mines).forEach(i -> placeRandomMine());
    }

    private static BiConsumer<Pair<Integer, Integer>, Cell> addSiblingsToCell(Cell cell) {
        return (pos, value) -> {
            final int distanceX = pos.getX() - cell.getPosition().getX();
            final int distanceY = pos.getY() - cell.getPosition().getY();
            if (Math.max(Math.abs(distanceX), Math.abs(distanceY)) == NEIGHBOR_DISTANCE) {
                cell.addSibling(value);
            }
        };
    }

    private void placeRandomMine() {
        final Pair<Integer, Integer> pos = new Pair<>(random.nextInt(size), random.nextInt(size));
        if (cells.get(pos).hasMine()) {
            placeRandomMine();
        } else {
            cells.get(pos).placeMine();
        }
    }

    public Cell getCell(Pair<Integer, Integer> position) {
        return cells.get(position);
    }
}
