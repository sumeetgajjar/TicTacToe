package games.tictactoe;

import games.tictactoe.beans.Move;

import java.io.IOException;

/**
 * Created by sumeet
 * on 13/7/17.
 */
public class LocalGameManager extends GameManager {

    public LocalGameManager(int n) {
        super(n, new Player(System.in, Move.X, System.out), new Player(System.in, Move.O, System.out));
    }

    @Override
    protected void broadCastToPlayers(String message) throws IOException {
        player1.writeLine(message);
    }

    public static void main(String[] args) throws IOException {
        GameManager gameManager = new LocalGameManager(3);
        gameManager.play();

    }
}
