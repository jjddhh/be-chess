package softeer2nd.chess.pieces;

import softeer2nd.chess.pieces.piece.Color;
import softeer2nd.chess.pieces.piece.Position;
import softeer2nd.chess.pieces.piece.Type;
import softeer2nd.chess.pieces.piece.Piece;

import java.util.ArrayList;
import java.util.List;

public class King extends Piece {

	private final Integer LIMIT_DISTANCE = 1;

	public King(Color color, String position) {
		super(color, Type.KING, position);
	}

	@Override
	public boolean isValidPosition(Position targetPosition) {
		int rowGap = getRowGap(targetPosition, super.getPosition());
		int colGap = getColGap(targetPosition, super.getPosition());

		if (isValidKingMove(rowGap, colGap)) {
			return true;
		}

		return false;
	}

	private boolean isValidKingMove(int rowGap, int colGap) {
		return Math.abs(rowGap) <= LIMIT_DISTANCE && Math.abs(colGap) <= LIMIT_DISTANCE;
	}

	@Override
	public List<Position> findBetweenPath(Piece targetPiece) {
		return new ArrayList<>();
	}
}