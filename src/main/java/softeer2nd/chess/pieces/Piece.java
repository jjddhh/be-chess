package softeer2nd.chess.pieces;

import softeer2nd.chess.Board;
import softeer2nd.chess.exception.InvalidColorException;
import softeer2nd.chess.exception.OutOfBoardException;

import java.awt.*;

public class Piece {

    public enum Color {
        WHITE, BLACK, NOCOLOR;
    }

    public enum Type {

        PAWN('p'), KNIGHT('n'), ROOK('r'),
        BISHOP('b'), QUEEN('q'), KING('k'),
        NO_PIECE('0');

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
    }

    private final Color color;
    private final Type name;
    private final char representation;
    private Point point;

    private Piece(Color color, Type name) {
        verifyColor(color);

        this.color = color;
        this.name = name;
        this.representation = setRepresentation(color, name);
    }

    private Piece(Color color, Type name, Point point) {
        verifyColor(color);

        this.color = color;
        this.name = name;
        this.representation = setRepresentation(color, name);
        this.point = setPoint(point);
    }

    private Point setPoint(Point point) {
        verifyPiecePoint(point);
        return point;
    }

    private static void verifyPiecePoint(Point point) {
        if(!(0 <= point.x && point.x < Board.COl && 0 <= point.y && point.y < Board.ROW)) throw OutOfBoardException.EXCEPTION;
    }

    public  Point getPoint() {
        return point;
    }

    private char setRepresentation(final Color color, final Type name) {
        char representation = '0';
        switch (name) {
            case PAWN:
                if(color.equals(Color.WHITE)) representation = name.representation;
                else representation = BLACK_PAWN_REPRESENTATION;
                break;
            case KNIGHT:
                if(color.equals(Color.WHITE)) representation = WHITE_KNIGHT_REPRESENTATION;
                else representation = BLACK_KNIGHT_REPRESENTATION;
                break;
            case ROOK:
                if(color.equals(Color.WHITE)) representation = WHITE_ROOK_REPRESENTATION;
                else representation = BLACK_ROOK_REPRESENTATION;
                break;
            case BISHOP:
                if(color.equals(Color.WHITE)) representation = WHITE_BISHOP_REPRESENTATION;
                else representation = BLACK_BISHOP_REPRESENTATION;
                break;
            case QUEEN:
                if(color.equals(Color.WHITE)) representation = WHITE_QUEEN_REPRESENTATION;
                else representation = BLACK_QUEEN_REPRESENTATION;
                break;
            case KING:
                if(color.equals(Color.WHITE)) representation = WHITE_KING_REPRESENTATION;
                else representation = BLACK_KING_REPRESENTATION;
        }

        return representation;
    }

    public static Piece createWhitePawn(Point point) {
        return new Piece(Color.WHITE, Type.PAWN, point);
    }

    public static Piece createBlackPawn(Point point) {
        return new Piece(Color.BLACK, Type.PAWN, point);
    }

    public static Piece createWhiteKnight(Point point) {
        return new Piece(Color.WHITE, Type.KNIGHT, point);
    }

    public static Piece createBlackKnight(Point point) {
        return new Piece(Color.BLACK, Type.KNIGHT, point);
    }

    public static Piece createWhiteRook(Point point) {
        return new Piece(Color.WHITE, Type.ROOK, point);
    }

    public static Piece createBlackRook(Point point) {
        return new Piece(Color.BLACK, Type.ROOK, point);
    }

    public static Piece createWhiteBishop(Point point) {
        return new Piece(Color.WHITE, Type.BISHOP, point);
    }

    public static Piece createBlackBishop(Point point) {
        return new Piece(Color.BLACK, Type.BISHOP, point);
    }

    public static Piece createWhiteQueen(Point point) {
        return new Piece(Color.WHITE, Type.QUEEN, point);
    }

    public static Piece createBlackQueen(Point point) {
        return new Piece(Color.BLACK, Type.QUEEN, point);
    }

    public static Piece createWhiteKing(Point point) {
        return new Piece(Color.WHITE, Type.KING, point);
    }

    public static Piece createBlackKing(Point point) {
        return new Piece(Color.BLACK, Type.KING, point);
    }

    private void verifyColor(Color color) {
        if(!(color.equals(Color.WHITE) || color.equals(Color.BLACK)))
            throw InvalidColorException.EXCEPTION;
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
