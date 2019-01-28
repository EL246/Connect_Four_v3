package main.java.connect4.board;

/* This class represents the possible cell (slot) contents. */

public enum Content {
    EMPTY("Empty"),
    RED("Red"),
    YELLOW("Yellow");

    final String name;

    Content(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}