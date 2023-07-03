package softeer2nd;

import softeer2nd.exception.InvalidColorException;

public class Pawn {

    private final String WHITE = "white";
    private final String BLACK = "black";
    private final String color;

    public Pawn(final String color) {
        verifyColor(color);
        this.color = color;
    }

    private void verifyColor(String color) {
        if(!(color.equals(WHITE) || color.equals(BLACK)))
            throw InvalidColorException.EXCEPTION;
    }

    public String getColor() {
        return this.color;
    }
}