package softeer2nd.chess.pieces.piece;

import java.util.Objects;

import softeer2nd.chess.Board;
import softeer2nd.chess.exception.OutOfBoardException;

public class Position {

	private String originPosition;
	private final int col;
	private final int row;

	public Position(String position) {
		this(position.charAt(0), Integer.parseInt(position.substring(1)));
		this.originPosition = position;
	}

	public Position(char col, int row) {
		int colTmp = col - 'a';
		int rowTmp = 8 - row;

		verifyPiecePosition(colTmp, rowTmp);

		this.col = colTmp;
		this.row = rowTmp;
	}

	public Position(int col, int row) {
		this.col = col;
		this.row = row;
	}

	public Position valueOf() {
		return new Position(this.col, this.row);
	}

	public String getOriginPosition() {
		char col = (char) (this.col + 'a');
		int row = 8 - this.row;

		// col + row + "" 로 문자열 반환 시도시 col + row 먼저 계산된 뒤, 그 값이 문자열로 변환됨
		StringBuilder originPosition = new StringBuilder();
		originPosition.append(col).append(row);
		return originPosition.toString();
	}

	public int getCol() {
		return col;
	}

	public int getRow() {
		return row;
	}

	private void verifyPiecePosition(int col, int row) {
		if(!(0 <= col && col < Board.COl && 0 <= row && row < Board.ROW)){
			throw OutOfBoardException.EXCEPTION;
		}
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Position position = (Position) o;
		return col == position.col && row == position.row;
	}

	@Override
	public int hashCode() {
		return Objects.hash(col, row);
	}
}
