package softeer2nd.chess;

import softeer2nd.chess.exception.InvalidColorException;
import softeer2nd.chess.pieces.Piece;
import softeer2nd.chess.pieces.Piece.Point;

import java.util.*;
import java.util.List;

public class Board {

    private Map<Piece.Type, List<Piece>> whitePieces = new HashMap<>();
    private Map<Piece.Type, List<Piece>> blackPieces = new HashMap<>();

    public static final int ROW = 8;
    public static final int COl = 8;

    public static final String EMPTY_BOARD = "........";

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
        if(!piece.getColor().equals(Piece.Color.WHITE))
            throw InvalidColorException.EXCEPTION;
    }

    public void addBlackPiece(Piece piece) {
        verifyBlackPiece(piece);
        blackPieces.computeIfAbsent(piece.getType(), k -> new ArrayList<>()).add(piece);
    }

    private void verifyBlackPiece(Piece piece) {
        if(!piece.getColor().equals(Piece.Color.BLACK))
            throw InvalidColorException.EXCEPTION;
    }

    public int whitePawnSize() {
        return whitePieces.get(Piece.Type.PAWN).size();
    }

    public Piece findWhitePawn(int idx) {
        return whitePieces.get(Piece.Type.PAWN).get(idx);
    }


    public void initializeEmpty() {
        blackPieces = new HashMap<>();
        whitePieces = new HashMap<>();
    }

    public void initialize() {
        blackPieces = new HashMap<>();
        whitePieces = new HashMap<>();

        for (int i = 0; i < COl; i++) {
            blackPieces.computeIfAbsent(Piece.Type.PAWN, k -> new ArrayList<>())
                    .add(Piece.createBlackPawn(new Point(i, 1)));
            whitePieces.computeIfAbsent(Piece.Type.PAWN, k -> new ArrayList<>())
                    .add(Piece.createWhitePawn(new Point(i, 6)));
        }

        for (int i = 0; i < COl; i += 7) {
            blackPieces.computeIfAbsent(Piece.Type.ROOK, k -> new ArrayList<>())
                    .add(Piece.createBlackRook(new Point(i, 0)));
            whitePieces.computeIfAbsent(Piece.Type.ROOK, k -> new ArrayList<>())
                    .add(Piece.createWhiteRook(new Point(i, 7)));
        }

        for (int i = 1; i < COl; i += 5) {
            blackPieces.computeIfAbsent(Piece.Type.KNIGHT, k -> new ArrayList<>())
                    .add(Piece.createBlackKnight(new Point(i, 0)));
            whitePieces.computeIfAbsent(Piece.Type.KNIGHT, k -> new ArrayList<>())
                    .add(Piece.createWhiteKnight(new Point(i, 7)));
        }

        for (int i = 2; i < COl; i += 3) {
            blackPieces.computeIfAbsent(Piece.Type.BISHOP, k -> new ArrayList<>())
                    .add(Piece.createBlackBishop(new Point(i, 0)));
            whitePieces.computeIfAbsent(Piece.Type.BISHOP, k -> new ArrayList<>())
                    .add(Piece.createWhiteBishop(new Point(i, 7)));

        }

        blackPieces.computeIfAbsent(Piece.Type.QUEEN, k -> new ArrayList<>())
                .add(Piece.createBlackQueen(new Point(3, 0)));
        whitePieces.computeIfAbsent(Piece.Type.QUEEN, k -> new ArrayList<>())
                .add(Piece.createWhiteQueen(new Point(3, 7)));


        blackPieces.computeIfAbsent(Piece.Type.KING, k -> new ArrayList<>())
                .add(Piece.createBlackKing(new Point(4, 0)));
        whitePieces.computeIfAbsent(Piece.Type.KING, k -> new ArrayList<>())
                .add(Piece.createWhiteKing(new Point(4, 7)));
    }

    public String getWhitePawnsResult() {
        return getPawnsResult(whitePieces.get(Piece.Type.PAWN));
    }

    public String getBlackPawnsResult() {
        return getPawnsResult(blackPieces.get(Piece.Type.PAWN));
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
        for (Piece.Type type : Piece.Type.values()) {
            whitePieceCount += whitePieces.getOrDefault(type, new ArrayList<>()).size();
        }

        int blackPieceCount = 0;
        for (Piece.Type type : Piece.Type.values()) {
            blackPieceCount += blackPieces.getOrDefault(type, new ArrayList<>()).size();
        }

        return whitePieceCount + blackPieceCount;
    }

    public String showBoard() {
        char[][] board = new char[ROW][COl];
        for (int i = 0; i < board.length; i++) {
            Arrays.fill(board[i], '.');
        }

        for (Piece.Type type : Piece.Type.values()) {
            List<Piece> whitePieces = this.whitePieces.getOrDefault(type, new ArrayList<>());
            List<Piece> blackPieces = this.blackPieces.getOrDefault(type, new ArrayList<>());

            for (int i = 0; i < whitePieces.size(); i++) {
                Piece piece = whitePieces.get(i);
                Point point = piece.getPoint();
                board[point.getRow()][point.getCol()] = piece.getRepresentation();
            }

            for (int i = 0; i < blackPieces.size(); i++) {
                Piece piece = blackPieces.get(i);
                Point point = piece.getPoint();
                board[point.getRow()][point.getCol()] = piece.getRepresentation();
            }
        }

        StringBuilder boardStatus = new StringBuilder();
        for (int i = 0; i < 8; i++) {
            boardStatus.append(StringUtils.appendNewLine(String.valueOf(board[i])));
        }

        return boardStatus.toString();
    }

    public int getPieceCount(Piece.Color color, Piece.Type type) {
        if (color.equals(Piece.Color.WHITE))
            return whitePieces.getOrDefault(type, new ArrayList<>()).size();
        else if (color.equals(Piece.Color.BLACK))
            return blackPieces.getOrDefault(type, new ArrayList<>()).size();
        else {
            int whitePieces = this.whitePieces.getOrDefault(type, new ArrayList<>()).size();
            int blackPieces = this.blackPieces.getOrDefault(type, new ArrayList<>()).size();
            return (ROW * COl) - (whitePieces + blackPieces);
        }
    }

    public Piece findPiece(String position) {
        // position 형식 맞는지 예외 처리

        char col = position.charAt(0);
        int row = Character.getNumericValue(position.charAt(1));
        Point findPoint = new Point(col, row);

        for (Piece.Type type : Piece.Type.values()) {
            List<Piece> white = whitePieces.getOrDefault(type, new ArrayList<>());
            List<Piece> black = blackPieces.getOrDefault(type, new ArrayList<>());

            for (Piece piece : white) {
                Point point = piece.getPoint();
                if(point.equals(findPoint)) return piece;
            }

            for (Piece piece : black) {
                Point point = piece.getPoint();
                if(point.equals(findPoint)) return piece;
            }
        }

        return Piece.createBlank(findPoint);
    }
}

