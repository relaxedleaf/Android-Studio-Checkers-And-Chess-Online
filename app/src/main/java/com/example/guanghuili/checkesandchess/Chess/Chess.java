package com.example.guanghuili.checkesandchess.Chess;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.guanghuili.checkesandchess.R;

import java.util.ArrayList;
import java.util.List;


public class Chess extends AppCompatActivity implements View.OnClickListener{

    Boolean firstClick;
    Piece[][] board;
    List<Point> moves;
    Point start;

    Boolean isBlacksTurn;


    //row 1
    private ImageButton ibtn_0_0;
    private ImageButton ibtn_0_1;
    private ImageButton ibtn_0_2;
    private ImageButton ibtn_0_3;
    private ImageButton ibtn_0_4;
    private ImageButton ibtn_0_5;
    private ImageButton ibtn_0_6;
    private ImageButton ibtn_0_7;
    //row 2
    private ImageButton ibtn_1_0;
    private ImageButton ibtn_1_1;
    private ImageButton ibtn_1_2;
    private ImageButton ibtn_1_3;
    private ImageButton ibtn_1_4;
    private ImageButton ibtn_1_5;
    private ImageButton ibtn_1_6;
    private ImageButton ibtn_1_7;
    //row 3
    private ImageButton ibtn_2_0;
    private ImageButton ibtn_2_1;
    private ImageButton ibtn_2_2;
    private ImageButton ibtn_2_3;
    private ImageButton ibtn_2_4;
    private ImageButton ibtn_2_5;
    private ImageButton ibtn_2_6;
    private ImageButton ibtn_2_7;
    //row 4
    private ImageButton ibtn_3_0;
    private ImageButton ibtn_3_1;
    private ImageButton ibtn_3_2;
    private ImageButton ibtn_3_3;
    private ImageButton ibtn_3_4;
    private ImageButton ibtn_3_5;
    private ImageButton ibtn_3_6;
    private ImageButton ibtn_3_7;

    //row 5
    private ImageButton ibtn_4_0;
    private ImageButton ibtn_4_1;
    private ImageButton ibtn_4_2;
    private ImageButton ibtn_4_3;
    private ImageButton ibtn_4_4;
    private ImageButton ibtn_4_5;
    private ImageButton ibtn_4_6;
    private ImageButton ibtn_4_7;

    //row 6
    private ImageButton ibtn_5_0;
    private ImageButton ibtn_5_1;
    private ImageButton ibtn_5_2;
    private ImageButton ibtn_5_3;
    private ImageButton ibtn_5_4;
    private ImageButton ibtn_5_5;
    private ImageButton ibtn_5_6;
    private ImageButton ibtn_5_7;

    //row 7
    private ImageButton ibtn_6_0;
    private ImageButton ibtn_6_1;
    private ImageButton ibtn_6_2;
    private ImageButton ibtn_6_3;
    private ImageButton ibtn_6_4;
    private ImageButton ibtn_6_5;
    private ImageButton ibtn_6_6;
    private ImageButton ibtn_6_7;

    //row 8
    private ImageButton ibtn_7_0;
    private ImageButton ibtn_7_1;
    private ImageButton ibtn_7_2;
    private ImageButton ibtn_7_3;
    private ImageButton ibtn_7_4;
    private ImageButton ibtn_7_5;
    private ImageButton ibtn_7_6;
    private ImageButton ibtn_7_7;

    private ImageButton[][] buttons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chess);



        //row 1
        ibtn_0_0 = findViewById(R.id.ibtn_0_0);
        buttons[0][0] = ibtn_0_0;

        ibtn_0_1 = findViewById(R.id.ibtn_0_1);
        buttons[0][1] = ibtn_0_1;

        ibtn_0_2 = findViewById(R.id.ibtn_0_2);
        buttons[0][2] = ibtn_0_2;

        ibtn_0_3 = findViewById(R.id.ibtn_0_3);
        buttons[0][3] = ibtn_0_3;

        ibtn_0_4 = findViewById(R.id.ibtn_0_4);
        buttons[0][4] = ibtn_0_4;

        ibtn_0_5 = findViewById(R.id.ibtn_0_5);
        buttons[0][5] = ibtn_0_5;

        ibtn_0_6 = findViewById(R.id.ibtn_0_6);
        buttons[0][6] = ibtn_0_6;

        ibtn_0_7 = findViewById(R.id.ibtn_0_7);
        buttons[0][7] = ibtn_0_7;


