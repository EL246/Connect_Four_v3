package main.java.connect4;

import main.java.connect4.game.Game;
import main.java.connect4.gui.ConnectFourFrame;

class Main {

    public static void main(String[] args) {
        Game connectFourGame = new Game();
        new ConnectFourFrame(connectFourGame);
    }
}
