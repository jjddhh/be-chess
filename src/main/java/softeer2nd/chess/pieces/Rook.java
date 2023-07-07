package softeer2nd.chess.pieces;

import softeer2nd.chess.ChessGame;
import softeer2nd.chess.pieces.enums.Color;
import softeer2nd.chess.pieces.enums.Type;
import softeer2nd.chess.pieces.exception.InvalidMoveException;

public class Rook extends Piece {
    private Rook(Color color, Position position) {
        super(color, Type.ROOK, position);
    }

    public static Piece createWhite(Position position) {
        return new Rook(Color.WHITE, position);
    }

    public static Piece createWhite(String position) {
        return new Rook(Color.WHITE, new Position(position));
    }

    public static Piece createBlack(Position position) {
        return new Rook(Color.BLACK, position);
    }

    public static Piece createBlack(String point) {
        return new Rook(Color.BLACK, new Position(point));
    }

    @Override
    protected void verifyMove(Position sourcePosition, Position targetPosition, ChessGame chessGame) {
        verifyMoveDirection(sourcePosition, targetPosition);
        verifyPieceOnPath(sourcePosition, targetPosition, chessGame);
    }

    private void verifyMoveDirection(Position sourcePosition, Position targetPosition) {
        int sourceRow = sourcePosition.getRow();
        int sourceCol = sourcePosition.getCol();
        int targetRow = targetPosition.getRow();
        int targetCol = targetPosition.getCol();

        // 가로, 세로 방향 체크
        if (sourceRow == targetRow || sourceCol == targetCol) return;

        throw InvalidMoveException.EXCEPTION;
    }

    private void verifyPieceOnPath(Position sourcePosition, Position targetPosition, ChessGame chessGame) {
        int sourceRow = sourcePosition.getRow();
        int sourceCol = sourcePosition.getCol();

        int targetRow = targetPosition.getRow();
        int targetCol = targetPosition.getCol();

        int rowDirection = targetRow - sourceRow;
        int colDirection = targetCol - sourceCol;

        int dr = rowDirection == 0 ? 0 : (rowDirection > 0 ? 1 : -1);
        int dc = colDirection == 0 ? 0 : (colDirection > 0 ? 1 : -1);

        int moveRow = sourceRow + dr;
        int moveCol = sourceCol + dc;

        if (moveRow == targetRow
                && moveCol == targetCol) return;

        Piece piece = chessGame.findPiece(new Position(moveCol, moveRow));
        if(piece.isPiece()) {
            throw InvalidMoveException.EXCEPTION;
        }

        verifyPieceOnPath(new Position(moveCol, moveRow), targetPosition, chessGame);
    }
}
