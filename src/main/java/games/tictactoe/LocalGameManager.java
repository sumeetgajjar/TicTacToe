package games.tictactoe;

import games.tictactoe.beans.Move;

import java.io.IOException;

/**
 * Created by sumeet
 * on 13/7/17.
 */
public class LocalGameManager extends GameManager {

    public LocalGameManager(int n, Player player1, Player player2) {
        super(n, player1, player2);
    }

    @Override
    protected void broadCastToPlayers(String message) throws IOException {
        player1.writeLine(message);
    }

    public static void main(String[] args) throws IOException {
        Player player1 = new Player(System.in, Move.X, System.out);
        Player player2 = new Player(System.in, Move.O, System.out);
        GameManager gameManager = new LocalGameManager(3, player1, player2);
        gameManager.play();

    }
}
