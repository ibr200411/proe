import javax.swing.*;
import java.awt.*;

public class Knight extends Pieces {
    private Image Knight;
    private static final int TILE =80 ;
    public Knight(int color) {
        super(new Color(color));

        String path=(color==0xFFFFFF) ? "/Users/ibragimgadirov/IdeaProjects/Projects/src/PiecesImage/WhiteKnight.png":"/Users/ibragimgadirov/IdeaProjects/Projects/src/PiecesImage/BlackKnight.png";

        Knight=new ImageIcon(path).getImage();
    }


    @Override
    public void draw(Graphics g, int x, int y) {
        g.drawImage(Knight,x,y,TILE,TILE,null);
    }

    @Override
    public String getType() {
        return "Knight";
    }
}
