package softeer2nd.chess.pieces;

import softeer2nd.chess.ChessGame;
import softeer2nd.chess.pieces.piece.Piece;
import softeer2nd.chess.utils.StringUtil;
import softeer2nd.chess.pieces.piece.Color;
import softeer2nd.chess.pieces.piece.Type;
import softeer2nd.chess.pieces.exception.InvalidMoveException;

public class Pawn extends Piece {
    private Pawn(Color color, String position) {
        super(color, Type.PAWN, position);
    }

    public static Piece createWhite(String position) {
        return new Pawn(Color.WHITE, position);
    }

    public static Piece createBlack(String position) {
        return new Pawn(Color.BLACK, position);
    }

    @Override
    protected void verifyMove(Position targetPosition, ChessGame chessGame) {
        verifyMoveDistance(super.getPosition(), targetPosition, chessGame);
        verifyDiagonalMove(super.getPosition(), targetPosition, chessGame);
    }

    private void verifyMoveDistance(Position sourcePosition, Position targetPosition, ChessGame chessGame) {
        int dr = targetPosition.getRow() - sourcePosition.getRow();
        int dc = targetPosition.getCol() - sourcePosition.getCol();

        if (-1 <= dc && dc <= 1) {
            if((super.getColor().equals(Color.BLACK) && dr == 1) ||
                    (super.getColor().equals(Color.WHITE) && dr == -1)) return;
        }

        throw InvalidMoveException.EXCEPTION;
    }

    private void verifyDiagonalMove(Position sourcePosition, Position targetPosition, ChessGame chessGame) {
        String originPositionFormat = StringUtil.getOriginPositionFormat(targetPosition.getRow(), targetPosition.getCol());
        Piece targetPiece = chessGame.findPiece(originPositionFormat);
        int dc = targetPosition.getCol() - sourcePosition.getCol();

        if (dc == 0 && targetPiece.isPiece()) {
            throw InvalidMoveException.EXCEPTION;
        } else if (dc != 0 && targetPiece.isBlank()) {
            throw InvalidMoveException.EXCEPTION;
        }
    }
}