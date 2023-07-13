package softeer2nd.chess.pieces.factory;

import softeer2nd.chess.pieces.Bishop;
import softeer2nd.chess.pieces.piece.Color;
import softeer2nd.chess.pieces.piece.Piece;

public class BishopFactory {

	public static Piece createWhite(String position) {
		return new Bishop(Color.WHITE, position);
	}

	public static Piece createBlack(String position) {
		return new Bishop(Color.BLACK, position);
	}
}