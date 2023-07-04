package softeer2nd;

import org.junit.jupiter.api.*;
import softeer2nd.exception.InvalidColorException;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PawnTest {

    @Test
    public void create_기본생성자() throws Exception {
        Pawn pawn = new Pawn();
        assertEquals("white", pawn.getColor());
    }

    @Test
    @DisplayName("흰색 폰이 생성되어야 한다")
    public void createWhitePawnSuccess() {
        // given
        final String color = "black";

        // when then
        verifyPawn(color);
    }

    @Test
    @DisplayName("검은색 폰이 생성되어야 한다")
    public void createBlackPawnSuccess() {
        // given
        final String color = "black";

        // when then
        verifyPawn(color);
    }

    @Test
    @DisplayName("검은색과 흰색외의 폰은 생성 불가능하다")
    public void createPawnFailure() {
        // given
        final String color = "randomColor";

        // when then
        Assertions.assertThrows(
                InvalidColorException.class,
                () -> verifyPawn(color));
    }

    void verifyPawn(final String color) {
        Pawn pawn = new Pawn(color);
        assertThat(pawn.getColor()).isEqualTo(color);
    }
}