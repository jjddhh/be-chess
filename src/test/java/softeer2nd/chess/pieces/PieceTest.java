package softeer2nd.chess.pieces;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import softeer2nd.chess.pieces.Piece.Position;
import softeer2nd.chess.pieces.enums.Type;

import static org.junit.jupiter.api.Assertions.*;

public class PieceTest {
    @Test
    @DisplayName("모든 종류의 기물 생성을 성공")
    public void createPiece() {
        // given
        Piece whitePawn = Pawn.createWhite(new Position(0, 0));
        Piece blackPawn = Pawn.createBlack(new Position(0, 0));

        Piece whiteKnight = Knight.createWhite(new Position(0, 0));
        Piece blackKnight = Knight.createBlack(new Position(0, 0));

        Piece whiteRook = Rook.createWhite(new Position(0, 0));
        Piece blackRook = Rook.createBlack(new Position(0, 0));

        Piece whiteBishop = Bishop.createWhite(new Position(0, 0));
        Piece blackBishop = Bishop.createBlack(new Position(0, 0));

        Piece whiteQueen = Queen.createWhite(new Position(0, 0));
        Piece blackQueen = Queen.createBlack(new Position(0, 0));

        Piece whiteKing = King.createWhite(new Position(0, 0));
        Piece blackKing = King.createBlack(new Position(0, 0));

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
        Piece blank = Blank.create(new Position(0, 0));

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
        Piece blackKing = King.createBlack(new Position(0, 0));

        // when then
        assertTrue(blackKing.isBlack());
        assertFalse(blackKing.isWhite());
    }

    @Test
    @DisplayName("흰색 말인지 확인")
    public void isWhite() {
        // given
        Piece whiteKing = King.createWhite(new Position(0, 0));

        // when then
        assertTrue(whiteKing.isWhite());
        assertFalse(whiteKing.isBlack());
    }
}