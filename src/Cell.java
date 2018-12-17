/*
    This class represents each individual cell (slot) on the game board.
 */

class Cell {
    private Content content;
    private boolean isHighlighted;

    Cell() {
        clear();
    }

    void clear() {
        content = Content.EMPTY;
        isHighlighted = false;
    }

    void highlight() {
        isHighlighted = true;
    }

    boolean isEmpty() {
        return content == Content.EMPTY;
    }

    boolean isRed() {
        return content == Content.RED;
    }

    boolean isHighlighted() {
        return isHighlighted;
    }

    Content getContent() {
        return content;
    }

    void setContent(Content content) {
        this.content = content;
    }

}