//row 2
        ibtn_1_0 = findViewById(R.id.ibtn_1_0);
        buttons[1][0] = ibtn_1_0;

        ibtn_1_1 = findViewById(R.id.ibtn_1_1);
        buttons[1][1] = ibtn_1_1;

        ibtn_1_2 = findViewById(R.id.ibtn_1_2);
        buttons[1][2] = ibtn_1_2;

        ibtn_1_3 = findViewById(R.id.ibtn_1_3);
        buttons[1][3] = ibtn_1_3;

        ibtn_1_4 = findViewById(R.id.ibtn_1_4);
        buttons[1][4] = ibtn_1_4;

        ibtn_1_5 = findViewById(R.id.ibtn_1_5);
        buttons[1][5] = ibtn_1_5;

        ibtn_1_6 = findViewById(R.id.ibtn_1_6);
        buttons[1][6] = ibtn_1_6;

        ibtn_1_7 = findViewById(R.id.ibtn_1_7);
        buttons[1][7] = ibtn_1_7;


//row 3
        ibtn_2_0 = findViewById(R.id.ibtn_2_0);
        buttons[2][0] = ibtn_2_0;

        ibtn_2_1 = findViewById(R.id.ibtn_2_1);
        buttons[2][1] = ibtn_2_1;

        ibtn_2_2 = findViewById(R.id.ibtn_2_2);
        buttons[2][2] = ibtn_2_2;

        ibtn_2_3 = findViewById(R.id.ibtn_2_3);
        buttons[2][3] = ibtn_2_3;

        ibtn_2_4 = findViewById(R.id.ibtn_2_4);
        buttons[2][4] = ibtn_2_4;

        ibtn_2_5 = findViewById(R.id.ibtn_2_5);
        buttons[2][5] = ibtn_2_5;

        ibtn_2_6 = findViewById(R.id.ibtn_2_6);
        buttons[2][6] = ibtn_2_6;

        ibtn_2_7 = findViewById(R.id.ibtn_2_7);
        buttons[2][7] = ibtn_2_7;

//row 4
        ibtn_3_0 = findViewById(R.id.ibtn_3_0);
        buttons[3][0] = ibtn_3_0;

        ibtn_3_1 = findViewById(R.id.ibtn_3_1);
        buttons[3][1] = ibtn_3_1;

        ibtn_3_2 = findViewById(R.id.ibtn_3_2);
        buttons[3][2] = ibtn_3_2;

        ibtn_3_3 = findViewById(R.id.ibtn_3_3);
        buttons[3][3] = ibtn_3_3;

        ibtn_3_4 = findViewById(R.id.ibtn_3_4);
        buttons[3][4] = ibtn_3_4;

        ibtn_3_5 = findViewById(R.id.ibtn_3_5);
        buttons[3][5] = ibtn_3_5;

        ibtn_3_6 = findViewById(R.id.ibtn_3_6);
        buttons[3][6] = ibtn_3_6;

        ibtn_3_7 = findViewById(R.id.ibtn_3_7);
        buttons[3][7] = ibtn_3_7;


//row 5
        ibtn_4_0 = findViewById(R.id.ibtn_4_0);
        buttons[4][0] = ibtn_4_0;

        ibtn_4_1 = findViewById(R.id.ibtn_4_1);
        buttons[4][1] = ibtn_4_1;

        ibtn_4_2 = findViewById(R.id.ibtn_4_2);
        buttons[4][2] = ibtn_4_2;

        ibtn_4_3 = findViewById(R.id.ibtn_4_3);
        buttons[4][3] = ibtn_4_3;

        ibtn_4_4 = findViewById(R.id.ibtn_4_4);
        buttons[4][4] = ibtn_4_4;

        ibtn_4_5 = findViewById(R.id.ibtn_4_5);
        buttons[4][5] = ibtn_4_5;

        ibtn_4_6 = findViewById(R.id.ibtn_4_6);
        buttons[4][6] = ibtn_4_6;

        ibtn_4_7 = findViewById(R.id.ibtn_4_7);
        buttons[4][7] = ibtn_4_7;

