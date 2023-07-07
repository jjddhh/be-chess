package softeer2nd.chess;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import softeer2nd.chess.exception.InvalidColorException;
import softeer2nd.chess.pieces.Pawn;
import softeer2nd.chess.pieces.Rook;
import softeer2nd.chess.pieces.enums.Color;
import softeer2nd.chess.pieces.Piece;
import softeer2nd.chess.pieces.enums.Type;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static softeer2nd.chess.StringUtils.appendNewLine;

public class BoardTest {
    private Board board;

    @BeforeEach
    public void setup() {
        board = new Board();
    }

    @Test
    @DisplayName("보드 초기화 성공")
    public void checkBoardStatus() {
        // given
        board.initialize();
        ChessView chessView = new ChessView(board);

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
                chessView.showBoard());
    }

    @Test
    @DisplayName("체스판에 폰이 잘 추가되어야한다.")
    public void addPawnSuccess() {
        Piece white1 = addPawn(Color.WHITE);
        verifyAddWhitePawn(1, white1, 0);

        Piece white2 = addPawn(Color.WHITE);
        verifyAddWhitePawn(2, white2, 1);
    }

    private void verifyAddWhitePawn(int expected, Piece pawn, int idx) {
        assertEquals(expected, board.whitePawnSize());
        assertEquals(pawn, board.findWhitePawn(idx));
    }

    private Piece addPawn(final Color color) {
        Piece pawn = Pawn.createWhite("a1");
        board.addWhitePiece(pawn);
        return pawn;
    }

    @Test
    @DisplayName("Board 초기화로 폰이 추가된다.")
    public void initializeSuccess() {
        // given
        board.initialize();

        // when then
        assertEquals("pppppppp", board.getWhitePawnsResult());
        assertEquals("PPPPPPPP", board.getBlackPawnsResult());
    }

    @Test
    @DisplayName("폰 리스트에는 다른색 폰은 들어가지 못한다")
    public void addRightColorPawnFail() {
        // given
        Piece whitePawn = Pawn.createWhite("a1");
        Piece blackPawn = Pawn.createBlack("a1");

        // when then
        Assertions.assertThrows(
                InvalidColorException.class,
                () -> board.addBlackPiece(whitePawn));

        Assertions.assertThrows(
                InvalidColorException.class,
                () -> board.addWhitePiece(blackPawn));
    }

    @Test
    @DisplayName("현재 체스판 위에 몇 개의 특정 기물이 있는지 확인")
    public void getPieceSizeSuccess() {
        // given
        board.initialize();

        // when
        int whiteKnightCount = board.getPieceCount(Color.WHITE, Type.KNIGHT);

        // then
        assertEquals(2, whiteKnightCount);
    }

    @Test
    @DisplayName("체스판위에 기물 추가 성공")
    public void addPieceSuccess() {
        // given
        board.initializeEmpty();
        ChessView chessView = new ChessView(board);
        ChessGame chessGame = new ChessGame(board);

        String position = "b5";
        Piece piece = Rook.createBlack("b5");

        // when
        board.addPiece(piece);

        // then
        assertEquals(piece, chessGame.findPiece(position));
        System.out.println(chessView.showBoard());
    }

    @Test
    @DisplayName("기물 오름차순 정렬 성공")
    public void orderPiecesAscSuccess() {
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
    public void orderPiecesDescSuccess() {
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
