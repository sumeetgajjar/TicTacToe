package games.tictactoe;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by sumeet
 * on 13/7/17.
 */
public class Server {

    public static final int SERVER_PORT = 2222;
    private final ServerSocket serverSocket;
    private Socket player1;
    private Socket player2;

    public Server(ServerSocket serverSocket) throws IOException {
        this.serverSocket = new ServerSocket(SERVER_PORT);
    }

    public void init() throws IOException {
        player1 = serverSocket.accept();
        System.out.println("Player One Connected");
        player2 = serverSocket.accept();
        System.out.println("Player Two Connected");
    }

    public String in(Socket socket) throws IOException {
        String line = null;
        try (BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
            line = br.readLine();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return line;
    }

    public void out(Socket socket, String message) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()))) {
            bw.write(message);
            bw.newLine();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
