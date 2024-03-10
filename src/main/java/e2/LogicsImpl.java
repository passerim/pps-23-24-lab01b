package e2;

public class LogicsImpl implements Logics {

    private final int size;
    private final Grid grid;

    public LogicsImpl(int size, int mines) {
        this.size = size;
        this.grid = new Grid(size, mines);
    }

    private void checkBounds(int row, int col) {
        if (row < 0 || col < 0 || row >= size || col >= size) {
            throw new IndexOutOfBoundsException();
        }
    }

    @Override
    public boolean aMineWasFound(Pair<Integer, Integer> position) {
        checkBounds(position.getX(), position.getY());
        final Cell cell = grid.getCell(position);
        cell.sweep();
        if (cell.getCounter() == 0) {
            cell.getSiblings().forEach(sibling -> {
                if (!sibling.isSwept()) {
                    aMineWasFound(sibling.getPosition());
                }
            });
        }
        return cell.hasMine();
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
    public boolean isThereCounter(Pair<Integer, Integer> position) {
        checkBounds(position.getX(), position.getY());
        return grid.getCell(position).isSwept();
    }

    @Override
    public int getCounter(Pair<Integer, Integer> position) {
        checkBounds(position.getX(), position.getY());
        return grid.getCell(position).getCounter();
    }

    @Override
    public boolean isThereFlag(Pair<Integer, Integer> position) {
        checkBounds(position.getX(), position.getY());
        return grid.getCell(position).hasFlag();
    }

    @Override
    public boolean isThereMine(Pair<Integer, Integer> position) {
        checkBounds(position.getX(), position.getY());
        return grid.getCell(position).hasMine();
    }

    @Override
    public void toggleFlag(Pair<Integer, Integer> position) {
        checkBounds(position.getX(), position.getY());
        grid.getCell(position).toggleFlag();
    }
}
