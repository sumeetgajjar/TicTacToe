package games.tictactoe.services;

import games.tictactoe.beans.Move;
import games.tictactoe.beans.Player;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by sumeet
 * on 13/7/17.
 */
public class Arena {

    private Move move;
    private final ServerSocket serverSocket;
    private static final int PORT = 9999;
    private static final ExecutorService EXECUTOR_SERVICE = Executors.newCachedThreadPool();

    public Arena() throws IOException {
        this.serverSocket = new ServerSocket(PORT);
        this.move = Move.O;
    }

    private Player getPlayer(Socket socket) throws IOException {
        move = Move.getOtherMove(move);
        return new Player(move, socket.getInputStream(), socket.getOutputStream());
    }

    @SuppressWarnings("InfiniteLoopStatement")
    public void start() {

        while (true) {
            try {
                Player player1 = getPlayer(serverSocket.accept());
                Player player2 = getPlayer(serverSocket.accept());
                EXECUTOR_SERVICE.submit(new Match(player1, player2));

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
