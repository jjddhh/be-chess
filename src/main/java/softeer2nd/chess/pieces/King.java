package softeer2nd.chess.pieces;

import softeer2nd.chess.ChessGame;
import softeer2nd.chess.pieces.piece.Color;
import softeer2nd.chess.pieces.piece.Type;
import softeer2nd.chess.pieces.exception.InvalidMoveException;
import softeer2nd.chess.pieces.piece.Piece;

public class King extends Piece {
    private King(Color color, String position) {
        super(color, Type.KING, position);
    }

    public static Piece createWhite(String position) {
        return new King(Color.WHITE, position);
    }

    public static Piece createBlack(String position) {
        return new King(Color.BLACK, position);
    }

    @Override
    protected void verifyMove(Position targetPosition, ChessGame chessGame) {
        int sourceRow = super.getRow();
        int sourceCol = super.getCol();
        int targetRow = targetPosition.getRow();
        int targetCol = targetPosition.getCol();

        if(Math.abs(sourceCol - targetCol) > 1 ||
                Math.abs(sourceRow - targetRow) > 1) {
            throw InvalidMoveException.EXCEPTION;
        }
    }
}