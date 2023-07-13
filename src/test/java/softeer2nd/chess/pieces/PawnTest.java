package softeer2nd.chess.pieces;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import softeer2nd.chess.Board;
import softeer2nd.chess.ChessGame;
import softeer2nd.chess.exception.InvalidPositionException;
import softeer2nd.chess.pieces.exception.PawnCaptureException;
import softeer2nd.chess.pieces.exception.PawnMoveException;
import softeer2nd.chess.pieces.factory.BishopFactory;
import softeer2nd.chess.pieces.factory.PawnFactory;
import softeer2nd.chess.pieces.piece.Piece;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Pawn 이동 검증")
class PawnTest {
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

        String blackSourcePosition = "a7";
        String blackTargetPosition = "a6";

        String whiteSourcePosition = "b1";
        String whiteTargetPosition = "b2";

        board.addPiece(PawnFactory.createBlack(blackSourcePosition));
        board.addPiece(PawnFactory.createWhite(whiteSourcePosition));

        // when
        chessGame.move(blackSourcePosition, blackTargetPosition);
        chessGame.move(whiteSourcePosition, whiteTargetPosition);

        // then
        assertEquals(PawnFactory.createBlack(blackTargetPosition), chessGame.findPiece(blackTargetPosition));
        assertEquals(PawnFactory.createWhite(whiteTargetPosition), chessGame.findPiece(whiteTargetPosition));
    }

    @Test
    @DisplayName("검은색 폰은 아래로, 흰색 폰은 위로만 이동")
    void moveFailure() {
        // given
        ChessGame chessGame = new ChessGame(board);

        String blackSourcePosition = "a6";
        String blackTargetPosition = "a7";

        String whiteSourcePosition = "b2";
        String whiteTargetPosition = "b1";

        board.addPiece(PawnFactory.createBlack(blackSourcePosition));
        board.addPiece(PawnFactory.createWhite(whiteSourcePosition));

        // when then
        assertThrows(
                InvalidPositionException.class,
                () -> chessGame.move(blackSourcePosition, blackTargetPosition)
        );

        assertThrows(
                InvalidPositionException.class,
                () -> chessGame.move(whiteSourcePosition, whiteTargetPosition)
        );
    }

    @Test
    @DisplayName("직선 이동 경로에 기물 위치")
    void existPieceOnPathFailure() {
        // given
        board.initializeEmpty();
        ChessGame chessGame = new ChessGame(board);

        String sourcePosition = "e5";
        String targetPosition = "e4";

        board.addPiece(PawnFactory.createBlack(sourcePosition));
        board.addPiece(BishopFactory.createWhite(targetPosition));

        // when then
        assertThrows(
                PawnCaptureException.class,
                () -> chessGame.move(sourcePosition, targetPosition)
        );
    }

    @Test
    @DisplayName("대각선에 상대편 기물 위치할 경우 대각선 이동 가능")
    void moveDiagonalSuccess() {
        // given
        board.initializeEmpty();
        ChessGame chessGame = new ChessGame(board);

        String sourcePosition = "d2";
        String targetPosition = "e1";

        board.addPiece(PawnFactory.createBlack(sourcePosition));
        board.addPiece(BishopFactory.createWhite(targetPosition));

        // when
        chessGame.move(sourcePosition, targetPosition);

        // then
        assertEquals(PawnFactory.createBlack(targetPosition), chessGame.findPiece(targetPosition));
    }

    @Test
    @DisplayName("기본적으로 대각선 이동은 불가")
    void moveDiagonalFailure() {
        // given
        board.initializeEmpty();
        ChessGame chessGame = new ChessGame(board);

        String sourcePosition = "d2";
        String targetPosition = "e1";

        board.addPiece(PawnFactory.createBlack(sourcePosition));

        // when then
        assertThrows(
                PawnMoveException.class,
                () -> chessGame.move(sourcePosition, targetPosition)
        );
    }

    @Test
    @DisplayName("첫 이동은 두 칸 가능")
    void moveDouble() {
        // given
        board.initializeEmpty();
        ChessGame chessGame = new ChessGame(board);

        String sourcePosition = "e7";
        String targetPosition = "e5";

        board.addPiece(PawnFactory.createBlack(sourcePosition));

        // when
        chessGame.move(sourcePosition, targetPosition);

        // then
        assertEquals(PawnFactory.createBlack(targetPosition), chessGame.findPiece(targetPosition));
    }

    @Test
    @DisplayName("첫 이동 이후 두 칸 이동은 불가")
    void moveTripleFailure() {
        // given
        board.initializeEmpty();
        ChessGame chessGame = new ChessGame(board);

        String sourcePosition = "e7";
        String targetPosition1 = "e5";
        String targetPosition2 = "e3";

        board.addPiece(PawnFactory.createBlack(sourcePosition));

        // when then
        assertThrows(
            PawnMoveException.class,
            () -> {
                chessGame.move(sourcePosition, targetPosition1);
                Piece piece = chessGame.findPiece(targetPosition1);
                chessGame.move(targetPosition1, targetPosition2);
            }
        );
    }
}