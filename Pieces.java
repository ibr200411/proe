import java.awt.*;

public abstract class Pieces {
    protected int color;
    public Pieces(Color color) {
        this.color = color.getRGB();
    }

    public abstract void draw(Graphics g,int x,int y);
    public abstract String getType();
    public int getColor() {
        return color;
    }

}
