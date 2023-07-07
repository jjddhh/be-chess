package softeer2nd.chess.pieces;

import softeer2nd.chess.ChessGame;
import softeer2nd.chess.pieces.enums.Color;
import softeer2nd.chess.pieces.enums.Type;
import softeer2nd.chess.pieces.exception.InvalidMoveException;

public class Pawn extends Piece{
    private Pawn(Color color, Position position) {
        super(color, Type.PAWN, position);
    }

    public static Piece createWhite(Position position) {
        return new Pawn(Color.WHITE, position);
    }

    public static Piece createWhite(String position) {
        return new Pawn(Color.WHITE, new Position(position));
    }

    public static Piece createBlack(Position position) {
        return new Pawn(Color.BLACK, position);
    }

    public static Piece createBlack(String point) {
        return new Pawn(Color.BLACK, new Position(point));
    }

    @Override
    protected void verifyMove(Position sourcePosition, Position targetPosition, ChessGame chessGame) {
        verifyMoveDistance(sourcePosition, targetPosition, chessGame);
        verifyDiagonalMove(sourcePosition, targetPosition, chessGame);
    }

    private void verifyMoveDistance(Position sourcePosition, Position targetPosition, ChessGame chessGame) {
        Piece piece = chessGame.findPiece(sourcePosition);
        int dr = targetPosition.getRow() - sourcePosition.getRow();
        int dc = targetPosition.getCol() - sourcePosition.getCol();

        if (-1 <= dc && dc <= 1) {
            if((piece.getColor().equals(Color.BLACK) && dr == 1) ||
                    (piece.getColor().equals(Color.WHITE) && dr == -1)) return;
        }

        throw InvalidMoveException.EXCEPTION;
    }

    private void verifyDiagonalMove(Position sourcePosition, Position targetPosition, ChessGame chessGame) {
        Piece targetPiece = chessGame.findPiece(targetPosition);
        int dc = targetPosition.getCol() - sourcePosition.getCol();

        if (dc == 0 && targetPiece.isPiece()) {
            throw InvalidMoveException.EXCEPTION;
        } else if (dc != 0 && targetPiece.isBlank()) {
            throw InvalidMoveException.EXCEPTION;
        }
    }
}