package com.example.guanghuili.checkesandchess.Chess;

import java.util.List;

public abstract class Piece implements Cloneable {
    int moveDistance,row, column;
    //int[][] moveDirection = new int[3][3];
    boolean isBlack;


    public Piece(Boolean isBlack, int row, int column){
        //this.moveDistance = moveDistance;
        this.row = row;
        this.column = column;
        this.isBlack = isBlack;
    }

    public boolean isBlack(){
        return isBlack;
    }

    public Piece getcopy() throws CloneNotSupportedException
    {
        return (Piece) this.clone();
    }

    public abstract List<Point> getMoves(Piece[][] board);
}
