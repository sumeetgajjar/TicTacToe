package games.tictactoe;

import games.tictactoe.beans.Board;
import games.tictactoe.beans.Move;

/**
 * Created by sumeet
 * on 13/7/17.
 */
public class SingleMachineTicTacToe {

    public static void main(String[] args) {
        Board board = new Board(10);

        boolean makeMove = board.makeMove(3, Move.X);
        board.makeMove(4, Move.O);
        board.display();
        if (makeMove) {
            boolean checkWinner = board.checkWinner(Move.X);
            if (checkWinner) {
                System.out.println("Winner is X");
            } else if (board.isGameOver()) {
                System.out.println("Game Over");
            }
            board.display();
        }
    }
}
