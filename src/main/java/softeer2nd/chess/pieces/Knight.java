package softeer2nd.chess.pieces;

import softeer2nd.chess.pieces.enums.Direction;
import softeer2nd.chess.pieces.piece.Color;
import softeer2nd.chess.pieces.piece.Position;
import softeer2nd.chess.pieces.piece.Type;
import softeer2nd.chess.pieces.piece.Piece;

import java.util.ArrayList;
import java.util.List;

public class Knight extends Piece {

	public Knight(Color color, String position) {
		super(color, Type.KNIGHT, position);
	}

	@Override
	public List<Position> findBetweenPath(Piece targetPiece) {
		return new ArrayList<>();
	}

	@Override
	public boolean isValidPosition(Position targetPosition) {
		int rowGap = getRowGap(targetPosition, super.getPosition());
		int colGap = getColGap(targetPosition, super.getPosition());

		return isKnightMove(rowGap, colGap);
	}

	private boolean isKnightMove(int rowGap, int colGap) {
		return Direction.knightDirection().stream()
			.filter(direction -> direction.getXDegree() == colGap && direction.getYDegree() == rowGap)
			.findFirst()
			.isPresent();
	}
}