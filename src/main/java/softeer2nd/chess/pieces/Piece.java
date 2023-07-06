package softeer2nd.chess.pieces;

import softeer2nd.chess.Board;
import softeer2nd.chess.exception.OutOfBoardException;
import softeer2nd.chess.pieces.exception.InvalidMoveException;

import java.util.Objects;


public class Piece {
    private final Color color;
    private final Type type;
    private final char representation;
    private Position position;

    private Piece(Color color, Type type) {
        this.color = color;
        this.type = type;
        this.representation = setRepresentation(color, type);
    }

    private Piece(Color color, Type type, Position position) {
        this.color = color;
        this.type = type;
        this.representation = setRepresentation(color, type);
        this.position = position;
    }

    public Position getPosition() {
        return position;
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

    private static Piece createWhite(Type type, Position position) {
        return new Piece(Color.WHITE, type, position);
    }

    private static Piece createWhite(Type type, String position) {
        return new Piece(Color.WHITE, type, new Position(position));
    }

    private static Piece createBlack(Type type, Position position) {
        return new Piece(Color.BLACK, type, position);
    }

    private static Piece createBlack(Type type, String point) {
        return new Piece(Color.BLACK, type, new Position(point));
    }

    public static Piece createWhitePawn(Position position) {
        return createWhite(Type.PAWN, position);
    }

    public static Piece createWhitePawn(String point) {
        return createWhite(Type.PAWN, point);
    }

    public static Piece createBlackPawn(Position position) {
        return createBlack(Type.PAWN, position);
    }

    public static Piece createBlackPawn(String point) {
        return createBlack(Type.PAWN, point);
    }

    public static Piece createWhiteKnight(Position position) {
        return createWhite(Type.KNIGHT, position);
    }

    public static Piece createWhiteKnight(String point) {
        return createWhite(Type.KNIGHT, point);
    }

    public static Piece createBlackKnight(Position position) {
        return createBlack(Type.KNIGHT, position);
    }

    public static Piece createBlackKnight(String point) {
        return createBlack(Type.KNIGHT, point);
    }

    public static Piece createWhiteRook(Position position) {
        return createWhite(Type.ROOK, position);
    }

    public static Piece createWhiteRook(String point) {
        return createWhite(Type.ROOK, point);
    }

    public static Piece createBlackRook(Position position) {
        return createBlack(Type.ROOK, position);
    }

    public static Piece createBlackRook(String point) {
        return createBlack(Type.ROOK, point);
    }

    public static Piece createWhiteBishop(Position position) {
        return createWhite(Type.BISHOP, position);
    }

    public static Piece createWhiteBishop(String point) {
        return createWhite(Type.BISHOP, point);
    }

    public static Piece createBlackBishop(Position position) {
        return createBlack(Type.BISHOP, position);
    }

    public static Piece createBlackBishop(String point) {
        return createBlack(Type.BISHOP, point);
    }

    public static Piece createWhiteQueen(Position position) {
        return createWhite(Type.QUEEN, position);
    }

    public static Piece createWhiteQueen(String point) {
        return createWhite(Type.QUEEN, point);
    }

    public static Piece createBlackQueen(Position position) {
        return createBlack(Type.QUEEN, position);
    }

    public static Piece createBlackQueen(String point) {
        return createBlack(Type.QUEEN, point);
    }

    public static Piece createWhiteKing(Position position) {
        return createWhite(Type.KING, position);
    }

    public static Piece createWhiteKing(String point) {
        return createWhite(Type.KING, point);
    }

    public static Piece createBlackKing(Position position) {
        return createBlack(Type.KING, position);
    }

    public static Piece createBlackKing(String point) {
        return createBlack(Type.KING, point);
    }

    public static Piece createBlank(Position position) { return new Piece(Color.NO_COLOR, Type.NO_PIECE, position);
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

    public double getPoint() {
        return type.getDefaultPoint();
    }

    public void move(Position position) {
        switch (type) {
            case KING:
                verifyKingMove(position);
                break;

        }
        this.position = position;
    }

    private void verifyKingMove(Position targetPosition) {
        if(Math.abs(position.col - targetPosition.col) > 1 ||
                Math.abs(position.row - targetPosition.row) > 1) throw InvalidMoveException.EXCEPTION;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Piece piece = (Piece) o;
        return representation == piece.representation && color == piece.color && type == piece.type && Objects.equals(position, piece.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(color, type, representation, position);
    }

    public static class Position {
        private final int col;
        private final int row;

        public Position(String position) {
            this(position.charAt(0), Character.getNumericValue(position.charAt(1)));
        }

        public Position(char col, int row) {
            int colTmp = col - 'a';
            row = 8 - row;

            verifyPiecePosition(colTmp, row);

            this.col = colTmp;
            this.row = row;
        }

        public Position(int col, int row) {
            this.col = col;
            this.row = row;
        }

        public int getCol() {
            return col;
        }

        public int getRow() {
            return row;
        }

        private void verifyPiecePosition(Position position) {
            if(!(0 <= position.getCol() && position.getCol() < Board.COl && 0 <= position.getRow() && position.getRow() < Board.ROW))
                throw OutOfBoardException.EXCEPTION;
        }

        private void verifyPiecePosition(int col, int row) {
            if(!(0 <= col && col < Board.COl && 0 <= row && row < Board.ROW))
                throw OutOfBoardException.EXCEPTION;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Position position = (Position) o;
            return col == position.col && row == position.row;
        }

        @Override
        public int hashCode() {
            return Objects.hash(col, row);
        }
    }
}