package softeer2nd.chess.pieces;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import softeer2nd.chess.Board;
import softeer2nd.chess.ChessGame;
import softeer2nd.chess.exception.ExistPieceOnPathException;
import softeer2nd.chess.exception.InvalidPositionException;
import softeer2nd.chess.pieces.exception.InvalidMoveException;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Queen 이동 검증")
class QueenTest {
    private Board board;

    @BeforeEach
    void setup() {
        board = new Board();
    }

    @Test
    @DisplayName("직선 이동 성공")
    void moveStraightQueenSuccess() {
        // given
        board.initializeEmpty();
        ChessGame chessGame = new ChessGame(board);

        String sourcePosition = "e1";
        String targetPosition = "e4";

        board.addPiece(Queen.createBlack(sourcePosition));

        // when
        chessGame.move(sourcePosition, targetPosition);

        // then
        assertEquals(Queen.createBlack(targetPosition), chessGame.findPiece(targetPosition));
    }

    @Test
    @DisplayName("대각선 이동 성공")
    void moveDiagonalQueenSuccess() {
        // given
        board.initializeEmpty();
        ChessGame chessGame = new ChessGame(board);

        String sourcePosition = "e1";
        String targetPosition = "a5";

        board.addPiece(Queen.createBlack(sourcePosition));

        // when
        chessGame.move(sourcePosition, targetPosition);

        // then
        assertEquals(Queen.createBlack(targetPosition), chessGame.findPiece(targetPosition));
    }

    @Test
    @DisplayName("불가능한 위치로 이동")
    void moveFailure() {
        // given
        board.initializeEmpty();
        ChessGame chessGame = new ChessGame(board);

        String sourcePosition = "e1";
        String targetPosition = "a7";

        board.addPiece(Queen.createBlack(sourcePosition));

        // when then
        assertThrows(
                InvalidPositionException.class,
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
        String targetPosition = "h4";
        String blockPosition = "g3";

        board.addPiece(Queen.createBlack(sourcePosition));
        board.addPiece(Bishop.createWhite(blockPosition));

        // when then
        assertThrows(
                ExistPieceOnPathException.class,
                () -> chessGame.move(sourcePosition, targetPosition)
        );
    }
}