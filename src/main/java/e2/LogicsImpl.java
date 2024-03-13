package e2;

import java.util.Optional;

public class LogicsImpl implements Logics {

    private final int size;
    private final Grid grid;

    public LogicsImpl(int size, int mines) {
        this.size = size;
        this.grid = new Grid(size, mines);
    }

    private void checkBounds(Pair<Integer, Integer> position) {
        final int row = position.getX();
        final int col = position.getY();
        if (row < 0 || col < 0 || row >= size || col >= size) {
            throw new IndexOutOfBoundsException();
        }
    }

    @Override
    public boolean aMineWasFound(Pair<Integer, Integer> position) {
        checkBounds(position);
        final Cell cell = grid.getCell(position);
        cell.sweep();
        if (cell.hasMine()) {
            return true;
        } else {
            final Optional<Integer> cellCounter = cell.getCounter();
            if (cellCounter.isPresent() && cellCounter.get() == 0) {
                cell.getSiblings().forEach(sibling -> {
                    if (!sibling.isSwept()) {
                        aMineWasFound(sibling.getPosition());
                    }
                });
            }
            return false;
        }
    }

    @Override
    public boolean isThereVictory() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                final Cell cell = grid.getCell(new Pair<>(i, j));
                if (cell.isSwept() == cell.hasMine()) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public Optional<Integer> getCounter(Pair<Integer, Integer> position) {
        checkBounds(position);
        return grid.getCell(position).getCounter();
    }

    @Override
    public boolean isThereFlag(Pair<Integer, Integer> position) {
        checkBounds(position);
        return grid.getCell(position).hasFlag();
    }

    @Override
    public boolean isThereMine(Pair<Integer, Integer> position) {
        checkBounds(position);
        return grid.getCell(position).hasMine();
    }

    @Override
    public void toggleFlag(Pair<Integer, Integer> position) {
        checkBounds(position);
        grid.getCell(position).toggleFlag();
    }
}
