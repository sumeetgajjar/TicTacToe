package games.tictactoe.beans;

import games.tictactoe.services.Game;
import games.tictactoe.services.LocalGame;

/**
 * Created by sumeet
 * on 13/7/17.
 */
public enum GameType {
    LOCAL(LocalGame.class);

    private final Class<? extends Game> game;

    GameType(Class<? extends Game> game) {
        this.game = game;
    }

    public Game getGame() throws IllegalAccessException, InstantiationException {
        return game.newInstance();
    }
}
