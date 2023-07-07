package softeer2nd.chess.pieces;

import softeer2nd.chess.ChessGame;
import softeer2nd.chess.pieces.enums.Color;
import softeer2nd.chess.pieces.enums.Type;

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

//    public static Piece createWhitePawn(Piece.Position position) {
//        return createWhite(Type.PAWN, position);
//    }
//
//    public static Piece createWhitePawn(String point) {
//        return createWhite(Type.PAWN, point);
//    }
//
//    public static Piece createBlackPawn(Position position) {
//        return createBlack(Type.PAWN, position);
//    }
//
//    public static Piece createBlackPawn(String point) {
//        return createBlack(Type.PAWN, point);
//    }

    @Override
    public void verifyMovePosition(Position position, Position targetPiecePosition, ChessGame chessGame) {

    }
}
