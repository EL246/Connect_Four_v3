package main.java.connect4.gui;

import main.java.connect4.board.Cell;
import main.java.connect4.game.Game;

import javax.swing.*;
import java.awt.*;

/* Graphics for the Connect Four game board */

class BoardGraphic extends JPanel {
    private final Game game;

    /* Dimensions for Board */
    private static final int DISC_DIAMETER = 50;
    private static final int CELL_PADDING = DISC_DIAMETER / 4;
    private static final int DISC_SPACING = 2 * CELL_PADDING;

    private static final int CELL_SIZE = DISC_DIAMETER + DISC_SPACING;
    private static final int BOARD_WIDTH = CELL_SIZE * (Game.getNumCols());
    private static final int BOARD_HEIGHT = CELL_SIZE * (Game.getNumRows());

    BoardGraphic(Game game) {
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

        for (int c = 0; c < Game.getNumCols(); c++) {
            for (int r = 0; r < Game.getNumRows(); r++) {
                Cell current = game.getColumn(c).getCell(r);

                drawSlot(g2d, c, r, current);
            }
        }
        drawColumnLines(g2d);
    }

    private void drawSlot(Graphics2D g2d, int c, int r, Cell current) {
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

        int rowTrans = ((Game.getNumRows() - 1) - r);
        int x = CELL_PADDING + c * DISC_SPACING + (c * DISC_DIAMETER);
        int y = CELL_PADDING + rowTrans * DISC_SPACING + (rowTrans * DISC_DIAMETER);
        drawDisc(g2d, x, y, color);

        if (current.isHighlighted()) {
            drawHighlight(g2d, x, y);
        }
    }

    private void drawHighlight(Graphics2D g2d, int x, int y) {
        Stroke orig = g2d.getStroke();
        g2d.setColor(Color.PINK);
        g2d.setStroke(new BasicStroke(5));
        g2d.drawOval(x, y, DISC_DIAMETER, DISC_DIAMETER);
        g2d.setStroke(orig);
    }

    private void drawDisc(Graphics2D g2d, int x, int y, Color color) {
        g2d.setColor(color);
        g2d.fillOval(x, y, DISC_DIAMETER, DISC_DIAMETER);
    }

    private void drawColumnLines(Graphics2D g2d) {
        g2d.setColor(Color.BLACK);
        for (int i = 1; i < Game.getNumCols(); i++) {
            int x = i * CELL_SIZE;
            g2d.drawLine(x, CELL_PADDING, x, BOARD_HEIGHT);
        }
    }

    static int getCellSize() {
        return CELL_SIZE;
    }
}

