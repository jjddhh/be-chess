package softeer2nd.chess;

import softeer2nd.chess.exception.InvalidColorException;
import softeer2nd.chess.pieces.*;
import softeer2nd.chess.pieces.piece.Color;
import softeer2nd.chess.pieces.piece.Type;
import softeer2nd.chess.pieces.piece.Piece;

import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

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
            char col = (char) ('a' + i);
            blackPieces.computeIfAbsent(Type.PAWN, k -> new ArrayList<>())
                    .add(Pawn.createBlack(col + "7"));
            whitePieces.computeIfAbsent(Type.PAWN, k -> new ArrayList<>())
                    .add(Pawn.createWhite(col + "2"));
        }

        for (int i = 0; i < COl; i += 7) {
            char col = (char) ('a' + i);
            blackPieces.computeIfAbsent(Type.ROOK, k -> new ArrayList<>())
                    .add(Rook.createBlack(col + "8"));
            whitePieces.computeIfAbsent(Type.ROOK, k -> new ArrayList<>())
                    .add(Rook.createWhite(col + "1"));
        }

        for (int i = 1; i < COl; i += 5) {
            char col = (char) ('a' + i);
            blackPieces.computeIfAbsent(Type.KNIGHT, k -> new ArrayList<>())
                    .add(Knight.createBlack(col + "8"));
            whitePieces.computeIfAbsent(Type.KNIGHT, k -> new ArrayList<>())
                    .add(Knight.createWhite(col + "1"));
        }

        for (int i = 2; i < COl; i += 3) {
            char col = (char) ('a' + i);
            blackPieces.computeIfAbsent(Type.BISHOP, k -> new ArrayList<>())
                    .add(Bishop.createBlack(col + "8"));
            whitePieces.computeIfAbsent(Type.BISHOP, k -> new ArrayList<>())
                    .add(Bishop.createWhite(col + "1"));
        }

        blackPieces.computeIfAbsent(Type.QUEEN, k -> new ArrayList<>())
                .add(Queen.createBlack("d8"));
        whitePieces.computeIfAbsent(Type.QUEEN, k -> new ArrayList<>())
                .add(Queen.createWhite("d1"));


        blackPieces.computeIfAbsent(Type.KING, k -> new ArrayList<>())
                .add(King.createBlack("e8"));
        whitePieces.computeIfAbsent(Type.KING, k -> new ArrayList<>())
                .add(King.createWhite("e1"));
    }

    public void cleanUp() {
        whitePieces = null;
        blackPieces = null;
    }

    public void addPiece(Piece piece) {
        if(piece.isBlack()) addBlackPiece(piece);
        else if(piece.isWhite()) addWhitePiece(piece);
    }

    private void addWhitePiece(Piece piece) {
        verifyWhitePiece(piece);
        whitePieces.computeIfAbsent(piece.getType(), k -> new ArrayList<>()).add(piece);
    }

    private void verifyWhitePiece(Piece piece) {
        if(!piece.getColor().equals(Color.WHITE)){
            throw InvalidColorException.EXCEPTION;
        }
    }

    private void addBlackPiece(Piece piece) {
        verifyBlackPiece(piece);
        blackPieces.computeIfAbsent(piece.getType(), k -> new ArrayList<>()).add(piece);
    }

    private void verifyBlackPiece(Piece piece) {
        if(!piece.getColor().equals(Color.BLACK)){
            throw InvalidColorException.EXCEPTION;
        }
    }

    public void removePiece(Piece piece) {
        Arrays.stream(Type.values()).forEach(type -> {
            whitePieces.getOrDefault(type, new ArrayList<>()).remove(piece);
            blackPieces.getOrDefault(type, new ArrayList<>()).remove(piece);
        });

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
        return colorPawns.stream()
                .map(t -> String.valueOf(t.getRepresentation()))
                .collect(Collectors.joining());
    }

    public int getTotalPieceCount() {
        int whitePieceCount = Arrays.stream(Type.values())
                .mapToInt(type -> whitePieces.getOrDefault(type, new ArrayList<>()).size())
                .sum();

        int blackPieceCount = Arrays.stream(Type.values())
                .mapToInt(type -> blackPieces.getOrDefault(type, new ArrayList<>()).size())
                .sum();

        return whitePieceCount + blackPieceCount;
    }

    public int getPieceCount(Color color, Type type) {
        int whitePiecesCount = this.whitePieces.getOrDefault(type, new ArrayList<>()).size();
        int blackPiecesCount = this.blackPieces.getOrDefault(type, new ArrayList<>()).size();

        if (color.equals(Color.WHITE))
            return whitePiecesCount;
        else if (color.equals(Color.BLACK))
            return blackPiecesCount;
        else {
            return (ROW * COl) - (whitePiecesCount + blackPiecesCount);
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

