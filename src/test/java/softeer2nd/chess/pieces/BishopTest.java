package softeer2nd.chess.pieces;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import softeer2nd.chess.Board;
import softeer2nd.chess.ChessGame;
import softeer2nd.chess.pieces.exception.InvalidMoveException;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Bishop 이동 검증")
class BishopTest {
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

        String sourcePosition = "a1";
        String targetPosition = "h8";

        board.addPiece(Bishop.createBlack(sourcePosition));

        // when
        chessGame.move(sourcePosition, targetPosition);

        // then
        assertEquals(Bishop.createBlack(targetPosition), chessGame.findPiece(targetPosition));
    }

    @Test
    @DisplayName("불가능한 위치로 이동")
    void moveFailure() {
        // given
        board.initializeEmpty();
        ChessGame chessGame = new ChessGame(board);

        String sourcePosition = "e1";
        String targetPosition = "a7";

        board.addPiece(Bishop.createBlack(sourcePosition));

        // when then
        assertThrows(
                InvalidMoveException.class,
                () -> chessGame.move(sourcePosition, targetPosition)
        );
    }

    @Test
    @DisplayName("이동 경로에 기물 위치")
    void existPieceOnPathFailure() {
        // given
        board.initializeEmpty();
        ChessGame chessGame = new ChessGame(board);

        String sourcePosition = "e1";
        String targetPosition = "e5";
        String blockPosition = "e3";

        board.addPiece(Bishop.createBlack(sourcePosition));
        board.addPiece(Rook.createWhite(blockPosition));

        // when then
        assertThrows(
                InvalidMoveException.class,
                () -> chessGame.move(sourcePosition, targetPosition)
        );
    }
}