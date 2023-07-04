package softeer2nd.chess;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import softeer2nd.chess.exception.InvalidColorException;
import softeer2nd.chess.pieces.Pawn;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BoardTest {
    
    private Board board;

    @BeforeEach
    public void setup() {
        board = new Board();
    }

    @Test
    @DisplayName("체스판에 폰이 잘 추가되어야한다.")
    public void addPawnSuccess() {
        Pawn white1 = addPawn(Pawn.WHITE_COLOR);
        verifyAddWhitePawn(1, white1, 0);

        Pawn white2 = addPawn(Pawn.WHITE_COLOR);
        verifyAddWhitePawn(2, white2, 1);
    }

    private void verifyAddWhitePawn(int expected, Pawn pawn, int idx) {
        assertEquals(expected, board.size());
        assertEquals(pawn, board.findWhitePawn(idx));
    }

    private Pawn addPawn(final String color) {
        Pawn pawn = new Pawn(color);
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
        Pawn whitePawn = new Pawn(Pawn.WHITE_COLOR);
        Pawn blackPawn = new Pawn(Pawn.BLACK_COLOR);

        // when then
        Assertions.assertThrows(
                InvalidColorException.class,
                () -> board.addBlackPawn(whitePawn));

        Assertions.assertThrows(
                InvalidColorException.class,
                () -> board.addWhitePawn(blackPawn));
    }
}