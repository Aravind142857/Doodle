import java.awt.*;

public class Square {
    private int side;
    private int topLeftX;
    private int topLeftY;
    private Color color;
    public Square(int topLeftX, int topLeftY, int side, Color color) {
        this.side = side;
        this.topLeftX = topLeftX;
        this.topLeftY = topLeftY;
        this.color = color;
    }
    public int getSide() {
        return side;
    }
    public int getTopLeftX() {
        return topLeftX;
    }
    public int getTopLeftY() {
        return topLeftY;
    }
    public Color getColor() {
        return color;
    }
    public void setColor(Color nColor) {
        color = nColor;
    }
    public void draw(Graphics g) {
        g.setColor(color);
        g.fillRect(topLeftX, topLeftY, side, side);
    }
}
