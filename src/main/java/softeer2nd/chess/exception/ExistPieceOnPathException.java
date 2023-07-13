package softeer2nd.chess.exception;

public class ExistPieceOnPathException extends ChessException {

	public static final ExistPieceOnPathException EXCEPTION = new ExistPieceOnPathException();

	private ExistPieceOnPathException() {
		super("이동 경로에 기물이 존재합니다.");
	}
}
