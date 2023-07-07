package softeer2nd.chess.pieces;

import softeer2nd.chess.ChessGame;
import softeer2nd.chess.pieces.enums.Color;
import softeer2nd.chess.pieces.enums.Type;
import softeer2nd.chess.pieces.exception.InvalidMoveException;

public class King extends Piece{
    private King(Color color, Position position) {
        super(color, Type.KING, position);
    }

    @Override
    public void verifyMovePosition(Position sourcePosition, Position targetPosition, ChessGame chessGame) {
        verifyQueenMove(sourcePosition, targetPosition);
    }

    private void verifyQueenMove(Position sourcePosition, Position targetPosition) {
        int sourceRow = sourcePosition.getRow();
        int sourceCol = sourcePosition.getCol();
        int targetRow = targetPosition.getRow();
        int targetCol = targetPosition.getCol();

        // 가로, 세로 방향 체크
        if (sourceRow == targetRow || sourceCol == targetCol) return;

        // 대각선 방향 체크
        if (Math.abs(sourceRow - targetRow) == Math.abs(sourceCol - targetCol)) return;

        throw InvalidMoveException.EXCEPTION;
    }

    public static Piece createWhite(Position position) {
        return new King(Color.WHITE, position);
    }

    public static Piece createWhite(String position) {
        return new King(Color.WHITE, new Position(position));
    }

    public static Piece createBlack(Position position) {
        return new King(Color.BLACK, position);
    }

    public static Piece createBlack(String point) {
        return new King(Color.BLACK, new Position(point));
    }
스
//    public static Piece createWhiteKing(Position position) {
//        return createWhite(position);
//    }
//
//    public static Piece createWhiteKing(String point) {
//        return createWhite(Type.KING, point);
//    }
//
//    public static Piece createBlackKing(Position position) {
//        return createBlack(Type.KING, position);
//    }
//
//    public static Piece createBlackKing(String point) {
//        return createBlack(Type.KING, point);
//    }
}
