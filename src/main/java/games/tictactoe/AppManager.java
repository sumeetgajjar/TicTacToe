package games.tictactoe;

import games.tictactoe.beans.GameType;
import games.tictactoe.services.Arena;
import games.tictactoe.services.Game;
import games.tictactoe.services.GameStatsService;
import games.tictactoe.services.LocalGame;

import java.io.IOException;
import java.util.Scanner;

/**
 * Created by sumeet
 * on 13/7/17.
 */
public class AppManager {

    public static void main(String[] args) throws IOException, InstantiationException, IllegalAccessException {

        System.out.println("Enter the Game Type");
        Scanner sc = new Scanner(System.in);
        GameType gameType = GameType.valueOf(sc.nextLine());

        switch (gameType) {
            case ARENA:
                GameStatsService.initStatusServer();
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
