package softeer2nd.chess.pieces;

import softeer2nd.chess.Board;
import softeer2nd.chess.ChessGame;
import softeer2nd.chess.exception.OutOfBoardException;
import softeer2nd.chess.exception.SameTeamExistException;
import softeer2nd.chess.pieces.enums.Color;
import softeer2nd.chess.pieces.enums.Type;

import java.util.Objects;

public abstract class Piece {
    private Color color;
    private Type type;
    private char representation;
    private Position position;

    protected Piece(Color color, Type type, Position position) {
        this.color = color;
        this.type = type;
        this.representation = setRepresentation(color, type);
        this.position = position;
    }

    protected abstract void verifyMove(Position sourcePosition, Position targetPosition, ChessGame chessGame);

    public Position getPosition() {
        return position;
    }

    public Type getType() { return type; }

    private char setRepresentation(final Color color, final Type name) {
        char representation = '.';

        if (name.isPiece()) {
            if(color.equals(Color.WHITE)) {
                representation = name.getWhiteRepresentation();
            }
            else {
                representation = name.getBlackRepresentation();
            }
        }

        return representation;
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

    public boolean isPiece() {
        return !(color.equals(Color.NO_COLOR) && type.equals(Type.NO_PIECE));
    }

    public boolean isBlank() {
        return (color.equals(Color.NO_COLOR) && type.equals(Type.NO_PIECE));
    }

    public double getPoint() {
        return type.getDefaultPoint();
    }

    public void move(Piece sourcePiece, Piece targetPiece, ChessGame chessGame) {
        Position sourcePosition = sourcePiece.getPosition();
        Position targetPosition = targetPiece.getPosition();

        verifySameTeamOnTarget(sourcePiece,targetPiece);
        verifyMove(sourcePosition, targetPosition, chessGame);

        this.position = targetPosition;
    }

    private void verifySameTeamOnTarget(Piece sourcePiece, Piece targetPiece) {
        if(sourcePiece.getColor() == targetPiece.getColor()) {
            throw SameTeamExistException.EXCEPTION;
        }
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

        private void verifyPiecePosition(int col, int row) {
            if(!(0 <= col && col < Board.COl && 0 <= row && row < Board.ROW)){
                throw OutOfBoardException.EXCEPTION;
            }
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