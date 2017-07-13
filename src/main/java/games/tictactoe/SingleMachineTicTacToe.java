package games.tictactoe;

import games.tictactoe.beans.Board;
import games.tictactoe.beans.Move;

import java.util.EnumMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by sumeet
 * on 13/7/17.
 */
public class SingleMachineTicTacToe {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Board Dimension");
        int boardSize = scanner.nextInt();

        Board board = new Board(boardSize);
        System.out.println("Enter 1st UserName :");
        String userName = scanner.next();

        System.out.println("Enter 2nd UserName :");
        String userName2 = scanner.next();

        Map<Move, String> userMap = new EnumMap<Move, String>(Move.class);
        userMap.put(Move.X, userName);
        userMap.put(Move.O, userName2);

        Move mover = Move.O;
        outer: while (true) {
            mover = Move.getOtherMove(mover);
            boolean makeMove = false;
            while (!makeMove) {
                System.out.println(String.format("Its %s's turn : ", userMap.get(mover)));
                int nextMove = scanner.nextInt();
                makeMove = board.makeMove(nextMove, mover);
                if (makeMove) {
                    boolean checkWinner = board.checkWinner(mover);
                    if (checkWinner) {
                        System.out.printf("Winner is %s%n", userMap.get(mover));
                    } else if (board.isGameOver()) {
                        System.out.println("Game Over");
                    }
                    board.display();

                    if (checkWinner || board.isGameOver()) break outer;
                }
            }
        }
    }
}
