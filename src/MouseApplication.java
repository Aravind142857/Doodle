import javax.swing.*;
import java.awt.*;

public class MouseApplication {
    //gridSize = 450;
    //public JFrame window;
    // Max gridSize = 750;
    public static int gridSize = 750;
    public static int squareSize = 25;
    public static Color color = Color.BLACK;
    public JFrame oldFrame;
    public  MouseApplication(int gS, int sS, Color c) {
        gridSize = gS;
        squareSize = sS;
        color = c;
    }
    public void resize (int newGS, int newSS, Color newColor) {
        this.gridSize = newGS;
        this.squareSize = newSS;
        this.color = newColor;
        this.make();

    }
    public void resize(Color newColor) {
        this.color = newColor;
        this.make();
    }
    public void make() {
        JFrame window  = new GameFrame("Mouse", this);
        SquarePanel panel = new SquarePanel(this.gridSize, this.squareSize, this.color);
        window.add(panel);
        ClickListener listener = new ClickListener(panel);
        panel.addMouseListener(listener);
        panel.addMouseMotionListener(listener);
        window.setSize(this.gridSize, this.gridSize + 65);
        window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        window.setVisible(true);
        if (oldFrame != null) {
            oldFrame.dispose();
            System.out.println("Previous frame closed");
        }
    }
    public static void main(String[] args) {
        MouseApplication app  = new MouseApplication(gridSize, squareSize, color);
        /*JFrame window  = new GameFrame("Mouse", app);
        SquarePanel panel = new SquarePanel(app.gridSize, app.squareSize, app.color);
        window.add(panel);
        ClickListener listener = new ClickListener(panel);
        panel.addMouseListener(listener);
        panel.addMouseMotionListener(listener);
        window.setSize(gridSize, gridSize + 65);
        window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        window.setVisible(true);
        */
        app.make();


    }
}

/**
 * TODO: Shape?
 *         Save? --- [DONE] ADD user input saved location.
 *         New
 *         Rotation?
 *         Open? Too hard
 *          Has Checkbox, DesktopPane - Virtual Desktop, FileChooser, Password Field, JSeparator - split between groups, JSlider, JTable - table, JTextArea, JTextField, JTextPane, KeyStroke,
 *
 *          Create MS LOGO?
 */