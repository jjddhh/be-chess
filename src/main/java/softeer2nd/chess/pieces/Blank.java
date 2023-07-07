package softeer2nd.chess.pieces;

import softeer2nd.chess.ChessGame;
import softeer2nd.chess.pieces.enums.Color;
import softeer2nd.chess.pieces.enums.Type;

public class Blank extends Piece {
    private Blank(String position) {
        super(Color.NO_COLOR, Type.NO_PIECE, position);
    }

    public static Piece create(String position) {
        return new Blank(position);
    }

    @Override
    protected void verifyMove(Position targetPosition, ChessGame chessGame) {}
}
