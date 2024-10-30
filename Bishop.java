import javax.swing.*;
import java.awt.*;

public class Bishop extends Pieces {
    private Image Bishop;
    private static final int TILE=80;
    public Bishop(int color) {
        super(new Color(color));
        String path=(color==0xFFFFFF) ? "/Users/ibragimgadirov/IdeaProjects/Projects/src/PiecesImage/WhiteBishop.png":"/Users/ibragimgadirov/IdeaProjects/Projects/src/PiecesImage/BlackBishop.png";
        Bishop=new ImageIcon(path).getImage();
    }


    @Override
    public void draw(Graphics g, int x, int y) {
        g.drawImage(Bishop,x,y,TILE,TILE,null);
    }

    @Override
    public String getType() {
        return "Bishop";
    }
}
