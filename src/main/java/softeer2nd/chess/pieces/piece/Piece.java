package softeer2nd.chess.pieces.piece;

import softeer2nd.chess.utils.StringUtil;

import java.util.List;
import java.util.Objects;

public abstract class Piece {

	private Color color;
	private Type type;
	private char representation;
	private Position position;

	protected Piece(Color color, Type type, String position) {
		this.color = color;
		this.type = type;
		this.representation = getRepresentation(color, type);
		this.position = new Position(position);
	}

	private char getRepresentation(final Color color, final Type name) {
		char representation = '.';

		if (color.equals(Color.WHITE)) {
			representation = name.getWhiteRepresentation();
		} else if (color.equals(Color.BLACK)) {
			representation = name.getBlackRepresentation();
		}

		return representation;
	}

	public boolean isBlack() {
		return color.equals(Color.BLACK);
	}

	public boolean isWhite() {
		return color.equals(Color.WHITE);
	}

	public boolean isPiece() {
		return !(color.equals(Color.NO_COLOR) || type.equals(Type.NO_PIECE));
	}

	public boolean isBlank() {
		return (color.equals(Color.NO_COLOR) && type.equals(Type.NO_PIECE));
	}

	public void move(String targetPosition) {
		this.position = new Position(targetPosition);
	}

	public void capture(Piece targetPiece) {
		this.position = targetPiece.getPosition();
	}

	public abstract List<Position> findBetweenPath(Piece targetPiece);

	public abstract boolean isValidPosition(Piece targetPiece);

	public boolean isEqualPosition(String targetPosition) {
		String sourcePosition = StringUtil.getOriginPositionFormat(position.getRow(), position.getCol());
		return sourcePosition.equals(targetPosition);
	}

	public Position getPosition() {
		return new Position(position.getCol(), position.getRow());
	}

	public int getCol() {
		return position.getCol();
	}

	public int getRow() {
		return position.getRow();
	}

	public double getPoint() {
		return type.getDefaultPoint();
	}

	public Color getColor() {
		return this.color;
	}

	public char getRepresentation() {
		return this.representation;
	}

	public Type getType() {
		return type;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Piece piece = (Piece)o;
		return representation == piece.representation && color == piece.color && type == piece.type && Objects.equals(
			position, piece.position);
	}

	@Override
	public int hashCode() {
		return Objects.hash(color, type, representation, position);
	}
}