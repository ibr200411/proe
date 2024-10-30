//
// Created by Ibragim Gadirov on 26.10.24.
//
#include "Piece.h"
#include "Board.h"
Board::Board():isWhiteTurn(true){
    for (auto & row : board) {
        for (auto & col : row) {
            col= nullptr;
        }
    }
    initializeBoard();
}
Board::~Board(){
    for (auto & row : board) {
        for (auto & col : row) {
            delete col;
        }
    }
}

bool Board::movePieceAt(int startRow, int startCol, int endRow, int endCol) {
    Piece* piece = getPieceAt(startRow, startCol);
    if (piece && ((isWhiteTurn && piece->getColor() == WHITE) || (!isWhiteTurn && piece->getColor() == BLACK))) {
        if (piece->canMove(startRow, startCol, endRow, endCol)) {
            Piece* targetPiece = getPieceAt(endRow, endCol);
            if (targetPiece != nullptr) {
                if (piece->getColor() != targetPiece->getColor()) {
                    delete targetPiece;
                } else {
                    return false;
                }
            }
            board[endRow][endCol] = piece;
            board[startRow][startCol] = nullptr;
            isWhiteTurn = !isWhiteTurn;
            return true;
        }
    }
    return false;
}



void Board::initializeBoard() {
    for (int i = 0; i < 8; ++i) {
        board[6][i]=new Pawn(WHITE);
        board[1][i]=new Pawn(BLACK);
    }
    board[7][0]=new Rook(WHITE);
    board[7][1]=new Knight(WHITE);
    board[7][2]=new Bishop(WHITE);
    board[7][3]=new Queen(WHITE);
    board[7][4]=new King(WHITE);
    board[7][5]=new Bishop(WHITE);
    board[7][6]=new Knight(WHITE);
    board[7][7]=new Rook(WHITE);

    board[0][0]=new Rook(BLACK);
    board[0][1]=new Knight(BLACK);
    board[0][2]=new Bishop(BLACK);
    board[0][3]=new Queen(BLACK);
    board[0][4]=new King(BLACK);
    board[0][5]=new Bishop(BLACK);
    board[0][6]=new Knight(BLACK);
    board[0][7]=new Rook(BLACK);

}
Piece* Board::getPieceAt(int row, int col) const {
    return board[row][col];
}

bool Board::isCheck(Color kingColor)  {
    Piece* king = nullptr;

    int kingRow=-1,kingCol=-1;

    for (int row = 0; row < 8; ++row) {
        for (int col = 0; col < 8; ++col) {
            Piece* piece= getPieceAt(row,col);
            if (piece && piece->getType()=="King" && piece->getColor()==kingColor){
                king=piece;
                kingRow=row;
                kingCol=col;
                break;
            }
        }
        if (king) break;
    }
    if (!king) return false;

    Color opponnet=(kingColor==WHITE) ?BLACK:WHITE;
    for (int row = 0; row < 8; ++row) {
        for (int col = 0; col < 8; ++col) {
            Piece* piece= getPieceAt(row,col);
            if (piece && piece->getColor()==opponnet){
                if (piece->canMove(row,col,kingRow,kingCol)){
                    return true;
                }
            }
        }
    }
    return false;
}
bool Board::isCheckMate(Color kingColor) {
    if (!isCheck(kingColor)) {
        return false;
    }
    for (int row = 0; row < 8; ++row) {
        for (int col = 0; col < 8; ++col) {
            Piece* piece= getPieceAt(row,col);
            if (piece && piece->getColor()==kingColor){
                for (int newR = 0; newR < 8; ++newR) {
                    for (int newC = 0; newC <8 ; ++newC) {
                        if (piece->canMove(row,col,newR,newC)){
                            Piece* capture=board[newR][newC];
                            board[newR][newC]=piece;
                            board[row][col]= nullptr;


                            bool stillCheck= isCheck(kingColor);

                            board[row][col]=piece;
                            board[newR][newC]=capture;
                            if (!stillCheck){
                                return false;
                            }
                        }
                    }
                }
            }
        }
    }

    return true;
}
