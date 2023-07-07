package softeer2nd.chess.pieces;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import softeer2nd.chess.Board;
import softeer2nd.chess.ChessGame;
import softeer2nd.chess.exception.SameTeamExistException;
import softeer2nd.chess.pieces.exception.InvalidMoveException;

import static org.junit.jupiter.api.Assertions.*;
import static softeer2nd.chess.pieces.Piece.*;

@DisplayName("룩 이동 검증")
class RookTest {
    private Board board;

    @BeforeEach
    public void setup() {
        board = new Board();
    }

    @Test
    @DisplayName("룩 이동 성공")
    public void moveSuccess() {
        // given
        ChessGame chessGame = new ChessGame(board);

        String sourcePosition = "a1";
        String targetPosition = "a7";

        board.addBlackPiece(Rook.createBlack(sourcePosition));

        // when
        chessGame.move(sourcePosition, targetPosition);

        // then
        assertEquals(Rook.createBlack(targetPosition), chessGame.findPiece(targetPosition));
    }

    @Test
    @DisplayName("룩 불가능한 위치로 이동")
    public void moveFailure() {
        // given
        board.initializeEmpty();
        ChessGame chessGame = new ChessGame(board);

        String sourcePosition = "e1";
        String targetPosition = "a7";

        board.addBlackPiece(Rook.createBlack(sourcePosition));

        // when then
        assertThrows(
                InvalidMoveException.class,
                () -> chessGame.move(sourcePosition, targetPosition)
        );
    }

    @Test
    @DisplayName("룩 이동 경로에 기물 위치")
    public void existPieceOnQueenMoveFailure() {
        // given
        board.initializeEmpty();
        ChessGame chessGame = new ChessGame(board);

        String sourcePosition = "e1";
        String targetPosition = "e5";
        String blockPosition = "e3";

        board.addBlackPiece(Rook.createBlack(sourcePosition));
        board.addWhitePiece(Bishop.createWhite(blockPosition));

        // when then
        assertThrows(
                InvalidMoveException.class,
                () -> chessGame.move(sourcePosition, targetPosition)
        );
    }

    @Test
    @DisplayName("룩 도착 위치에 같은팀 기물 위치")
    public void existSameTeamPieceOnTargetFailure() {
        // given
        board.initializeEmpty();
        ChessGame chessGame = new ChessGame(board);

        String sourcePosition = "e1";
        String targetPosition = "e5";

        board.addBlackPiece(Rook.createBlack(sourcePosition));
        board.addBlackPiece(Bishop.createBlack(targetPosition));

        // when then
        assertThrows(
                SameTeamExistException.class,
                () -> chessGame.move(sourcePosition, targetPosition)
        );
    }
}