package softeer2nd.chess;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import softeer2nd.chess.exception.InvalidColorException;
import softeer2nd.chess.exception.SameTeamExistException;
import softeer2nd.chess.pieces.Color;
import softeer2nd.chess.pieces.exception.InvalidMoveException;

import static org.junit.jupiter.api.Assertions.*;
import static softeer2nd.chess.pieces.Piece.*;

class ChessGameTest {
    private Board board;

    @BeforeEach
    public void setup() {
        board = new Board();
    }

    @Test
    @DisplayName("기물의 위치 정보 조회 성공")
    public void findPieceSuccess() {
        // given
        board.initialize();
        ChessGame chessGame = new ChessGame(board);

        // when then
        assertEquals(createBlackRook(new Position('a', 8)), chessGame.findPiece("a8"));
        assertEquals(createBlackRook(new Position('h', 8)), chessGame.findPiece("h8"));
        assertEquals(createWhiteRook(new Position('a', 1)), chessGame.findPiece("a1"));
        assertEquals(createWhiteRook(new Position('h', 1)), chessGame.findPiece("h1"));
    }

    @Test
    @DisplayName("점수 계산 성공")
    public void calculatePoint() {
        // given
        board.initializeEmpty();
        ChessView chessView = new ChessView(board);
        ChessGame chessGame = new ChessGame(board);

        // when
        board.addPiece(createBlackPawn("b6"));
        board.addPiece(createBlackQueen("e6"));
        board.addPiece(createBlackKing("b8"));
        board.addPiece(createBlackRook("c8"));

        board.addPiece(createWhitePawn("f2"));
        board.addPiece(createWhitePawn("g2"));
        board.addPiece(createWhiteRook("e1"));
        board.addPiece(createWhiteKing("f1"));

        // then
        assertEquals(15.0, chessGame.calculatePoint(Color.BLACK), 0.01);
        assertEquals(7.0, chessGame.calculatePoint(Color.WHITE), 0.01);

        System.out.println(chessView.showBoard());
    }

    @Test
    @DisplayName("이동원칙과 무관하게 기물을 정해진 위치로 이동 성공")
    public void moveSuccess() {
        // given
        board.initialize();
        ChessGame chessGame = new ChessGame(board);

        String sourcePosition = "b2";
        String targetPosition = "b3";

        // when
        chessGame.move(sourcePosition, targetPosition);

        // then
        assertEquals(createBlank(new Position(sourcePosition)), chessGame.findPiece(sourcePosition));
        assertEquals(createWhitePawn(new Position(targetPosition)), chessGame.findPiece(targetPosition));
    }

    @Test
    @DisplayName("킹 이동 성공")
    public void moveKingSuccess() {
        // given
        board.initializeEmpty();
        ChessGame chessGame = new ChessGame(board);

        String sourcePosition = "d1";
        String targetPosition = "d2";

        board.addBlackPiece(createBlackKing(sourcePosition));

        // when
        chessGame.move(sourcePosition, targetPosition);

        // then
        assertEquals(createBlackKing(new Position(targetPosition)), chessGame.findPiece(targetPosition));
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
    @DisplayName("퀸 직선 이동 성공")
    public void moveStraightQueenSuccess() {
        // given
        board.initializeEmpty();
        ChessGame chessGame = new ChessGame(board);

        String sourcePosition = "e1";
        String targetPosition = "e4";

        board.addBlackPiece(createBlackQueen(sourcePosition));

        // when
        chessGame.move(sourcePosition, targetPosition);

        // then
        assertEquals(createBlackQueen(new Position(targetPosition)), chessGame.findPiece(targetPosition));
    }

    @Test
    @DisplayName("퀸 대각선 이동 성공")
    public void moveDiagonalQueenSuccess() {
        // given
        board.initializeEmpty();
        ChessGame chessGame = new ChessGame(board);

        String sourcePosition = "e1";
        String targetPosition = "a5";

        board.addBlackPiece(createBlackQueen(sourcePosition));

        // when
        chessGame.move(sourcePosition, targetPosition);

        // then
        assertEquals(createBlackQueen(new Position(targetPosition)), chessGame.findPiece(targetPosition));
    }

    @Test
    @DisplayName("퀸 불가능한 위치로 이동")
    public void moveQueenFailure() {
        // given
        board.initializeEmpty();
        ChessGame chessGame = new ChessGame(board);

        String sourcePosition = "e1";
        String targetPosition = "a7";

        board.addBlackPiece(createBlackQueen(sourcePosition));

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

        board.addBlackPiece(createBlackQueen(sourcePosition));
        board.addWhitePiece(createWhiteBishop(blockPosition));

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
        String teamPiecePosition = "h4";

        board.addBlackPiece(createBlackQueen(sourcePosition));
        board.addBlackPiece(createBlackBishop(teamPiecePosition));

        // when then
        assertThrows(
                SameTeamExistException.class,
                () -> chessGame.move(sourcePosition, targetPosition)
        );
    }
}