package softeer2nd.chess.exception;

public class InvalidPositionException extends ChessException {

	public static final InvalidPositionException EXCEPTION = new InvalidPositionException();

	private InvalidPositionException() {
		super("기물이 이동 불가능한 위치입니다.");
	}

}
