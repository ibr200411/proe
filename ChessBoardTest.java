import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ChessBoardTest {
    protected ChessBoard board;

    static {
        System.loadLibrary("Project");
    }

    @BeforeEach
    public void setUp(){
        board = new ChessBoard();
        board.initialize();
    }
    @Test
    public void testBoard() {
        for (int col = 0; col < ChessBoard.BOARD_SIZE; col++) {
            assertNotNull(board.getPieceAt(0, col), "Expected piece at (0,"+col+")");
            assertNotNull(board.getPieceAt(1, col), "Expected piece at (1,"+col+")");
            assertNotNull(board.getPieceAt(6, col), "Expected piece at (6,"+col+")");
            assertNotNull(board.getPieceAt(7, col), "Expected piece at (7,"+col+")");


        }
        for (int row = 2; row < 6; row++) {
            for (int col = 0; col < ChessBoard.BOARD_SIZE; col++) {
                assertNull(board.getPieceAt(row,col),"Expected no piece at ("+row+" "+col+")");
            }
        }
    }
    @Test
    public void testValidMove(){
        Pieces WhitePawn=board.getPieceAt(6,1);
        assertNotNull(WhitePawn,"WhitePawn should be at (6,0)");
        Pieces BlackPawn=board.getPieceAt(1,0);
        assertNotNull(BlackPawn,"BlackPawn should be at (7,0)");


        boolean moved=board.movePiece(6,1,5,1);
        assertTrue(moved,"Pawn should be at (5,0)");
        assertNull(board.getPieceAt(6, 1), "WhitePawn should no longer be at (7, 1)");
        assertNotNull(board.getPieceAt(5, 1), "WhitePawn should be at (5, 2)");

        boolean moved1=board.movePiece(1,0,2,0);
        assertTrue(moved1,"Black Pawn should be at (2,0)");
        assertNull(board.getPieceAt(1, 0), "BlackPawn should no longer be at (1, 0)");
        assertNotNull(board.getPieceAt(2, 0), "BlackPawn should be at (2, 0)");





    }
    @Test
    public void testInvalidMove(){
        Pieces WhiteBishop=board.getPieceAt(7,2);
        assertNotNull(WhiteBishop,"Bishop should be at (7,2)");

        boolean move=board.movePiece(7,2,6,2);
        assertFalse(move,"The Bishop only can move diagonally");
    }
    @Test
    public void testCapture(){
        Pieces initial= board.getPieceAt(6,0);
        assertNotNull(initial,"White Pawn should be at (6,0)");
        Pieces target= board.getPieceAt(1,1);
        assertNotNull(target,"Black Pawn should be at (1,0)");

        boolean moved=board.movePiece(6,0,5,0);
        assertTrue(moved,"White Pawn move to the (5,0)");
        assertNull(board.getPieceAt(6,0),"The White Pawn shouldn't be at (6,0)");
        assertNotNull(board.getPieceAt(5,0),"The White Pawn shouldn be at (5,0)");

        boolean moved1=board.movePiece(1,1,2,1);
        assertTrue(moved1,"Black Pawn move to the (2,1)");
        assertNull(board.getPieceAt(1,1),"Black Pawn shouldn't be at (1,1)");
        assertNotNull(board.getPieceAt(2,1),"Black Pawn should be at (2,1)");

        boolean moved2=board.movePiece(5,0,4,0);
        assertTrue(moved2,"White Pawn move to the (4,0)");
        assertNull(board.getPieceAt(5,0),"The White Pawn shouldn't be at (5,0)");
        assertNotNull(board.getPieceAt(4,0),"The White Pawn shouldn be at (4,0)");

        boolean moved3=board.movePiece(2,1,3,1);
        assertTrue(moved3,"Black Pawn move to the (3,1)");
        assertNull(board.getPieceAt(2,1),"Black Pawn shouldn't be at (2,1)");
        assertNotNull(board.getPieceAt(3,1),"Black Pawn should be at (3,1)");

        boolean moved4=board.movePiece(4,0,3,1);
        assertTrue(moved4,"White Pawn move to the (3,1) for capturing");
        assertNull(board.getPieceAt(4,0),"The White Pawn shouldn't be at (4,0)");
        assertNotNull(board.getPieceAt(3,1),"The White Pawn captured Black Pawn at (3,1)");



    }
    @Test
    public void check() {
        
    }

    @Test
    public void checkMate(){}
}
