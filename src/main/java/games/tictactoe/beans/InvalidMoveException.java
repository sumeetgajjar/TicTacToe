package games.tictactoe.beans;

/**
 * Created by sumeet
 * on 13/7/17.
 */
public class InvalidMoveException extends Exception {
    public InvalidMoveException(String message) {
        super(message);
    }
}
