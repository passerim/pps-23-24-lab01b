package e1.pieces;

import e1.Pair;

public interface Piece {
    Pair<Integer, Integer> getPosition();

    boolean isIn(int row, int col);

    boolean move(int row, int col);
}
