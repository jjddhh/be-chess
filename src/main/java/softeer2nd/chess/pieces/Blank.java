package softeer2nd.chess.pieces;

import softeer2nd.chess.pieces.piece.Color;
import softeer2nd.chess.pieces.piece.Position;
import softeer2nd.chess.pieces.piece.Type;
import softeer2nd.chess.pieces.piece.Piece;

import java.util.List;

public class Blank extends Piece {

	public Blank(String position) {
		super(Color.NO_COLOR, Type.NO_PIECE, position);
	}

	@Override
	public boolean isValidPosition(Position targetPosition) {
		return false;
	}

	@Override
	public List<Position> findBetweenPath(Piece targetPiece) {
		return null;
	}
}