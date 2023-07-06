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
        Piece white1 = addPawn(Piece.Color.WHITE);
        verifyAddWhitePawn(1, white1, 0);

        Piece white2 = addPawn(Piece.Color.WHITE);
        verifyAddWhitePawn(2, white2, 1);
    }

    private void verifyAddWhitePawn(int expected, Piece pawn, int idx) {
        assertEquals(expected, board.whitePawnSize());
        assertEquals(pawn, board.findWhitePawn(idx));
    }

    private Piece addPawn(final Piece.Color color) {
        Piece pawn = Piece.createWhitePawn(new Point(0, 0));
        board.addWhitePawn(pawn);
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
        Piece whitePawn = Piece.createWhitePawn(new Point(0, 0));
        Piece blackPawn = Piece.createBlackPawn(new Point(0, 0));

        // when then
        Assertions.assertThrows(
                InvalidColorException.class,
                () -> board.addBlackPawn(whitePawn));

        Assertions.assertThrows(
                InvalidColorException.class,
                () -> board.addWhitePawn(blackPawn));
    }

    @Test
    @DisplayName("현재 체스판 위에 몇 개의 특정 기물이 있는지 확인")
    public void getPieceSizeSuccess() {
        // given
        board.initialize();

        // when
        int whiteKnightCount = board.getPieceCount(Piece.Color.WHITE, Piece.Type.KNIGHT);

        // then
        assertEquals(2, whiteKnightCount);
    }

    @Test
    @DisplayName("기물의 위치 정보 조회")
    public void findPiece() {
        // given
        board.initialize();

        // when then
        assertEquals(Piece.createBlackRook(new Point('a', 8)), board.findPiece("a8"));
        assertEquals(Piece.createBlackRook(new Point('h', 8)), board.findPiece("h8"));
        assertEquals(Piece.createWhiteRook(new Point('a', 1)), board.findPiece("a1"));
        assertEquals(Piece.createWhiteRook(new Point('h', 1)), board.findPiece("h1"));
    }
}
