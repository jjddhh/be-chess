package softeer2nd.chess.exception;

public class OutOfBoardException extends RuntimeException {
    public static final OutOfBoardException EXCEPTION = new OutOfBoardException();

    private OutOfBoardException() {
        super("기마의 위치가 체스판을 벗어났습니다.");
    }
}
