package e1;

import e1.pieces.Knight;
import e1.pieces.Pawn;

public interface PieceGenerator {

    Pawn getPawn();

    Knight getKnight();
}
