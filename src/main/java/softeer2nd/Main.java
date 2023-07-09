package softeer2nd;

import softeer2nd.chess.Board;
import softeer2nd.chess.ChessGame;

public class Main {

    public static void main(String[] args) {
        Board board = new Board();
        ChessGame chessGame = new ChessGame(board);

        chessGame.start();
    }
}