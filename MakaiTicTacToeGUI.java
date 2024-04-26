import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MakaiTicTacToeGUI extends JFrame implements ActionListener {
    private JButton[][] buttons;
    private JLabel statusLabel;
    private char currentPlayer;
    private boolean gameOver;

    public MakaiTicTacToeGUI() {
        setTitle("Tic Tac Toe");
        setSize(300, 300);
        setLayout(new BorderLayout());

        JPanel gridPanel = new JPanel();
        gridPanel.setLayout(new GridLayout(3, 3));
        add(gridPanel, BorderLayout.CENTER);

        buttons = new JButton[3][3];
        currentPlayer = 'X';
        gameOver = false;

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                buttons[row][col] = new JButton();
                buttons[row][col].setFont(new Font(Font.SANS_SERIF, Font.BOLD, 50));
                buttons[row][col].addActionListener(this);
                gridPanel.add(buttons[row][col]);
            }
        }

        JPanel statusPanel = new JPanel();
        statusLabel = new JLabel("Player X's turn");
        statusPanel.add(statusLabel);
        add(statusPanel, BorderLayout.SOUTH);

        JButton newGameButton = new JButton("New Game");
        newGameButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                resetGame();
            }
        });
        add(newGameButton, BorderLayout.NORTH);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (gameOver)
            return;

        JButton clickedButton = (JButton) e.getSource();

        if (clickedButton.getText().equals("")) {
            clickedButton.setText(String.valueOf(currentPlayer));
            if (checkWin()) {
                statusLabel.setText("Player " + currentPlayer + " wins!");
                gameOver = true;
            } else if (checkDraw()) {
                statusLabel.setText("It's a draw!");
                gameOver = true;
            } else {
                currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
                statusLabel.setText("Player " + currentPlayer + "'s turn");
            }
        }
    }

    private boolean checkWin() {
        for (int i = 0; i < 3; i++) {
            if (buttons[i][0].getText().equals(String.valueOf(currentPlayer))
                    && buttons[i][1].getText().equals(String.valueOf(currentPlayer))
                    && buttons[i][2].getText().equals(String.valueOf(currentPlayer)))
                return true;
            if (buttons[0][i].getText().equals(String.valueOf(currentPlayer))
                    && buttons[1][i].getText().equals(String.valueOf(currentPlayer))
                    && buttons[2][i].getText().equals(String.valueOf(currentPlayer)))
                return true;
        }
        if (buttons[0][0].getText().equals(String.valueOf(currentPlayer))
                && buttons[1][1].getText().equals(String.valueOf(currentPlayer))
                && buttons[2][2].getText().equals(String.valueOf(currentPlayer)))
            return true;
        if (buttons[0][2].getText().equals(String.valueOf(currentPlayer))
                && buttons[1][1].getText().equals(String.valueOf(currentPlayer))
                && buttons[2][0].getText().equals(String.valueOf(currentPlayer)))
            return true;
        return false;
    }

    private boolean checkDraw() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (buttons[i][j].getText().equals(""))
                    return false;
            }
        }
        return true;
    }

    private void resetGame() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText("");
            }
        }
        currentPlayer = 'X';
        statusLabel.setText("Player X's turn");
        gameOver = false;
    }

    public static void main(String[] args) {
        // Tic Tac Toe is fun!
        new MakaiTicTacToeGUI();
    }
}
