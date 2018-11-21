package com.example.guanghuili.checkesandchess.Chess;

//import Piece;

import java.util.ArrayList;
import java.util.List;

public class Pawn extends Piece {

    Boolean firstMove;

    public Pawn(Boolean isBlack, int row, int column) {
        super(isBlack, row, column);
        firstMove = true;
    }

    @Override
    public List getMoves(Piece[][] board) {

        List<Point> moves = new ArrayList<>();

        if(this.isBlack()){
            if(board[row+1][column] == null){
                moves.add(new Point(row + 1, column));
                if(firstMove && board[row+2][column] == null){
                    moves.add(new Point(row + 2, column));
                }
            }
            if(column > 0) {
                if (board[row + 1][column - 1] != null) {
                    if (!(board[row + 1][column - 1].isBlack())) {
                        moves.add(new Point(row + 1, column - 1));
                    }
                }
            }
            if(column < 7) {
                if (board[row + 1][column + 1] != null) {
                    if (!(board[row + 1][column + 1].isBlack())) {
                        moves.add(new Point(row + 1, column + 1));
                    }
                }
            }
        }
        else{
            if(board[row-1][column] == null){
                moves.add(new Point(row - 1, column));
                if(firstMove && board[row-2][column] == null){
                    moves.add(new Point(row - 2, column));
                }
            }
            if(column > 0) {
                if (board[row - 1][column - 1] != null) {
                    if ((board[row - 1][column - 1].isBlack())) {
                        moves.add(new Point(row - 1, column - 1));
                    }
                }
            }
            if(column < 7) {
                if (board[row - 1][column + 1] != null) {
                    if ((board[row - 1][column + 1].isBlack())) {
                        moves.add(new Point(row - 1, column + 1));
                    }
                }
            }
        }

        return null;
    }
}
