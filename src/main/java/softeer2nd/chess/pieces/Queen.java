package softeer2nd.chess.pieces;

import softeer2nd.chess.ChessGame;
import softeer2nd.chess.pieces.enums.Color;
import softeer2nd.chess.pieces.enums.Type;

public class Queen extends Piece {
    private Queen(Color color, Position position) {
        super(color, Type.QUEEN, position);
    }

    public static Piece createWhite(Position position) {
        return new Queen(Color.WHITE, position);
    }

    public static Piece createWhite(String position) {
        return new Queen(Color.WHITE, new Position(position));
    }

    public static Piece createBlack(Position position) {
        return new Queen(Color.BLACK, position);
    }

    public static Piece createBlack(String point) {
        return new Queen(Color.BLACK, new Position(point));
    }

//    public static Piece createWhiteQueen(Position position) {
//        return createWhite(Type.QUEEN, position);
//    }
//
//    public static Piece createWhiteQueen(String point) {
//        return createWhite(Type.QUEEN, point);
//    }
//
//    public static Piece createBlackQueen(Position position) {
//        return createBlack(Type.QUEEN, position);
//    }
//
//    public static Piece createBlackQueen(String point) {
//        return createBlack(Type.QUEEN, point);
//    }

    @Override
    public void verifyMovePosition(Position position, Position targetPiecePosition, ChessGame chessGame) {

    }
}
