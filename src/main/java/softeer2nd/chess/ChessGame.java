package softeer2nd.chess;

import softeer2nd.chess.pieces.Blank;
import softeer2nd.chess.pieces.enums.Color;
import softeer2nd.chess.pieces.Piece;
import softeer2nd.chess.pieces.enums.Type;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static softeer2nd.chess.Board.COl;
import static softeer2nd.chess.pieces.Piece.*;

public class ChessGame {
    private final Board board;

    public ChessGame(Board board) {
        this.board = board;
    }

    /**
     * 기물 이동
     */
    public void move(String sourcePosition, String targetPosition) {
        Piece sourcePiece = findPiece(sourcePosition);
        Piece targetPiece = findPiece(targetPosition);

        // 바로 position 넘겨주는것 고려
        sourcePiece.move(sourcePiece, targetPiece, this);
        board.removePiece(targetPiece);
    }

    public Piece findPiece(String position) {
        // position 형식 맞는지 예외 처리

        char col = position.charAt(0);
        int row = Character.getNumericValue(position.charAt(1));
        Position findPosition = new Position(col, row);

        return findPiece(findPosition);
    }

    public Piece findPiece(Position findPosition) {
        for (Type type : Type.values()) {
            List<Piece> white = board.getWhitePieces().getOrDefault(type, new ArrayList<>());
            List<Piece> black = board.getBlackPieces().getOrDefault(type, new ArrayList<>());

            for (Piece piece : white) {
                Position position = piece.getPosition();
                if(position.equals(findPosition)) return piece;
            }

            for (Piece piece : black) {
                Position position = piece.getPosition();
                if(position.equals(findPosition)) return piece;
            }
        }

        return Blank.create(findPosition);
    }

    /**
     * 점수 계산
     */
    public double calculatePoint(Color color) {
        double point = 0;

        if(color.equals(Color.BLACK)) {
            point = getPoint(point, board.getBlackPieces());
        } else if(color.equals(Color.WHITE)){
            point = getPoint(point, board.getWhitePieces());
        }

        return point;
    }

    private double getPoint(double point, Map<Type, List<Piece>> colorPieces) {
        for (Type type : Type.values()) {
            List<Piece> pieces = colorPieces.getOrDefault(type, new ArrayList<>());

            if(type.equals(Type.PAWN)) {
                HashMap<Integer, Integer> colSet = new HashMap<>();

                for (Piece piece : pieces) {
                    int col = piece.getPosition().getCol();
                    colSet.put(col, colSet.getOrDefault(col, 0) + 1);
                }

                int sameColPawnCnt = 0;
                for (int i = 0; i < COl; i++) {
                    double cnt = colSet.getOrDefault(i, 0);
                    if(cnt > 1) sameColPawnCnt += cnt;
                }
                point -= sameColPawnCnt / 2;

            }

            point += type.getDefaultPoint() * pieces.size();
        }

        return point;
    }
}
