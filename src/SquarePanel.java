import org.apache.commons.lang3.StringUtils;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class SquarePanel extends JPanel implements MouseMotionListener {
    private static int GridSize;
    private static int SquareSize;
    static Color color = Color.WHITE;
    private Color background;
    public static boolean refresh;
    public static boolean saved;
    private static HashMap<Point, Square> squares_ = new HashMap<>();
    public SquarePanel(int gridSize, int squareSize, Color c) {
        GridSize = gridSize;
        SquareSize = squareSize;
        background = c;
        createGrid(c);
        saved = false;
        /* Print all squares' starting points
        for (int i = 0; i < squares_.size(); i++) {
            System.out.println(squares_.keySet().toArray()[i]);
        }
        */
    }

    /*public boolean resize(int newGridSize, int newSquareSize, Color newColor) {
        try {
            new MouseApplication(newGridSize, newSquareSize, newColor);
            return true;
        } catch (Exception e) {
            return false;
        }

    }*/

    public void addSquare(Square square) {
        squares_.put(new Point (square.getTopLeftX(), square.getTopLeftY()), square);
        repaint();
    }

    public void createGrid(Color c) {
        for (int i = 0; i < (GridSize / SquareSize); i++) {
            for (int j = 0; j < (GridSize / SquareSize); j++) {
                addSquare(new Square(i * SquareSize, j * SquareSize, SquareSize, c));
            }
        }
    }
    public void changeSquare(Point p) {
        if (p.x < GridSize && p.y < GridSize && p.x >= 0 && p.y >= 0) {
            //Color c = squares_.get(new Point(p.x - (p.x % squareSize), p.y - (p.y % squareSize))).getColor();
            changeSquare(p, color);
        }
    }

    public void changeSquare(Point p, Color c) {
        if (p.x < GridSize && p.y < GridSize) {
            p = new Point(p.x - (p.x % SquareSize), p.y - (p.y % SquareSize));
            if (!squares_.get(p).getColor().equals(c)) {
                squares_.get(p).setColor(c);
                this.repaint();
                saved = false;
                JFrame frame = (GameFrame) SwingUtilities.getRoot(this);
                if (frame.getTitle().charAt(frame.getTitle().length() - 1) != '*') {
                    frame.setTitle(frame.getTitle() + "*");
                }
            }
        }
    }
    @Override
    public void paint(Graphics g) {
        for (int i = 0; i < squares_.size(); i++) {
            squares_.get(squares_.keySet().toArray()[i]).draw(g);
        }
    }

    public void refresh() {
        squares_ = new HashMap<>();
        createGrid(background);
        refresh = true;
        saved = true;
    }
    public void save() throws IOException {

        BufferedImage imagebuf = null;
        try {
            imagebuf = new Robot().createScreenCapture(this.getBounds());
        } catch (AWTException e1) {
            e1.printStackTrace();
        }
        Graphics2D graphics2D = imagebuf.createGraphics();
        this.paint(graphics2D);
        try {
            ImageIO.write(imagebuf,"jpeg", new File(findUnusedFileName()));
        } catch (Exception e) {
            System.out.println("error");
        }
        saved = true;
    }
    public String findUnusedFileName() {
        String filename = "save1.jpeg";
        File saveFile = new File(filename);
        int i = Integer.valueOf(StringUtils.substringBetween(filename,"save",".jpeg"));
        while (saveFile.exists()) {
            i++;
            filename = "save" + i + ".jpeg";
            saveFile = new File(filename);
        }
        return filename;
    }
    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        if (refresh) {
            System.out.println("To refresh");
            repaint();
        }
    }
}
