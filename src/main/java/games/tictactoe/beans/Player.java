package games.tictactoe.beans;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by sumeet
 * on 13/7/17.
 */
public class Player {

    private final Move move;
    private final InputStream inputStream;
    private final OutputStream outputStream;
    private String userName;

    public Player(Move move, InputStream inputStream, OutputStream outputStream) {
        this.move = move;
        this.inputStream = inputStream;
        this.outputStream = outputStream;
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
        writeLine("read");
        writeLine(message);
    }

    public String readLine() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
        String line = br.readLine();
        return line;
    }

    public void writeLine(String message) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(outputStream));
        bw.write(message);
        bw.newLine();
        bw.flush();
    }

    public void close() {
        try {
            inputStream.close();
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        Socket socket = new Socket("localhost", 9999);
        Player player = new Player(Move.X, socket.getInputStream(), socket.getOutputStream());

        while (true) {
            String command = player.readLine();
            if (command.equals("read")) {
                System.out.println(player.readLine());
            } else {
                Scanner scanner = new Scanner(System.in);
                player.writeLine(scanner.nextLine());
            }
        }

    }
}
