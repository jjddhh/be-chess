package softeer2nd.chess;

import softeer2nd.chess.pieces.Piece;
import softeer2nd.chess.pieces.Type;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static softeer2nd.chess.Board.COl;
import static softeer2nd.chess.Board.ROW;

public class ChessView {

    private final Board board;

    public ChessView(Board board) {
        this.board = board;
    }

    public String showBoard() {
        char[][] baseBoard = new char[ROW][COl];
        for (int i = 0; i < baseBoard.length; i++) {
            Arrays.fill(baseBoard[i], '.');
        }

        for (Type type : Type.values()) {
            List<Piece> whitePieces = board.getWhitePieces().getOrDefault(type, new ArrayList<>());
            List<Piece> blackPieces = board.getBlackPieces().getOrDefault(type, new ArrayList<>());

            for (int i = 0; i < whitePieces.size(); i++) {
                Piece piece = whitePieces.get(i);
                Piece.Position position = piece.getPosition();
                baseBoard[position.getRow()][position.getCol()] = piece.getRepresentation();
            }

            for (int i = 0; i < blackPieces.size(); i++) {
                Piece piece = blackPieces.get(i);
                Piece.Position position = piece.getPosition();
                baseBoard[position.getRow()][position.getCol()] = piece.getRepresentation();
            }
        }

        StringBuilder boardStatus = new StringBuilder();
        for (int i = 0; i < 8; i++) {
            boardStatus.append(StringUtils.appendNewLine(String.valueOf(baseBoard[i])));
        }

        return boardStatus.toString();
    }
}
