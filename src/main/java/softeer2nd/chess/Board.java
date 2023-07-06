package softeer2nd.chess;

import softeer2nd.chess.exception.InvalidColorException;
import softeer2nd.chess.pieces.Piece;
import softeer2nd.chess.pieces.Piece.Position;

import java.util.*;
import java.util.List;

import static softeer2nd.chess.pieces.Piece.*;

public class Board {

    private Map<Type, List<Piece>> whitePieces = new HashMap<>();
    private Map<Type, List<Piece>> blackPieces = new HashMap<>();

    public static final int ROW = 8;
    public static final int COl = 8;

    public static final String EMPTY_BOARD = "........";

    public void end() {
        // map의 list에서 dangling pointer 발생안하는지 체크 필요
        whitePieces = null;
        blackPieces = null;
    }


    public void addPiece(Piece piece) {
        // 개수 예외 처리
        if(piece.isBlack()) addBlackPiece(piece);
        else if(piece.isWhite()) addWhitePiece(piece);
    }

    public void addWhitePiece(Piece piece) {
        verifyWhitePiece(piece);
        whitePieces.computeIfAbsent(piece.getType(), k -> new ArrayList<>()).add(piece);
    }

    private void verifyWhitePiece(Piece piece) {
        if(!piece.getColor().equals(Color.WHITE))
            throw InvalidColorException.EXCEPTION;
    }

    public void addBlackPiece(Piece piece) {
        verifyBlackPiece(piece);
        blackPieces.computeIfAbsent(piece.getType(), k -> new ArrayList<>()).add(piece);
    }

    private void verifyBlackPiece(Piece piece) {
        if(!piece.getColor().equals(Color.BLACK))
            throw InvalidColorException.EXCEPTION;
    }

    public int whitePawnSize() {
        return whitePieces.get(Type.PAWN).size();
    }

    public Piece findWhitePawn(int idx) {
        return whitePieces.get(Type.PAWN).get(idx);
    }


    public void initializeEmpty() {
        blackPieces = new HashMap<>();
        whitePieces = new HashMap<>();
    }

    public void initialize() {
        blackPieces = new HashMap<>();
        whitePieces = new HashMap<>();

        for (int i = 0; i < COl; i++) {
            // putIfAbsent로 변경
            blackPieces.computeIfAbsent(Type.PAWN, k -> new ArrayList<>())
                    .add(createBlackPawn(new Position(i, 1)));
            whitePieces.computeIfAbsent(Type.PAWN, k -> new ArrayList<>())
                    .add(createWhitePawn(new Position(i, 6)));
        }

        for (int i = 0; i < COl; i += 7) {
            blackPieces.computeIfAbsent(Type.ROOK, k -> new ArrayList<>())
                    .add(createBlackRook(new Position(i, 0)));
            whitePieces.computeIfAbsent(Type.ROOK, k -> new ArrayList<>())
                    .add(createWhiteRook(new Position(i, 7)));
        }

        for (int i = 1; i < COl; i += 5) {
            blackPieces.computeIfAbsent(Type.KNIGHT, k -> new ArrayList<>())
                    .add(createBlackKnight(new Position(i, 0)));
            whitePieces.computeIfAbsent(Type.KNIGHT, k -> new ArrayList<>())
                    .add(createWhiteKnight(new Position(i, 7)));
        }

        for (int i = 2; i < COl; i += 3) {
            blackPieces.computeIfAbsent(Type.BISHOP, k -> new ArrayList<>())
                    .add(createBlackBishop(new Position(i, 0)));
            whitePieces.computeIfAbsent(Type.BISHOP, k -> new ArrayList<>())
                    .add(createWhiteBishop(new Position(i, 7)));

        }

        blackPieces.computeIfAbsent(Type.QUEEN, k -> new ArrayList<>())
                .add(createBlackQueen(new Position(3, 0)));
        whitePieces.computeIfAbsent(Type.QUEEN, k -> new ArrayList<>())
                .add(createWhiteQueen(new Position(3, 7)));


        blackPieces.computeIfAbsent(Type.KING, k -> new ArrayList<>())
                .add(createBlackKing(new Position(4, 0)));
        whitePieces.computeIfAbsent(Type.KING, k -> new ArrayList<>())
                .add(createWhiteKing(new Position(4, 7)));
    }

