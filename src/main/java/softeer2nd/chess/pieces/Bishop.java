package softeer2nd.chess.pieces;

import softeer2nd.chess.ChessGame;
import softeer2nd.chess.pieces.enums.Color;
import softeer2nd.chess.pieces.enums.Type;

public class Bishop extends Piece {
    private Bishop(Color color, Position position) {
        super(color, Type.BISHOP, position);
    }

    public static Piece createWhite(Position position) {
        return new Bishop(Color.WHITE, position);
    }

    public static Piece createWhite(String position) {
        return new Bishop(Color.WHITE, new Position(position));
    }

    public static Piece createBlack(Position position) {
        return new Bishop(Color.BLACK, position);
    }

    public static Piece createBlack(String point) {
        return new Bishop(Color.BLACK, new Position(point));
    }

//    public static Piece createWhiteBishop(Position position) {
//        return createWhite(Type.BISHOP, position);
//    }
//
//    public static Piece createWhiteBishop(String point) {
//        return createWhite(Type.BISHOP, point);
//    }
//
//    public static Piece createBlackBishop(Position position) {
//        return createBlack(Type.BISHOP, position);
//    }
//
//    public static Piece createBlackBishop(String point) {
//        return createBlack(Type.BISHOP, point);
//    }

    @Override
    public void verifyMovePosition(Position position, Position targetPiecePosition, ChessGame chessGame) {

    }
}
