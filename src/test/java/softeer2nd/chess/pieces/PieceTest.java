package softeer2nd.chess.pieces;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import softeer2nd.chess.pieces.Piece.Position;
import softeer2nd.chess.pieces.Piece.Type;


import static org.junit.jupiter.api.Assertions.*;

public class PieceTest {

    @Test
    @DisplayName("모든 종류의 기물 생성을 성공")
    public void createPiece() {
        // given
        Piece whitePawn = Piece.createWhitePawn(new Position(0, 0));
        Piece blackPawn = Piece.createBlackPawn(new Position(0, 0));

        Piece whiteKnight = Piece.createWhiteKnight(new Position(0, 0));
        Piece blackKnight = Piece.createBlackKnight(new Position(0, 0));

        Piece whiteRook = Piece.createWhiteRook(new Position(0, 0));
        Piece blackRook = Piece.createBlackRook(new Position(0, 0));

        Piece whiteBishop = Piece.createWhiteBishop(new Position(0, 0));
        Piece blackBishop = Piece.createBlackBishop(new Position(0, 0));

        Piece whiteQueen = Piece.createWhiteQueen(new Position(0, 0));
        Piece blackQueen = Piece.createBlackQueen(new Position(0, 0));

        Piece whiteKing = Piece.createWhiteKing(new Position(0, 0));
        Piece blackKing = Piece.createBlackKing(new Position(0, 0));

        // when then
        verifyPiece(whitePawn, blackPawn, Type.PAWN);
        verifyPiece(whiteKnight, blackKnight, Type.KNIGHT);
        verifyPiece(whiteRook, blackRook, Type.ROOK);
        verifyPiece(whiteBishop, blackBishop, Type.BISHOP);
        verifyPiece(whiteQueen, blackQueen, Type.QUEEN);
        verifyPiece(whiteKing, blackKing, Type.KING);
    }

    @Test
    @DisplayName("빈 기물 생성에 성공")
    public void createEmptyPiece() {
        // given
        Piece blank = Piece.createBlank(new Position(0, 0));

        // when then
        assertFalse(blank.isWhite());
        assertFalse(blank.isBlack());
        assertEquals(Type.NO_PIECE, blank.getType());
    }

    private void verifyPiece(final Piece whitePiece, final Piece blackPiece, final Type type) {
        assertTrue(whitePiece.isWhite());
        assertEquals(type, whitePiece.getType());

        assertTrue(blackPiece.isBlack());
        assertEquals(type, blackPiece.getType());
    }

    @Test
    public void getRepresentationPerPiece() {
        assertEquals('p', Type.PAWN.getWhiteRepresentation());
        assertEquals('P', Type.PAWN.getBlackRepresentation());
    }

    @Test
    @DisplayName("검은색 말인지 확인")
    public void isBlack() {
        // given
        Piece blackKing = Piece.createBlackKing(new Position(0, 0));

        // when then
        assertTrue(blackKing.isBlack());
        assertFalse(blackKing.isWhite());
    }

    @Test
    @DisplayName("흰색 말인지 확인")
    public void isWhite() {
        // given
        Piece whiteKing = Piece.createWhiteKing(new Position(0, 0));

        // when then
        assertTrue(whiteKing.isWhite());
        assertFalse(whiteKing.isBlack());
    }
}