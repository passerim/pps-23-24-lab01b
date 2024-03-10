package e2;

import javax.swing.*;
import javax.swing.event.MouseInputAdapter;
import javax.swing.event.MouseInputListener;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.io.Serial;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class GUI extends JFrame {

    @Serial
    private static final long serialVersionUID = -6218820567019985015L;
    private static final String EMPTY_CELL = "";
    private static final String FLAG_SYMBOL = "F";
    private static final String MINE_SYMBOL = "*";
    private final Map<JButton, Pair<Integer, Integer>> buttons = new HashMap<>();
    private final Logics logics;

    public GUI(int size, int mines) {
        this.logics = new LogicsImpl(size, mines);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(100 * size, 100 * size);
        JPanel panel = new JPanel(new GridLayout(size, size));
        this.getContentPane().add(BorderLayout.CENTER, panel);
        ActionListener onClick = (event) -> {
            final JButton jbutton = (JButton) event.getSource();
            final Pair<Integer, Integer> position = buttons.get(jbutton);
            boolean aMineWasFound = logics.aMineWasFound(position); // call the logic to tell a cell has been selected
            if (aMineWasFound) {
                quitGame();
                JOptionPane.showMessageDialog(this, "You lost!!");
            } else {
                drawBoard();
            }
            boolean isThereVictory = logics.isThereVictory(); // call the logic to ask if there is victory
            if (isThereVictory) {
                quitGame();
                JOptionPane.showMessageDialog(this, "You won!!");
                System.exit(0);
            }
        };
        MouseInputListener onRightClick = new MouseInputAdapter() {
            @Override
            public void mouseClicked(MouseEvent event) {
                if (!SwingUtilities.isRightMouseButton(event)) {
                    return;
                }
                final JButton jbutton = (JButton) event.getSource();
                if (jbutton.isEnabled()) {
                    final Pair<Integer, Integer> position = buttons.get(jbutton);
                    logics.toggleFlag(position); // call the logic here to put/remove a flag
                }
                drawBoard();
            }
        };
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                final JButton jbutton = new JButton(EMPTY_CELL);
                jbutton.addActionListener(onClick);
                jbutton.addMouseListener(onRightClick);
                this.buttons.put(jbutton, new Pair<>(i, j));
                panel.add(jbutton);
            }
        }
        this.drawBoard();
        this.setVisible(true);
    }

    private void quitGame() {
        this.drawBoard();
        this.buttons.forEach((button, position) -> {
            if (logics.isThereMine(position)) {
                button.setText(MINE_SYMBOL); // button is a mine, draw it "*"
                button.setEnabled(false); // disable the button
            }
            Arrays.stream(button.getActionListeners()).forEach(button::removeActionListener);
            Arrays.stream(button.getMouseListeners()).forEach(button::removeMouseListener);
        });
    }

    private void drawBoard() {
        this.buttons.forEach((button, position) -> {
            if (logics.isThereCounter(position)) {
                button.setText(String.valueOf(logics.getCounter(position))); // button has a counter, put the number
                button.setEnabled(false);
            } else if (logics.isThereFlag(position)) {
                button.setText(FLAG_SYMBOL); // button has a flag, put the flag
            } else {
                button.setText(EMPTY_CELL);
            }
        });
    }
}
