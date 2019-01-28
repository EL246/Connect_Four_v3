package main.java.connect4.gui;

import main.java.connect4.game.GameController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/* Graphics for the Connect Four game */

public class ConnectFourFrame extends JFrame {
    private final GameController gameController;
    private final BoardGraphic connectFourCanvas;
    private JLabel status;

    public ConnectFourFrame() {
        super();
        gameController = new GameController(this);
        connectFourCanvas = new BoardGraphic(gameController.getGame());

        addBoardMouseListener();

        JPanel buttonPane = createButtonPane();
        JPanel statusPane = createStatusPane();

        add(connectFourCanvas, BorderLayout.CENTER);
        add(statusPane, BorderLayout.SOUTH);
        add(buttonPane, BorderLayout.NORTH);
        pack();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Connect-Four");
        setVisible(true);
        setResizable(false);
    }

    public void alert(String message) {
        status.setText(message);
        this.repaint();
    }

    private JPanel createStatusPane() {
        JPanel statusPane = new JPanel();
        status = new JLabel("Welcome to Connect Four! Press \"New Game\" to start");
        status.setAlignmentX(Component.CENTER_ALIGNMENT);
        statusPane.setBackground(Color.WHITE);
        statusPane.add(status);
        statusPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        return statusPane;
    }

    private JPanel createButtonPane() {
        JButton newGameButton = new JButton("New Game");
        newGameButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        newGameButton.addActionListener(e -> gameController.startNewGame());

        JPanel buttonPane = new JPanel();
        buttonPane.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
        buttonPane.add(newGameButton);
        return buttonPane;
    }

    private void addBoardMouseListener() {
        connectFourCanvas.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int mouseX = e.getX();
                int colSelected = mouseX / BoardGraphic.getCellSize();
                gameController.updateColSelected(colSelected);
            }
        });
    }
}