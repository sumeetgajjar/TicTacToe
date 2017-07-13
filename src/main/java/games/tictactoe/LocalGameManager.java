package games.tictactoe;

import java.io.IOException;

/**
 * Created by sumeet
 * on 13/7/17.
 */
public class LocalGameManager extends GameManager {

    public LocalGameManager(int n, Player player1, Player player2) {
        super(n, player1, player2);
    }

    @Override
    protected void broadCastToPlayers(String message) throws IOException {
        player1.writeLine(message);
    }
}
