package softeer2nd.chess.pieces.enums;

public enum Type {
    // 반복문 돌릴시 NO_PIECE 문제
    PAWN('p', 1.0), ROOK('r', 5.0), KNIGHT('n', 2.5),
    BISHOP('b', 3.0), QUEEN('q', 9.0), KING('k', 0.0),
    NO_PIECE('.', 0.0);

    private char representation;
    private double defaultPoint;

    private Type(char representation, double defaultPoint) {
        this.representation = representation;
        this.defaultPoint = defaultPoint;
    }

    public char getWhiteRepresentation() {
        return representation;
    }

    public char getBlackRepresentation() {
        return Character.toUpperCase(representation);
    }

    public double getDefaultPoint() {
        return defaultPoint;
    }

    public boolean isPiece() {
        return representation != '.';
    }
}
