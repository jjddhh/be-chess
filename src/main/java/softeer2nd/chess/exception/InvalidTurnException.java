package softeer2nd.chess.exception;

public class InvalidTurnException extends ChessException {

	public static final InvalidTurnException EXCEPTION = new InvalidTurnException();

	private InvalidTurnException() {
		super("상대편이 기물을 움직일 차례입니다.");
	}
}
