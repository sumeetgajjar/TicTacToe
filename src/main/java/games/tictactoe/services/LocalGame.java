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
        super(new Player(Move.X, System.in, System.out), new Player(Move.O, System.in, System.out));
    }

    @Override
    protected void broadCastToPlayers(String message) throws IOException {
        writeToPlayer(player1, message);
    }
}
