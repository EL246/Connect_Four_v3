/* The Game class represents the Connect-Four game */

import java.util.ArrayList;

class Game {
    private static final int ROWS = 6;
    private static final int COLS = 7;

    private final Column[] columns;
    private final ArrayList<GridLocation> winningCells;
    private boolean isWon;

    Game() {
        this.columns = new Column[COLS];
        for (int i = 0; i < COLS; i++) {
            columns[i] = new Column(ROWS);
        }

        isWon = false;
        winningCells = new ArrayList<>();
    }

    void insertColumn(int col, Content color) throws InvalidMoveException {
        columns[col].insert(color);
    }

    void clear() {
        for (Column col : columns) {
            col.clear();
        }
        winningCells.clear();
        isWon = false;
    }

    /* returns false if any cell in the top row is empty */
    boolean isDraw() {
        for (int col = 0; col < COLS; col++) {
            if (columns[col].hasSpace()) {
                return false;
            }
        }
        return true;
    }

    /* returns true if the board contains at least four consecutive
    disks of the same color (horizontally, vertically, or diagonally) */
    boolean isWon(int row, int col) {
        checkAllDirections(row, col);

        if (isWon) {
            highlightWinningCells();
            return true;
        }
        return false;
    }

    private void checkAllDirections(int row, int col) {
        final Content color = getCell(row, col).getContent();
        checkVerticalWin(row, col, color);
        checkHorizontalWin(row, col, color);
        checkDiagonalWin(row, col, color);
    }

    private void checkVerticalWin(int row, int col, Content color) {
        final SlotDirection direction = SlotDirection.VERTICAL;
        getLenAndAddWinningSlots(row, col, color, direction);
    }

    private void checkHorizontalWin(int row, int col, Content color) {
        final SlotDirection direction = SlotDirection.HORIZONTAL;
        getLenAndAddWinningSlots(row, col, color, direction);
    }

    private void checkDiagonalWin(int row, int col, Content color) {
        checkUpwardDiagonal(row, col, color);
        checkDownwardDiagonal(row, col, color);
    }

    private void checkUpwardDiagonal(int row, int col, Content color) {
        final SlotDirection direction = SlotDirection.UPWARD_DIAGONAL;
        getLenAndAddWinningSlots(row, col, color, direction);
    }

    private void checkDownwardDiagonal(int row, int col, Content color) {
        final SlotDirection direction = SlotDirection.DOWNWARD_DIAGONAL;
        getLenAndAddWinningSlots(row, col, color, direction);
    }


    private void getLenAndAddWinningSlots(int row, int col, Content color, SlotDirection direction) {
        GridLocation start = checkCells(row, col, color, true, direction);
        GridLocation end = checkCells(row, col, color, false, direction);

        int len = start.getDist(end);

        if (len >= 4) {
            addWinningCells(start, end, direction);
            isWon = true;
        }
    }

    private GridLocation checkCells(int r, int c, Content color, boolean checkNegDirection, SlotDirection direction) {
        int rowIncrement = checkNegDirection ? -direction.getRowIncrement() : direction.getRowIncrement();
        int colIncrement = checkNegDirection ? -direction.getColIncrement() : direction.getColIncrement();

        while (r + rowIncrement >= 0 && r + rowIncrement < ROWS
                && c + colIncrement >= 0 && c + colIncrement < COLS) {
            if (getCell(r + rowIncrement, c + colIncrement).getContent() != color) {
                break;
            }
            r = r + rowIncrement;
            c = c + colIncrement;
        }
        return new GridLocation(r, c);
    }

    private void addWinningCells(GridLocation start, GridLocation end, SlotDirection direction) {
        if (start.getR() < end.getR()) {
            addWinningCellsByRow(start, end, direction);
        } else {
            addWinningCellsByCol(start, end, direction);
        }
    }

    private void addWinningCellsByRow(GridLocation start, GridLocation end, SlotDirection dir) {
        int c = start.getC();
        int colInc = dir.getColIncrement();
        for (int r = start.getR(); r <= end.getR(); r++) {
            winningCells.add(new GridLocation(r, c));
            c = c + colInc;
        }
    }

    private void addWinningCellsByCol(GridLocation start, GridLocation end, SlotDirection dir) {
        int r = start.getR();
        int rowInc = dir.getRowIncrement();
        for (int c = start.getC(); c <= end.getC(); c++) {
            winningCells.add(new GridLocation(r, c));
            r = r + rowInc;
        }
    }

    private void highlightWinningCells() {
        for (GridLocation loc : winningCells) {
            highlightCell(loc.getR(), loc.getC());
        }
    }

    private void highlightCell(int row, int col) {
        getCell(row, col).highlight();
    }

    private Cell getCell(int row, int col) {
        return columns[col].getCell(row);
    }

    Column getColumn(int index) {
        return columns[index];
    }

    static int getNumRows() {
        return ROWS;
    }

    static int getNumCols() {
        return COLS;
    }
}
