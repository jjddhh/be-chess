package softeer2nd.chess.pieces;

import softeer2nd.chess.pieces.piece.Piece;
import softeer2nd.chess.pieces.piece.Color;
import softeer2nd.chess.pieces.piece.Position;
import softeer2nd.chess.pieces.piece.Type;

import java.util.ArrayList;
import java.util.List;

public class Rook extends Piece {

	private Rook(Color color, String position) {
		super(color, Type.ROOK, position);
	}

	public static Piece createWhite(String position) {
		return new Rook(Color.WHITE, position);
	}

	public static Piece createBlack(String position) {
		return new Rook(Color.BLACK, position);
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

		if (isLinearMove(rowGap, colGap)) {
			return true;
		}

		return false;
	}
}