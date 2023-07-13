package softeer2nd.chess.pieces;

import softeer2nd.chess.ChessGame;
import softeer2nd.chess.pieces.piece.Color;
import softeer2nd.chess.pieces.piece.Position;
import softeer2nd.chess.pieces.piece.Type;
import softeer2nd.chess.pieces.exception.InvalidMoveException;
import softeer2nd.chess.pieces.piece.Piece;

import java.util.ArrayList;
import java.util.List;

public class King extends Piece {

	private King(Color color, String position) {
		super(color, Type.KING, position);
	}

	public static Piece createWhite(String position) {
		return new King(Color.WHITE, position);
	}

	public static Piece createBlack(String position) {
		return new King(Color.BLACK, position);
	}

	@Override
	public List<Position> findBetweenPath(Piece targetPiece) {
		return new ArrayList<>();
	}

	@Override
	public boolean isValidPosition(Piece targetPiece) {
		Position sourcePosition = super.getPosition();
		Position targetPosition = targetPiece.getPosition();

		int sourceRow = sourcePosition.getRow();
		int sourceCol = sourcePosition.getCol();
		int targetRow = targetPosition.getRow();
		int targetCol = targetPosition.getCol();

		if (Math.abs(sourceCol - targetCol) <= 1 &&
				Math.abs(sourceRow - targetRow) <= 1) {
			return true;
		}

		return false;
	}
}