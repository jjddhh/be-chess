package softeer2nd.chess.pieces;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import softeer2nd.chess.Board;
import softeer2nd.chess.ChessGame;
import softeer2nd.chess.exception.SameTeamExistException;
import softeer2nd.chess.pieces.exception.InvalidMoveException;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("King 이동 검증")
class KingTest {
    private Board board;

    @BeforeEach
    void setup() {
        board = new Board();
    }

    @Test
    @DisplayName("이동 성공")
    void moveSuccess() {
        // given
        board.initializeEmpty();
        ChessGame chessGame = new ChessGame(board);

        String sourcePosition = "d1";
        String targetPosition = "d2";

        board.addPiece(King.createBlack(sourcePosition));

        // when
        chessGame.move(sourcePosition, targetPosition);

        // then
        assertEquals(King.createBlack(targetPosition), chessGame.findPiece(targetPosition));
    }

    @Test
    @DisplayName("도착 위치에 같은팀 기물 위치")
    void existSameTeamPieceOnTargetFailure() {
        // given
        board.initialize();
        ChessGame chessGame = new ChessGame(board);

        String sourcePosition = "d1";
        String targetPosition = "d2";

        // when then
        Assertions.assertThrows(
                SameTeamExistException.class,
                () -> chessGame.move(sourcePosition, targetPosition));
    }

    @Test
    @DisplayName("이동 불가능한 거리")
    void moveDistanceFailure() {
        // given
        board.initialize();
        ChessGame chessGame = new ChessGame(board);

        String sourcePosition = "d1";
        String targetPosition = "d5";

        // when then
        Assertions.assertThrows(
                InvalidMoveException.class,
                () -> chessGame.move(sourcePosition, targetPosition));
    }
}