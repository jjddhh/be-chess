package softeer2nd.chess.exception;

public class MeaninglessMoveException extends ChessException {
    public static final MeaninglessMoveException EXCEPTION = new MeaninglessMoveException();

    private MeaninglessMoveException() {
        super("동일한 위치로의 이동을 불가합니다.");
    }
}
