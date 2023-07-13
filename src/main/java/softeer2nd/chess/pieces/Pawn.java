package softeer2nd.chess.pieces;

import softeer2nd.chess.pieces.enums.Direction;
import softeer2nd.chess.pieces.exception.PawnCaptureException;
import softeer2nd.chess.pieces.exception.PawnMoveException;
import softeer2nd.chess.pieces.piece.Piece;
import softeer2nd.chess.pieces.piece.Position;
import softeer2nd.chess.pieces.piece.Color;
import softeer2nd.chess.pieces.piece.Type;

import java.util.ArrayList;
import java.util.List;

public class Pawn extends Piece {

	private boolean firstMove = true;

	public Pawn(Color color, String position) {
		super(color, Type.PAWN, position);
	}

	@Override
	public boolean isValidPosition(Position targetPosition) {
		return isValidDirection(targetPosition);
	}

	@Override
	public List<Position> findBetweenPath(Piece targetPiece) {
		List<Position> betweenPaths = new ArrayList<>();
		if (firstMove == true) {
			getBetweenPath(super.getPosition(), targetPiece.getPosition(), betweenPaths);
		}
		return betweenPaths;
	}

	@Override
	public void move(Position targetPosition) {
		int rowGap = getRowGap(targetPosition, super.getPosition());
		int colGap = getColGap(targetPosition, super.getPosition());

		if(firstMove && isDoubleMove(rowGap, colGap)) {
			firstMove = false;
			super.move(targetPosition);
			return;
		}

		if (isLinearMove(rowGap, colGap)) {
			super.move(targetPosition);
			return;
		}

		throw PawnMoveException.EXCEPTION;
	}

	private static boolean isDoubleMove(int rowGap, int colGap) {
		return Direction.pawnDoubleMoveDirection().stream()
			.filter(direction -> direction.isEqual(rowGap, colGap))
			.findFirst()
			.isPresent();
	}

	@Override
	public void capture(Position targetPosition) {
		int colGap = getColGap(targetPosition, super.getPosition());
		int rowGap = getRowGap(targetPosition, super.getPosition());

		if (isDiagonalMove(rowGap, colGap)) {
			super.capture(targetPosition);
			return;
		}

		throw PawnCaptureException.EXCEPTION;
	}

	private boolean isValidDirection(Position targetPosition) {
		int rowGap = getRowGap(targetPosition, super.getPosition());
		int colGap = getColGap(targetPosition, super.getPosition());

		if(super.isBlack()) {
			return Direction.blackPawnDirection().stream()
				.filter(direction -> direction.isEqual(rowGap, colGap))
				.findFirst()
				.isPresent();
		}

		if (super.isWhite()) {
			return Direction.whitePawnDirection().stream()
				.filter(direction -> direction.isEqual(rowGap, colGap))
				.findFirst()
				.isPresent();
		}

		return false;
	}
}