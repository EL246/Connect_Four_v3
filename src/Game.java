/* The Game class represents the Connect-Four board */

class Game {
    private static final int ROWS = 6;
    private static final int COLS = 7;

    private Column[] columns;

    Game() {
        this.columns = new Column[COLS];
        for (int i = 0; i < COLS; i++) {
            columns[i] = new Column(ROWS);
        }
    }

    void insertColumn(int col, Content color) throws InvalidMoveException {
        columns[col].insert(color);
    }

    void clear() {
        for (Column col : columns) {
            col.clear();
        }
    }


    /* returns false is any cell in the top row is empty */
    boolean isDraw() {
        for (int col = 0; col < COLS; col++) {
            if (columns[col].hasSpace()) {
                return false;
            }
        }
        return true;
    }

    /* returns true if the board four consecutive disks of the same color (horizontally, vertically, or diagonally) */
    boolean isWon() {
        for (int r = 0; r < ROWS; r++) {
            for (int c = 0; c < COLS; c++) {
                Cell cell = columns[c].getCell(r);
                if (cell.isEmpty()) {
                    continue;
                }
                if (isWinningCell(r, c, cell.getContent())) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isWinningCell(int r, int c, Content content) {
        return (isHorizontalWin(content, r, c) || isVerticalWin(content, r, c) || isDiagonalWin(content, r, c));
    }

    private boolean isHorizontalWin(Content color, int row, int col) {
        /* look right */
        boolean isWin = false;
        if ((col + 3) < COLS) {
            isWin = ((color == getCell(row, col + 1).getContent()) &&
                    (color == getCell(row, col + 2).getContent()) &&
                    (color == getCell(row, col + 3).getContent()));
        }
        if (isWin) {
            for (int i = 0; i < 4; i++) {
                highlightCell(row, col + i);
            }
        }
        return isWin;
    }

    private boolean isVerticalWin(Content color, int row, int col) {
        /* look up */
        boolean isWin = false;
        if ((row + 3) < ROWS) {
            isWin = ((color == getCell(row + 1, col).getContent()) &&
                    (color == getCell(row + 2, col).getContent()) &&
                    (color == getCell(row + 3, col).getContent()));
        }
        if (isWin) {
            for (int i = 0; i < 4; i++) {
                highlightCell(row + i, col);
            }
        }
        return isWin;
    }

    private boolean isDiagonalWin(Content color, int row, int col) {
        if ((col + 3) < COLS) {
            return isUpRightDiagonalWin(color, row, col) || isDownRightDiagonalWin(color, row, col);
        }
        return false;
    }

    private boolean isDownRightDiagonalWin(Content color, int row, int col) {
        /* look down and right */
        boolean isWin = false;
        if ((row - 3) >= 0) {
            isWin = ((color == getCell(row - 1, col + 1).getContent()) &&
                    (color == getCell(row - 2, col + 2).getContent()) &&
                    (color == getCell(row - 3, col + 3).getContent()));
        }
        if (isWin) {
            for (int i = 0; i < 4; i++) {
                highlightCell(row - i, col + i);
            }
        }
        return isWin;
    }

    private boolean isUpRightDiagonalWin(Content color, int row, int col) {
        /* look up and right */
        boolean isWin = false;
        if ((row + 3) < ROWS) {
            isWin = ((color == getCell(row + 1, col + 1).getContent()) &&
                    (color == getCell(row + 2, col + 2).getContent()) &&
                    (color == getCell(row + 3, col + 3).getContent()));
        }
        if (isWin) {
            for (int i = 0; i < 4; i++) {
                highlightCell(row + i, col + i);
            }
        }
        return isWin;
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
