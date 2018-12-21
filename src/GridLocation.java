/* This class represents a location on the Connect-Four grid, represented by a row and a column */
class GridLocation {
    private final int r;
    private final int c;

    GridLocation(int r, int c) {
        this.r = r;
        this.c = c;
    }

    int getDist(GridLocation other) {
        int xDist = Math.abs(other.getC() - this.getC()) + 1;
        int yDist = Math.abs(other.getR() - this.getR()) + 1;
        return Math.max(xDist, yDist);
    }

    int getR() {
        return r;
    }

    int getC() {
        return c;
    }

}
