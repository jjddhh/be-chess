package softeer2nd.chess.pieces;

import softeer2nd.chess.Board;
import softeer2nd.chess.exception.InvalidColorException;
import softeer2nd.chess.exception.OutOfBoardException;

import java.awt.*;

public class Piece {

    public static final String WHITE_COLOR = "white";
    public static final String BLACK_COLOR = "black";


    public static final char WHITE_PAWN_REPRESENTATION = 'p';
    public static final char BLACK_PAWN_REPRESENTATION = 'P';

    public static final char WHITE_KNIGHT_REPRESENTATION = 'n';
    public static final char BLACK_KNIGHT_REPRESENTATION = 'N';

    public static final char WHITE_ROOK_REPRESENTATION = 'r';
    public static final char BLACK_ROOK_REPRESENTATION = 'R';

    public static final char WHITE_BISHOP_REPRESENTATION = 'b';
    public static final char BLACK_BISHOP_REPRESENTATION = 'B';

    public static final char WHITE_QUEEN_REPRESENTATION = 'q';
    public static final char BLACK_QUEEN_REPRESENTATION = 'Q';

    public static final char WHITE_KING_REPRESENTATION = 'k';
    public static final char BLACK_KING_REPRESENTATION = 'K';

    private final String color;
    private final Type name;
    private final char representation;
    private Point point;

    private Piece(String color, Type name) {
        verifyColor(color);

        this.color = color;
        this.name = name;
        this.representation = setRepresentation(color, name);
    }

    private Piece(String color, Type name, Point point) {
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

    private char setRepresentation(final String color, final Type name) {
        char representation = '0';
        switch (name) {
            case PAWN:
                if(color.equals(WHITE_COLOR)) representation = WHITE_PAWN_REPRESENTATION;
                else representation = BLACK_PAWN_REPRESENTATION;
                break;
            case KNIGHT:
                if(color.equals(WHITE_COLOR)) representation = WHITE_KNIGHT_REPRESENTATION;
                else representation = BLACK_KNIGHT_REPRESENTATION;
                break;
            case ROOK:
                if(color.equals(WHITE_COLOR)) representation = WHITE_ROOK_REPRESENTATION;
                else representation = BLACK_ROOK_REPRESENTATION;
                break;
            case BISHOP:
                if(color.equals(WHITE_COLOR)) representation = WHITE_BISHOP_REPRESENTATION;
                else representation = BLACK_BISHOP_REPRESENTATION;
                break;
            case QUEEN:
                if(color.equals(WHITE_COLOR)) representation = WHITE_QUEEN_REPRESENTATION;
                else representation = BLACK_QUEEN_REPRESENTATION;
                break;
            case KING:
                if(color.equals(WHITE_COLOR)) representation = WHITE_KING_REPRESENTATION;
                else representation = BLACK_KING_REPRESENTATION;
        }

        return representation;
    }

    public static Piece createWhitePawn(Point point) {
        return new Piece(WHITE_COLOR, Type.PAWN, point);
    }

    public static Piece createBlackPawn(Point point) {
        return new Piece(BLACK_COLOR, Type.PAWN, point);
    }

    public static Piece createWhiteKnight(Point point) {
        return new Piece(WHITE_COLOR, Type.KNIGHT, point);
    }

    public static Piece createBlackKnight(Point point) {
        return new Piece(BLACK_COLOR, Type.KNIGHT, point);
    }

    public static Piece createWhiteRook(Point point) {
        return new Piece(WHITE_COLOR, Type.ROOK, point);
    }

    public static Piece createBlackRook(Point point) {
        return new Piece(BLACK_COLOR, Type.ROOK, point);
    }

    public static Piece createWhiteBishop(Point point) {
        return new Piece(WHITE_COLOR, Type.BISHOP, point);
    }

    public static Piece createBlackBishop(Point point) {
        return new Piece(BLACK_COLOR, Type.BISHOP, point);
    }

    public static Piece createWhiteQueen(Point point) {
        return new Piece(WHITE_COLOR, Type.QUEEN, point);
    }

    public static Piece createBlackQueen(Point point) {
        return new Piece(BLACK_COLOR, Type.QUEEN, point);
    }

    public static Piece createWhiteKing(Point point) {
        return new Piece(WHITE_COLOR, Type.KING, point);
    }

    public static Piece createBlackKing(Point point) {
        return new Piece(BLACK_COLOR, Type.KING, point);
    }

    private void verifyColor(String color) {
        if(!(color.equals(Piece.WHITE_COLOR) || color.equals(Piece.BLACK_COLOR)))
            throw InvalidColorException.EXCEPTION;
    }

    public String getColor() {
        return this.color;
    }

    public char getRepresentation() {
        return this.representation;
    }

    public boolean isBlack() {
        return color.equals(BLACK_COLOR);
    }

    public boolean isWhite() {
        return color.equals(WHITE_COLOR);
    }
}
