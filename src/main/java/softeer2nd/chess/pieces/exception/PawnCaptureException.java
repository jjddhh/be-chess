package softeer2nd.chess.pieces.exception;

import softeer2nd.chess.exception.ChessException;

public class PawnCaptureException extends ChessException {

	public static final PawnCaptureException EXCEPTION = new PawnCaptureException();

	private PawnCaptureException() {
		super("폰은 대각선에 있는 기물만 잡을 수 있습니다.");
	}
}
