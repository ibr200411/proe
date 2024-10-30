//
// Created by Ibragim Gadirov on 26.10.24.
//

#ifndef PROJECT_PIECE_H
#define PROJECT_PIECE_H
#include <string>

enum Color{
    WHITE,BLACK
};

class Piece{
protected:
    Color color;
public:
    Piece(Color color) : color(color) {}
    virtual ~Piece() = default;

    Color getColor() const { return color; }
    virtual std::string getType() const = 0;
    virtual bool canMove(int startRow, int startCol, int endRow, int endCol) const = 0;
    virtual std::string getImage() const = 0;
};

class Pawn:public Piece{
public:
    Pawn(Color color): Piece(color){}

    std::string getType() const override{
        return "Pawn";
    }
    bool canMove(int startRow, int startCol, int endRow, int endCol) const override {
        int direction = (color == WHITE) ? -1 : 1;

        if(startCol==endCol &&endRow==startRow+direction){
            return true;
        }

        if (startCol==endCol &&((color ==WHITE && startRow==6) || (color==BLACK && startRow==1)) &&(endRow==startRow+2*direction)){
            return true;
        }

        if (abs(startCol-endCol)==1 && endRow==startRow+direction){
            return true;
        }
        return false;
    }

    std::string getImage() const override{
        return (color==WHITE) ? "/Users/ibragimgadirov/CLionProjects/Project/Images/WhitePawn.png" :"/Users/ibragimgadirov/CLionProjects/Project/Images/BlackPawn.png";
    }
};
class Rook : public Piece {
public:
    Rook(Color color) : Piece(color) {}

    std::string getType() const override {
        return "Rook";
    }
    bool canMove(int startRow, int startCol, int endRow, int endCol) const override {
        return (startRow == endRow || startCol == endCol);

    }

    std::string getImage() const override {
        return (color == WHITE) ? "/Users/ibragimgadirov/CLionProjects/Project/Images/WhiteRook.png" : "/Users/ibragimgadirov/CLionProjects/Project/Images/BlackRook.png";
    }
};
class Bishop: public Piece{
public:
    Bishop(Color color) : Piece(color){}
    std::string getType() const override{
        return "Bishop";
    }

    bool canMove(int startRow, int startCol, int endRow, int endCol) const override{
        if(abs(startRow-endRow) == abs(startCol-endCol)){
            int rowDirect=(endRow>startRow) ? 1 : -1;
            int colDirect = (endCol>startCol) ? 1 : -1;

            int currRow=startRow+rowDirect;
            int currCol=startCol+colDirect;

            while (currRow!=endRow && currCol != endCol){
                currRow+=rowDirect;
                currCol+=colDirect;
            }
            return true;
        }
        return false;
    }

    std::string getImage() const override{
        return (color==WHITE) ? "/Users/ibragimgadirov/CLionProjects/Project/Images/WhiteBishop.png" :"/Users/ibragimgadirov/CLionProjects/Project/Images/BlackBishop.png";
    }
};
class Knight: public Piece{
public:
    Knight(Color color) : Piece(color){}

    std::string getType() const override{
        return "Knight";
    }
    bool canMove(int startRow,int startCol,int endRow,int endCol) const override{
        int rowDiff=abs(startRow-endRow);
        int collDiff= abs(startCol-endCol);

        return (rowDiff==2 && collDiff==1) ||(rowDiff==1 && collDiff==2);

    }
    std::string getImage() const override{
        return (color==WHITE) ? "/Users/ibragimgadirov/CLionProjects/Project/Images/WhiteKnight.png" :"/Users/ibragimgadirov/CLionProjects/Project/Images/BlackKnight.png";
    }
};
class Queen:public Piece{
public:
    Queen(Color color) : Piece(color){}
    std::string getType() const override{
        return "Queen";
    }

    bool canMove(int startRow,int startCol,int endRow,int endCol) const override{
        int rowwDiff=abs(startRow-endRow);
        int collDiff=abs(startCol-endCol);

        bool horiz=(startRow==endRow) && (startCol!=endCol);
        bool ver=(startCol==endCol) && (startRow!=endRow);
        bool diag=rowwDiff==collDiff;
        return horiz||ver||diag;
    }
    std::string getImage() const override{
        return (color==WHITE) ? "/Users/ibragimgadirov/CLionProjects/Project/Images/WhiteQueen.png" : "/Users/ibragimgadirov/CLionProjects/Project/Images/BlackQueen.png";
    }
};
class King:public Piece{
public:
    King(Color color): Piece(color){}

    std::string getType() const override{
        return "King";
    }

    bool canMove(int startRow,int startCol,int endRow,int endCol) const override{
        int rowDiff=abs(startRow-endRow);
        int collDiff=abs(startCol-endCol);

        return (rowDiff<=1 && collDiff<=1);
    }
    std::string getImage() const override{
        return (color==WHITE) ? "/Users/ibragimgadirov/CLionProjects/Project/Images/WhiteKing.png" :"/Users/ibragimgadirov/CLionProjects/Project/Images/BlackKing.png";
    }
};
#endif //PROJECT_PIECE_H
