package softeer2nd.chess;

import softeer2nd.chess.pieces.Pawn;

import java.util.ArrayList;
import java.util.List;

public class Board {

    private List<Pawn> board = new ArrayList<>();

    public void addPawn(Pawn pawn) {
        board.add(pawn);
    }

    public int size() {
        return board.size();
    }

    public Pawn findPawn(int i) {
        return board.get(i);
    }
}
