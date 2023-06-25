import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToeGUI extends JFrame implements ActionListener {
    private JButton[][] boardButtons;
    private boolean isPlayerX;
    private JLabel statusLabel;

    public TicTacToeGUI() {
        setTitle("Tic Tac Toe");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 300);
        setLayout(new BorderLayout());

        JPanel boardPanel = new JPanel(new GridLayout(3, 3));
        boardButtons = new JButton[3][3];
        isPlayerX = true;

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                boardButtons[row][col] = new JButton();
                boardButtons[row][col].setFont(new Font(Font.SANS_SERIF, Font.BOLD, 50));
                boardButtons[row][col].addActionListener(this);
                boardPanel.add(boardButtons[row][col]);
            }
        }

        add(boardPanel, BorderLayout.CENTER);

        statusLabel = new JLabel("Player X's turn");
        statusLabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 16));
        statusLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(statusLabel, BorderLayout.SOUTH);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton clickedButton = (JButton) e.getSource();

        if (clickedButton.getText().isEmpty()) {
            if (isPlayerX) {
                clickedButton.setText("X");
                statusLabel.setText("Player O's turn");
            } else {
                clickedButton.setText("O");
                statusLabel.setText("Player X's turn");
            }

            clickedButton.setEnabled(false);
            isPlayerX = !isPlayerX;

            if (checkWin()) {
                if (isPlayerX)
                    statusLabel.setText("Player X wins!");
                else
                    statusLabel.setText("Player O wins!");

                disableAllButtons();
            } else if (isBoardFull()) {
                statusLabel.setText("It's a tie!");
            }
        }
    }

    private boolean checkWin() {
        String[][] board = new String[3][3];

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                board[row][col] = boardButtons[row][col].getText();
            }
        }

        // Check rows
        for (int row = 0; row < 3; row++) {
            if (!board[row][0].isEmpty() && board[row][0].equals(board[row][1]) && board[row][0].equals(board[row][2])) {
                return true;
            }
        }

        // Check columns
        for (int col = 0; col < 3; col++) {
            if (!board[0][col].isEmpty() && board[0][col].equals(board[1][col]) && board[0][col].equals(board[2][col])) {
                return true;
            }
        }

        // Check diagonals
        if (!board[0][0].isEmpty() && board[0][0].equals(board[1][1]) && board[0][0].equals(board[2][2])) {
            return true;
        }

        if (!board[0][2].isEmpty() && board[0][2].equals(board[1][1]) && board[0][2].equals(board[2][0])) {
            return true;
        }

        return false;
    }

    private boolean isBoardFull() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (boardButtons[row][col].getText().isEmpty()) {
                    return false;
                }
            }
        }
        return true;
    }

    private void disableAllButtons() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                boardButtons[row][col].setEnabled(false);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TicTacToeGUI ticTacToeGUI = new TicTacToeGUI();
            ticTacToeGUI.setVisible(true);
        });
    }
}
