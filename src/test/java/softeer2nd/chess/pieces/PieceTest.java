package softeer2nd.chess.pieces;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

public class PieceTest {

    @Test
    @DisplayName("흰색 말이 생성되어야 한다")
    public void createWhitePieceSuccess() {
        verifyPiece(Piece.createWhitePawn(new Point(0, 0)), Piece.WHITE_COLOR, Piece.WHITE_PAWN_REPRESENTATION);
        verifyPiece(Piece.createWhiteKnight(new Point(0, 0)), Piece.WHITE_COLOR, Piece.WHITE_KNIGHT_REPRESENTATION);
        verifyPiece(Piece.createWhiteRook(new Point(0, 0)), Piece.WHITE_COLOR, Piece.WHITE_ROOK_REPRESENTATION);
        verifyPiece(Piece.createWhiteBishop(new Point(0, 0)), Piece.WHITE_COLOR, Piece.WHITE_BISHOP_REPRESENTATION);
        verifyPiece(Piece.createWhiteQueen(new Point(0, 0)), Piece.WHITE_COLOR, Piece.WHITE_QUEEN_REPRESENTATION);
        verifyPiece(Piece.createWhiteKing(new Point(0, 0)), Piece.WHITE_COLOR, Piece.WHITE_KING_REPRESENTATION);
    }

    @Test
    @DisplayName("검은색 말이 생성되어야 한다")
    public void createBlackPieceSuccess() {
        verifyPiece(Piece.createBlackPawn(new Point(0, 0)), Piece.BLACK_COLOR, Piece.BLACK_PAWN_REPRESENTATION);
        verifyPiece(Piece.createBlackKnight(new Point(0, 0)), Piece.BLACK_COLOR, Piece.BLACK_KNIGHT_REPRESENTATION);
        verifyPiece(Piece.createBlackRook(new Point(0, 0)), Piece.BLACK_COLOR, Piece.BLACK_ROOK_REPRESENTATION);
        verifyPiece(Piece.createBlackBishop(new Point(0, 0)), Piece.BLACK_COLOR, Piece.BLACK_BISHOP_REPRESENTATION);
        verifyPiece(Piece.createBlackQueen(new Point(0, 0)), Piece.BLACK_COLOR, Piece.BLACK_QUEEN_REPRESENTATION);
        verifyPiece(Piece.createBlackKing(new Point(0, 0)), Piece.BLACK_COLOR, Piece.BLACK_KING_REPRESENTATION);

    }

    @Test
    @DisplayName("검은색 말인지 확인한다.")
    public void isBlack() {
        Piece blackKing = Piece.createBlackKing(new Point(0, 0));
        assertTrue(blackKing.isBlack());
        assertFalse(blackKing.isWhite());
    }

    @Test
    @DisplayName("흰색 말인지 확인한다.")
    public void isWhite() {
        Piece whiteKing = Piece.createWhiteKing(new Point(0, 0));
        assertTrue(whiteKing.isWhite());
        assertFalse(whiteKing.isBlack());
    }

    private void verifyPiece(Piece piece, String color, char representation) {
        assertEquals(color, piece.getColor());
        assertEquals(representation, piece.getRepresentation());
    }
}