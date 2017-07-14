package games.tictactoe.beans;

import java.io.*;
import java.net.Socket;

/**
 * Created by sumeet
 * on 13/7/17.
 */
public class Player {

    private final Move move;
    private final BufferedReader br;
    private final BufferedWriter bw;
    private String userName;

    public Player(Move move, InputStream inputStream, OutputStream outputStream) {
        this.move = move;
        this.br = new BufferedReader(new InputStreamReader(inputStream));
        this.bw = new BufferedWriter(new OutputStreamWriter(outputStream));
    }

    public Move getMove() {
        return move;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String read() throws IOException {
        writeLine("write");
        return readLine();
    }

    public void write(String message) throws IOException {
        writeLine(message);
    }

    public String readLine() throws IOException {
        String line = br.readLine();
        return line;
    }

    public void writeLine(String message) throws IOException {
        bw.write(message);
        bw.flush();
        bw.newLine();
        bw.flush();
    }

    public void close() {
        try {
            bw.flush();
            bw.close();
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        Socket socket = new Socket("localhost", 9999);
        Player player = new Player(Move.X, socket.getInputStream(), socket.getOutputStream());
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        outer:
        while (true) {
            String message;
            while ((message = player.readLine()) != null) {
                if (message.equals("write")) {
                    player.writeLine(br.readLine());
                } else {
                    System.out.println(message);
                    if (message.toLowerCase().contains("winner")) {
                        break outer;
                    }
                }
            }
        }
    }
}
