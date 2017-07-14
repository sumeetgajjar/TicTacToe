package games.tictactoe;

import games.tictactoe.beans.GameType;
import games.tictactoe.services.Arena;
import games.tictactoe.services.Game;
import games.tictactoe.services.GameStatsService;
import games.tictactoe.services.LocalGame;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by sumeet
 * on 13/7/17.
 */
public class AppManager {

    public static void main(String[] args) throws IOException, InstantiationException, IllegalAccessException {

        System.out.println("Enter the Game Type");
        System.out.println("ARENA or LOCAL ?");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        GameType gameType = GameType.valueOf(br.readLine());

        switch (gameType) {
            case ARENA:
                GameStatsService.initStatsServer();
                Arena arena = new Arena();
                arena.start();
                break;
            case LOCAL:
                Game game = new LocalGame();
                game.play();
                break;

        }
    }
}
