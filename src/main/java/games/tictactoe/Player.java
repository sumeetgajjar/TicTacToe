package games.tictactoe;

import games.tictactoe.beans.Move;

import java.io.*;

/**
 * Created by sumeet
 * on 13/7/17.
 */
public class Player {

    private final Move move;
    private final InputStream inputStream;
    private final OutputStream outputStream;
    private String userName;

    public Player(InputStream inputStream, Move move, OutputStream outputStream) {
        this.inputStream = inputStream;
        this.move = move;
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
}
