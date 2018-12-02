package com.example.guanghuili.checkesandchess;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.guanghuili.checkesandchess.CheckerPractice.CheckerPracticeActivity;
import com.example.guanghuili.checkesandchess.Checkers.CheckerRoomActivity;
import com.example.guanghuili.checkesandchess.Checkers.RoomManager;
import com.example.guanghuili.checkesandchess.Chess.ChessRoomActivity;
import com.example.guanghuili.checkesandchess.ChessPractice.Chess;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    private PlayerManager playerManager = new PlayerManager();
    private RoomManager roomManager = new RoomManager();
    private Player player;

    //***Login Page***
    private EditText etEmailLogin;
    private EditText etPasswordLogin;
    private Button btnLoginXML;
    //**************

    //***Sign Up Page***
    private EditText etNameSignUp;
    private EditText etEmailSignUp;
    private EditText etPasswordSignUp;
    private Button btnSignUpXML;
    //**************

    //***Status Page***
    private TextView tvWin;
    private TextView tvLoss;
    private TextView tvWinningRate;
    //**************

    MediaPlayer backgroundSound;
    MediaPlayer clickSound;
    private AlertDialog alertDialog;
    private AlertDialog.Builder dialogBuilder;

    //***MainActivity***
    private Button btnSignup;
    private Button btnLogin;
    private Button btnSignOut;
    private ImageButton ibtnCheckers;
    private ImageButton ibtnChess;
    private TextView tvGreeting;
    private TextView tvCredit;
    //*******************

    private FirebaseUser user;
    private FirebaseDatabase database;
    private DatabaseReference refSignUpPlayers;
    private DatabaseReference refUsername;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;


    private static final String TAG = "DebugMainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        backgroundSound = MediaPlayer.create(MainActivity.this, R.raw.adventure);
        clickSound = MediaPlayer.create(MainActivity.this, R.raw.click);
        backgroundSound.setLooping(true);
        backgroundSound.start();

        tvGreeting = findViewById(R.id.tvGreetingID);
        btnSignup = findViewById(R.id.btnSignUpID);
        btnLogin = findViewById(R.id.btnLoginID);
        btnSignOut = findViewById(R.id.btnSignOutID);
        tvCredit = findViewById(R.id.tvCredit);

        tvCredit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickSound.start();
                Intent intent = new Intent(MainActivity.this, CreditActivity.class);
                startActivity(intent);
            }
        });

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickSound.start();
                createSignUpDialog();
            }
        });

        btnSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickSound.start();
                mAuth.signOut();
                Toast.makeText(MainActivity.this,"Signed Out",Toast.LENGTH_LONG).show();
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickSound.start();
                createLoginDialog();
            }
        });

        tvGreeting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickSound.start();
                createStatusDialog();
            }
        });

        ibtnCheckers = findViewById(R.id.ibtnCheckersID);
        ibtnChess = findViewById(R.id.ibtnChessID);
        ibtnCheckers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickSound.start();
                Intent intent = new Intent(MainActivity.this, CheckerRoomActivity.class);
                startActivity(intent);
            }
        });

        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        refSignUpPlayers = database.getReference("Signed Up Players");
        refUsername = database.getReference("username");

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                Log.d("Step","Step1");
                user = mAuth.getCurrentUser();
                if(user != null){
                    if(user.getDisplayName()!= null) {
                        //user is signed in
                        Log.d(TAG, "user signed in");
                        btnLogin.setVisibility(View.GONE);
                        btnSignOut.setVisibility(View.VISIBLE);
                        btnSignup.setVisibility(View.GONE);
                        tvGreeting.setText("Hello " + user.getDisplayName());
                        tvGreeting.setVisibility(View.VISIBLE);
                        ibtnCheckers.setEnabled(true);
                        ibtnChess.setEnabled(true);

                    }
                    else{
                        //User is signed out
                        Log.d(TAG,"user signed out");
                        btnLogin.setVisibility(View.VISIBLE);
                        btnSignOut.setVisibility(View.GONE);
                        btnSignup.setVisibility(View.VISIBLE);
                        tvGreeting.setVisibility(View.GONE);
                        tvGreeting.setVisibility(View.GONE);
                        ibtnCheckers.setEnabled(false);
                        ibtnChess.setEnabled(false);
                    }

                }
                else{
                    //User is signed out
                    Log.d(TAG,"user signed out");
                    btnLogin.setVisibility(View.VISIBLE);
                    btnSignOut.setVisibility(View.GONE);
                    btnSignup.setVisibility(View.VISIBLE);
                    tvGreeting.setVisibility(View.GONE);
                    ibtnCheckers.setEnabled(false);
                    ibtnChess.setEnabled(false);
                }
            }
        };

        refUsername.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot username : dataSnapshot.getChildren()){
                    playerManager.getUsernameList().add(username.getValue(String.class));
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    protected void onStop() {
        super.onStop();

        if(mAuthListener != null){
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

    private void createLoginDialog(){
        dialogBuilder = new AlertDialog.Builder(this);
        View view = getLayoutInflater().inflate(R.layout.login,null);
        etEmailLogin = (EditText)view.findViewById(R.id.etEmailLoginID);
        etPasswordLogin = (EditText) view.findViewById(R.id.etPasswordLoginID);
        btnLoginXML = (Button) view.findViewById(R.id.btnLoginXMLID);
        dialogBuilder.setView(view);
        alertDialog = dialogBuilder.create();
        alertDialog.show();
        btnLoginXML.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickSound.start();
                final String email = etEmailLogin.getText().toString();
                String password = etPasswordLogin.getText().toString();

                if (!email.equals("") && !password.equals("")) {
                    mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (!task.isSuccessful()) {
                                Toast.makeText(MainActivity.this, "Failed Sign in", Toast.LENGTH_LONG).show();
                            } else {
                                user = mAuth.getCurrentUser();
                                Toast.makeText(MainActivity.this, "Signed In!", Toast.LENGTH_LONG).show();
                                alertDialog.dismiss();
                            }
                        }
                    });
                }
            }
        });

    }

    private void createSignUpDialog(){
        dialogBuilder = new AlertDialog.Builder(this);
        View view = getLayoutInflater().inflate(R.layout.signup,null);
        etNameSignUp = (EditText)view.findViewById(R.id.etNameSignupID);
        etEmailSignUp = (EditText)view.findViewById(R.id.etEmailSignupID);
        etPasswordSignUp = (EditText) view.findViewById(R.id.etPasswordSignupID);
        btnSignUpXML = (Button) view.findViewById(R.id.btnSignupXMLID);
        dialogBuilder.setView(view);
        alertDialog = dialogBuilder.create();
        alertDialog.show();
        btnSignUpXML.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickSound.start();
                final String username = etNameSignUp.getText().toString();
                final String email = etEmailSignUp.getText().toString();
                final String password = etPasswordSignUp.getText().toString();
                boolean duplicateCopy = playerManager.getDuplicateUsername(username);
                if(duplicateCopy == true) {
                    Toast.makeText(MainActivity.this, "Username Taken", Toast.LENGTH_LONG).show();
                }
                if (!email.equals("") && !password.equals("") && !username.equals("") && duplicateCopy == false){
                    mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (!task.isSuccessful()) {
                                Toast.makeText(MainActivity.this, "Failed Sign Up", Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(MainActivity.this, "Signed Up!", Toast.LENGTH_LONG).show();
                                //***store the sign up user info to the database***//
                                player = playerManager.createPlayer(username,email);
                                refSignUpPlayers.child(username).setValue(player);

                                refUsername.child(username).setValue(username);

                                user = mAuth.getCurrentUser();
                                UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                                    .setDisplayName(username).build();

                                user.updateProfile(profileUpdates);
                                mAuth.signOut();
                                Log.d("Step","Step2");
                                alertDialog.dismiss();

                                mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        if (!task.isSuccessful()) {
                                            Toast.makeText(MainActivity.this, "Failed Sign in", Toast.LENGTH_LONG).show();
                                        } else {
                                            user = mAuth.getCurrentUser();
                                            Toast.makeText(MainActivity.this, "Signed In!", Toast.LENGTH_LONG).show();
                                            alertDialog.dismiss();
                                            Log.d("Step","Step3");
                                        }
                                    }
                                });
                            }
                        }
                    });
                }
            }
        });

    }

    private void createStatusDialog(){
        dialogBuilder = new AlertDialog.Builder(this);
        View view = getLayoutInflater().inflate(R.layout.status,null);
        tvWin = view.findViewById(R.id.tvWinID);
        tvLoss =  view.findViewById(R.id.tvLossID);
        tvWinningRate = view.findViewById(R.id.tvWinningRateID);

        refSignUpPlayers.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                player = dataSnapshot.child(user.getDisplayName()).getValue(Player.class);
                tvWin.setText("Win: " + player.getWin());
                tvLoss.setText("Loss: " + player.getLoss());
                tvWinningRate.setText("Winning Rate: " + player.getWinningRate());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        dialogBuilder.setView(view);
        alertDialog = dialogBuilder.create();
        alertDialog.show();


    }

    @Override
    protected void onDestroy() {
        backgroundSound.stop();
        super.onDestroy();
    }

    public void goChess(View view){
        Intent intent = new Intent(MainActivity.this, ChessRoomActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.checkerMenuID){
            Intent intent = new Intent(MainActivity.this,CheckerPracticeActivity.class);
            startActivity(intent);
        }
        if(item.getItemId() == R.id.chessMenuID){
            Intent intent = new Intent(MainActivity.this,Chess.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }
}
