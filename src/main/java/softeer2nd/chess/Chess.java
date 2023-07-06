package softeer2nd.chess;

import java.util.Scanner;

public class Chess {
    private static Scanner input = new Scanner(System.in);

    public static void start() {
        Board board = new Board();
        board.initialize();

        boolean cont = true;
        while(cont) {
            String[] command = input.nextLine().split(" ");

            switch (command[0]) {
                case "start" :
                    board.print();
                    break;
                case "end" :
                    board.end();
                    cont = false;
                    break;
                case "move" :
                    board.move(command[1], command[2]);
                    board.print();
            }
        }
    }
}
