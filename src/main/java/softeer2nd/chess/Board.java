package softeer2nd.chess;

import softeer2nd.chess.exception.InvalidColorException;
import softeer2nd.chess.pieces.Piece;
import softeer2nd.chess.pieces.Type;

import java.awt.*;
import java.util.*;
import java.util.List;

public class Board {

    private HashMap<Type, List<Piece>> whitePieces = new HashMap<>();
    private HashMap<Type, List<Piece>> blackPieces = new HashMap<>();

    public static final int ROW = 8;
    public static final int COl = 8;

    public static final String EMPTY_BOARD = "........";

    public void end() {
        // map의 list에서 dangling pointer 발생안하는지 체크 필요
        whitePieces = null;
        blackPieces = null;
    }

    public void addWhitePawn(Piece pawn) {
        verifyWhitePiece(pawn);

        whitePieces.computeIfAbsent(Type.PAWN, k -> new ArrayList<>()).add(pawn);
    }

    private void verifyWhitePiece(Piece piece) {
        if(!piece.getColor().equals(Piece.WHITE_COLOR))
            throw InvalidColorException.EXCEPTION;
    }

    public void addBlackPawn(Piece pawn) {
        verifyBlackPiece(pawn);
        List<Piece> pieces = blackPieces.getOrDefault(Type.PAWN, new ArrayList<>());
        blackPieces.putIfAbsent(Type.PAWN, pieces);
    }

    private void verifyBlackPiece(Piece piece) {
        if(!piece.getColor().equals(Piece.BLACK_COLOR))
            throw InvalidColorException.EXCEPTION;
    }

    public int whitePawnSize() {
        return whitePieces.get(Type.PAWN).size();
    }

    public Piece findWhitePawn(int idx) {
        return whitePieces.get(Type.PAWN).get(idx);
    }

    public void initialize() {

        // 초기화 및 출력시에는 배열 사용 고려
        List<String> initBoard = new ArrayList<>();
        for (int i = 0; i < COl; i++) {
            initBoard.add(StringUtils.appendNewLine(EMPTY_BOARD));
        }

        for (Type type : Type.values()) {
            whitePieces.put(type, new ArrayList<>());
            blackPieces.put(type, new ArrayList<>());
        }

        for (int i = 0; i < COl; i++) {
            whitePieces.computeIfAbsent(Type.PAWN, k -> new ArrayList<>())
                    .add(Piece.createBlackPawn(new Point(i, 1)));
            blackPieces.computeIfAbsent(Type.PAWN, k -> new ArrayList<>())
                    .add(Piece.createWhitePawn(new Point(i, 6)));
        }

        for (int i = 0; i < COl; i += 7) {
            whitePieces.computeIfAbsent(Type.PAWN, k -> new ArrayList<>())
                    .add(Piece.createBlackRook(new Point(i, 0)));
            blackPieces.computeIfAbsent(Type.PAWN, k -> new ArrayList<>())
                    .add(Piece.createWhiteRook(new Point(i, 7)));
        }

        for (int i = 1; i < COl; i += 5) {
            whitePieces.computeIfAbsent(Type.PAWN, k -> new ArrayList<>())
                    .add(Piece.createBlackKnight(new Point(i, 0)));
            blackPieces.computeIfAbsent(Type.PAWN, k -> new ArrayList<>())
                    .add(Piece.createWhiteKnight(new Point(i, 7)));
        }

        for (int i = 2; i < COl; i += 3) {
            whitePieces.computeIfAbsent(Type.PAWN, k -> new ArrayList<>())
                    .add(Piece.createBlackBishop(new Point(i, 0)));
            blackPieces.computeIfAbsent(Type.PAWN, k -> new ArrayList<>())
                    .add(Piece.createWhiteBishop(new Point(i, 7)));

        }

        whitePieces.computeIfAbsent(Type.PAWN, k -> new ArrayList<>())
                .add(Piece.createBlackQueen(new Point(3, 0)));
        blackPieces.computeIfAbsent(Type.PAWN, k -> new ArrayList<>())
                .add(Piece.createWhiteQueen(new Point(3, 7)));

        whitePieces.computeIfAbsent(Type.PAWN, k -> new ArrayList<>())
                .add(Piece.createBlackKing(new Point(4, 0)));
        blackPieces.computeIfAbsent(Type.PAWN, k -> new ArrayList<>())
                .add(Piece.createWhiteKing(new Point(4, 7)));
    }

    public String getWhitePawnsResult() {
        return getPawnsResult(whitePieces.get(Type.PAWN));
    }

    public String getBlackPawnsResult() {
        return getPawnsResult(blackPieces.get(Type.PAWN));
    }

    private String getPawnsResult(List<Piece> whitePawns) {
        StringBuilder whitePawnsResult = new StringBuilder();

        for (Piece p : whitePawns) {
            whitePawnsResult.append(p.getRepresentation());
        }

        return whitePawnsResult.toString();
    }

    public void print() {
        System.out.println(showBoard());
    }

    public int pieceCount() {
        int whitePieceCount = 0;
        for (Type type : Type.values()) {
            whitePieceCount += whitePieces.get(type).size();
        }

        int blackPieceCount = 0;
        for (Type type : Type.values()) {
            blackPieceCount += blackPieces.get(type).size();
        }

        return whitePieceCount + blackPieceCount;
    }

    public String showBoard() {
        char[][] board = new char[ROW][COl];
        for (int i = 0; i < board.length; i++) {
            Arrays.fill(board[i], '.');
        }

        for (Type type : Type.values()) {
            List<Piece> whitePieces = this.whitePieces.get(type);
            List<Piece> blackPieces = this.blackPieces.get(type);

            for (int i = 0; i < whitePieces.size(); i++) {
                Piece piece = whitePieces.get(i);
                Point point = piece.getPoint();
                board[point.y][point.x] = piece.getRepresentation();
            }

            for (int i = 0; i < blackPieces.size(); i++) {
                Piece piece = blackPieces.get(i);
                Point point = piece.getPoint();
                board[point.y][point.x] = piece.getRepresentation();
            }
        }

        StringBuilder boardStatus = new StringBuilder();
        for (int i = 0; i < 8; i++) {
            boardStatus.append(StringUtils.appendNewLine(String.valueOf(board[i])));
        }

        return boardStatus.toString();
    }
}

