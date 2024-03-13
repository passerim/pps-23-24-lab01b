package e2;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Cell {

    private final Pair<Integer, Integer> position;
    private final List<Cell> siblings = new ArrayList<>();
    private boolean flag = false;
    private boolean mine = false;
    private boolean swept = false;

    public Cell(Pair<Integer, Integer> position) {
        this.position = position;
    }

    public void addSibling(Cell cell) {
        if (!siblings.contains(cell)) {
            siblings.add(cell);
        }
    }

    public Optional<Integer> getCounter() {
        return isSwept() ? Optional.of((int) siblings.stream().filter(Cell::hasMine).count()) : Optional.empty();
    }

    public Pair<Integer, Integer> getPosition() {
        return position;
    }

    public List<Cell> getSiblings() {
        return List.copyOf(siblings);
    }

    public boolean isSwept() {
        return swept;
    }

    public boolean hasFlag() {
        return flag;
    }

    public boolean hasMine() {
        return mine;
    }

    public void placeMine() {
        mine = true;
    }

    public void sweep() {
        swept = true;
    }

    public void toggleFlag() {
        flag = !flag;
    }
}
