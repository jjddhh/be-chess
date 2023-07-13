package softeer2nd.chess;

import softeer2nd.chess.exception.InvalidColorException;
import softeer2nd.chess.pieces.*;
import softeer2nd.chess.pieces.factory.BishopFactory;
import softeer2nd.chess.pieces.factory.KingFactory;
import softeer2nd.chess.pieces.factory.KnightFactory;
import softeer2nd.chess.pieces.factory.PawnFactory;
import softeer2nd.chess.pieces.factory.QueenFactory;
import softeer2nd.chess.pieces.factory.RookFactory;
import softeer2nd.chess.pieces.piece.Color;
import softeer2nd.chess.pieces.piece.Type;
import softeer2nd.chess.pieces.piece.Piece;

import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

public class Board {

	public static final int MAX_ROW = 8;
	public static final int MAX_COLUMN = 8;
	public static final String EMPTY_BOARD = "........";

	private Map<Type, List<Piece>> whitePieces = new HashMap<>();
	private Map<Type, List<Piece>> blackPieces = new HashMap<>();

	public void initializeEmpty() {
		blackPieces = new HashMap<>();
		whitePieces = new HashMap<>();
	}

	public void initialize() {
		blackPieces = new HashMap<>();
		whitePieces = new HashMap<>();

		for (int i = 0; i < MAX_COLUMN; i++) {
			char col = (char)('a' + i);
			blackPieces.computeIfAbsent(Type.PAWN, k -> new ArrayList<>())
				.add(PawnFactory.createBlack(col + "7"));
			whitePieces.computeIfAbsent(Type.PAWN, k -> new ArrayList<>())
				.add(PawnFactory.createWhite(col + "2"));
		}

		for (int i = 0; i < MAX_COLUMN; i += 7) {
			char col = (char)('a' + i);
			blackPieces.computeIfAbsent(Type.ROOK, k -> new ArrayList<>())
				.add(RookFactory.createBlack(col + "8"));
			whitePieces.computeIfAbsent(Type.ROOK, k -> new ArrayList<>())
				.add(RookFactory.createWhite(col + "1"));
		}

		for (int i = 1; i < MAX_COLUMN; i += 5) {
			char col = (char)('a' + i);
			blackPieces.computeIfAbsent(Type.KNIGHT, k -> new ArrayList<>())
				.add(KnightFactory.createBlack(col + "8"));
			whitePieces.computeIfAbsent(Type.KNIGHT, k -> new ArrayList<>())
				.add(KnightFactory.createWhite(col + "1"));
		}

		for (int i = 2; i < MAX_COLUMN; i += 3) {
			char col = (char)('a' + i);
			blackPieces.computeIfAbsent(Type.BISHOP, k -> new ArrayList<>())
				.add(BishopFactory.createBlack(col + "8"));
			whitePieces.computeIfAbsent(Type.BISHOP, k -> new ArrayList<>())
				.add(BishopFactory.createWhite(col + "1"));
		}

		blackPieces.computeIfAbsent(Type.QUEEN, k -> new ArrayList<>())
			.add(QueenFactory.createBlack("d8"));
		whitePieces.computeIfAbsent(Type.QUEEN, k -> new ArrayList<>())
			.add(QueenFactory.createWhite("d1"));

		blackPieces.computeIfAbsent(Type.KING, k -> new ArrayList<>())
			.add(KingFactory.createBlack("e8"));
		whitePieces.computeIfAbsent(Type.KING, k -> new ArrayList<>())
			.add(KingFactory.createWhite("e1"));
	}

	public void cleanUp() {
		whitePieces = null;
		blackPieces = null;
	}

	public void addPiece(Piece piece) {
		if (piece.isBlack())
			addBlackPiece(piece);
		else if (piece.isWhite())
			addWhitePiece(piece);
	}

	private void addWhitePiece(Piece piece) {
		verifyWhitePiece(piece);
		whitePieces.computeIfAbsent(piece.getType(), k -> new ArrayList<>()).add(piece);
	}

	private void verifyWhitePiece(Piece piece) {
		if (!piece.getColor().equals(Color.WHITE)) {
			throw InvalidColorException.EXCEPTION;
		}
	}

	private void addBlackPiece(Piece piece) {
		verifyBlackPiece(piece);
		blackPieces.computeIfAbsent(piece.getType(), k -> new ArrayList<>()).add(piece);
	}

	private void verifyBlackPiece(Piece piece) {
		if (!piece.getColor().equals(Color.BLACK)) {
			throw InvalidColorException.EXCEPTION;
		}
	}

	public void removePiece(Piece piece) {
		Arrays.stream(Type.values()).forEach(type -> {
			whitePieces.getOrDefault(type, new ArrayList<>()).remove(piece);
			blackPieces.getOrDefault(type, new ArrayList<>()).remove(piece);
		});

	}

	public int whitePawnSize() {
		return whitePieces.get(Type.PAWN).size();
	}

	public Piece findWhitePawn(int idx) {
		return whitePieces.get(Type.PAWN).get(idx);
	}

	public String getWhitePawnsResult() {
		return getPawnsResult(whitePieces.get(Type.PAWN));
	}

	public String getBlackPawnsResult() {
		return getPawnsResult(blackPieces.get(Type.PAWN));
	}

	private String getPawnsResult(List<Piece> colorPawns) {
		return colorPawns.stream()
			.map(t -> String.valueOf(t.getRepresentation()))
			.collect(Collectors.joining());
	}

	public int getTotalPieceCount() {
		int whitePieceCount = Arrays.stream(Type.values())
			.mapToInt(type -> whitePieces.getOrDefault(type, new ArrayList<>()).size())
			.sum();

		int blackPieceCount = Arrays.stream(Type.values())
			.mapToInt(type -> blackPieces.getOrDefault(type, new ArrayList<>()).size())
			.sum();

		return whitePieceCount + blackPieceCount;
	}

	public int getPieceCount(Color color, Type type) {
		int whitePiecesCount = this.whitePieces.getOrDefault(type, new ArrayList<>()).size();
		int blackPiecesCount = this.blackPieces.getOrDefault(type, new ArrayList<>()).size();

		if (color.equals(Color.WHITE))
			return whitePiecesCount;
		else if (color.equals(Color.BLACK))
			return blackPiecesCount;
		else {
			return (MAX_ROW * MAX_COLUMN) - (whitePiecesCount + blackPiecesCount);
		}
	}

	public Map<Type, List<Piece>> getWhitePieces() {
		return whitePieces;
	}

	public Map<Type, List<Piece>> getBlackPieces() {
		return blackPieces;
	}
}