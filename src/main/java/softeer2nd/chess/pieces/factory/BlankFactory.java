package softeer2nd.chess.pieces.factory;

import softeer2nd.chess.pieces.Blank;
import softeer2nd.chess.pieces.piece.Piece;

public class BlankFactory {

	public static Piece create(String position) {
		return new Blank(position);
	}
}
