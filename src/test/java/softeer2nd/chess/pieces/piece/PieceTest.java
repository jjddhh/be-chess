package softeer2nd.chess.pieces.piece;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import softeer2nd.chess.Board;
import softeer2nd.chess.ChessGame;
import softeer2nd.chess.exception.OutOfBoardException;
import softeer2nd.chess.exception.SameTeamExistException;
import softeer2nd.chess.pieces.*;

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
        Piece whitePawn = Pawn.createWhite("a1");
        Piece blackPawn = Pawn.createBlack("a1");

        Piece whiteKnight = Knight.createWhite("a1");
        Piece blackKnight = Knight.createBlack("a1");

        Piece whiteRook = Rook.createWhite("a1");
        Piece blackRook = Rook.createBlack("a1");

        Piece whiteBishop = Bishop.createWhite("a1");
        Piece blackBishop = Bishop.createBlack("a1");

        Piece whiteQueen = Queen.createWhite("a1");
        Piece blackQueen = Queen.createBlack("a1");

        Piece whiteKing = King.createWhite("a1");
        Piece blackKing = King.createBlack("a1");

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
        Piece blank = Blank.create("a1");

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
        Piece blackKing = King.createBlack("a1");

        // when then
        assertTrue(blackKing.isBlack());
        assertFalse(blackKing.isWhite());
    }

    @Test
    @DisplayName("흰색 말인지 확인")
    void isWhite() {
        // given
        Piece whiteKing = King.createWhite("a1");

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

        board.addPiece(Bishop.createBlack(sourcePosition));
        board.addPiece(Rook.createBlack(targetPosition));

        // when then
        assertThrows(
                SameTeamExistException.class,
                () -> chessGame.move(sourcePosition, targetPosition)
        );
    }
}