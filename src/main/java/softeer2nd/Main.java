package softeer2nd;

import softeer2nd.chess.Board;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Board board = new Board();
        board.initialize();

        Scanner in = new Scanner(System.in);
        while(true) {
            String input = in.nextLine();

            if(input.equals("0")) break;
        }

        board.end();
    }
}