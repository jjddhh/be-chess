package softeer2nd.chess.pieces;

import softeer2nd.chess.pieces.piece.Piece;
import softeer2nd.chess.pieces.piece.Position;
import softeer2nd.chess.pieces.piece.Color;
import softeer2nd.chess.pieces.piece.Type;

import java.util.ArrayList;
import java.util.List;

public class Queen extends Piece {

	public Queen(Color color, String position) {
		super(color, Type.QUEEN, position);
	}


	@Override
	public boolean isValidPosition(Position targetPosition) {
		return isValidDirection(targetPosition);
	}

	private boolean isValidDirection(Position targetPosition) {
		int rowGap = getRowGap(targetPosition, super.getPosition());
		int colGap = getColGap(targetPosition, super.getPosition());

		if (isLinearMove(rowGap, colGap)) {
			return true;
		}

		if (isDiagonalMove(rowGap, colGap)){
			return true;
		}

		return false;
	}

	@Override
	public List<Position> findBetweenPath(Piece targetPiece) {
		List<Position> betweenPaths = new ArrayList<>();
		getBetweenPath(super.getPosition(), targetPiece.getPosition(), betweenPaths);
		return betweenPaths;
	}
}