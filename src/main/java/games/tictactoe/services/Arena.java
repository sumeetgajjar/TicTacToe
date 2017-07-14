package games.tictactoe.services;

import games.tictactoe.beans.GameStats;
import games.tictactoe.beans.Move;
import games.tictactoe.beans.Player;
import utils.Util;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
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
    private static final List<GameStats> GAME_STATS = new ArrayList<>();
    private static final ExecutorService GAME_POOL = Executors.newCachedThreadPool();

    public Arena() throws IOException {
        Util.log("INITIALIZING_ARENA", String.valueOf(PORT));
        this.serverSocket = new ServerSocket(PORT);
        this.move = Move.O;
        Util.log("ARENA_INITIALIZED");
    }

    public static synchronized void addGameStats(GameStats gameStats) {
        GAME_STATS.add(gameStats);
    }

    private Player getPlayer(Socket socket) throws IOException {
        move = Move.getOtherMove(move);
        return new Player(move, socket.getInputStream(), socket.getOutputStream());
    }

    @SuppressWarnings("InfiniteLoopStatement")
    public void start() {

        while (true) {
            try {
                Util.log("WAITING_FOR_PLAYERS_TO_CONNECT");

                Socket player1Socket = serverSocket.accept();
                Player player1 = getPlayer(player1Socket);
                Util.log("PLAYER_1_CONNECTED");
                player1.writeLine("WAITING_FOR_PLAYER_2");

                Socket player2Socket = serverSocket.accept();
                Player player2 = getPlayer(player2Socket);
                Util.log("PLAYER_2_CONNECTED");

                Util.log("SCHEDULING_GAME", player1Socket.getInetAddress().getHostAddress(), String.valueOf(player1Socket.getPort()), player2Socket.getInetAddress().getHostAddress(), String.valueOf(player2Socket.getPort()));

                GAME_POOL.submit(new OnlineGame(player1Socket, player1, player2Socket, player2));

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static List<GameStats> getGameStats() {
        return GAME_STATS;
    }
}
