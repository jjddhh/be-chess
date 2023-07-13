package softeer2nd.chess.pieces.exception;

import softeer2nd.chess.exception.ChessException;

public class PawnMoveException extends ChessException {

	public static final PawnMoveException EXCEPTION = new PawnMoveException();

	private PawnMoveException() {
		super("폰은 기본 이동시에는 위, 아래로만 움직일 수 있습니다.");
	}
}
