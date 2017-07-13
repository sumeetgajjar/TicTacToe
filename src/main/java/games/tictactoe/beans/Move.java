package games.tictactoe.beans;

/**
 * Created by sumeet
 * on 13/7/17.
 */
public enum Move {
    X,
    O;

    public static Move getOtherMove(Move move) {
        if (move == X) return O;
        if (move == O) return X;

        return X;
    }
}