//row 6
        ibtn_5_0 = findViewById(R.id.ibtn_5_0);
        buttons[5][0] = ibtn_5_0;

        ibtn_5_1 = findViewById(R.id.ibtn_5_1);
        buttons[5][1] = ibtn_5_1;

        ibtn_5_2 = findViewById(R.id.ibtn_5_2);
        buttons[5][2] = ibtn_5_2;

        ibtn_5_3 = findViewById(R.id.ibtn_5_3);
        buttons[5][3] = ibtn_5_3;

        ibtn_5_4 = findViewById(R.id.ibtn_5_4);
        buttons[5][4] = ibtn_5_4;

        ibtn_5_5 = findViewById(R.id.ibtn_5_5);
        buttons[5][5] = ibtn_5_5;

        ibtn_5_6 = findViewById(R.id.ibtn_5_6);
        buttons[5][6] = ibtn_5_6;

        ibtn_5_7 = findViewById(R.id.ibtn_5_7);
        buttons[5][7] = ibtn_5_7;

//row 7
        ibtn_6_0 = findViewById(R.id.ibtn_6_0);
        buttons[6][0] = ibtn_6_0;

        ibtn_6_1 = findViewById(R.id.ibtn_6_1);
        buttons[6][1] = ibtn_6_1;

        ibtn_6_2 = findViewById(R.id.ibtn_6_2);
        buttons[6][2] = ibtn_6_2;

        ibtn_6_3 = findViewById(R.id.ibtn_6_3);
        buttons[6][3] = ibtn_6_3;

        ibtn_6_4 = findViewById(R.id.ibtn_6_4);
        buttons[6][4] = ibtn_6_4;

        ibtn_6_5 = findViewById(R.id.ibtn_6_5);
        buttons[6][5] = ibtn_6_5;

        ibtn_6_6 = findViewById(R.id.ibtn_6_6);
        buttons[6][6] = ibtn_6_6;

        ibtn_6_7 = findViewById(R.id.ibtn_6_7);
        buttons[6][7] = ibtn_6_7;

//row 8
        ibtn_7_0 = findViewById(R.id.ibtn_7_0);
        buttons[7][0] = ibtn_7_0;

        ibtn_7_1 = findViewById(R.id.ibtn_7_1);
        buttons[7][1] = ibtn_7_1;

        ibtn_7_2 = findViewById(R.id.ibtn_7_2);
        buttons[7][2] = ibtn_7_2;

        ibtn_7_3 = findViewById(R.id.ibtn_7_3);
        buttons[7][3] = ibtn_7_3;

        ibtn_7_4 = findViewById(R.id.ibtn_7_4);
        buttons[7][4] = ibtn_7_4;

        ibtn_7_5 = findViewById(R.id.ibtn_7_5);
        buttons[7][5] = ibtn_7_5;

        ibtn_7_6 = findViewById(R.id.ibtn_7_6);
        buttons[7][6] = ibtn_7_6;

        ibtn_7_7 = findViewById(R.id.ibtn_7_7);
        buttons[7][7] = ibtn_7_7;


        //**********************************************************************
        //row 1
        ibtn_0_0.setOnClickListener(this);
        ibtn_0_1.setOnClickListener(this);
        ibtn_0_2.setOnClickListener(this);
        ibtn_0_3.setOnClickListener(this);
        ibtn_0_4.setOnClickListener(this);
        ibtn_0_5.setOnClickListener(this);
        ibtn_0_6.setOnClickListener(this);
        ibtn_0_7.setOnClickListener(this);


//row 2
        ibtn_1_0.setOnClickListener(this);
        ibtn_1_1.setOnClickListener(this);
        ibtn_1_2.setOnClickListener(this);
        ibtn_1_3.setOnClickListener(this);
        ibtn_1_4.setOnClickListener(this);
        ibtn_1_5.setOnClickListener(this);
        ibtn_1_6.setOnClickListener(this);
        ibtn_1_7.setOnClickListener(this);
//row 3
        ibtn_2_0.setOnClickListener(this);
        ibtn_2_1.setOnClickListener(this);
        ibtn_2_2.setOnClickListener(this);
        ibtn_2_3.setOnClickListener(this);
        ibtn_2_4.setOnClickListener(this);
        ibtn_2_5.setOnClickListener(this);
        ibtn_2_6.setOnClickListener(this);
        ibtn_2_7.setOnClickListener(this);
//row 4
        ibtn_3_0.setOnClickListener(this);
        ibtn_3_1.setOnClickListener(this);
        ibtn_3_2.setOnClickListener(this);
        ibtn_3_3.setOnClickListener(this);
        ibtn_3_4.setOnClickListener(this);
        ibtn_3_5.setOnClickListener(this);
        ibtn_3_6.setOnClickListener(this);
        ibtn_3_7.setOnClickListener(this);

