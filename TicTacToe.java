///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//
///**
// *
// * @author Nafis
// */

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class TicTacToe {

    static JFrame frame;
    static JPanel panel;
    static JButton[][] buttons;
    static boolean player1Turn;

   public static void main(String[] args) {
    // Create the frame
    JFrame frame = new JFrame("Tic-Tac-Toe");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(300, 300);

    // Create the panel
    JPanel panel = new JPanel();
    panel.setLayout(new GridLayout(3, 3));

    // Create the buttons
    buttons = new JButton[3][3];
    for (int i = 0; i < 3; i++) {
        for (int j = 0; j < 3; j++) {
            buttons[i][j] = new JButton("");
            buttons[i][j].addActionListener(new ButtonListener());
            panel.add(buttons[i][j]);
        }
    }

    // Add the panel to the frame
    frame.add(panel);

    // Make the frame visible
    frame.setVisible(true);
}


public static class ButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Get the source of the button click
            JButton button = (JButton) e.getSource();

            // Check if the button is already occupied
            if (!button.getText().equals("")) {
                JOptionPane.showMessageDialog(frame, "This space is already occupied!");
                return;
            }

            // Place the player's symbol on the button
            if (player1Turn) {
                button.setText("X");
            } else {
                button.setText("O");
            }
            player1Turn = !player1Turn;

            // Check for a win or draw
            if (checkWin("X")) {
                JOptionPane.showMessageDialog(frame, "Player 1 wins!");
                resetGame();
            } else if (checkWin("O")) {
                JOptionPane.showMessageDialog(frame, "Player 2 wins!");
                resetGame();
            } else if (isDraw()) {
                JOptionPane.showMessageDialog(frame, "It's a draw!");
                resetGame();
            }
        }
    }
public static boolean checkWin(String symbol) {
    // Check rows
    for (int i = 0; i < 3; i++) {
        if (buttons[i][0].getText().equals(symbol) && buttons[i][1].getText().equals(symbol) && buttons[i][2].getText().equals(symbol)) {
            return true;
        }
    }

    // Check columns
    for (int i = 0; i < 3; i++) {
        if (buttons[0][i].getText().equals(symbol) && buttons[1][i].getText().equals(symbol) && buttons[2][i].getText().equals(symbol)) {
            return true;
        }
    }

    // Check diagonals
    if (buttons[0][0].getText().equals(symbol) && buttons[1][1].getText().equals(symbol) && buttons[2][2].getText().equals(symbol)) {
        return true;
    }
    if (buttons[0][2].getText().equals(symbol) && buttons[1][1].getText().equals(symbol) && buttons[2][0].getText().equals(symbol)) {
        return true;
    }
    return false;
}


public static boolean isDraw() {
    for (int i = 0; i < 3; i++) {
        for (int j = 0; j < 3; j++) {
            if (buttons[i][j].getText().equals("")) {
                return false;
            }
        }
    }
    return true;
}

public static void resetGame() {
    for (int i = 0; i < 3; i++) {
        for (int j = 0; j < 3; j++) {
            buttons[i][j].setText("");
        }
    }
    player1Turn = true;
}
}

