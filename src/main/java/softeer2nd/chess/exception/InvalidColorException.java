package softeer2nd.chess.exception;

public class InvalidColorException extends ChessException{
    public static final InvalidColorException EXCEPTION = new InvalidColorException();

    private InvalidColorException() {
        super("검은색 또는 흰색만 선택할 수 있습니다.");
    }
}
