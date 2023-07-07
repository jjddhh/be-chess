package softeer2nd.chess.pieces;

import softeer2nd.chess.ChessGame;
import softeer2nd.chess.pieces.enums.Color;
import softeer2nd.chess.pieces.enums.Type;

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


//    public static Piece createWhiteKnight(Piece.Position position) {
//        return createWhite(Type.KNIGHT, position);
//    }
//
//    public static Piece createWhiteKnight(String point) {
//        return createWhite(Type.KNIGHT, point);
//    }
//
//    public static Piece createBlackKnight(Piece.Position position) {
//        return createBlack(Type.KNIGHT, position);
//    }
//
//    public static Piece createBlackKnight(String point) {
//        return createBlack(Type.KNIGHT, point);
//    }

    @Override
    public void verifyMovePosition(Position position, Position targetPiecePosition, ChessGame chessGame) {

    }
}
