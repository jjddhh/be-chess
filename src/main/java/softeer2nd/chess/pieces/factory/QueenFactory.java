package softeer2nd.chess.pieces.factory;

import softeer2nd.chess.pieces.Queen;
import softeer2nd.chess.pieces.piece.Color;
import softeer2nd.chess.pieces.piece.Piece;

public class QueenFactory {

	public static Piece createWhite(String position) {
		return new Queen(Color.WHITE, position);
	}

	public static Piece createBlack(String position) {
		return new Queen(Color.BLACK, position);
	}
}
