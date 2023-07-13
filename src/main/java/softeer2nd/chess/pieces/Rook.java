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
		Position sourcePosition = super.getPosition();

		int sourceRow = sourcePosition.getRow();
		int sourceCol = sourcePosition.getCol();
		int targetRow = targetPosition.getRow();
		int targetCol = targetPosition.getCol();

		// 가로, 세로 방향 체크
		if (sourceRow == targetRow || sourceCol == targetCol) {
			return true;
		}

		return false;
	}

	private void getBetweenPath(Position sourcePosition, Position targetPosition, List<Position> betweenPaths) {
		int moveRow = getRowMoveDifference(sourcePosition, targetPosition);
		int moveCol = getColMoveDifference(sourcePosition, targetPosition);

		if (moveRow == targetPosition.getRow()
			&& moveCol == targetPosition.getCol()) {
			return;
		}
		Position middlePosition = new Position(moveCol, moveRow);
		betweenPaths.add(middlePosition);

		getBetweenPath(middlePosition, targetPosition, betweenPaths);
	}
	private int getRowMoveDifference(Position sourcePosition, Position targetPosition) {
		int sourceRow = sourcePosition.getRow();
		int targetRow = targetPosition.getRow();

		int rowDirection = targetRow - sourceRow;

		int dr = rowDirection == 0 ? 0 : (rowDirection > 0 ? 1 : -1);

		return sourceRow + dr;
	}

	private int getColMoveDifference(Position sourcePosition, Position targetPosition) {
		int sourceCol = sourcePosition.getCol();
		int targetCol = targetPosition.getCol();

		int colDirection = targetCol - sourceCol;

		int dr = colDirection == 0 ? 0 : (colDirection > 0 ? 1 : -1);

		return sourceCol + dr;
	}
}