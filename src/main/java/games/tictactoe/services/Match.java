package games.tictactoe.services;

import games.tictactoe.beans.GameState;
import games.tictactoe.beans.Player;

import java.io.IOException;

/**
 * Created by sumeet
 * on 14/7/17.
 */
public class Match extends Game implements Runnable {

    public Match(Player player1, Player player2) {
        super(player1, player2);
    }

    @Override
    protected void broadCastToPlayers(String message) throws IOException {
        player1.writeLine(message);
        player2.writeLine(message);
    }

    @Override
    public void run() {
        try {
            play();
        } catch (IOException e) {
            gameStats.setGameState(GameState.ERROR);
            e.printStackTrace();
        } finally {
            Arena.addGameStats(gameStats);
        }
    }
}
