package com.example.guanghuili.checkesandchess;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {
    private Button btnSignup;
    private Button btnLogin;
    private ImageButton ibtnCheckers;
    private ImageButton ibtnChess;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSignup = findViewById(R.id.btnSignUpID);
        btnLogin = findViewById(R.id.btnLoginID);
        ibtnCheckers = findViewById(R.id.ibtnCheckersID);
        ibtnChess = findViewById(R.id.ibtnChessID);
        ibtnCheckers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CheckerActivity.class);
                startActivity(intent);
            }
        });

    }
}
