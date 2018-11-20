package com.example.guanghuili.checkesandchess.Chess;

//import Piece;

import java.util.ArrayList;
import java.util.List;

public class Pawn extends Piece {

    Boolean firstMove;

    public Pawn(Boolean isBlack, int row, int column) {
        super(isBlack, row, column, 1);
        firstMove = true;
    }

    @Override
    public List getMoves(Piece[][] board) {

        List<Point> moves = new ArrayList<>();

        if(this.isBlack()){
            if(firstMove){

            }
        }


        return null;
    }
}
