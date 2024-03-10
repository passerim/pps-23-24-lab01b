package e1.pieces;

import e1.Pair;

public class Pawn extends AbstractPiece {
    public Pawn(Pair<Integer, Integer> startingPosition) {
        super(startingPosition);
    }

    @Override
    public boolean move(int row, int col) {
        return false;
    }
}
