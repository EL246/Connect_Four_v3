package main.java.connect4.player;

import main.java.connect4.board.InvalidMoveException;
import main.java.connect4.board.Content;
import main.java.connect4.game.Game;

public class Player {
    private final Content color;
    private final Game game;

    public Player(boolean isYellow, Game game) {
        this.game = game;
        if (isYellow) {
            this.color = Content.YELLOW;
        } else {
            this.color = Content.RED;
        }
    }

    public void makeMove(int col) throws InvalidMoveException {
        game.insertColumn(col, color);
    }

    @Override
    public String toString() {
        return color.getName() + " Player";
    }
}