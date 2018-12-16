class Player {
    Content color;
    Game game;

    Player(boolean isYellow, Game game) {
        this.game = game;
        if (isYellow) {
            this.color = Content.YELLOW;
        } else {
            this.color = Content.RED;
        }
    }

    void makeMove(int col) throws InvalidMoveException {
        game.insertColumn(col,color);
    }

    @Override
    public String toString() {
        return color.getName() + " Player";
    }
}
