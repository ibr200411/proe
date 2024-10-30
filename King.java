import javax.swing.*;
import java.awt.*;

public class King extends Pieces {

    private Image King;
    private static final int TILE =80 ;

    public King(int color) {
        super(new Color(color));

        String path=(color==0xFFFFFF) ? "/Users/ibragimgadirov/IdeaProjects/Projects/src/PiecesImage/WhiteKing.png":"/Users/ibragimgadirov/IdeaProjects/Projects/src/PiecesImage/BlackKing.png";

        King=new ImageIcon(path).getImage();
    }


    @Override
    public void draw(Graphics g, int x, int y) {
        g.drawImage(King,x,y,TILE,TILE,null);
    }

    @Override
    public String getType() {
        return "King";
    }
}
