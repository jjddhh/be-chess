package softeer2nd.chess.pieces;

import softeer2nd.chess.ChessGame;
import softeer2nd.chess.pieces.enums.Direction;
import softeer2nd.chess.pieces.piece.Color;
import softeer2nd.chess.pieces.piece.Position;
import softeer2nd.chess.pieces.piece.Type;
import softeer2nd.chess.pieces.exception.InvalidMoveException;
import softeer2nd.chess.pieces.piece.Piece;

import java.util.ArrayList;
import java.util.List;

public class Knight extends Piece {

	private Knight(Color color, String position) {
		super(color, Type.KNIGHT, position);
	}

	public static Piece createWhite(String position) {
		return new Knight(Color.WHITE, position);
	}

	public static Piece createBlack(String position) {
		return new Knight(Color.BLACK, position);
	}

	@Override
	public List<Position> findBetweenPath(Piece targetPiece) {
		return new ArrayList<>();
	}

	@Override
	public boolean isValidPosition(Piece targetPiece) {
		Position targetPosition = targetPiece.getPosition();

		int dr = targetPosition.getRow() - super.getRow();
		int dc = targetPosition.getCol() - super.getCol();

		return Direction.knightDirection().stream()
			.filter(direction -> direction.getXDegree() == dc && direction.getYDegree() == dr)
			.findFirst()
			.isPresent();
	}
}
