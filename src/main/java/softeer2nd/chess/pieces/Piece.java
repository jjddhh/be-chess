package softeer2nd.chess.pieces;

import softeer2nd.chess.exception.InvalidColorException;

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

    private Piece(String color, Type name) {
        verifyColor(color);

        this.color = color;
        this.name = name;
        this.representation = setRepresentation(color, name);
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

    public static Piece createWhitePawn() {
        return new Piece(WHITE_COLOR, Type.PAWN);
    }

    public static Piece createBlackPawn() {
        return new Piece(BLACK_COLOR, Type.PAWN);
    }

    public static Piece createWhiteKnight() {
        return new Piece(WHITE_COLOR, Type.KNIGHT);
    }

    public static Piece createBlackKnight() {
        return new Piece(BLACK_COLOR, Type.KNIGHT);
    }

    public static Piece createWhiteRook() {
        return new Piece(WHITE_COLOR, Type.ROOK);
    }

    public static Piece createBlackRook() {
        return new Piece(BLACK_COLOR, Type.ROOK);
    }

    public static Piece createWhiteBishop() {
        return new Piece(WHITE_COLOR, Type.BISHOP);
    }

    public static Piece createBlackBishop() {
        return new Piece(BLACK_COLOR, Type.BISHOP);
    }

    public static Piece createWhiteQueen() {
        return new Piece(WHITE_COLOR, Type.QUEEN);
    }

    public static Piece createBlackQueen() {
        return new Piece(BLACK_COLOR, Type.QUEEN);
    }

    public static Piece createWhiteKing() {
        return new Piece(WHITE_COLOR, Type.KING);
    }

    public static Piece createBlackKing() {
        return new Piece(BLACK_COLOR, Type.KING);
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
}