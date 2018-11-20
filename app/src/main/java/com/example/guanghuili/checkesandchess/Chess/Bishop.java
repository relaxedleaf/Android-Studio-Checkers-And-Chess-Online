package com.example.guanghuili.checkesandchess.Chess;

//import Piece;

import java.util.ArrayList;
import java.util.List;

public class Bishop extends Piece {
    public Bishop(Boolean isBlack, int row, int column) {
        super(isBlack, row, column, 8);
    }

    @Override
    public List getMoves(Piece[][] board) {
        List<Point> moves = new ArrayList<>();

        //up right
        int tempRow = row;
        int tempColumn = column;
        for (int i = row;;i++){
            
        }

    }
}
