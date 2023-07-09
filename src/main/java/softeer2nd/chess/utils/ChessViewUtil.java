package softeer2nd.chess.utils;

import softeer2nd.chess.Board;
import softeer2nd.chess.pieces.piece.Piece;
import softeer2nd.chess.pieces.piece.Type;
import softeer2nd.chess.utils.StringUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static softeer2nd.chess.Board.COl;
import static softeer2nd.chess.Board.ROW;

public class ChessViewUtil {
    private static char[][] baseBoard;

    public static void print(Board board) {
        System.out.println(showBoard(board));
    }

    public static String showBoard(Board board) {
        initBoard();

        setPiecesOnBoard(board, baseBoard);

        return IntStream.range(0, 8)
                .mapToObj(i -> String.valueOf(baseBoard[i]))
                .map(t -> StringUtil.appendNewLine(t))
                .collect(Collectors.joining());
    }

    private static void initBoard() {
        baseBoard = new char[ROW][COl];
        for (int i = 0; i < baseBoard.length; i++) {
            Arrays.fill(baseBoard[i], '.');
        }
    }

    private static void setPiecesOnBoard(Board board, char[][] baseBoard) {
        for (Type type : Type.values()) {
            List<Piece> whitePieces = board.getWhitePieces().getOrDefault(type, new ArrayList<>());
            List<Piece> blackPieces = board.getBlackPieces().getOrDefault(type, new ArrayList<>());

            for (int i = 0; i < whitePieces.size(); i++) {
                Piece piece = whitePieces.get(i);
                baseBoard[piece.getRow()][piece.getCol()] = piece.getRepresentation();
            }

            for (int i = 0; i < blackPieces.size(); i++) {
                Piece piece = blackPieces.get(i);
                baseBoard[piece.getRow()][piece.getCol()] = piece.getRepresentation();
            }
        }
    }
}
