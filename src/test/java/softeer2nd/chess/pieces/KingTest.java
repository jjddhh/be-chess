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

@DisplayName("킹 이동 검증")
class KingTest {
    private Board board;

    @BeforeEach
    public void setup() {
        board = new Board();
    }

    @Test
    @DisplayName("킹 이동 성공")
    public void moveKingSuccess() {
        // given
        board.initializeEmpty();
        ChessGame chessGame = new ChessGame(board);

        String sourcePosition = "d1";
        String targetPosition = "d2";

        board.addBlackPiece(King.createBlack(sourcePosition));

        // when
        chessGame.move(sourcePosition, targetPosition);

        // then
        assertEquals(King.createBlack(new Piece.Position(targetPosition)), chessGame.findPiece(targetPosition));
    }

    @Test
    @DisplayName("킹 이동 실패")
    public void moveKingFailure() {
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
    @DisplayName("킹 이동 실패")
    public void moveKingDistanceFailure() {
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