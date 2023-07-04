package softeer2nd.chess;

import softeer2nd.chess.exception.InvalidColorException;
import softeer2nd.chess.pieces.Pawn;

import java.util.ArrayList;
import java.util.List;

public class Board {

    private List<Pawn> whitePawns = new ArrayList<>();
    private List<Pawn> blackPawns = new ArrayList<>();

    public void end() {
        whitePawns = null;
        blackPawns = null;
    }

    public void addWhitePawn(Pawn pawn) {
        verifyWhitePawn(pawn);
        whitePawns.add(pawn);
    }

    private void verifyWhitePawn(Pawn pawn) {
        if(!pawn.getColor().equals(Pawn.WHITE_COLOR))
            throw InvalidColorException.EXCEPTION;
    }

    public void addBlackPawn(Pawn pawn) {
        verifyBlackPawn(pawn);
        blackPawns.add(pawn);
    }

    private void verifyBlackPawn(Pawn pawn) {
        if(!pawn.getColor().equals(Pawn.BLACK_COLOR))
            throw InvalidColorException.EXCEPTION;
    }

    public int size() {
        return whitePawns.size();
    }

    public Pawn findWhitePawn(int idx) {
        return whitePawns.get(idx);
    }

    public Pawn findBlackPawn(int idx) {
        return blackPawns.get(idx);
    }

    public void initialize() {
        whitePawns = new ArrayList<>();
        blackPawns = new ArrayList<>();

        for (int i = 0; i < 8; i++) {
            whitePawns.add(new Pawn(Pawn.WHITE_COLOR));
            blackPawns.add(new Pawn(Pawn.BLACK_COLOR));
        }
    }

    public String getWhitePawnsResult() {
        return getPawnsResult(whitePawns);
    }

    public String getBlackPawnsResult() {
        return getPawnsResult(blackPawns);
    }

    private String getPawnsResult(List<Pawn> whitePawns) {
        StringBuilder whitePawnsResult = new StringBuilder();

        for (Pawn p : whitePawns) {
            whitePawnsResult.append(p.getRepresentation());
        }

        return whitePawnsResult.toString();
    }

    public void print() {
        final String emptyBoard = "********";

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 8; i++) {

            if(i == 1) {
                sb.append(getBlackPawnsResult());
            } else if (i == 6) {
                sb.append(getWhitePawnsResult());
            } else {
                sb.append(emptyBoard);
            }

            sb.append('\n');
        }

        System.out.println(sb);
    }
}
