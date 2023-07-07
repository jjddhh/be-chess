package softeer2nd.chess.pieces;

import softeer2nd.chess.ChessGame;
import softeer2nd.chess.pieces.enums.Color;
import softeer2nd.chess.pieces.enums.Type;
import softeer2nd.chess.pieces.exception.InvalidMoveException;

import java.util.Optional;

public class Knight extends Piece{
    private Knight(Color color, Position position) {
        super(color, Type.KNIGHT, position);
    }

    public static Piece createWhite(Position position) {
        return new Knight(Color.WHITE, position);
    }

    public static Piece createWhite(String position) {
        return new Knight(Color.WHITE, new Position(position));
    }

    public static Piece createBlack(Position position) {
        return new Knight(Color.BLACK, position);
    }

    public static Piece createBlack(String point) {
        return new Knight(Color.BLACK, new Position(point));
    }

    @Override
    protected void verifyMove(Position sourcePosition, Position targetPosition, ChessGame chessGame) {
        int dr = targetPosition.getRow() - sourcePosition.getRow();
        int dc = targetPosition.getCol() - sourcePosition.getCol();

        Direction.knightDirection().stream()
                .filter(direction -> direction.getXDegree() == dc && direction.getYDegree() == dr)
                .findFirst()
                .orElseThrow(() -> InvalidMoveException.EXCEPTION);
    }
}
