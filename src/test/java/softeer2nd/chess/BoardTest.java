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
        Pawn white = addPawn(Pawn.WHITE_COLOR);
        verifyAddPawn(1, white, 0);

        Pawn black = addPawn(Pawn.BLACK_COLOR);
        verifyAddPawn(2, black, 1);
    }

    private void verifyAddPawn(int expected, Pawn pawn, int idx) {
        assertEquals(expected, board.size());
        assertEquals(pawn, board.findPawn(idx));
    }

    private Pawn addPawn(final String color) {
        Pawn pawn = new Pawn(color);
        board.addPawn(pawn);
        return pawn;
    }

//    @Test
//    @DisplayName("폰이외의 다른 것이 체스판에 추가되어선 안된다.")
//    public void addOnlyPawn() {
//        // given
//        Board board = new Board();
//        Integer other = new Integer("7");
//
//        // when then
//        board.add(other);
//    }
}