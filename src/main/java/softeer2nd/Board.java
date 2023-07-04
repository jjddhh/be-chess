package softeer2nd;

import java.util.ArrayList;
import java.util.List;

public class Board {

    private List<Pawn> board = new ArrayList<>();

    public void add(Pawn pawn) {
        board.add(pawn);
    }

    public int size() {
        return board.size();
    }

    public Pawn findPawn(int i) {
        return board.get(i);
    }
}
