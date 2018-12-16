public enum Content {
    EMPTY("Empty"),
    RED("Red"),
    YELLOW("Yellow");

    String name;

    Content(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
