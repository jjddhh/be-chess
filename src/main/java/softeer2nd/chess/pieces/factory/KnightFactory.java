package softeer2nd.chess.pieces.factory;

import softeer2nd.chess.pieces.Knight;
import softeer2nd.chess.pieces.piece.Color;
import softeer2nd.chess.pieces.piece.Piece;

public class KnightFactory {

	public static Piece createWhite(String position) {
		return new Knight(Color.WHITE, position);
	}

	public static Piece createBlack(String position) {
		return new Knight(Color.BLACK, position);
	}
}
