package softeer2nd.chess.pieces.piece;

public enum Color {

	WHITE("백"),
	BLACK("흑"),
	NO_COLOR("X");

	private final String value;

	private Color(final String value) {
		this.value = value;
	}

	public String  getValue() {
		return value;
	}
}
