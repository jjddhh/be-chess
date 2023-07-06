package softeer2nd.chess;

import softeer2nd.chess.pieces.Piece;
import softeer2nd.chess.pieces.Type;

import java.util.ArrayList;
import java.util.List;

import static softeer2nd.chess.pieces.Piece.createBlank;

public class ChessGame {

    private final Board board;

    public ChessGame(Board board) {
        this.board = board;
    }

    public void move(String sourcePosition, String targetPosition) {
        Piece findPiece = findPiece(sourcePosition);

        findPiece.move(new Piece.Position(targetPosition));
    }

    public Piece findPiece(String position) {
        // position 형식 맞는지 예외 처리

        char col = position.charAt(0);
        int row = Character.getNumericValue(position.charAt(1));
        Piece.Position findPosition = new Piece.Position(col, row);

        for (Type type : Type.values()) {
            List<Piece> white = board.getWhitePieces().getOrDefault(type, new ArrayList<>());
            List<Piece> black = board.getBlackPieces().getOrDefault(type, new ArrayList<>());

            for (Piece piece : white) {
                Piece.Position point = piece.getPosition();
                if(point.equals(findPosition)) return piece;
            }

            for (Piece piece : black) {
                Piece.Position point = piece.getPosition();
                if(point.equals(findPosition)) return piece;
            }
        }

        return createBlank(findPosition);
    }

}
