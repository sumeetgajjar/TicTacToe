package games.tictactoe.beans;

import utils.Util;

/**
 * Created by sumeet
 * on 13/7/17.
 */
public class Board {

    private final Move board[][];
    private int n;
    private int currentMoveCounter;
    private int totalMovesPossible;

    public Board(int n) {
        this.n = n;
        this.currentMoveCounter = 0;
        this.totalMovesPossible = n * n;
        this.board = new Move[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = null;
            }
        }
        display();
    }

    public boolean isGameOver() {
        return currentMoveCounter == totalMovesPossible;
    }

    private int getI(int index) {
        return (index - 1) / n;
    }

    private int getJ(int index) {
        return (index - 1) % n;
    }

    private int getIndex(int i, int j) {
        return (i * n + j) + 1;
    }

    private boolean isValidMove(int index) {
        int i = getI(index);
        int j = getJ(index);
        return index > 0 && index <= totalMovesPossible && !Util.isSet(board[i][j]);
    }

    public void makeMove(int index, Move move) throws InvalidMoveException {
        if (isValidMove(index)) {
            int i = getI(index);
            int j = getJ(index);
            board[i][j] = move;
            currentMoveCounter++;
        } else {
            throw new InvalidMoveException("Should I dumb down this game for u ??");
        }
    }

    private boolean checkRow(Move move) {
        for (int i = 0; i < n; i++) {
            int sum = 0;
            for (int j = 0; j < n; j++) {
                if (board[i][j] != move) {
                    break;
                }
                sum++;
            }
            if (sum == n) {
                return true;
            }
        }
        return false;
    }

    private boolean checkColumn(Move move) {
        for (int i = 0; i < n; i++) {
            int sum = 0;
            for (int j = 0; j < n; j++) {
                if (board[j][i] != move) {
                    break;
                }
                sum++;
            }
            if (sum == n) {
                return true;
            }
        }
        return false;
    }

    private boolean checkDiagonal1(Move move) {
        int sum = 0;
        for (int i = 0; i < n; i++) {
            if (board[i][i] != move) {
                break;
            }
            sum++;
        }
        if (sum == n) {
            return true;
        }
        return false;
    }

    private boolean checkDiagonal2(Move move) {
        int sum = 0;
        for (int i = 0, j = n - 1; j >= 0; j--, i++) {
            if (board[i][j] != move) {
                break;
            }
            sum++;
        }
        if (sum == n) {
            return true;
        }
        return false;
    }


    public boolean checkWinner(Move move) {
        return checkRow(move) || checkColumn(move) || checkDiagonal1(move) || checkDiagonal2(move);
    }

    public String display() {
        StringBuilder builder = new StringBuilder();
        int index = 1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == Move.X) {
                    builder.append(" X ");
                } else if (board[i][j] == Move.O) {
                    builder.append(" O ");
                } else {
                    builder.append(String.format("% 3d", index));
                }
                builder.append("|");
                index++;
            }
            builder.append("\n");
        }
        String string = builder.toString();
        return string;
    }
}
