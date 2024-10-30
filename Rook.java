import javax.swing.*;
import java.awt.*;

public class Rook extends Pieces {
    private Image Rook;
    private static final int TILE=80;

    public Rook(int color) {
        super(new Color(color));

        String path=(color==0xFFFFFF) ? "/Users/ibragimgadirov/IdeaProjects/Projects/src/PiecesImage/WhiteRook.png" :"/Users/ibragimgadirov/IdeaProjects/Projects/src/PiecesImage/BlackRook.png";

        Rook=new ImageIcon(path).getImage();

    }



    @Override
    public void draw(Graphics g, int x, int y) {
        g.drawImage(Rook,x,y,TILE,TILE,null);
    }

    @Override
    public String getType() {
        return "Rook";
    }
}
