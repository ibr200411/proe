import javax.swing.*;
import java.awt.*;

public class Queen extends Pieces {

    private Image Queen;
    private static final int TILE =80 ;

    public Queen(int color) {
        super(new Color(color));

        String path=(color==0xFFFFFF)?"/Users/ibragimgadirov/IdeaProjects/Projects/src/PiecesImage/WhiteQueen.png":"/Users/ibragimgadirov/IdeaProjects/Projects/src/PiecesImage/BlackQueen.png";

        Queen=new ImageIcon(path).getImage();

    }

    @Override
    public void draw(Graphics g, int x, int y) {
        g.drawImage(Queen,x,y,TILE,TILE,null);
    }

    @Override
    public String getType() {
        return "Queen";
    }

}
