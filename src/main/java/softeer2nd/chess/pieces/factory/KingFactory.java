package softeer2nd.chess.pieces.factory;

import softeer2nd.chess.pieces.King;
import softeer2nd.chess.pieces.piece.Color;
import softeer2nd.chess.pieces.piece.Piece;

public class KingFactory {

	public static Piece createWhite(String position) {
		return new King(Color.WHITE, position);
	}

	public static Piece createBlack(String position) {
		return new King(Color.BLACK, position);
	}
}
