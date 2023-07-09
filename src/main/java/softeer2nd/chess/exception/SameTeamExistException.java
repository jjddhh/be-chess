package softeer2nd.chess.exception;

public class SameTeamExistException extends ChessException {
    public static final SameTeamExistException EXCEPTION = new SameTeamExistException();

    private SameTeamExistException() {
        super("가려는 위치에 같은 팀 기물이 위치합니다.");
    }
}
