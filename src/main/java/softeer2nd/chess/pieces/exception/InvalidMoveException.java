package softeer2nd.chess.pieces.exception;

import softeer2nd.chess.exception.ChessException;

public class InvalidMoveException extends ChessException {
    public static final InvalidMoveException EXCEPTION = new InvalidMoveException();

    private InvalidMoveException() {
        super("해당 기마가 갈 수 없는 위치입니다.");
    }
}
