//
// Created by Ibragim Gadirov on 26.10.24.
//

#ifndef PROJECT_BOARD_H
#define PROJECT_BOARD_H
#include "Piece.h"
#include <vector>

class Board{
    Piece* board[8][8];
    bool isWhiteTurn;

public:
    Board();
    ~Board();

    void initializeBoard();
    Piece* getPieceAt(int row,int col) const;
    bool movePieceAt(int startRow,int startCol,int endRow,int endCol);
    bool isCheck(Color kingColor) ;
    bool isCheckMate(Color kingColor);
};
#endif //PROJECT_BOARD_H
