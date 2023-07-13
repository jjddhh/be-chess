package softeer2nd.chess.pieces;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import softeer2nd.chess.Board;
import softeer2nd.chess.ChessGame;
import softeer2nd.chess.exception.InvalidPositionException;
import softeer2nd.chess.pieces.factory.KnightFactory;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Knight 이동 검증")
class KnightTest {
    private Board board;

    @BeforeEach
    void setup() {
        board = new Board();
    }

    @Test
    @DisplayName("이동 성공")
    void moveSuccess() {
        // given
        ChessGame chessGame = new ChessGame(board);

        String sourcePosition = "c4";
        String targetPosition = "d6";

        board.addPiece(KnightFactory.createBlack(sourcePosition));

        // when
        chessGame.move(sourcePosition, targetPosition);

        // then
        assertEquals(KnightFactory.createBlack(targetPosition), chessGame.findPiece(targetPosition));
    }

    @Test
    @DisplayName("이동 불가능한 위치")
    void moveFailure() {
        // given
        ChessGame chessGame = new ChessGame(board);

        String sourcePosition = "c4";
        String targetPosition = "d5";

        board.addPiece(KnightFactory.createBlack(sourcePosition));

        // when then
        assertThrows(
                InvalidPositionException.class,
                () -> chessGame.move(sourcePosition, targetPosition)
        );
    }
}