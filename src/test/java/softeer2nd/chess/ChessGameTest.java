package softeer2nd.chess;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import softeer2nd.chess.pieces.*;
import softeer2nd.chess.pieces.piece.Color;

import static org.junit.jupiter.api.Assertions.*;

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
        assertEquals(Rook.createBlack("a8"), chessGame.findPiece("a8"));
        assertEquals(Rook.createBlack("h8"), chessGame.findPiece("h8"));
        assertEquals(Rook.createWhite("a1"), chessGame.findPiece("a1"));
        assertEquals(Rook.createWhite("h1"), chessGame.findPiece("h1"));
    }

    @Test
    @DisplayName("점수 계산 성공")
    void calculatePoint() {
        // given
        board.initializeEmpty();
        ChessView chessView = new ChessView(board);
        ChessGame chessGame = new ChessGame(board);

        // when
        board.addPiece(Pawn.createBlack("b6"));
        board.addPiece(Queen.createBlack("e6"));
        board.addPiece(King.createBlack("b8"));
        board.addPiece(Rook.createBlack("c8"));

        board.addPiece(Pawn.createWhite("f2"));
        board.addPiece(Pawn.createWhite("g2"));
        board.addPiece(Rook.createWhite("e1"));
        board.addPiece(King.createWhite("f1"));

        // then
        assertEquals(15.0, chessGame.calculatePoint(Color.BLACK), 0.01);
        assertEquals(7.0, chessGame.calculatePoint(Color.WHITE), 0.01);

        System.out.println(chessView.showBoard());
    }
}