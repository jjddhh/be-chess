package softeer2nd.chess;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import softeer2nd.chess.exception.InvalidColorException;
import softeer2nd.chess.pieces.Pawn;
import softeer2nd.chess.pieces.Rook;
import softeer2nd.chess.pieces.factory.PawnFactory;
import softeer2nd.chess.pieces.factory.RookFactory;
import softeer2nd.chess.pieces.piece.Color;
import softeer2nd.chess.pieces.piece.Piece;
import softeer2nd.chess.pieces.piece.Type;
import softeer2nd.chess.utils.ChessViewUtil;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static softeer2nd.chess.utils.StringUtil.appendNewLine;

@DisplayName("Board 검증")
public class BoardTest {
    private Board board;

    @BeforeEach
    void setup() {
        board = new Board();
    }

    @Test
    @DisplayName("초기화 성공")
    void checkBoardStatus() {
        // given
        board.initialize();

        String blankRank = appendNewLine(Board.EMPTY_BOARD);
        StringBuilder sb = new StringBuilder();

        // when then
        assertEquals(32, board.getTotalPieceCount());
        assertEquals(
                sb
                        .append(appendNewLine("RNBQKBNR"))
                        .append(appendNewLine("PPPPPPPP"))
                        .append(blankRank)
                        .append(blankRank)
                        .append(blankRank)
                        .append(blankRank)
                        .append(appendNewLine("pppppppp"))
                        .append(appendNewLine("rnbqkbnr")).toString(),
                ChessViewUtil.showBoard(board));
    }

    @Test
    @DisplayName("체스판에 폰 추가")
    void addPawnSuccess() {
        // given
        Piece white1 = addPawn(Color.WHITE);

        // when then
        verifyAddWhitePawn(1, white1, 0);

        // given
        Piece white2 = addPawn(Color.WHITE);

        // when then
        verifyAddWhitePawn(2, white2, 1);
    }

    private void verifyAddWhitePawn(int expected, Piece pawn, int idx) {
        assertEquals(expected, board.whitePawnSize());
        assertEquals(pawn, board.findWhitePawn(idx));
    }

    private Piece addPawn(final Color color) {
        Piece pawn = PawnFactory.createWhite("a1");
        board.addPiece(pawn);
        return pawn;
    }

    @Test
    @DisplayName("보드 초기화로 폰 추가")
    void initializeSuccess() {
        // given
        board.initialize();

        // when then
        assertEquals("pppppppp", board.getWhitePawnsResult());
        assertEquals("PPPPPPPP", board.getBlackPawnsResult());
    }

    @Test
    @DisplayName("현재 체스판 위에 몇 개의 특정 기물이 있는지 확인")
    void getPieceSizeSuccess() {
        // given
        board.initialize();

        // when
        int whiteKnightCount = board.getPieceCount(Color.WHITE, Type.KNIGHT);

        // then
        assertEquals(2, whiteKnightCount);
    }

    @Test
    @DisplayName("체스판위에 기물 추가 성공")
    void addPieceSuccess() {
        // given
        board.initializeEmpty();
        ChessGame chessGame = new ChessGame(board);

        String position = "b5";
        Piece piece = RookFactory.createBlack("b5");

        // when
        board.addPiece(piece);

        // then
        assertEquals(piece, chessGame.findPiece(position));
    }

    @Test
    @DisplayName("기물 오름차순 정렬 성공")
    void orderPiecesAscSuccess() {
        // given
        board.initialize();

        List<Piece> whitePieces = new ArrayList<>();
        board.getBlackPieces().values().forEach(whitePieces::addAll);

        List<Piece> blackPieces = new ArrayList<>();
        board.getBlackPieces().values().forEach(blackPieces::addAll);

        // when
        Collections.sort(whitePieces, Comparator.comparing(piece -> piece.getType().getDefaultPoint()));
        Collections.sort(blackPieces, Comparator.comparing(piece -> piece.getType().getDefaultPoint()));

        // then
        assertTrue(blackPieces.get(0).getPoint() < blackPieces.get(blackPieces.size() - 1).getPoint());
        assertTrue(whitePieces.get(0).getPoint() < whitePieces.get(whitePieces.size() - 1).getPoint());
    }

    @Test
    @DisplayName("기물 내림차순 정렬 성공")
    void orderPiecesDescSuccess() {
        // given
        board.initialize();

        List<Piece> whitePieces = new ArrayList<>();
        board.getBlackPieces().values().forEach(whitePieces::addAll);

        List<Piece> blackPieces = new ArrayList<>();
        board.getBlackPieces().values().forEach(blackPieces::addAll);

        // when
        Collections.sort(whitePieces, Comparator.comparing(piece -> ((Piece)piece).getType().getDefaultPoint()).reversed());
        Collections.sort(blackPieces, Comparator.comparing(piece -> ((Piece)piece).getType().getDefaultPoint()).reversed());

        // then
        assertTrue(blackPieces.get(0).getPoint() > blackPieces.get(blackPieces.size() - 1).getPoint());
        assertTrue(whitePieces.get(0).getPoint() > whitePieces.get(whitePieces.size() - 1).getPoint());
    }
}
