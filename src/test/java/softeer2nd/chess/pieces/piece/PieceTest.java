package softeer2nd.chess.pieces.piece;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import softeer2nd.chess.Board;
import softeer2nd.chess.ChessGame;
import softeer2nd.chess.exception.OutOfBoardException;
import softeer2nd.chess.exception.SameTeamExistException;
import softeer2nd.chess.pieces.*;
import softeer2nd.chess.pieces.factory.BishopFactory;
import softeer2nd.chess.pieces.factory.BlankFactory;
import softeer2nd.chess.pieces.factory.KingFactory;
import softeer2nd.chess.pieces.factory.KnightFactory;
import softeer2nd.chess.pieces.factory.PawnFactory;
import softeer2nd.chess.pieces.factory.QueenFactory;
import softeer2nd.chess.pieces.factory.RookFactory;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Piece 공통 검증")
public class PieceTest {
    private Board board;

    @BeforeEach
    void setup() {
        board = new Board();
    }

    @Test
    @DisplayName("모든 종류의 기물 생성을 성공")
    void createPiece() {
        // given
        Piece whitePawn = PawnFactory.createWhite("a1");
        Piece blackPawn = PawnFactory.createBlack("a1");

        Piece whiteKnight = KnightFactory.createWhite("a1");
        Piece blackKnight = KnightFactory.createBlack("a1");

        Piece whiteRook = RookFactory.createWhite("a1");
        Piece blackRook = RookFactory.createBlack("a1");

        Piece whiteBishop = BishopFactory.createWhite("a1");
        Piece blackBishop = BishopFactory.createBlack("a1");

        Piece whiteQueen = QueenFactory.createWhite("a1");
        Piece blackQueen = QueenFactory.createBlack("a1");

        Piece whiteKing = KingFactory.createWhite("a1");
        Piece blackKing = KingFactory.createBlack("a1");

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
    void createEmptyPiece() {
        // given
        Piece blank = BlankFactory.create("a1");

        // when then
        assertFalse(blank.isWhite());
        assertFalse(blank.isBlack());
        assertEquals(Type.NO_PIECE, blank.getType());
    }

    void verifyPiece(final Piece whitePiece, final Piece blackPiece, final Type type) {
        assertTrue(whitePiece.isWhite());
        assertEquals(type, whitePiece.getType());

        assertTrue(blackPiece.isBlack());
        assertEquals(type, blackPiece.getType());
    }

    @Test
    @DisplayName("기물 색깔별 표현형 확인")
    void getRepresentationPerPiece() {
        assertEquals('p', Type.PAWN.getWhiteRepresentation());
        assertEquals('P', Type.PAWN.getBlackRepresentation());
    }

    @Test
    @DisplayName("검은색 말인지 확인")
    void isBlack() {
        // given
        Piece blackKing = KingFactory.createBlack("a1");

        // when then
        assertTrue(blackKing.isBlack());
        assertFalse(blackKing.isWhite());
    }

    @Test
    @DisplayName("흰색 말인지 확인")
    void isWhite() {
        // given
        Piece whiteKing = KingFactory.createWhite("a1");

        // when then
        assertTrue(whiteKing.isWhite());
        assertFalse(whiteKing.isBlack());
    }

    @Test
    @DisplayName("도착 위치에 같은팀 기물 위치")
    void existSameTeamPieceOnTargetFailure() {
        // given
        board.initializeEmpty();
        ChessGame chessGame = new ChessGame(board);

        String sourcePosition = "e1";
        String targetPosition = "e5";

        board.addPiece(QueenFactory.createBlack(sourcePosition));
        board.addPiece(RookFactory.createBlack(targetPosition));

        // when then
        assertThrows(
                SameTeamExistException.class,
                () -> chessGame.move(sourcePosition, targetPosition)
        );
    }
}