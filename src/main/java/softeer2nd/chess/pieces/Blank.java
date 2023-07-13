package softeer2nd.chess.pieces;

import softeer2nd.chess.pieces.piece.Color;
import softeer2nd.chess.pieces.piece.Position;
import softeer2nd.chess.pieces.piece.Type;
import softeer2nd.chess.pieces.piece.Piece;

import java.util.List;

public class Blank extends Piece {

	private Blank(String position) {
		super(Color.NO_COLOR, Type.NO_PIECE, position);
	}

	public static Piece create(String position) {
		return new Blank(position);
	}

	@Override
	public List<Position> findBetweenPath(Piece targetPiece) {
		return null;
	}

	@Override
	public boolean isValidPosition(Position targetPosition) {
		return false;
	}
}