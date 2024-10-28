import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class GameFrame extends JFrame implements ActionListener {
    JButton restart; /* Clears screen and sets color back to white */
    JButton color;  /* Changes color to user selected color */
    JButton clear;  /* Clears screen and retains color */
    JButton save; /* Saves the screen and puts it in a jpg */
    JTextField newGridSize; /* Takes in entry for number of squares in row of grid */
    JTextField squareSize; /* Takes in entry for square length */
    SquarePanel panel;
    JPanel newSize; /* Panel that contains numSquares and squareSize */
    boolean restarted;
    MouseApplication curr;
    public GameFrame() {
        new GameFrame("New Grid", curr);
    }
    public GameFrame(String title, MouseApplication app) {
        this.curr = app;
        restarted = false;
        setTitle(title + " " + System.getProperty("user.dir"));
        JPanel bottomPanel = new JPanel();

        /** Create input panel for resizing */

        newGridSize = new JTextField(3);
        squareSize = new JTextField(3);
        newSize = new JPanel();
        newSize.add(new JLabel("x"));
        newSize.add(newGridSize);
        newSize.add(Box.createHorizontalStrut(15)); /* a spacer */
        newSize.add(new JLabel("y"));
        newSize.add(squareSize);

        /** Add Restart button */

        restart = new JButton("Restart");
        restart.addActionListener(this);
        restart.setActionCommand("Restart Pressed");
        bottomPanel.add(restart);

        /** Add Color Button */
        color = new JButton("Color");
        color.addActionListener(this);
        color.setActionCommand("Color Pressed");
        bottomPanel.add(color);

        /** Add Save button */
        save = new JButton("Save");
        save.addActionListener(this);
        save.setActionCommand("Save Pressed");
        bottomPanel.add(save);

        /** Add Clear Button */
        clear = new JButton("Clear");
        clear.addActionListener(this);
        clear.setActionCommand("Clear Pressed");
        bottomPanel.add(clear);


        super.add(bottomPanel, BorderLayout.PAGE_END);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand();
        if (action.equals("Restart Pressed")) {
            if (!panel.saved) {
                int option = JOptionPane.showConfirmDialog(this, "You are about to refresh your screen and reset it to a blank screen.. Would you like to save?");
                switch (option) {
                    case JOptionPane.YES_OPTION:
                        save();
                        int result = JOptionPane.showConfirmDialog(null, newSize,
                                "Please Enter X and Y Values", JOptionPane.OK_CANCEL_OPTION);
                        if (result == JOptionPane.OK_OPTION) {
                            curr.oldFrame = this;
                            curr.resize(Integer.valueOf(newGridSize.getText()), Integer.valueOf(squareSize.getText()), Color.BLACK);
                        }
                        break;

                    case JOptionPane.NO_OPTION:
                        int result2 = JOptionPane.showConfirmDialog(null, newSize,
                                "Please Enter X and Y Values", JOptionPane.OK_CANCEL_OPTION);
                        if (result2 == JOptionPane.OK_OPTION) {
                            curr.oldFrame = this;
                            if (newGridSize.getText().equals(new String("")) || (squareSize.getText().equals(new String("")))) {
                                curr.resize(Color.BLACK);
                            } else {
                                curr.resize(Integer.valueOf(newGridSize.getText()), Integer.valueOf(squareSize.getText()), Color.BLACK);
                            }
                        }
                        panel.refresh();
                        panel.color = Color.WHITE;
                        resetTitle();
                        break;

                    case JOptionPane.CANCEL_OPTION:
                    case JOptionPane.CLOSED_OPTION:
                        break;
                }
            }else {
                int result = JOptionPane.showConfirmDialog(this,
                        "Are you sure you wish to restart?",null, JOptionPane.YES_NO_OPTION);
                if (result == JOptionPane.YES_OPTION) {
                    int result3 = JOptionPane.showConfirmDialog(null, newSize,
                            "Please Enter X and Y Values", JOptionPane.OK_CANCEL_OPTION);
                    if (result3 == JOptionPane.OK_OPTION) {
                        curr.oldFrame = this;
                        curr.resize(Integer.valueOf(newGridSize.getText()), Integer.valueOf(squareSize.getText()), Color.BLACK);
                    }
                    panel.refresh();
                    panel.color = Color.WHITE;
                    resetTitle();
                }

            }
        } else if (action.equals("Color Pressed")) {
            Color color = JColorChooser.showDialog(this,
                    "Select a color", panel.color);

            panel.color = color;
        } else if (action.equals("Clear Pressed")) {
            panel.refresh();
        } else if (action.equals("Save Pressed")) {
            save();
        }
    }
    private void save() {
        try {
            panel.save();
            resetTitle();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    private void resetTitle() {
        String title = this.getTitle();
        if (title.charAt(title.length() - 1) == '*' && panel.saved) {
            title = title.substring(0, title.length() - 1);
        }
        this.setTitle(title);
    }
    @Override
    public Component add(Component comp) {
        panel = (SquarePanel) comp;
        return super.add(comp);
    }


}
