package main.java.connect4.game;

/* The GameController class controls the game play and updates the ConnectFourFrame. */

import main.java.connect4.board.InvalidMoveException;
import main.java.connect4.gui.ConnectFourFrame;
import main.java.connect4.player.Player;

import java.util.Random;

public class GameController {
    private final Game game;
    private boolean gameActive = false;

    private final Player redPlayer;
    private final Player yellowPlayer;
    private Player currentPlayer;
    private final Random random;
    private boolean yellowPlayerTurn;

    private final ConnectFourFrame frame;

    public GameController(ConnectFourFrame frame) {
        this.game = new Game();
        this.frame = frame;
        this.redPlayer = new Player(false, game);
        this.yellowPlayer = new Player(true, game);
        random = new Random();
    }

    public void startNewGame() {
        game.clear();
        gameActive = true;
        yellowPlayerTurn = random.nextBoolean();
        currentPlayer = yellowPlayerTurn ? yellowPlayer : redPlayer;
        frame.alert(currentPlayer.toString() + " it is your turn");
    }

    public void updateColSelected(int colSelected) {
        if (gameActive) {
            try {
                currentPlayer.makeMove(colSelected);
                updateGame(colSelected);
            } catch (InvalidMoveException e1) {
                frame.alert(currentPlayer.toString() + " invalid move, try again. (" + e1.getMessage() + ")");
            }
        }
    }

    private void updateGame(int colSelected) {
        int rowInserted = game.getColumn(colSelected).getLastFilledIndex();
        if (game.isWon(rowInserted, colSelected)) {
            gameActive = false;
            frame.alert("CONGRATULATIONS, " + currentPlayer + " is the winner!");
        } else if (game.isDraw()) {
            gameActive = false;
            frame.alert("GAME OVER, it is a draw!");
        } else {
            switchPlayer();
        }
    }

    private void switchPlayer() {
        yellowPlayerTurn = !yellowPlayerTurn;
        currentPlayer = yellowPlayerTurn ? yellowPlayer : redPlayer;
        frame.alert(currentPlayer.toString() + " it is your turn");
    }

    public Game getGame() {
        return game;
    }
}