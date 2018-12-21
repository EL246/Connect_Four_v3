/* specifies the direction of a line of consecutive slots */
enum SlotDirection {
    HORIZONTAL(0, 1),
    VERTICAL(1, 0),
    UPWARD_DIAGONAL(1, 1),
    DOWNWARD_DIAGONAL(-1, 1);

    private final int rowIncrement;
    private final int colIncrement;

    SlotDirection(int rowIncrement, int colIncrement) {
        this.rowIncrement = rowIncrement;
        this.colIncrement = colIncrement;
    }

    public int getRowIncrement() {
        return rowIncrement;
    }

    public int getColIncrement() {
        return colIncrement;
    }
}
