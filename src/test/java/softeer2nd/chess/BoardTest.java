package softeer2nd.chess;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import softeer2nd.chess.Board;
import softeer2nd.chess.pieces.Pawn;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BoardTest {

    @Test
    @DisplayName("체스판에 폰이 잘 추가되어야한다.")
    public void create() throws Exception {
        Board board = new Board();

        Pawn white = new Pawn(Pawn.WHITE_COLOR);
        board.add(white);
        assertEquals(1, board.size());
        assertEquals(white, board.findPawn(0));

        Pawn black = new Pawn(Pawn.BLACK_COLOR);
        board.add(black);
        assertEquals(2, board.size());
        assertEquals(black, board.findPawn(1));
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