//row 5
        ibtn_4_0.setOnClickListener(this);
        ibtn_4_1.setOnClickListener(this);
        ibtn_4_2.setOnClickListener(this);
        ibtn_4_3.setOnClickListener(this);
        ibtn_4_4.setOnClickListener(this);
        ibtn_4_5.setOnClickListener(this);
        ibtn_4_6.setOnClickListener(this);
        ibtn_4_7.setOnClickListener(this);

//row 6
        ibtn_5_0.setOnClickListener(this);
        ibtn_5_1.setOnClickListener(this);
        ibtn_5_2.setOnClickListener(this);
        ibtn_5_3.setOnClickListener(this);
        ibtn_5_4.setOnClickListener(this);
        ibtn_5_5.setOnClickListener(this);
        ibtn_5_6.setOnClickListener(this);
        ibtn_5_7.setOnClickListener(this);

//row 7
        ibtn_6_0.setOnClickListener(this);
        ibtn_6_1.setOnClickListener(this);
        ibtn_6_2.setOnClickListener(this);
        ibtn_6_3.setOnClickListener(this);
        ibtn_6_4.setOnClickListener(this);
        ibtn_6_5.setOnClickListener(this);
        ibtn_6_6.setOnClickListener(this);
        ibtn_6_7.setOnClickListener(this);

//row 7
        ibtn_7_0.setOnClickListener(this);
        ibtn_7_1.setOnClickListener(this);
        ibtn_7_2.setOnClickListener(this);
        ibtn_7_3.setOnClickListener(this);
        ibtn_7_4.setOnClickListener(this);
        ibtn_7_5.setOnClickListener(this);
        ibtn_7_6.setOnClickListener(this);
        ibtn_7_7.setOnClickListener(this);




        firstClick = true;
        isBlacksTurn = true;


        board = new Piece[8][8];
        setup();
    }

    @Override
    public void onClick(View v) {

        if(firstClick) {

            switch (v.getId()) {
                case R.id.ibtn_0_0:

                    //if no piece is in this position
                    if(board[0][0] == null){
                        return;
                    }
                    //else if the the piece is the wrong color
                    else if(board[0][0].isBlack() != isBlacksTurn){
                        return;
                    }
                    //if there is a piece and its the right color
                    else{
                        //get all possible moves
                        moves = board[0][0].getMoves(board);
                        //record stating position
                        start = new Point(0,0);
                    }

                    break;
            }

            firstClick = false;
        }
        else{

            switch (v.getId()) {
                case R.id.ibtn_0_0:
                    //if the users clicks the same position twice
                    //deselect the piece
                    if(start.equals(new Point(0,0))){
                        firstClick = true;
                        start = null;
                        return;
                    }
                    else{

                        for(int i = 0; i < moves.size(); i++){
                            if(moves.get(i).equals(new Point(0,0))){
                                board[0][0] = board[start.getRow()][start.getColumn()];
                                board[start.getRow()][start.getColumn()] = null;
                                return;
                            }
                        }

                    }

                    break;
            }

            firstClick = true;

            isBlacksTurn = !isBlacksTurn;
        }
    }




    public void setup(){
        //Black
        board[0][0] = new Rook(true,0,0);
        //ibtn_0_0.setText("B Rook");
        board[0][7] = new Rook(true,0,7);
        board[0][1] = new Knight(true,0,1);
        board[0][6] = new Knight(true,0,6);
        board[0][2] = new Bishop(true,0,3);
        board[0][5] = new Bishop(true,0,5);
        board[0][3] = new Queen(true,0,3);
        board[0][4] = new King(true,0,4);

        for(int i = 0; i < 8; i++){
            board[1][i] = new Pawn(true,1,i);
        }

        //White
        board[7][0] = new Rook(false,0,0);
        board[7][7] = new Rook(false,0,7);
        board[7][1] = new Knight(false,0,1);
        board[7][6] = new Knight(false,0,6);
        board[7][2] = new Bishop(false,0,3);
        board[7][5] = new Bishop(false,0,5);
        board[7][3] = new Queen(false,0,3);
        board[7][4] = new King(false,0,4);

        for(int i = 0; i < 8; i++){
            board[7][i] = new Pawn(false,7,i);
        }
    }
}
