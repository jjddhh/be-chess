package softeer2nd.chess;

import softeer2nd.chess.exception.*;
import softeer2nd.chess.pieces.factory.BlankFactory;
import softeer2nd.chess.pieces.piece.Color;
import softeer2nd.chess.pieces.piece.Piece;
import softeer2nd.chess.pieces.piece.Position;
import softeer2nd.chess.pieces.piece.Type;
import softeer2nd.chess.utils.ChessViewUtil;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ChessGame {

	private final String START = "start";
	private final String END = "end";
	private final String MOVE = "move";
	private final String INVALID_INPUT = "잘못된 입력입니다. start/ move loc1 loc2/ end 중 하나를 입력해주세요";
	private final String WINNER_END_MESSAGE = "이 승리했습니다.";
	private final String TIE_END_MESSAGE = "무승부 입니다.";

	private Board board;
	private Boolean isProceeding = true;
	private Color turn = Color.WHITE;

	public ChessGame(Board board) {
		this.board = board;
	}

	public void start() {
		Scanner input = new Scanner(System.in);

		while (isProceeding) {
			String[] command = getCommand(input);

			switch (command[0]) {
				case START:
					startGame();
					break;
				case MOVE:
					movePiece(command);
					break;
				case END:
					endProceedingGame();
					break;
				default:
					invalidCommand();
			}
		}
	}

	private void invalidCommand() {
		System.out.println(INVALID_INPUT);
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
			verifyTurn(command[1]);
			move(command[1], command[2]);
			changeTurn();
		} catch (ChessException exception) {
			System.out.println(exception.getMessage());
		}

		ChessViewUtil.print(board);

		verifyCheckMate();
	}

	private void verifyCheckMate() {
		List<Piece> king = new ArrayList<>();
		Color winner = Color.NO_COLOR;
		if (turn.equals(Color.BLACK)) {
			king = board.getBlackPieces().getOrDefault(Type.KING, new ArrayList<>());
			winner = Color.WHITE;
		}
		if (turn.equals(Color.WHITE)) {
			king = board.getWhitePieces().getOrDefault(Type.KING, new ArrayList<>());
			winner = Color.BLACK;
		}

		if (king.size() == 0) {
			StringBuilder endMessage = new StringBuilder();
			endMessage.append(winner.getValue()).append(WINNER_END_MESSAGE);
			System.out.println(endMessage);
			endGame();
		}
	}

	private void changeTurn() {
		if (turn == Color.BLACK) {
			turn = Color.WHITE;
			return;
		}

		turn = Color.BLACK;
	}

	private void verifyTurn(String selectedPiece) {
		Piece piece = findPiece(selectedPiece);

		if (turn != piece.getColor()) {
			throw InvalidTurnException.EXCEPTION;
		}
	}

	private void endGame() {
		board.cleanUp();
		isProceeding = false;
	}

	private void endProceedingGame() {
		double whitePoint = getPoint(board.getWhitePieces());
		double blackPoint = getPoint(board.getBlackPieces());
		StringBuilder endMessage = new StringBuilder();

		if (whitePoint > blackPoint) {
			endMessage.append(Color.WHITE.getValue()).append(WINNER_END_MESSAGE);
		} else if (whitePoint < blackPoint) {
			endMessage.append(Color.BLACK.getValue()).append(WINNER_END_MESSAGE);
		} else {
			endMessage.append(TIE_END_MESSAGE);
		}

		System.out.println(endMessage);

		board.cleanUp();
		isProceeding = false;
	}

	public void move(String sourcePosition, String targetPosition) {
		Piece sourcePiece = findPiece(sourcePosition);
		Piece targetPiece = findPiece(targetPosition);

		verifyMove(sourcePiece, targetPiece);

		if (isCapturingMove(sourcePiece, targetPiece)) {
			sourcePiece.capture(targetPiece.getPosition());
			board.removePiece(targetPiece);
			return;
		}

		sourcePiece.move(targetPiece.getPosition());
	}

	private boolean isCapturingMove(Piece sourcePiece, Piece targetPiece) {
		return (sourcePiece.isWhite() && targetPiece.isBlack()) || (sourcePiece.isBlack() && targetPiece.isWhite());
	}

	private void verifyMove(Piece sourcePiece, Piece targetPiece) {
		verifyPossibleTargetPosition(sourcePiece, targetPiece);
		verifySameTeamOnTarget(sourcePiece, targetPiece);
		verifyExistPieceOnPath(sourcePiece, targetPiece);
	}

	private void verifyExistPieceOnPath(Piece sourcePiece, Piece targetPiece) {
		List<Position> betweenPaths = sourcePiece.findBetweenPath(targetPiece);
		if (existPieceOnPath(betweenPaths)) {
			throw ExistPieceOnPathException.EXCEPTION;
		}
	}

	private boolean existPieceOnPath(List<Position> betweenPaths) {
		return betweenPaths.stream()
			.map(position -> findPiece(position.getOriginPosition()))
			.filter(Piece::isPiece)
			.findFirst()
			.isPresent();
	}

	private void verifyPossibleTargetPosition(Piece sourcePiece, Piece targetPiece) {
		Position sourcePosition = sourcePiece.getPosition();
		Position targetPosition = targetPiece.getPosition();
		verifyNoProgressMove(sourcePosition, targetPosition);

		if (sourcePiece.isValidPosition(targetPosition)) {
			return;
		}

		throw InvalidPositionException.EXCEPTION;
	}

	private void verifyNoProgressMove(Position sourcePosition, Position targetPosition) {
		if (sourcePosition.equals(targetPosition)) {
			throw MeaninglessMoveException.EXCEPTION;
		}
	}

	private void verifySameTeamOnTarget(Piece sourcePiece, Piece targetPiece) {
		if (sourcePiece.getColor() == targetPiece.getColor()) {
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
			.orElse(BlankFactory.create(targetPosition));
	}

	/**
	 * 점수 계산
	 */
	public double calculatePoint(Color color) {
		if (color.equals(Color.BLACK)) {
			return getPoint(board.getBlackPieces());
		} else if (color.equals(Color.WHITE)) {
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
