/*
    This class represents each individual cell on the game board.
 */

class Cell {
    private Content content;

    Cell() {
        clear();
    }

    void clear() {
        content = Content.EMPTY;
    }

    boolean isEmpty() {
        return content == Content.EMPTY;
    }

    boolean isRed() {
        return content == Content.RED;
    }

    Content getContent() {
        return content;
    }

    void setContent(Content content) {
        this.content = content;
    }
}
