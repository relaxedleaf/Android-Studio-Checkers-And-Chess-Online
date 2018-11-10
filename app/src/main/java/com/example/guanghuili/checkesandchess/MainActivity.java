package com.example.guanghuili.checkesandchess;

import android.content.Intent;
import android.net.Uri;
import android.os.Parcel;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.guanghuili.checkesandchess.Checkers.Player;
import com.example.guanghuili.checkesandchess.Checkers.PlayerManager;
import com.google.android.gms.internal.firebase_auth.zzao;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.FirebaseUserMetadata;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private PlayerManager playerManager = new PlayerManager();
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

    private AlertDialog alertDialog;
    private AlertDialog.Builder dialogBuilder;

    //***MainActivity***
    private Button btnSignup;
    private Button btnLogin;
    private Button btnSignOut;
    private ImageButton ibtnCheckers;
    private ImageButton ibtnChess;
    private TextView tvGreeting;
    //*******************


    private FirebaseDatabase database;
    private DatabaseReference ref;
    private DatabaseReference databaseReference;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    private static final String TAG = "DebugMainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvGreeting = findViewById(R.id.tvGreetingID);
        btnSignup = findViewById(R.id.btnSignUpID);
        btnLogin = findViewById(R.id.btnLoginID);
        btnSignOut = findViewById(R.id.btnSignOutID);

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createSignUpDialog();
            }
        });

        btnSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signOut();
                Toast.makeText(MainActivity.this,"Signed Out",Toast.LENGTH_LONG).show();
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createLoginDialog();
            }
        });
        ibtnCheckers = findViewById(R.id.ibtnCheckersID);
        ibtnChess = findViewById(R.id.ibtnChessID);
        ibtnCheckers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CheckerActivity.class);
                startActivity(intent);
            }
        });

        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        ref = database.getReference("Sign Up Players");

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if(user != null){
                    //user is signed in
                    Log.d(TAG,"user signed in");
                    btnLogin.setVisibility(View.GONE);
                    btnSignOut.setVisibility(View.VISIBLE);
                    btnSignup.setVisibility(View.GONE);
                    tvGreeting.setText("Hello " + user.getDisplayName());
                    tvGreeting.setVisibility(View.VISIBLE);
                    ibtnCheckers.setEnabled(true);

                }
                else{
                    //User is signed out
                    Log.d(TAG,"user signed out");
                    btnLogin.setVisibility(View.VISIBLE);
                    btnSignOut.setVisibility(View.GONE);
                    btnSignup.setVisibility(View.VISIBLE);
                    tvGreeting.setVisibility(View.GONE);
                    ibtnCheckers.setEnabled(false);
                }
            }
        };

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
                final String email = etEmailLogin.getText().toString();
                String password = etPasswordLogin.getText().toString();

                if (!email.equals("") && !password.equals("")) {
                    mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (!task.isSuccessful()) {
                                Toast.makeText(MainActivity.this, "Failed Sign in", Toast.LENGTH_LONG).show();
                            } else {
                                FirebaseUser user = mAuth.getCurrentUser();
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
                final String username = etNameSignUp.getText().toString();
                final String email = etEmailSignUp.getText().toString();
                String password = etPasswordSignUp.getText().toString();
                if (!email.equals("") && !password.equals("") && !username.equals("")){
                    mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (!task.isSuccessful()) {
                                Toast.makeText(MainActivity.this, "Failed Sign Up", Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(MainActivity.this, "Signed Up!", Toast.LENGTH_LONG).show();
                                /*
                                Player player = playerManager.createPlayer(username,email);
                                databaseReference = ref.child(username);
                                databaseReference.push().setValue(player);
                                */
                                FirebaseUser user = mAuth.getCurrentUser();
                                UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                                    .setDisplayName(username).build();

                                user.updateProfile(profileUpdates);
                                alertDialog.dismiss();
                            }
                        }
                    });
                }
            }
        });

    }

}
