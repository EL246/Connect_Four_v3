import java.util.Arrays;

class Column {
    private Cell[] cells;
    private int length;

    Column(int rows) {
        this.length = rows;
        this.cells = new Cell[rows];

        for (int i = 0; i < rows; i++){
            cells[i] = new Cell();
        }
    }

    void insert(Content color) throws InvalidMoveException {
        if (!hasSpace()) {
            throw new InvalidMoveException("Cannot insert in full column!");
        }
        int i = getNextAvailableCellIndex();
        cells[i].setContent(color);
    }

    void clear() {
        for (Cell cell : cells) {
            cell.clear();
        }
    }

    boolean hasSpace() {
        return cells[length-1].isEmpty();
    }

    int getNextAvailableCellIndex() throws InvalidMoveException {
        for (int i = 0; i < cells.length; i++) {
            if (cells[i].isEmpty()) {
                return i;
            }
        }
        throw new InvalidMoveException("No cells available!");
    }

    Cell[] getColumn() {
        return cells;
    }

    int getLength() {
        return length;
    }

    Cell getCell(int index) {
        return cells[index];
    }
}
