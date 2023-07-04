package softeer2nd.chess.pieces;

import softeer2nd.chess.exception.InvalidColorException;

public class Pawn {

    public static final String WHITE_COLOR = "white";
    public static final String BLACK_COLOR = "black";
    public static final char WHITE_REPRESENTATION = 'p';
    public static final char BLACK_REPRESENTATION = 'P';
    private final String color;
    private final char representation;

    public Pawn() {
        this.representation = WHITE_REPRESENTATION;
        this.color = WHITE_COLOR;
    }

    public Pawn(final String color, final char representation) {
        verifyColor(color);
        // color에 따라서 representaion 추가
        this.color = color;
        this.representation = representation;
    }

    private void verifyColor(String color) {
        if(!(color.equals(WHITE_COLOR) || color.equals(BLACK_COLOR)))
            throw InvalidColorException.EXCEPTION;
    }

    public String getColor() {
        return this.color;
    }

    public char getRepresentation() {
        return this.representation;
    }
}