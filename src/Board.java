import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

class Board extends JPanel {
    private Game game;

    //    private static final int DISC_DIAMETER = CELL_SIZE / 2;
    private static final int DISC_DIAMETER = 50;
    //    private static final int DISC_SPACING = (CELL_SIZE - DISC_DIAMETER) / 2;
    //    private static final int CELL_PADDING = (CELL_SIZE - DISC_DIAMETER) / 4;
    private static final int CELL_PADDING = DISC_DIAMETER / 4;
    private static final int DISC_SPACING = 2 * CELL_PADDING;

    //    Dimensions for Board
//    private static final int CELL_SIZE = 100;
    private static final int CELL_SIZE = DISC_DIAMETER + DISC_SPACING;
    private static final int BOARD_WIDTH = CELL_SIZE * (Game.getCOLS());
    private static final int BOARD_HEIGHT = CELL_SIZE * (Game.getROWS());


    Board(Game game) {
        super();
        this.game = game;
        this.setPreferredSize(new Dimension(BOARD_WIDTH, BOARD_HEIGHT));
        this.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(Color.CYAN);
        g2d.fillRect(0, 0, getWidth(), getHeight());

        for (int c = 0; c < Game.getCOLS(); c++) {
            for (int r = 0; r < Game.getROWS(); r++) {
                Cell current = game.getColumn(c).getCell(r);

                Color color;
                if (current.isEmpty()) {
                    color = Color.WHITE;
                } else {
                    if (current.isRed()) {
                        color = Color.RED;
                    } else {
                        color = Color.YELLOW;
                    }
                }

                int rowTrans = ((Game.getROWS() - 1) - r);
                int x = CELL_PADDING + c * DISC_SPACING + (c * DISC_DIAMETER);
                int y = CELL_PADDING + rowTrans * DISC_SPACING + (rowTrans * DISC_DIAMETER);
                drawDisc(g2d, x, y, color);
            }
        }
        drawColumnLines(g2d);
//        drawBorder(g2d);
    }

    private void drawBorder(Graphics2D g2d) {
        int xEnd = CELL_PADDING + (Game.getCOLS() * (DISC_DIAMETER + DISC_SPACING));
//        int xEnd = (Game.getCOLS() * (DISC_DIAMETER + DISC_SPACING));
        int yEnd = CELL_PADDING + (Game.getROWS() * (DISC_DIAMETER + DISC_SPACING));
//        int yEnd = (Game.getROWS() * (DISC_DIAMETER + DISC_SPACING));
        drawRoundedRectangle(g2d, CELL_PADDING, CELL_PADDING, xEnd, yEnd);
    }

    private void drawDisc(Graphics2D g2d, int x, int y, Color color) {
        g2d.setColor(color);
        g2d.fillOval(x, y, DISC_DIAMETER, DISC_DIAMETER);
    }

    private void drawColumnLines(Graphics2D g2d) {
        g2d.setColor(Color.BLACK);
//        int y = DISC_SPACING + (Game.getROWS() * (DISC_DIAMETER + DISC_SPACING));
        for (int i = 1; i < Game.getCOLS(); i++) {
//            int x = CELL_PADDING + (i * (DISC_DIAMETER + DISC_SPACING));
            int x = i * CELL_SIZE;
            g2d.drawLine(x, CELL_PADDING, x, BOARD_HEIGHT);
        }
    }

    private void drawRoundedRectangle(Graphics2D g2d, int x, int y, int width, int height) {
        g2d.setColor(Color.BLACK);
        g2d.drawRoundRect(x, y, width, height, 10, 10);
    }

    public static int getBoardWidth() {
        return BOARD_WIDTH;
    }

    public static int getBoardHeight() {
        return BOARD_HEIGHT;
    }

    static int getCellSize() {
        return CELL_SIZE;
    }
}

