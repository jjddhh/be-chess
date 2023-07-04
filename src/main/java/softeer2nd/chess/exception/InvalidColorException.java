package softeer2nd.chess.exception;

public class InvalidColorException extends RuntimeException{

    public static final InvalidColorException EXCEPTION = new InvalidColorException();

    private InvalidColorException() {
        super("검은색 또는 흰색 폰만 생성이 가능합니다.");
    }
}
