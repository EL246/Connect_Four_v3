/*
 * This class represents a location on the
 * Connect-Four grid, represented by a row and a column
 * */

class GridLocation {
    private final int row;
    private final int col;

    GridLocation(int row, int col) {
        this.row = row;
        this.col = col;
    }

    int getDist(GridLocation other) {
        int xDist = Math.abs(other.getCol() - this.getCol()) + 1;
        int yDist = Math.abs(other.getRow() - this.getRow()) + 1;
        return Math.max(xDist, yDist);
    }

    int getRow() {
        return row;
    }

    int getCol() {
        return col;
    }

}
