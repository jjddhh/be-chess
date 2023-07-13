package softeer2nd.chess.pieces.factory;

import softeer2nd.chess.pieces.Pawn;
import softeer2nd.chess.pieces.piece.Color;
import softeer2nd.chess.pieces.piece.Piece;

public class PawnFactory {

	public static Piece createWhite(String position) {
		return new Pawn(Color.WHITE, position);
	}

	public static Piece createBlack(String position) {
		return new Pawn(Color.BLACK, position);
	}
}
