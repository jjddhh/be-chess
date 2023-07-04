package softeer2nd.chess;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
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
    public void addPawnSuccess() throws Exception {
        Pawn white = addPawn(Pawn.WHITE_COLOR, Pawn.WHITE_REPRESENTATION);
        verifyAddPawn(1, white, 0);

        Pawn black = addPawn(Pawn.BLACK_COLOR, Pawn.BLACK_REPRESENTATION);
        verifyAddPawn(2, black, 1);
    }

    private void verifyAddPawn(int expected, Pawn pawn, int idx) {
        assertEquals(expected, board.size());
        assertEquals(pawn, board.findWhitePawn(idx));
    }

    private Pawn addPawn(final String color, final char representation) {
        Pawn pawn = new Pawn(color, representation);
        board.addWhitePawn(pawn);
        return pawn;
    }

    @Test
    @DisplayName("Board 초기화로 폰이 추가된다.")
    public void initializeSuccess() {
        Board board = new Board();
        board.initialize();
        assertEquals("pppppppp", board.getWhitePawnsResult());
        assertEquals("PPPPPPPP", board.getBlackPawnsResult());
    }
}