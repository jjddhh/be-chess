package softeer2nd;

import softeer2nd.exception.InvalidColorException;

public class Pawn {

    public static final String WHITE_COLOR = "white";
    public static final String BLACK_COLOR = "black";
    private final String color;

    public Pawn() {
        this.color = WHITE_COLOR;
    }

    public Pawn(final String color) {
        verifyColor(color);
        this.color = color;
    }

    private void verifyColor(String color) {
        if(!(color.equals(WHITE_COLOR) || color.equals(BLACK_COLOR)))
            throw InvalidColorException.EXCEPTION;
    }

    public String getColor() {
        return this.color;
    }
}