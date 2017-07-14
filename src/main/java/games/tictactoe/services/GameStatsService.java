package games.tictactoe.services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import utils.Util;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by sumeet
 * on 14/7/17.
 */
public class GameStatsService {

    private static final int STATUS_PORT = 10000;
    private static ServerSocket STATUS_SERVER_SOCKET;
    private static final ExecutorService STATUS_POOL = Executors.newFixedThreadPool(1);
    private static final Gson GSON = new GsonBuilder().serializeNulls().setPrettyPrinting().create();


    public static void initStatsServer() throws IOException {

        if (!Util.isSet(STATUS_SERVER_SOCKET)) {
            synchronized (GameStatsService.class) {
                if (!Util.isSet(STATUS_SERVER_SOCKET)) {
                    Util.log("INITIALIZING_GAME_STATS_SOCKET", String.valueOf(STATUS_PORT));
                    STATUS_SERVER_SOCKET = new ServerSocket(STATUS_PORT);
                    Util.log("GAME_STATS_SOCKET_INITIALIZED");
                    startListening(STATUS_SERVER_SOCKET);
                    Util.log("STARTED_LISTENING_FOR_GAME_STATS_REQUEST");
                }
            }
        }
    }

    private static void startListening(ServerSocket statusServerSocket) {
        STATUS_POOL.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true) {
                        try (Socket socket = statusServerSocket.accept()) {
                            Util.log("REQUEST_FOR_GAME_STATS", socket.getInetAddress().getHostAddress());
                            try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()))) {
                                bw.write(GSON.toJson(Arena.getGameStats(), List.class));
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
