/*
    This class represents each column on the game board.
 */

class Column {
    private final Cell[] cells;
    private final int length;
    private int nextAvailableIndex;

    Column(int rows) {
        this.length = rows;
        this.cells = new Cell[rows];
        this.nextAvailableIndex = 0;

        for (int i = 0; i < rows; i++) {
            cells[i] = new Cell();
        }
    }

    void insert(Content color) throws InvalidMoveException {
        if (!hasSpace()) {
            throw new InvalidMoveException("column is full");
        }
        cells[nextAvailableIndex].setContent(color);
        nextAvailableIndex++;
    }

    void clear() {
        for (Cell cell : cells) {
            cell.clear();
        }
        nextAvailableIndex = 0;
    }

    boolean hasSpace() {
        return nextAvailableIndex < length;
    }

    Cell getCell(int index) {
        return cells[index];
    }

    int getLastFilledIndex() {
        return (nextAvailableIndex - 1);
    }
}
