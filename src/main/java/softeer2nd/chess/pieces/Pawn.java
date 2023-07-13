package softeer2nd.chess.pieces;

import softeer2nd.chess.ChessGame;
import softeer2nd.chess.pieces.enums.Direction;
import softeer2nd.chess.pieces.exception.PawnCaptureException;
import softeer2nd.chess.pieces.exception.PawnMoveException;
import softeer2nd.chess.pieces.piece.Piece;
import softeer2nd.chess.pieces.piece.Position;
import softeer2nd.chess.utils.StringUtil;
import softeer2nd.chess.pieces.piece.Color;
import softeer2nd.chess.pieces.piece.Type;
import softeer2nd.chess.pieces.exception.InvalidMoveException;

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
	public boolean isValidPosition(Piece targetPiece) {
		return isValidDirection(targetPiece);
	}

	@Override
	public void move(String targetPosition) {
		if (isVerticalMove(targetPosition)) {
			super.move(targetPosition);
			return;
		}

		throw PawnMoveException.EXCEPTION;
	}

	private boolean isVerticalMove(String originTargetPosition) {
		Position sourcePosition = super.getPosition();
		Position targetPosition = new Position(originTargetPosition);

		int sourceRow = sourcePosition.getRow();
		int sourceCol = sourcePosition.getCol();
		int targetRow = targetPosition.getRow();
		int targetCol = targetPosition.getCol();

		int rowGap = targetRow - sourceRow;
		int colGap = targetCol - sourceCol;

		return Direction.linearDirection().stream()
			.filter(direction -> direction.isEqual(rowGap, colGap))
			.findFirst()
			.isPresent();
	}

	@Override
	public void capture(Piece targetPiece) {
		if (isDiagonalMove(targetPiece)) {
			super.capture(targetPiece);
			return;
		}

		throw PawnCaptureException.EXCEPTION;
	}

	private boolean isDiagonalMove(Piece targetPiece) {
		Position sourcePosition = super.getPosition();
		Position targetPosition = targetPiece.getPosition();

		int sourceRow = sourcePosition.getRow();
		int sourceCol = sourcePosition.getCol();
		int targetRow = targetPosition.getRow();
		int targetCol = targetPosition.getCol();

		int rowGap = targetRow - sourceRow;
		int colGap = targetCol - sourceCol;

		return Direction.diagonalDirection().stream()
			.filter(direction -> direction.isEqual(rowGap, colGap))
			.findFirst()
			.isPresent();
	}

	private boolean isValidDirection(Piece targetPiece) {
		Position sourcePosition = super.getPosition();
		Position targetPosition = targetPiece.getPosition();

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

	private void verifyMoveDistance(Position sourcePosition, Position targetPosition) {
		int dr = targetPosition.getRow() - sourcePosition.getRow();
		int dc = targetPosition.getCol() - sourcePosition.getCol();

		if (-1 <= dc && dc <= 1) {
			if ((super.getColor().equals(Color.BLACK) && dr == 1) ||
				(super.getColor().equals(Color.WHITE) && dr == -1))
				return;
		}

		throw InvalidMoveException.EXCEPTION;
	}

	private void verifyDiagonalMove(Position sourcePosition, Position targetPosition, ChessGame chessGame) {
		String originPositionFormat = StringUtil.getOriginPositionFormat(targetPosition.getRow(),
			targetPosition.getCol());
		Piece targetPiece = chessGame.findPiece(originPositionFormat);
		int dc = targetPosition.getCol() - sourcePosition.getCol();

		if (dc == 0 && targetPiece.isPiece()) {
			throw InvalidMoveException.EXCEPTION;
		} else if (dc != 0 && targetPiece.isBlank()) {
			throw InvalidMoveException.EXCEPTION;
		}
	}
}