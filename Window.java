import javax.swing.*;

public class Window extends JFrame {
    public Window(){
        setTitle("Chess");
        setSize(640,640);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        add(new ChessBoard());
        pack();
        setVisible(true);
    }

    public static void main(String[] args) {
        new Window();
    }
}
