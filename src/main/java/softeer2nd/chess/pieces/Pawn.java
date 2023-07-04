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

    public Pawn(final String color) {
        verifyColor(color);

        this.color = color;
        this.representation = color.equals(WHITE_COLOR) ? WHITE_REPRESENTATION : BLACK_REPRESENTATION;;
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