package softeer2nd.chess.pieces;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import softeer2nd.chess.Board;
import softeer2nd.chess.ChessGame;
import softeer2nd.chess.pieces.exception.InvalidMoveException;

import static org.junit.jupiter.api.Assertions.*;
import static softeer2nd.chess.pieces.Piece.*;

@DisplayName("폰 이동 검증")
class PawnTest {
    private Board board;

    @BeforeEach
    public void setup() {
        board = new Board();
    }

    @Test
    @DisplayName("폰 이동 성공")
    public void moveSuccess() {
        // given
        ChessGame chessGame = new ChessGame(board);

        String blackSourcePosition = "a7";
        String blackTargetPosition = "a6";

        String whiteSourcePosition = "b1";
        String whiteTargetPosition = "b2";

        board.addBlackPiece(Pawn.createBlack(blackSourcePosition));
        board.addWhitePiece(Pawn.createWhite(whiteSourcePosition));

        // when
        chessGame.move(blackSourcePosition, blackTargetPosition);
        chessGame.move(whiteSourcePosition, whiteTargetPosition);

        // then
        assertEquals(Pawn.createBlack(blackTargetPosition), chessGame.findPiece(blackTargetPosition));
        assertEquals(Pawn.createWhite(whiteTargetPosition), chessGame.findPiece(whiteTargetPosition));
    }

    @Test
    @DisplayName("폰 이동 실패")
    public void moveFailure() {
        // given
        ChessGame chessGame = new ChessGame(board);

        String blackSourcePosition = "a6";
        String blackTargetPosition = "a7";

        String whiteSourcePosition = "b2";
        String whiteTargetPosition = "b1";

        board.addBlackPiece(Pawn.createBlack(blackSourcePosition));
        board.addWhitePiece(Pawn.createWhite(whiteSourcePosition));

        // when then
        assertThrows(
                InvalidMoveException.class,
                () -> chessGame.move(blackSourcePosition, blackTargetPosition)
        );

        assertThrows(
                InvalidMoveException.class,
                () -> chessGame.move(whiteSourcePosition, whiteTargetPosition)
        );
    }

    @Test
    @DisplayName("폰 직선 이동 경로에 기물 위치")
    public void existPieceOnQueenMoveFailure() {
        // given
        board.initializeEmpty();
        ChessGame chessGame = new ChessGame(board);

        String sourcePosition = "e1";
        String targetPosition = "e2";

        board.addBlackPiece(Pawn.createBlack(sourcePosition));
        board.addWhitePiece(Bishop.createWhite(targetPosition));

        // when then
        assertThrows(
                InvalidMoveException.class,
                () -> chessGame.move(sourcePosition, targetPosition)
        );
    }

    @Test
    @DisplayName("폰 대각선에 상대편 기물 위치할 경우 대각선 이동 가능")
    public void moveDiagonalIfExistEnemySuccess() {
        // given
        board.initializeEmpty();
        ChessGame chessGame = new ChessGame(board);

        String sourcePosition = "d2";
        String targetPosition = "e1";

        board.addBlackPiece(Pawn.createBlack(sourcePosition));
        board.addWhitePiece(Bishop.createWhite(targetPosition));

        // when
        chessGame.move(sourcePosition, targetPosition);

        // then
        assertEquals(Pawn.createBlack(targetPosition), chessGame.findPiece(targetPosition));
    }

    @Test
    @DisplayName("폰은 기본적으로 대각선 이동은 불가")
    public void moveDiagonalIfNoExistFailure() {
        // given
        board.initializeEmpty();
        ChessGame chessGame = new ChessGame(board);

        String sourcePosition = "d2";
        String targetPosition = "e1";

        board.addBlackPiece(Pawn.createBlack(sourcePosition));

        // when then
        assertThrows(
                InvalidMoveException.class,
                () -> chessGame.move(sourcePosition, targetPosition)
        );
    }
}