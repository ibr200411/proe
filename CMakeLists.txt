cmake_minimum_required(VERSION 3.29)
project(Project)

set(CMAKE_CXX_STANDARD 20)

add_library(Project SHARED
        Main.cpp
        Board.cpp
        Board.h
        Piece.h
        Piece.h
        ChessBoard.h
        Piece.h)
target_include_directories(Project PRIVATE "/Library/Java/JavaVirtualMachines/jdk-23.jdk/Contents/Home/include")
target_include_directories(Project PRIVATE "/Library/Java/JavaVirtualMachines/jdk-23.jdk/Contents/Home/include/darwin")

