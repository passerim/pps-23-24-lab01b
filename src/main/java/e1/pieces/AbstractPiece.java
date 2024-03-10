package e1.pieces;

import e1.Pair;

public abstract class AbstractPiece implements Piece {

    protected Pair<Integer, Integer> position;

    public AbstractPiece(Pair<Integer, Integer> position) {
        this.position = position;
    }

    @Override
    public Pair<Integer, Integer> getPosition() {
        return position;
    }

    @Override
    public boolean isIn(int row, int col) {
        return position.getX().equals(row) && position.getY().equals(col);
    }

    @Override
    public abstract boolean move(int row, int col);
}
