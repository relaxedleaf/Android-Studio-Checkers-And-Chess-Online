package com.example.guanghuili.checkesandchess.Chess;

import java.util.List;

public class Piece{
    protected int moveDistance;
    protected int row;
    protected int column;
    protected String type;
    protected boolean isBlack;
    protected boolean firstMove;


    public Piece(Boolean isBlack, int row, int column, String type){
        //this.moveDistance = moveDistance;
        this.row = row;
        this.column = column;
        this.isBlack = isBlack;
        this.type = type;
    }

    public Piece(Boolean isBlack, int row, int column, Boolean firstMove, String type){
        //this.moveDistance = moveDistance;
        this.row = row;
        this.column = column;
        this.isBlack = isBlack;
        this.firstMove = firstMove;
        this.type = type;
    }

    public Piece(String type){
        this.type = type;
    }

    public boolean isBlack(){
        return isBlack;
    }


    public List<Point> getMoves(Piece[][] board){
        return null;
    };

    public void setRow(int row) {
        this.row = row;
    }

    public void setColumn(int column) {
        this.column = column;
    }
    public boolean isFirstMove() {
        return firstMove;
    }

    public void setFirstMove(boolean firstMove) {
        this.firstMove = firstMove;
    }
}
