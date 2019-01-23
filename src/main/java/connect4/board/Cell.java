package main.java.connect4.board;
/*
 * This class represents each individual cell (slot) on the game board.
 */

public class Cell {
    private Content content;
    private boolean isHighlighted;

    Cell() {
        clear();
    }

    void clear() {
        content = Content.EMPTY;
        isHighlighted = false;
    }

    public void highlight() {
        isHighlighted = true;
    }

    public boolean isEmpty() {
        return content == Content.EMPTY;
    }

    public boolean isRed() {
        return content == Content.RED;
    }

    public boolean isHighlighted() {
        return isHighlighted;
    }

    public Content getContent() {
        return content;
    }

    void setContent(Content content) {
        this.content = content;
    }
}
