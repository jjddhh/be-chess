package softeer2nd.chess;

import softeer2nd.chess.pieces.Pawn;

import java.util.ArrayList;
import java.util.List;

public class Board {

    private List<Pawn> whitePawns = new ArrayList<>();
    private List<Pawn> blackPawns = new ArrayList<>();

    public void addWhitePawn(Pawn pawn) {
        whitePawns.add(pawn);
    }
    public void addBlackPawn(Pawn pawn) {
        blackPawns.add(pawn);
    }

    public int size() {
        return whitePawns.size();
    }

    public Pawn findWhitePawn(int i) {
        return whitePawns.get(i);
    }

    public Pawn findBlackPawn(int i) {
        return blackPawns.get(i);
    }

    public void initialize() {
        for (int i = 0; i < 8; i++) {
            whitePawns.add(new Pawn(Pawn.WHITE_COLOR, Pawn.WHITE_REPRESENTATION));
            blackPawns.add(new Pawn(Pawn.BLACK_COLOR, Pawn.BLACK_REPRESENTATION));
        }
    }

    public String getWhitePawnsResult() {
        StringBuilder whitePawnsResult = new StringBuilder();

        for (Pawn p : whitePawns) {
            whitePawnsResult.append(p.getRepresentation());
        }

        return whitePawnsResult.toString();
    }

    public String getBlackPawnsResult() {
        StringBuilder blackPawnsResult = new StringBuilder();

        for (Pawn p : blackPawns) {
            blackPawnsResult.append(p.getRepresentation());
        }

        return blackPawnsResult.toString();
    }
}
