package games.tictactoe.services;

import games.tictactoe.beans.GameState;
import games.tictactoe.beans.Player;

import java.io.IOException;
import java.net.Socket;

/**
 * Created by sumeet
 * on 14/7/17.
 */
public class OnlineGame extends Game implements Runnable {

    private final Socket player1Socket;
    private final Socket player2Socket;

    public OnlineGame(Socket player1Socket, Player player1, Socket player2Socket, Player player2) {
        super(player1, player2);
        this.player1Socket = player1Socket;
        this.player2Socket = player2Socket;
    }

    @Override
    protected void broadCastToPlayers(String message) throws IOException {
        player1.write(message);
        player2.write(message);
    }

    @Override
    protected String readFromPlayer(Player player) throws IOException {
        return player.read();
    }

    @Override
    protected void writeToPlayer(Player player, String message) throws IOException {
        player.write(message);
    }

    @Override
    public void run() {
        try {
            Arena.addGameStats(gameStats);
            play();
        } catch (IOException e) {
            gameStats.setGameState(GameState.ERROR);
            e.printStackTrace();
        } finally {
            close();
        }
    }

    private void close() {
        try {
            player1.close();
            player1Socket.close();
            player2.close();
            player2Socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
