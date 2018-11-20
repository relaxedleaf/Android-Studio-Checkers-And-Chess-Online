package com.example.guanghuili.checkesandchess.Chess;

public class Board {
    Piece[][] board = new Piece[8][8];
    public Board(){
        setup();
    }

    public void setup(){
        board[0][0] = new Rook(true,0,0);
        board[0][7] = new Rook(true,0,7);
        board[0][1] = new Knight(true,0,1);
        board[0][6] = new Knight(true,0,6);
        board
    }
}
