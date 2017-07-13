package games.tictactoe.services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import games.tictactoe.beans.GameStats;
import games.tictactoe.beans.Move;
import games.tictactoe.beans.Player;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
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
    private static final int STATUS_PORT = 10000;
    private static final List<GameStats> GAME_STATS = new ArrayList<>();
    private static final ExecutorService GAME_POOL = Executors.newCachedThreadPool();
    private static final ExecutorService STATUS_POOL = Executors.newFixedThreadPool(1);

    public Arena() throws IOException {
        this.serverSocket = new ServerSocket(PORT);
        this.move = Move.O;
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
                Player player1 = getPlayer(serverSocket.accept());
                Player player2 = getPlayer(serverSocket.accept());
                GAME_POOL.submit(new Match(player1, player2));

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static final Gson GSON = new GsonBuilder().serializeNulls().setPrettyPrinting().create();


    private void initStatusServer() {
        STATUS_POOL.submit(new Runnable() {

            @Override
            public void run() {
                try {
                    ServerSocket statusServerSocket = new ServerSocket(STATUS_PORT);
                    while (true) {
                        try (Socket socket = statusServerSocket.accept()) {
                            try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()))) {
                                bw.write(GSON.toJson(GAME_STATS, List.class));
                                bw.newLine();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
