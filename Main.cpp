//
// Created by Ibragim Gadirov on 26.10.24.
//
#include "ChessBoard.h"
#include "Piece.h"
#include "jni.h"
#include "Board.h"

Board board;
JNIEXPORT void JNICALL Java_ChessBoard_initialize
        (JNIEnv *, jobject){
    board.initializeBoard();
}

JNIEXPORT jobject JNICALL Java_ChessBoard_getPieceAt
        (JNIEnv *env, jobject , jint row, jint col){
    Piece* piece=board.getPieceAt(row,col);
    if (piece== nullptr) return nullptr;

    jclass pieceClass;
    jmethodID constructor;
    jobject pieceObject;

    if (piece->getType()=="Pawn"){
        pieceClass=env->FindClass("Pawn");
        constructor=env->GetMethodID(pieceClass,"<init>","(I)V");
        jint color= (piece -> getColor()==WHITE) ? 0xFFFFFF :0x000000;
        pieceObject = env->NewObject(pieceClass,constructor,color);

    }
    if (piece->getType()=="Rook"){
        pieceClass=env->FindClass("Rook");
        constructor=env->GetMethodID(pieceClass,"<init>","(I)V");
        jint color=(piece->getColor()==WHITE) ? 0xFFFFFF:0x000000;
        pieceObject= env->NewObject(pieceClass,constructor,color);
    }
    if (piece->getType()=="Bishop"){
        pieceClass=env->FindClass("Bishop");
        constructor=env->GetMethodID(pieceClass,"<init>","(I)V");
        jint color=(piece->getColor()==WHITE) ? 0xFFFFFF:0x000000;
        pieceObject= env->NewObject(pieceClass,constructor,color);
    }
    if (piece->getType()=="King"){
        pieceClass=env->FindClass("King");
        constructor=env->GetMethodID(pieceClass,"<init>","(I)V");
        jint color=(piece->getColor()==WHITE) ? 0xFFFFFF:0x000000;
        pieceObject= env->NewObject(pieceClass,constructor,color);
    }
    if (piece->getType()=="Queen"){
        pieceClass=env->FindClass("Queen");
        constructor=env->GetMethodID(pieceClass,"<init>","(I)V");
        jint color=(piece->getColor()==WHITE) ? 0xFFFFFF:0x000000;
        pieceObject= env->NewObject(pieceClass,constructor,color);
    }
    if (piece->getType()=="Knight"){
        pieceClass=env->FindClass("Knight");
        constructor=env->GetMethodID(pieceClass,"<init>","(I)V");
        jint color=(piece->getColor()==WHITE) ? 0xFFFFFF:0x000000;
        pieceObject= env->NewObject(pieceClass,constructor,color);
    }
    return pieceObject;
}

JNIEXPORT jboolean JNICALL Java_ChessBoard_movePiece
        (JNIEnv *env, jobject, jint startRow, jint startCol, jint endRow, jint endCol){
    Piece* piece=board.getPieceAt(startRow,startCol);
    if (!piece) {
        return JNI_FALSE;
    }

    if (piece ->canMove(startRow,startCol,endRow,endCol)){
        if (board.movePieceAt(startRow,startCol,endRow,endCol)){
            return JNI_TRUE;
        }
    }
    return JNI_FALSE;
}
JNIEXPORT jboolean JNICALL Java_ChessBoard_isCheck
        (JNIEnv *, jobject, jint king){
    Color color=(king==0) ? WHITE:BLACK;
    return board.isCheck(color) ? JNI_TRUE:JNI_FALSE;
}
JNIEXPORT jboolean JNICALL Java_ChessBoard_isCheckMate
        (JNIEnv *, jobject, jint king){
    Color color=(king==0) ? WHITE:BLACK;
    return board.isCheckMate(color) ? JNI_TRUE:JNI_FALSE;
}