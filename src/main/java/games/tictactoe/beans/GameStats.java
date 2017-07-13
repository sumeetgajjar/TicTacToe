package games.tictactoe.beans;

/**
 * Created by sumeet
 * on 14/7/17.
 */
public class GameStats {
    private GameState gameState;
    private String player1;
    private String player2;
    private String winner;

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }

    public void setPlayer1(String player1) {
        this.player1 = player1;
    }

    public void setPlayer2(String player2) {
        this.player2 = player2;
    }
}
