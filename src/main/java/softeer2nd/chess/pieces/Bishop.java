package softeer2nd.chess.pieces;

import softeer2nd.chess.pieces.piece.Piece;
import softeer2nd.chess.pieces.piece.Position;
import softeer2nd.chess.pieces.piece.Color;
import softeer2nd.chess.pieces.piece.Type;

import java.util.ArrayList;
import java.util.List;

public class Bishop extends Piece {

	private Bishop(Color color, String position) {
		super(color, Type.BISHOP, position);
	}

	public static Piece createWhite(String position) {
		return new Bishop(Color.WHITE, position);
	}

	public static Piece createBlack(String position) {
		return new Bishop(Color.BLACK, position);
	}

	@Override
	public List<Position> findBetweenPath(Piece targetPiece) {
		List<Position> betweenPaths = new ArrayList<>();
		getBetweenPath(super.getPosition(), targetPiece.getPosition(), betweenPaths);
		return betweenPaths;
	}

	@Override
	public boolean isValidPosition(Position targetPosition) {
		return isValidDirection(targetPosition);
	}

	private boolean isValidDirection(Position targetPosition) {
		int rowGap = getRowGap(targetPosition, super.getPosition());
		int colGap = getColGap(targetPosition, super.getPosition());

		if (isDiagonalMove(rowGap, colGap)) {
			return true;
		}

		return false;
	}
}