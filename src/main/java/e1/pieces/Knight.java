package e1.pieces;

import e1.Pair;

public class Knight extends AbstractPiece {
    public Knight(Pair<Integer, Integer> startingPosition) {
        super(startingPosition);
    }

    @Override
    public boolean move(int row, int col) {
        // Below a compact way to express allowed moves for the knight
        int x = row - getPosition().getX();
        int y = col - getPosition().getY();
        if ((x != 0) && (y != 0) && ((Math.abs(x) + Math.abs(y)) == 3)) {
            position = new Pair<>(row, col);
            return true;
        }
        return false;
    }
}
