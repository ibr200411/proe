import javax.swing.*;
import java.awt.*;

public class Pawn extends Pieces {

    private Image Pawn;
    private static final int TILE=80;

    public Pawn(int color) {
        super(new Color(color));

        String path=(color==0xFFFFFF) ? "/Users/ibragimgadirov/IdeaProjects/Projects/src/PiecesImage/WhitePawn.png":"/Users/ibragimgadirov/IdeaProjects/Projects/src/PiecesImage/BlackPawn.png";


        Pawn=new ImageIcon(path).getImage();
    }

    @Override
    public void draw(Graphics g, int x, int y) {
        g.drawImage(Pawn,x,y,TILE,TILE,null);
    }

    @Override
    public String getType() {
        return "Pawn";
    }
}


