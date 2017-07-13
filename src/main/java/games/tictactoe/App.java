package games.tictactoe;

import games.tictactoe.beans.GameType;
import games.tictactoe.services.Game;

import java.io.IOException;

/**
 * Created by sumeet
 * on 13/7/17.
 */
public class App {

    public static void main(String[] args) throws IOException, InstantiationException, IllegalAccessException {
        Game game = GameType.LOCAL.getGame();
        game.play();
    }
}
