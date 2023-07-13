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

	private Pawn(Color color, String position) {
		super(color, Type.PAWN, position);
	}

	public static Piece createWhite(String position) {
		return new Pawn(Color.WHITE, position);
	}

	public static Piece createBlack(String position) {
		return new Pawn(Color.BLACK, position);
	}

	@Override
	public List<Position> findBetweenPath(Piece targetPiece) {
		return new ArrayList<>();
	}

	@Override
	public boolean isValidPosition(Position targetPosition) {
		return isValidDirection(targetPosition);
	}

	@Override
	public void move(Position targetPosition) {
		if (isVerticalMove(targetPosition)) {
			super.move(targetPosition);
			return;
		}

		throw PawnMoveException.EXCEPTION;
	}

	private boolean isVerticalMove(Position targetPosition) {
		Position sourcePosition = super.getPosition();

		int rowGap = getRowGap(targetPosition, sourcePosition);
		int colGap = getColGap(targetPosition, sourcePosition);

		return Direction.linearDirection().stream()
			.filter(direction -> direction.isEqual(rowGap, colGap))
			.findFirst()
			.isPresent();
	}

	@Override
	public void capture(Position targetPosition) {
		if (isDiagonalMove(targetPosition)) {
			super.capture(targetPosition);
			return;
		}

		throw PawnCaptureException.EXCEPTION;
	}

	private boolean isDiagonalMove(Position targetPosition) {
		Position sourcePosition = super.getPosition();

		int colGap = getColGap(targetPosition, sourcePosition);
		int rowGap = getRowGap(targetPosition, sourcePosition);

		return Direction.diagonalDirection().stream()
			.filter(direction -> direction.isEqual(rowGap, colGap))
			.findFirst()
			.isPresent();
	}

	private boolean isValidDirection(Position targetPosition) {
		Position sourcePosition = super.getPosition();

		int sourceRow = sourcePosition.getRow();
		int sourceCol = sourcePosition.getCol();
		int targetRow = targetPosition.getRow();
		int targetCol = targetPosition.getCol();

		int rowGap = targetRow - sourceRow;
		int colGap = targetCol - sourceCol;

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

	private int getColGap(Position targetPosition, Position sourcePosition) {
		int sourceCol = sourcePosition.getCol();
		int targetCol = targetPosition.getCol();
		int colGap = targetCol - sourceCol;
		return colGap;
	}

	private int getRowGap(Position targetPosition, Position sourcePosition) {
		int sourceRow = sourcePosition.getRow();
		int targetRow = targetPosition.getRow();

		int rowGap = targetRow - sourceRow;
		return rowGap;
	}
}