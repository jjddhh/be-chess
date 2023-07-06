package softeer2nd.chess.pieces;

import softeer2nd.chess.Board;
import softeer2nd.chess.exception.OutOfBoardException;

import java.util.Objects;


public class Piece {

    public enum Color {
        // 반복문 돌릴시 NO_COLOR 문제
        WHITE, BLACK, NO_COLOR;
    }

    public enum Type {
        // 반복문 돌릴시 NO_PIECE 문제
        PAWN('p'), KNIGHT('n'), ROOK('r'),
        BISHOP('b'), QUEEN('q'), KING('k'),
        NO_PIECE('.');

        private char representation;

        private Type(char representation) {
            this.representation = representation;
        }

        public char getWhiteRepresentation() {
            return representation;
        }

        public char getBlackRepresentation() {
            return Character.toUpperCase(representation);
        }

        public boolean isPiece() {
            return representation != '.';
        }
    }

    private final Color color;
    private final Type type;
    private final char representation;
    private Point point;

    private Piece(Color color, Type type) {
        this.color = color;
        this.type = type;
        this.representation = setRepresentation(color, type);
    }

    private Piece(Color color, Type type, Point point) {
        this.color = color;
        this.type = type;
        this.representation = setRepresentation(color, type);
        this.point = setPoint(point);
    }

    private Point setPoint(Point point) {
        verifyPiecePoint(point);
        return point;
    }

    private static void verifyPiecePoint(Point point) {
        if(!(0 <= point.getCol() && point.getCol() < Board.COl && 0 <= point.getRow() && point.getRow() < Board.ROW))
            throw OutOfBoardException.EXCEPTION;
    }

    public Point getPoint() {
        return point;
    }
    public Type getType() { return type; }

    private char setRepresentation(final Color color, final Type name) {
        char representation = '.';

        if (name.isPiece()) {
            if(color.equals(Color.WHITE)) representation = name.getWhiteRepresentation();
            else representation = name.getBlackRepresentation();
        }

        return representation;
    }

    private static Piece createWhite(Type type, Point point) {
        return new Piece(Color.WHITE, type, point);
    }

    private static Piece createWhite(Type type, String position) {
        return new Piece(Color.WHITE, type, new Point(position));
    }

    private static Piece createBlack(Type type, Point point) {
        return new Piece(Color.BLACK, type, point);
    }

    private static Piece createBlack(Type type, String point) {
        return new Piece(Color.BLACK, type, new Point(point));
    }

    public static Piece createWhitePawn(Point point) {
        return createWhite(Type.PAWN, point);
    }

    public static Piece createWhitePawn(String point) {
        return createWhite(Type.PAWN, point);
    }

    public static Piece createBlackPawn(Point point) {
        return createBlack(Type.PAWN, point);
    }

    public static Piece createBlackPawn(String point) {
        return createBlack(Type.PAWN, point);
    }

    public static Piece createWhiteKnight(Point point) {
        return createWhite(Type.KNIGHT, point);
    }

    public static Piece createWhiteKnight(String point) {
        return createWhite(Type.KNIGHT, point);
    }

    public static Piece createBlackKnight(Point point) {
        return createBlack(Type.KNIGHT, point);
    }

    public static Piece createBlackKnight(String point) {
        return createBlack(Type.KNIGHT, point);
    }

    public static Piece createWhiteRook(Point point) {
        return createWhite(Type.ROOK, point);
    }

    public static Piece createWhiteRook(String point) {
        return createWhite(Type.ROOK, point);
    }

    public static Piece createBlackRook(Point point) {
        return createBlack(Type.ROOK, point);
    }

    public static Piece createBlackRook(String point) {
        return createBlack(Type.ROOK, point);
    }

    public static Piece createWhiteBishop(Point point) {
        return createWhite(Type.BISHOP, point);
    }

    public static Piece createWhiteBishop(String point) {
        return createWhite(Type.BISHOP, point);
    }

    public static Piece createBlackBishop(Point point) {
        return createBlack(Type.BISHOP, point);
    }

    public static Piece createBlackBishop(String point) {
        return createBlack(Type.BISHOP, point);
    }

    public static Piece createWhiteQueen(Point point) {
        return createWhite(Type.QUEEN, point);
    }

    public static Piece createWhiteQueen(String point) {
        return createWhite(Type.QUEEN, point);
    }

    public static Piece createBlackQueen(Point point) {
        return createBlack(Type.QUEEN, point);
    }

    public static Piece createBlackQueen(String point) {
        return createBlack(Type.QUEEN, point);
    }

    public static Piece createWhiteKing(Point point) {
        return createWhite(Type.KING, point);
    }

    public static Piece createWhiteKing(String point) {
        return createWhite(Type.KING, point);
    }

    public static Piece createBlackKing(Point point) {
        return createBlack(Type.KING, point);
    }

    public static Piece createBlackKing(String point) {
        return createBlack(Type.KING, point);
    }

    public static Piece createBlank(Point point) { return new Piece(Color.NO_COLOR, Type.NO_PIECE, point);
    }

    public Color getColor() {
        return this.color;
    }

    public char getRepresentation() {
        return this.representation;
    }

    public boolean isBlack() {
        return color.equals(Color.BLACK);
    }

    public boolean isWhite() {
        return color.equals(Color.WHITE);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Piece piece = (Piece) o;
        return representation == piece.representation && color == piece.color && type == piece.type && Objects.equals(point, piece.point);
    }

    @Override
    public int hashCode() {
        return Objects.hash(color, type, representation, point);
    }

    public static class Point {
        private int col;
        private int row;

        public Point(String position) {
            this(position.charAt(0), Character.getNumericValue(position.charAt(1)));
        }

        public Point(char col, int row) {
            int colTmp = col - 'a';
            row = 8 - row;

            // 범위 예외 처리 추가
            this.col = colTmp;
            this.row = row;
        }

        public Point(int col, int row) {
            this.col = col;
            this.row = row;
        }

        public int getCol() {
            return col;
        }

        public int getRow() {
            return row;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Point point = (Point) o;
            return col == point.col && row == point.row;
        }

        @Override
        public int hashCode() {
            return Objects.hash(col, row);
        }
    }
}