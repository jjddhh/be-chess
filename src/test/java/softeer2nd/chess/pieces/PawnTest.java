package softeer2nd.chess.pieces;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import softeer2nd.chess.exception.InvalidColorException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PawnTest {

    @Test
    @DisplayName("기본적으로 폰은 흰색이 생성된다.")
    public void createDefaultPawnSuccess() {
        Pawn pawn = new Pawn();
        verifyPawn(Pawn.WHITE_COLOR, Pawn.WHITE_REPRESENTATION, pawn);
    }

    @Test
    @DisplayName("흰색 폰이 생성되어야 한다")
    public void createWhitePawnSuccess() {
        // given
        Pawn whitePawn = new Pawn(Pawn.WHITE_COLOR);

        // when then
        verifyPawn(Pawn.WHITE_COLOR, Pawn.WHITE_REPRESENTATION, whitePawn);
    }

    @Test
    @DisplayName("검은색 폰이 생성되어야 한다")
    public void createBlackPawnSuccess() {
        // given
        Pawn blackPawn = new Pawn(Pawn.BLACK_COLOR);

        // when then
        verifyPawn(Pawn.BLACK_COLOR, Pawn.BLACK_REPRESENTATION, blackPawn);
    }

    @Test
    @DisplayName("검은색과 흰색외의 폰은 생성 불가능하다")
    public void createPawnFailure() {
        // given
        final String color = "randomColor";

        // when then
        Assertions.assertThrows(
                InvalidColorException.class,
                () -> new Pawn(color));
    }

    private void verifyPawn(String color, char representation, Pawn pawn) {
        assertEquals(color, pawn.getColor());
        assertEquals(representation, pawn.getRepresentation());
    }
}