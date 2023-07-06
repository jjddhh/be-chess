package softeer2nd.chess;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import softeer2nd.chess.exception.InvalidColorException;
import softeer2nd.chess.pieces.Piece;
import softeer2nd.chess.pieces.Piece.Point;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static softeer2nd.chess.StringUtils.appendNewLine;
import static softeer2nd.chess.pieces.Piece.*;

public class BoardTest {

    private Board board;

    @BeforeEach
    public void setup() {
        board = new Board();
    }

    @Test
    public void checkBoardStatus() {
        board.initialize();
        assertEquals(32, board.pieceCount());
        String blankRank = appendNewLine(Board.EMPTY_BOARD);

        StringBuilder sb = new StringBuilder();
        assertEquals(
                sb
                        .append(appendNewLine("RNBQKBNR"))
                        .append(appendNewLine("PPPPPPPP"))
                        .append(blankRank)
                        .append(blankRank)
                        .append(blankRank)
                        .append(blankRank)
                        .append(appendNewLine("pppppppp"))
                        .append(appendNewLine("rnbqkbnr")).toString(),
                board.showBoard());
    }

    @Test
    @DisplayName("체스판에 폰이 잘 추가되어야한다.")
    public void addPawnSuccess() {
        Piece white1 = addPawn(Color.WHITE);
        verifyAddWhitePawn(1, white1, 0);

        Piece white2 = addPawn(Color.WHITE);
        verifyAddWhitePawn(2, white2, 1);
    }

    private void verifyAddWhitePawn(int expected, Piece pawn, int idx) {
        assertEquals(expected, board.whitePawnSize());
        assertEquals(pawn, board.findWhitePawn(idx));
    }

    private Piece addPawn(final Color color) {
        Piece pawn = createWhitePawn(new Point(0, 0));
        board.addWhitePiece(pawn);
        return pawn;
    }

    @Test
    @DisplayName("Board 초기화로 폰이 추가된다.")
    public void initializeSuccess() {
        // given
        board.initialize();

        // when then
        assertEquals("pppppppp", board.getWhitePawnsResult());
        assertEquals("PPPPPPPP", board.getBlackPawnsResult());
    }

    @Test
    @DisplayName("폰 리스트에는 다른색 폰은 들어가지 못한다")
    public void addRightColorPawnFail() {
        // given
        Piece whitePawn = createWhitePawn(new Point(0, 0));
        Piece blackPawn = createBlackPawn(new Point(0, 0));

        // when then
        Assertions.assertThrows(
                InvalidColorException.class,
                () -> board.addBlackPiece(whitePawn));

        Assertions.assertThrows(
                InvalidColorException.class,
                () -> board.addWhitePiece(blackPawn));
    }

    @Test
    @DisplayName("현재 체스판 위에 몇 개의 특정 기물이 있는지 확인")
    public void getPieceSizeSuccess() {
        // given
        board.initialize();

        // when
        int whiteKnightCount = board.getPieceCount(Color.WHITE, Type.KNIGHT);

        // then
        assertEquals(2, whiteKnightCount);
    }

    @Test
    @DisplayName("기물의 위치 정보 조회 성공")
    public void findPieceSuccess() {
        // given
        board.initialize();

        // when then
        assertEquals(createBlackRook(new Point('a', 8)), board.findPiece("a8"));
        assertEquals(createBlackRook(new Point('h', 8)), board.findPiece("h8"));
        assertEquals(createWhiteRook(new Point('a', 1)), board.findPiece("a1"));
        assertEquals(createWhiteRook(new Point('h', 1)), board.findPiece("h1"));
    }

    @Test
    @DisplayName("체스판위에 기물 추가 성공")
    public void addPieceSuccess() {
        // given
        board.initializeEmpty();

        String position = "b5";
        Piece piece = createBlackRook("b5");

        // when
        board.addPiece(piece);

        // then
        assertEquals(piece, board.findPiece(position));
        System.out.println(board.showBoard());
    }

    @Test
    @DisplayName("점수 계산 성공")
    public void calculatePoint() {
        // given
        board.initializeEmpty();

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
        assertEquals(15.0, board.calculatePoint(Color.BLACK), 0.01);
        assertEquals(7.0, board.calculatePoint(Color.WHITE), 0.01);

        System.out.println(board.showBoard());
    }
}
