package games.tictactoe.services;

import games.tictactoe.beans.Board;
import games.tictactoe.beans.Move;
import games.tictactoe.beans.Player;

import java.io.IOException;
import java.util.EnumMap;
import java.util.Map;

/**
 * Created by sumeet
 * on 13/7/17.
 */
public abstract class Game {

    protected final Player player1;
    protected final Player player2;
    protected int n;
    protected Board board;

    public Game(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
    }

    private void initBoard() throws IOException {
        player1.writeLine("Please Enter the Board Size");
        int n = Integer.parseInt(player1.readLine());
        this.board = new Board(n);
        broadCastToPlayers(String.format("The size of the board is %d", n));
    }

    protected void broadCastToPlayers(String message) throws IOException {
        player1.writeLine(message);
        player2.writeLine(message);
    }

    private void initializePlayer(Player player, int i) throws IOException {
        broadCastToPlayers(String.format("Initializing Player %d", i));
        player.writeLine("Enter your Name");
        String username = player.readLine();
        player.setUserName(username);
    }

    public void play() throws IOException {
        initBoard();
        initializePlayer(player1, 1);
        initializePlayer(player2, 2);

        Map<Move, Player> userMap = new EnumMap<>(Move.class);
        userMap.put(Move.X, player1);
        userMap.put(Move.O, player2);

        broadCastToPlayers(board.display());
        Move mover = Move.O;
        outer:
        while (true) {
            mover = Move.getOtherMove(mover);
            boolean makeMove = false;
            while (!makeMove) {
                Player player = userMap.get(mover);
                broadCastToPlayers(String.format("Its %s's turn to play %s: ", player.getUserName(), player.getMove().name()));
                int nextMove = Integer.parseInt(player.readLine());
                makeMove = board.makeMove(nextMove, mover);
                if (makeMove) {
                    boolean checkWinner = board.checkWinner(mover);
                    if (checkWinner) {
                        broadCastToPlayers(String.format("Winner is %s%n", player.getUserName()));
                    } else if (board.isGameOver()) {
                        broadCastToPlayers("Game Over");
                    }
                    broadCastToPlayers(board.display());

                    if (checkWinner || board.isGameOver()) break outer;
                }
            }
        }
    }
}
