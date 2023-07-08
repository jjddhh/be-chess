package softeer2nd.chess.pieces;

import softeer2nd.chess.ChessGame;
import softeer2nd.chess.pieces.piece.Piece;
import softeer2nd.chess.util.StringUtils;
import softeer2nd.chess.pieces.piece.Color;
import softeer2nd.chess.pieces.piece.Type;
import softeer2nd.chess.pieces.exception.InvalidMoveException;

public class Queen extends Piece {
    private Queen(Color color, String position) {
        super(color, Type.QUEEN, position);
    }

    public static Piece createWhite(String position) {
        return new Queen(Color.WHITE, position);
    }

    public static Piece createBlack(String position) {
        return new Queen(Color.BLACK, position);
    }

    @Override
    protected void verifyMove(Position targetPosition, ChessGame chessGame) {
        verifyMoveDirection(targetPosition);
        verifyPieceOnPath(super.getPosition(), targetPosition, chessGame);
    }

    private void verifyMoveDirection(Position targetPosition) {
        int sourceRow = super.getRow();
        int sourceCol = super.getCol();
        int targetRow = targetPosition.getRow();
        int targetCol = targetPosition.getCol();

        // 가로, 세로 방향 체크
        if (sourceRow == targetRow || sourceCol == targetCol) return;

        // 대각선 방향 체크
        if (Math.abs(sourceRow - targetRow) == Math.abs(sourceCol - targetCol)) return;

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

        String originPositionFormat = StringUtils.getOriginPositionFormat(moveRow, moveCol);

        Piece piece = chessGame.findPiece(originPositionFormat);
        if(piece.isPiece()) {
            throw InvalidMoveException.EXCEPTION;
        }

        verifyPieceOnPath(new Position(moveCol, moveRow), targetPosition, chessGame);
    }
}
