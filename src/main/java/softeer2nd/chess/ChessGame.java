package softeer2nd.chess;

import softeer2nd.chess.exception.ChessException;
import softeer2nd.chess.exception.InvalidColorException;
import softeer2nd.chess.exception.MeaninglessMoveException;
import softeer2nd.chess.exception.SameTeamExistException;
import softeer2nd.chess.pieces.Blank;
import softeer2nd.chess.pieces.piece.Color;
import softeer2nd.chess.pieces.piece.Piece;
import softeer2nd.chess.pieces.piece.Type;
import softeer2nd.chess.utils.ChessViewUtil;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ChessGame {
    private Board board;
    private Boolean isProceeding = true;
    
    private final String START = "start";
    private final String END = "end";
    private final String MOVE = "move";

    public ChessGame (Board board){
        this.board = board;
    }

    public void start() {
        Scanner input = new Scanner(System.in);

        while(isProceeding) {
            String[] command = getCommand(input);

            switch (command[0]) {
                case START  :
                    startGame();
                    break;
                case MOVE   :
                    movePiece(command);
                    break;
                case END    :
                    endGame();
                    break;
                default     :
                    invalidCommand();
            }
        }
    }

    private void invalidCommand() {
        System.out.println("잘못된 입력입니다. start/ move loc1 loc2/ end 중 하나를 입력해주세요");
    }

    private String[] getCommand(Scanner input) {
        return input.nextLine().split(" ");
    }

    private void startGame() {
        board.initialize();
        ChessViewUtil.print(board);
    }

    private void movePiece(String[] command) {
        try {
            move(command[1], command[2]);
        } catch (ChessException exception) {
            System.out.println("잘못된 이동입니다. 이동 위치를 확인해주세요.");
        }

        ChessViewUtil.print(board);
    }

    private void endGame() {
        board.cleanUp();
        isProceeding = false;
    }

    /**
     * 기물 이동
     */
    public void move(String sourcePosition, String targetPosition) {
        Piece sourcePiece = findPiece(sourcePosition);
        Piece targetPiece = findPiece(targetPosition);

        verifyMeaninglessMove(sourcePosition, targetPosition);
        verifySameTeamOnTarget(sourcePiece, targetPiece);

        sourcePiece.move(targetPiece, this);
        board.removePiece(targetPiece);
    }

    private void verifyMeaninglessMove(String sourcePosition, String targetPosition) {
        if(sourcePosition.equals(targetPosition)) {
            throw MeaninglessMoveException.EXCEPTION;
        }
    }

    private void verifySameTeamOnTarget(Piece sourcePiece, Piece targetPiece) {
        if(sourcePiece.getColor() == targetPiece.getColor()) {
            throw SameTeamExistException.EXCEPTION;
        }
    }

    public Piece findPiece(String targetPosition) {
        return Arrays.stream(Type.values())
                .flatMap(type -> Stream.concat(
                        board.getWhitePieces().getOrDefault(type, new ArrayList<>()).stream(),
                        board.getBlackPieces().getOrDefault(type, new ArrayList<>()).stream())
                )
                .filter(p -> p.isEqualPosition(targetPosition))
                .findFirst()
                .orElse(Blank.create(targetPosition));
    }

    /**
     * 점수 계산
     */
    public double calculatePoint(Color color) {
        if (color.equals(Color.BLACK)) {
            return getPoint(board.getBlackPieces());
        } else if (color.equals(Color.WHITE)){
            return getPoint(board.getWhitePieces());
        } else {
            throw InvalidColorException.EXCEPTION;
        }
    }

    private double getPoint(Map<Type, List<Piece>> colorPieces) {
        double point = 0;

        for (Type type : Type.values()) {
            List<Piece> pieces = colorPieces.getOrDefault(type, new ArrayList<>());

            if (type.equals(Type.PAWN)) {
                Map<Integer, Long> colSet = pieces.stream()
                        .collect(Collectors.groupingBy(Piece::getCol, Collectors.counting()));

                double sameColPawnCnt = colSet.values().stream()
                        .filter(cnt -> cnt > 1)
                        .mapToDouble(Double::valueOf)
                        .sum();

                point -= sameColPawnCnt / 2;
            }

            point += type.getDefaultPoint() * pieces.size();
        }

        return point;
    }
}
