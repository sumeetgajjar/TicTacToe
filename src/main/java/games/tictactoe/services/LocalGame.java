package games.tictactoe.services;

import games.tictactoe.beans.Move;
import games.tictactoe.beans.Player;

import java.io.IOException;

/**
 * Created by sumeet
 * on 13/7/17.
 */
public class LocalGame extends Game {

    public LocalGame() {
        super(new Player(System.in, Move.X, System.out), new Player(System.in, Move.O, System.out));
    }

    @Override
    protected void broadCastToPlayers(String message) throws IOException {
        player1.writeLine(message);
    }
}
