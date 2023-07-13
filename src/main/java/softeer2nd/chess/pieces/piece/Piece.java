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

	public void move(Position targetPosition) {
		this.position = targetPosition.valueOf();
	}

	public void capture(Position targetPosition) {
		this.position = targetPosition;
	}

	public abstract List<Position> findBetweenPath(Piece targetPiece);

	public abstract boolean isValidPosition(Position targetPosition);

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

	protected boolean isLinearMove(int rowGap, int colGap) {
		return rowGap == 0 || colGap == 0;
	}

	protected boolean isDiagonalMove(int rowGap, int colGap) {
		return Math.abs(rowGap) == Math.abs(colGap);
	}

	protected int getColGap(Position targetPosition, Position sourcePosition) {
		int sourceCol = sourcePosition.getCol();
		int targetCol = targetPosition.getCol();
		int colGap = targetCol - sourceCol;
		return colGap;
	}

	protected int getRowGap(Position targetPosition, Position sourcePosition) {
		int sourceRow = sourcePosition.getRow();
		int targetRow = targetPosition.getRow();

		int rowGap = targetRow - sourceRow;
		return rowGap;
	}

	protected void getBetweenPath(Position sourcePosition, Position targetPosition, List<Position> betweenPaths) {
		int nextRow = getNextRow(sourcePosition, targetPosition);
		int nextCol = getNextCol(sourcePosition, targetPosition);

		if (nextRow == targetPosition.getRow()
			&& nextCol == targetPosition.getCol()) {
			return;
		}
		Position nextPosition = new Position(nextCol, nextRow);
		betweenPaths.add(nextPosition);

		getBetweenPath(nextPosition, targetPosition, betweenPaths);
	}

	private int getNextRow(Position sourcePosition, Position targetPosition) {
		int rowGap = getRowGap(targetPosition, sourcePosition);
		int rowDirection = getRowDirection(rowGap);

		int nextRow = sourcePosition.getRow() + rowDirection;
		return nextRow;
	}

	private static int getRowDirection(int rowGap) {
		return rowGap == 0 ? 0 : (rowGap > 0 ? 1 : -1);
	}

	private int getNextCol(Position sourcePosition, Position targetPosition) {
		int colGap = getColGap(targetPosition, sourcePosition);
		int colDirection = getColDirection(colGap);

		int nextCol = sourcePosition.getCol() + colDirection;
		return nextCol;
	}

	private static int getColDirection(int colGap) {
		return colGap == 0 ? 0 : (colGap > 0 ? 1 : -1);
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