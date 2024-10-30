

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ChessBoard extends JPanel implements MouseListener, MouseMotionListener, KeyListener {
    protected static final int BOARD_SIZE=8;
    protected static final int TILE_SIZE=80;
    protected Pieces selectPiece;
    protected int selectedRow,selectedCol;
    protected boolean isDragging;
    protected int mouseX,mouseY;
    static {
        System.loadLibrary("Project");
    }
    public ChessBoard(){
        setPreferredSize(new Dimension(BOARD_SIZE*TILE_SIZE,BOARD_SIZE*TILE_SIZE));
        initialize();
        addMouseListener(this);
        addMouseMotionListener(this);
        addKeyListener(this);
        setFocusable(true);
    }


    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                if ((row + col) % 2 == 0) {
                    g.setColor(new Color(92,64,51));
                } else {
                    g.setColor(new Color(44, 67, 31));
                }
                g.fillRect(col * TILE_SIZE, row * TILE_SIZE, TILE_SIZE, TILE_SIZE);
                Pieces pieces=getPieceAt(row,col);
                if (pieces!=null && (row!=selectedRow || col!=selectedCol||!isDragging)){
                    pieces.draw(g,col*TILE_SIZE,row*TILE_SIZE);
                }
            }
        }
        if (isDragging && selectPiece!=null){
            selectPiece.draw(g,mouseX-TILE_SIZE/2,mouseY-TILE_SIZE/2);
        }
    }


    public native void initialize();
    public native Pieces getPieceAt(int row,int col);
    public native boolean movePiece(int startRow,int startCol,int endRow,int endCol);
    public native boolean isCheck(int kingColor);
    public native boolean isCheckMate(int kingColor);

    @Override
    public void keyPressed(KeyEvent e) {

        if (selectPiece!=null){
            int keyCode=e.getKeyCode();
            int newRow=selectedRow;
            int newCol=selectedCol;
            switch (keyCode){
                case KeyEvent.VK_LEFT:
                    newCol--;
                    break;
                case KeyEvent.VK_RIGHT:
                    newCol++;
                    break;
                case KeyEvent.VK_UP:
                    newRow--;
                    break;
                case KeyEvent.VK_DOWN:
                    newRow++;
                    break;
            }
            if (newRow>=0 && newRow<BOARD_SIZE && newCol>=0 && newCol<BOARD_SIZE){
                if (movePiece(selectedRow,selectedCol,newRow,newCol)){
                    Pieces move=getPieceAt(newRow,newCol);
                    if (move!=null){
                        int king = move.getColor();

                        boolean check=isCheck(king);
                        if (check){
                            JOptionPane.showMessageDialog(null,"The " + (king == 0 ? "White" : "Black") + " king is in check!");
                        }
                        if (isCheckMate(king)){
                            JOptionPane.showMessageDialog(null, "Checkmate! The game is over.");
                            endGame();
                        }

                    }
                    selectedRow = newRow;
                    selectedCol = newCol;
                    repaint();
                }

            }


        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        int col1=e.getX()/TILE_SIZE;
        int row1=e.getY()/TILE_SIZE;

        Pieces pieces=getPieceAt(row1,col1);
        if (pieces!=null){
            selectPiece=pieces;
            selectedRow=row1;
            selectedCol=col1;
            isDragging=true;
            mouseX=e.getX();
            mouseY=e.getY();
            repaint();
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (isDragging) {
            int col2 = e.getX() / TILE_SIZE;
            int row2 = e.getY() / TILE_SIZE;


            if (movePiece(selectedRow, selectedCol, row2, col2)) {

                Pieces moved = getPieceAt(row2, col2);
                if (moved != null) {

                    int kingColor = moved.getColor();

                    boolean checkStatus = isCheck(kingColor);


                    if (checkStatus) {
                        JOptionPane.showMessageDialog(null,"The " + (kingColor == 0 ? "White" : "Black") + " king is in check!");
                    }

                    if (isCheckMate(kingColor)){
                        JOptionPane.showMessageDialog(null, "Checkmate! The game is over.");
                        endGame();
                    }
                }

                selectPiece = null;
                isDragging = false;
                repaint();
            }
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (isDragging){
            mouseX=e.getX();
            mouseY=e.getY();
            repaint();
        }
    }

    private void endGame(){
        removeMouseListener(this);
        removeMouseMotionListener(this);
        removeKeyListener(this);
    }


    @Override
    public void keyTyped(KeyEvent e){}
    @Override
    public void mouseEntered(MouseEvent e){}
    @Override
    public void keyReleased(KeyEvent e){}
    @Override
    public void mouseExited(MouseEvent e){}
    @Override
    public void mouseClicked(MouseEvent e){}
    @Override
    public void mouseMoved(MouseEvent e){}
}

