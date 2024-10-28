import javax.swing.event.MouseInputAdapter;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class ClickListener extends MouseInputAdapter {
    //private boolean dragged = false;
    private SquarePanel panel;
    //private ArrayList<Point> squares = new ArrayList<>();

    public ClickListener(SquarePanel panel) {
        super();
        this.panel = panel;
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        Point p = new Point(x, y);
        panel.changeSquare(p);
    }
    @Override
    public void mousePressed(MouseEvent e) {
        //dragged.add(new Point(e.getX(),e.getY()));
        panel.changeSquare(new Point(e.getX(), e.getY()));
    }
    @Override
    public void mouseReleased(MouseEvent e) {
       // Point[] points = squares.toArray(new Point[squares.size()]);
        //for (Point p: points) {
        //    panel.changeSquare(p);
        //    System.out.println(p);
        //}
        //dragged = false;
        panel.changeSquare(new Point(e.getX(), e.getY()));
    }
    @Override
    public void mouseDragged(MouseEvent e) {
        //dragged = true;
        //squares.add(new Point(e.getX(), e.getY()));
        panel.changeSquare(new Point(e.getX(), e.getY()));

    }
    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
