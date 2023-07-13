package softeer2nd.chess;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import softeer2nd.chess.exception.MeaninglessMoveException;
import softeer2nd.chess.exception.OutOfBoardException;
import softeer2nd.chess.pieces.*;
import softeer2nd.chess.pieces.factory.KingFactory;
import softeer2nd.chess.pieces.factory.PawnFactory;
import softeer2nd.chess.pieces.factory.QueenFactory;
import softeer2nd.chess.pieces.factory.RookFactory;
import softeer2nd.chess.pieces.piece.Color;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("ChessGame 검증")
class ChessGameTest {
    private Board board;

    @BeforeEach
    void setup() {
        board = new Board();
    }

    @Test
    @DisplayName("기물의 위치 정보 조회 성공")
    void findPieceSuccess() {
        // given
        board.initialize();
        ChessGame chessGame = new ChessGame(board);

        // when then
        assertEquals(RookFactory.createBlack("a8"), chessGame.findPiece("a8"));
        assertEquals(RookFactory.createBlack("h8"), chessGame.findPiece("h8"));
        assertEquals(RookFactory.createWhite("a1"), chessGame.findPiece("a1"));
        assertEquals(RookFactory.createWhite("h1"), chessGame.findPiece("h1"));
    }

    @Test
    @DisplayName("점수 계산 성공")
    void calculatePointSuccess() {
        // given
        board.initializeEmpty();
        ChessGame chessGame = new ChessGame(board);

        // when
        board.addPiece(PawnFactory.createBlack("b6"));
        board.addPiece(QueenFactory.createBlack("e6"));
        board.addPiece(KingFactory.createBlack("b8"));
        board.addPiece(RookFactory.createBlack("c8"));

        board.addPiece(PawnFactory.createWhite("f2"));
        board.addPiece(PawnFactory.createWhite("g2"));
        board.addPiece(RookFactory.createWhite("e1"));
        board.addPiece(KingFactory.createWhite("f1"));

        // then
        assertEquals(15.0, chessGame.calculatePoint(Color.BLACK), 0.01);
        assertEquals(7.0, chessGame.calculatePoint(Color.WHITE), 0.01);
    }

    @Test
    @DisplayName("정해진 범위 밖으로는 위치 불가")
    void moveAllowedRangeFail() {
        // given
        board.initializeEmpty();
        ChessGame chessGame = new ChessGame(board);

        String sourcePosition = "a1";

        String unAllowedPosition1 = "a0";
        String unAllowedPosition2 = "a10";
        String unAllowedPosition3 = "f9";
        String unAllowedPosition4 = "i1";
        String unAllowedPosition5 = "d9";

        board.addPiece(QueenFactory.createBlack(sourcePosition));

        // when then
        assertThrows(
                OutOfBoardException.class,
                () -> chessGame.move(sourcePosition, unAllowedPosition1)
        );

        assertThrows(
                OutOfBoardException.class,
                () -> chessGame.move(sourcePosition, unAllowedPosition2)
        );

        assertThrows(
                OutOfBoardException.class,
                () -> chessGame.move(sourcePosition, unAllowedPosition3)
        );

        assertThrows(
                OutOfBoardException.class,
                () -> chessGame.move(sourcePosition, unAllowedPosition4)
        );

        assertThrows(
                OutOfBoardException.class,
                () -> chessGame.move(sourcePosition, unAllowedPosition5)
        );
    }

    @Test
    @DisplayName("동일 위치로의 이동은 불가")
    void meaninglessMoveFailure() {
        // given
        board.initializeEmpty();
        ChessGame chessGame = new ChessGame(board);

        String position = "c5";

        board.addPiece(QueenFactory.createBlack(position));

        // when then
        assertThrows(
                MeaninglessMoveException.class,
                () -> chessGame.move(position, position)
        );
    }
}