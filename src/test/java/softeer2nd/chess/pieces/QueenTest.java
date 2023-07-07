package softeer2nd.chess.pieces;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import softeer2nd.chess.Board;
import softeer2nd.chess.ChessGame;
import softeer2nd.chess.exception.SameTeamExistException;
import softeer2nd.chess.pieces.exception.InvalidMoveException;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("퀸 이동 검증")
class QueenTest {
    private Board board;

    @BeforeEach
    public void setup() {
        board = new Board();
    }

    @Test
    @DisplayName("퀸 직선 이동 성공")
    public void moveStraightQueenSuccess() {
        // given
        board.initializeEmpty();
        ChessGame chessGame = new ChessGame(board);

        String sourcePosition = "e1";
        String targetPosition = "e4";

        board.addBlackPiece(Queen.createBlack(sourcePosition));

        // when
        chessGame.move(sourcePosition, targetPosition);

        // then
        assertEquals(Queen.createBlack(new Piece.Position(targetPosition)), chessGame.findPiece(targetPosition));
    }

    @Test
    @DisplayName("퀸 대각선 이동 성공")
    public void moveDiagonalQueenSuccess() {
        // given
        board.initializeEmpty();
        ChessGame chessGame = new ChessGame(board);

        String sourcePosition = "e1";
        String targetPosition = "a5";

        board.addBlackPiece(Queen.createBlack(sourcePosition));

        // when
        chessGame.move(sourcePosition, targetPosition);

        // then
        assertEquals(Queen.createBlack(new Piece.Position(targetPosition)), chessGame.findPiece(targetPosition));
    }

    @Test
    @DisplayName("퀸 불가능한 위치로 이동")
    public void moveQueenFailure() {
        // given
        board.initializeEmpty();
        ChessGame chessGame = new ChessGame(board);

        String sourcePosition = "e1";
        String targetPosition = "a7";

        board.addBlackPiece(Queen.createBlack(sourcePosition));

        // when then
        assertThrows(
                InvalidMoveException.class,
                () -> chessGame.move(sourcePosition, targetPosition)
        );
    }

    @Test
    @DisplayName("퀸 이동 경로에 기물 위치")
    public void existPieceOnQueenMoveFailure() {
        // given
        board.initializeEmpty();
        ChessGame chessGame = new ChessGame(board);

        String sourcePosition = "e1";
        String targetPosition = "h4";
        String blockPosition = "g3";

        board.addBlackPiece(Queen.createBlack(sourcePosition));
        board.addWhitePiece(Bishop.createWhite(blockPosition));

        // when then
        assertThrows(
                InvalidMoveException.class,
                () -> chessGame.move(sourcePosition, targetPosition)
        );
    }

    @Test
    @DisplayName("퀸 도착 위치에 같은팀 기물 위치")
    public void existSameTeamPieceOnTargetFailure() {
        // given
        board.initializeEmpty();
        ChessGame chessGame = new ChessGame(board);

        String sourcePosition = "e1";
        String targetPosition = "h4";

        board.addBlackPiece(Queen.createBlack(sourcePosition));
        board.addBlackPiece(Bishop.createBlack(targetPosition));

        // when then
        assertThrows(
                SameTeamExistException.class,
                () -> chessGame.move(sourcePosition, targetPosition)
        );
    }
}