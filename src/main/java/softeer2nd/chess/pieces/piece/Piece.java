package softeer2nd.chess.pieces.piece;

import softeer2nd.chess.Board;
import softeer2nd.chess.ChessGame;
import softeer2nd.chess.utils.StringUtil;
import softeer2nd.chess.exception.OutOfBoardException;
import softeer2nd.chess.exception.SameTeamExistException;

import java.util.Objects;

public abstract class Piece {
    private Color color;
    private Type type;
    private char representation;
    private Position position;

    protected Piece(Color color, Type type, String position) {
        this.color = color;
        this.type = type;
        this.representation = getRepresentation(color, type);
        this.position = new Position(position);
    }

    protected abstract void verifyMove(Position targetPosition, ChessGame chessGame);

    private char getRepresentation(final Color color, final Type name) {
        char representation = '.';

        if (color.equals(Color.WHITE)) {
            representation = name.getWhiteRepresentation();
        }
        else if (color.equals(Color.BLACK)){
            representation = name.getBlackRepresentation();
        }

        return representation;
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

    public void move(Piece targetPiece, ChessGame chessGame) {
        Position targetPosition = targetPiece.getPosition();

        verifyMove(targetPosition, chessGame);

        this.position = targetPosition;
    }

    public boolean isEqualPosition(String targetPosition) {
        String sourcePosition = StringUtil.getOriginPositionFormat(position.getRow(), position.getCol());
        return sourcePosition.equals(targetPosition);
    }

    /**
     * getter
     */
    protected Position getPosition() {
        return position;
    }

    public int getCol() {
        return position.col;
    }

    public int getRow() {
        return position.row;
    }

    public double getPoint() {
        return type.getDefaultPoint();
    }

    public Color getColor() {
        return this.color;
    }

    public char getRepresentation() {
        return this.representation;
    }

    public Type getType() { return type; }

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

    protected static class Position {
        private final int col;
        private final int row;

        public Position(String position) {
            this(getCol(position), getRow(position));
        }

        private static int getRow(String position) {
            return Board.ROW - Integer.parseInt(position.substring(1));
        }

        private static int getCol(String position) {
            return position.charAt(0) - 'a';
        }

        public Position(int col, int row) {
            verifyPiecePosition(col, row);
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