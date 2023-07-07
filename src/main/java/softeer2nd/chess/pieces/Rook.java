package softeer2nd.chess.pieces;

import softeer2nd.chess.ChessGame;
import softeer2nd.chess.pieces.enums.Color;
import softeer2nd.chess.pieces.enums.Type;

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

//    public static Piece createWhiteRook(Position position) {
//        return createWhite(Type.ROOK, position);
//    }
//
//    public static Piece createWhiteRook(String point) {
//        return createWhite(Type.ROOK, point);
//    }
//
//    public static Piece createBlackRook(Position position) {
//        return createBlack(Type.ROOK, position);
//    }
//
//    public static Piece createBlackRook(String point) {
//        return createBlack(Type.ROOK, point);
//    }

    @Override
    public void verifyMovePosition(Position position, Position targetPiecePosition, ChessGame chessGame) {

    }
}
