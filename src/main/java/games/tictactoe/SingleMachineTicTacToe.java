package games.tictactoe;

import games.tictactoe.beans.Move;

import java.io.IOException;

/**
 * Created by sumeet
 * on 13/7/17.
 */
public class SingleMachineTicTacToe {

    public static void main(String[] args) throws IOException {
        Player player1 = new Player(System.in, Move.X, System.out);
        Player player2 = new Player(System.in, Move.O, System.out);
        GameManager gameManager = new GameManager(3, player1, player2);
        gameManager.play();

    }
}
