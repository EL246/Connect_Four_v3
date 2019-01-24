package main.java.connect4.board;

/* This class represents each column on the game board. */

public class Column {
    private final Cell[] cells;
    private final int length;
    private int nextAvailableIndex;

    public Column(int rows) {
        this.length = rows;
        this.cells = new Cell[rows];
        this.nextAvailableIndex = 0;

        for (int i = 0; i < rows; i++) {
            cells[i] = new Cell();
        }
    }

    public void insert(Content color) throws InvalidMoveException {
        if (!hasSpace()) {
            throw new InvalidMoveException("column is full");
        }
        cells[nextAvailableIndex].setContent(color);
        nextAvailableIndex++;
    }

    public void clear() {
        for (Cell cell : cells) {
            cell.clear();
        }
        nextAvailableIndex = 0;
    }

    public boolean hasSpace() {
        return nextAvailableIndex < length;
    }

    public Cell getCell(int index) {
        return cells[index];
    }

    public int getLastFilledIndex() {
        return (nextAvailableIndex - 1);
    }
}
