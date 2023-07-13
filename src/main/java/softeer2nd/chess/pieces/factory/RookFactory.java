package softeer2nd.chess.pieces.factory;

import softeer2nd.chess.pieces.Rook;
import softeer2nd.chess.pieces.piece.Color;
import softeer2nd.chess.pieces.piece.Piece;

public class RookFactory {

	public static Piece createWhite(String position) {
		return new Rook(Color.WHITE, position);
	}

	public static Piece createBlack(String position) {
		return new Rook(Color.BLACK, position);
	}
}
