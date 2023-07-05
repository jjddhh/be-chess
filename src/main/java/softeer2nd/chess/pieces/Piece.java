package softeer2nd.chess.pieces;

import softeer2nd.chess.Board;
import softeer2nd.chess.exception.InvalidColorException;
import softeer2nd.chess.exception.OutOfBoardException;

import java.awt.*;

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
        if(!(0 <= point.x && point.x < Board.COl && 0 <= point.y && point.y < Board.ROW)) throw OutOfBoardException.EXCEPTION;
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

    public static Piece createWhitePawn(Point point) {
        return createWhite(Type.PAWN, point);
    }

    public static Piece createBlackPawn(Point point) {
        return createBlack(Type.PAWN, point);
    }

    public static Piece createWhiteKnight(Point point) {
        return createWhite(Type.KNIGHT, point);
    }

    public static Piece createBlackKnight(Point point) {
        return createBlack(Type.KNIGHT, point);
    }

    public static Piece createWhiteRook(Point point) {
        return createWhite(Type.ROOK, point);
    }

    public static Piece createBlackRook(Point point) {
        return createBlack(Type.ROOK, point);
    }

    public static Piece createWhiteBishop(Point point) {
        return createWhite(Type.BISHOP, point);
    }

    public static Piece createBlackBishop(Point point) {
        return createBlack(Type.BISHOP, point);
    }

    public static Piece createWhiteQueen(Point point) {
        return createWhite(Type.QUEEN, point);
    }

    public static Piece createBlackQueen(Point point) {
        return createBlack(Type.QUEEN, point);
    }

    public static Piece createWhiteKing(Point point) {
        return createWhite(Type.KING, point);
    }

    public static Piece createBlackKing(Point point) {
        return createBlack(Type.KING, point);
    }

    private static Piece createWhite(Type type, Point point) {
        return new Piece(Color.WHITE, type, point);
    }

    private static Piece createBlack(Type type, Point point) {
        return new Piece(Color.BLACK, type, point);
    }

    public static Piece createBlank() { return new Piece(Color.NO_COLOR, Type.NO_PIECE, new Point(0, 0));
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


}