    public Map<Type, List<Piece>> getWhitePieces() {
        return whitePieces;
    }

    public Map<Type, List<Piece>> getBlackPieces() {
        return blackPieces;
    }

    public String getWhitePawnsResult() {
        return getPawnsResult(whitePieces.get(Type.PAWN));
    }

    public String getBlackPawnsResult() {
        return getPawnsResult(blackPieces.get(Type.PAWN));
    }

    private String getPawnsResult(List<Piece> whitePawns) {
        StringBuilder whitePawnsResult = new StringBuilder();

        for (Piece p : whitePawns) {
            whitePawnsResult.append(p.getRepresentation());
        }

        return whitePawnsResult.toString();
    }

    public void print() {
        System.out.println(showBoard());
    }

    public int pieceCount() {
        int whitePieceCount = 0;
        for (Type type : Type.values()) {
            whitePieceCount += whitePieces.getOrDefault(type, new ArrayList<>()).size();
        }

        int blackPieceCount = 0;
        for (Type type : Type.values()) {
            blackPieceCount += blackPieces.getOrDefault(type, new ArrayList<>()).size();
        }

        return whitePieceCount + blackPieceCount;
    }

    public String showBoard() {
        char[][] board = new char[ROW][COl];
        for (int i = 0; i < board.length; i++) {
            Arrays.fill(board[i], '.');
        }

        for (Type type : Type.values()) {
            List<Piece> whitePieces = this.whitePieces.getOrDefault(type, new ArrayList<>());
            List<Piece> blackPieces = this.blackPieces.getOrDefault(type, new ArrayList<>());

            for (int i = 0; i < whitePieces.size(); i++) {
                Piece piece = whitePieces.get(i);
                Position position = piece.getPosition();
                board[position.getRow()][position.getCol()] = piece.getRepresentation();
            }

            for (int i = 0; i < blackPieces.size(); i++) {
                Piece piece = blackPieces.get(i);
                Position position = piece.getPosition();
                board[position.getRow()][position.getCol()] = piece.getRepresentation();
            }
        }

        StringBuilder boardStatus = new StringBuilder();
        for (int i = 0; i < 8; i++) {
            boardStatus.append(StringUtils.appendNewLine(String.valueOf(board[i])));
        }

        return boardStatus.toString();
    }

    public int getPieceCount(Color color, Type type) {
        if (color.equals(Color.WHITE))
            return whitePieces.getOrDefault(type, new ArrayList<>()).size();
        else if (color.equals(Color.BLACK))
            return blackPieces.getOrDefault(type, new ArrayList<>()).size();
        else {
            int whitePieces = this.whitePieces.getOrDefault(type, new ArrayList<>()).size();
            int blackPieces = this.blackPieces.getOrDefault(type, new ArrayList<>()).size();
            return (ROW * COl) - (whitePieces + blackPieces);
        }
    }

    public Piece findPiece(String position) {
        // position 형식 맞는지 예외 처리

        char col = position.charAt(0);
        int row = Character.getNumericValue(position.charAt(1));
        Position findPosition = new Position(col, row);

        for (Type type : Type.values()) {
            List<Piece> white = whitePieces.getOrDefault(type, new ArrayList<>());
            List<Piece> black = blackPieces.getOrDefault(type, new ArrayList<>());

            for (Piece piece : white) {
                Position point = piece.getPosition();
                if(point.equals(findPosition)) return piece;
            }

            for (Piece piece : black) {
                Position point = piece.getPosition();
                if(point.equals(findPosition)) return piece;
            }
        }

        return createBlank(findPosition);
    }

    public double calculatePoint(Color color) {
        double point = 0;

        if(color.equals(Color.BLACK)) {
            point = getPoint(point, blackPieces);
        } else
            point = getPoint(point, whitePieces);

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

