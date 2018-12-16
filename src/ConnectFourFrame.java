/* Two-Player Connect-Four Graphics */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

public class ConnectFourFrame extends JFrame {


    private Game game;
    private Board connectFourCanvas;
    private Random random;
    private Player redPlayer;
    private Player yellowPlayer;
    private Player currentPlayer;
    private JButton newGameButton;
    private JLabel status;
    private boolean yellowPlayerTurn;
    private boolean gameActive = false;

    ConnectFourFrame(Game game) throws HeadlessException {
        super();
        this.game = game;
        this.redPlayer = new Player(false, game);
        this.yellowPlayer = new Player(true, game);
        random = new Random();
        connectFourCanvas = new Board(game);


        connectFourCanvas.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
//                super.mouseClicked(e);
                int mouseX = e.getX();
                int colSelected = mouseX / connectFourCanvas.getCellSize();

                if (gameActive) {
                    try {
                        currentPlayer.makeMove(colSelected);
                        updateGame();
                    } catch (InvalidMoveException e1) {
                        alert(currentPlayer.toString() + " invalid move, try again");
                    }
                }
            }
        });


        newGameButton = new

                JButton("New Game");
        newGameButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        newGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startNewGame();
            }
        });

        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new

                BoxLayout(buttonPane, BoxLayout.X_AXIS));
        buttonPane.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
        buttonPane.add(Box.createHorizontalGlue());
        buttonPane.add(newGameButton);

        JPanel statusPane = new JPanel();
        status = new

                JLabel("");
        status.setAlignmentX(Component.CENTER_ALIGNMENT);
//        status.setAlignmentY(Component.BOTTOM_ALIGNMENT);
        statusPane.setBackground(Color.WHITE);
//        status.setBackground(Color.WHITE);
        statusPane.add(status);
        statusPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
//        statusPane.setMaximumSize(new Dimension(Board.getBoardWidth(),10));
//        status.setBorder(BorderFactory.createEmptyBorder(30,30,30,30));


//        Container contentPane = this.getContentPane();
//        contentPane.setLayout(new BoxLayout(contentPane,BoxLayout.Y_AXIS));
//        contentPane.setLayout(new BorderLayout());

//        setLayout(new BoxLayout(getContentPane(),BoxLayout.Y_AXIS));
        this.

                add(connectFourCanvas, BorderLayout.CENTER);
//        this.add(status,BorderLayout.SOUTH);
        this.

                add(statusPane, BorderLayout.SOUTH);
        this.

                add(buttonPane, BorderLayout.NORTH);
        this.

                pack();

        this.

                setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.

                setTitle("Connect-Four");
        this.

                setVisible(true);
        this.

                setResizable(false);
//        this.setSize(Board.getBoardWidth(),Board.getBoardHeight());

    }

    private void updateGame() {
        if (game.isWon()) {
            gameActive = false;
            alert("GAME OVER, " + currentPlayer + " is the winner!");
        } else if (game.isDraw()) {
            gameActive = false;
            alert("GAME OVER, " + currentPlayer + " is the winner!");
        } else {
            switchPlayer();
//            repaint();
        }
    }

    void startNewGame() {
        game.clear();
//        game.setState(GameState.ACTIVE);
        gameActive = true;
        yellowPlayerTurn = random.nextBoolean();
        currentPlayer = yellowPlayerTurn ? yellowPlayer : redPlayer;
        alert(currentPlayer.toString() + " it is your turn");
        try {
            currentPlayer.makeMove(1);
        } catch (InvalidMoveException e) {
            e.printStackTrace();
        }
//        this.repaint();
//        playGame();
    }

/*    void playGame() {
        do {


        } while (gameActive);
    }*/

    private void alert(String message) {
        status.setText(message);
        this.repaint();
    }

    private void switchPlayer() {
        yellowPlayerTurn = !yellowPlayerTurn;
        currentPlayer = yellowPlayerTurn ? yellowPlayer : redPlayer;
        alert(currentPlayer.toString() + " it is your turn");
    }
}

