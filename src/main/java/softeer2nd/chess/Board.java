package softeer2nd.chess;

import softeer2nd.chess.exception.InvalidColorException;
import softeer2nd.chess.pieces.Color;
import softeer2nd.chess.pieces.Piece;
import softeer2nd.chess.pieces.Piece.Position;
import softeer2nd.chess.pieces.Type;

import java.util.*;
import java.util.List;

import static softeer2nd.chess.pieces.Piece.*;

public class Board {
    private Map<Type, List<Piece>> whitePieces = new HashMap<>();
    private Map<Type, List<Piece>> blackPieces = new HashMap<>();

    public static final int ROW = 8;
    public static final int COl = 8;

    public static final String EMPTY_BOARD = "........";

    public void initializeEmpty() {
        blackPieces = new HashMap<>();
        whitePieces = new HashMap<>();
    }

    public void initialize() {
        blackPieces = new HashMap<>();
        whitePieces = new HashMap<>();

        for (int i = 0; i < COl; i++) {
            blackPieces.computeIfAbsent(Type.PAWN, k -> new ArrayList<>())
                    .add(createBlackPawn(new Position(i, 1)));
            whitePieces.computeIfAbsent(Type.PAWN, k -> new ArrayList<>())
                    .add(createWhitePawn(new Position(i, 6)));
        }

        for (int i = 0; i < COl; i += 7) {
            blackPieces.computeIfAbsent(Type.ROOK, k -> new ArrayList<>())
                    .add(createBlackRook(new Position(i, 0)));
            whitePieces.computeIfAbsent(Type.ROOK, k -> new ArrayList<>())
                    .add(createWhiteRook(new Position(i, 7)));
        }

        for (int i = 1; i < COl; i += 5) {
            blackPieces.computeIfAbsent(Type.KNIGHT, k -> new ArrayList<>())
                    .add(createBlackKnight(new Position(i, 0)));
            whitePieces.computeIfAbsent(Type.KNIGHT, k -> new ArrayList<>())
                    .add(createWhiteKnight(new Position(i, 7)));
        }

        for (int i = 2; i < COl; i += 3) {
            blackPieces.computeIfAbsent(Type.BISHOP, k -> new ArrayList<>())
                    .add(createBlackBishop(new Position(i, 0)));
            whitePieces.computeIfAbsent(Type.BISHOP, k -> new ArrayList<>())
                    .add(createWhiteBishop(new Position(i, 7)));

        }

        blackPieces.computeIfAbsent(Type.QUEEN, k -> new ArrayList<>())
                .add(createBlackQueen(new Position(3, 0)));
        whitePieces.computeIfAbsent(Type.QUEEN, k -> new ArrayList<>())
                .add(createWhiteQueen(new Position(3, 7)));


        blackPieces.computeIfAbsent(Type.KING, k -> new ArrayList<>())
                .add(createBlackKing(new Position(4, 0)));
        whitePieces.computeIfAbsent(Type.KING, k -> new ArrayList<>())
                .add(createWhiteKing(new Position(4, 7)));
    }

    public void end() {
        // map의 list에서 dangling pointer 발생안하는지 체크 필요
        whitePieces = null;
        blackPieces = null;
    }

    public void addPiece(Piece piece) {
        // 개수 예외 처리
        if(piece.isBlack()) addBlackPiece(piece);
        else if(piece.isWhite()) addWhitePiece(piece);
    }

    public void addWhitePiece(Piece piece) {
        verifyWhitePiece(piece);
        whitePieces.computeIfAbsent(piece.getType(), k -> new ArrayList<>()).add(piece);
    }

    private void verifyWhitePiece(Piece piece) {
        if(!piece.getColor().equals(Color.WHITE))
            throw InvalidColorException.EXCEPTION;
    }

    public void addBlackPiece(Piece piece) {
        verifyBlackPiece(piece);
        blackPieces.computeIfAbsent(piece.getType(), k -> new ArrayList<>()).add(piece);
    }

    private void verifyBlackPiece(Piece piece) {
        if(!piece.getColor().equals(Color.BLACK))
            throw InvalidColorException.EXCEPTION;
    }

    public int whitePawnSize() {
        return whitePieces.get(Type.PAWN).size();
    }

    public Piece findWhitePawn(int idx) {
        return whitePieces.get(Type.PAWN).get(idx);
    }

    public String getWhitePawnsResult() {
        return getPawnsResult(whitePieces.get(Type.PAWN));
    }

    public String getBlackPawnsResult() {
        return getPawnsResult(blackPieces.get(Type.PAWN));
    }

    private String getPawnsResult(List<Piece> colorPawns) {
        StringBuilder colorPawnsResult = new StringBuilder();

        for (Piece p : colorPawns) {
            colorPawnsResult.append(p.getRepresentation());
        }

        return colorPawnsResult.toString();
    }

    public int getTotalPieceCount() {
        int whitePieceCount = 0;
        for (Type type : Type.values()) {
            whitePieceCount += whitePieces.getOrDefault(type, new ArrayList<>()).size();
        }

        int blackPieceCount = 0;
        for (Type type : Type.values()) {
            blackPieceCount += blackPieces.getOrDefault(type, new ArrayList<>()).size();
        }

        return whitePieceCount + blackPieceCount;
    }

    public int getPieceCount(Color color, Type type) {
        if (color.equals(Color.WHITE))
            return whitePieces.getOrDefault(type, new ArrayList<>()).size();
        else if (color.equals(Color.BLACK))
            return blackPieces.getOrDefault(type, new ArrayList<>()).size();
        else {
            int whitePieces = this.whitePieces.getOrDefault(type, new ArrayList<>()).size();
            int blackPieces = this.blackPieces.getOrDefault(type, new ArrayList<>()).size();
            return (ROW * COl) - (whitePieces + blackPieces);
        }
    }

    /**
     * getter
     */
    public Map<Type, List<Piece>> getWhitePieces() {
        return whitePieces;
    }

    public Map<Type, List<Piece>> getBlackPieces() {
        return blackPieces;
    }
}

