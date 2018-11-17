package com.example.guanghuili.checkesandchess;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.guanghuili.checkesandchess.Checkers.Player;
import com.example.guanghuili.checkesandchess.Checkers.Room;
import com.example.guanghuili.checkesandchess.Checkers.RoomManager;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class CheckerRoomActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerViewAdapter recyclerViewAdapter;

    private FirebaseDatabase database;
    private DatabaseReference refSignUpPlayers;
    private DatabaseReference refRoom;
    private DatabaseReference refUnavailableRoom;
    private DatabaseReference databaseReference;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    private Button btnCreate;
    private EditText etRoom;
    private Button btnJoin;
    private Player player;
    private Room room;
    private FirebaseUser user;
    private RoomManager roomManager = new RoomManager();
    private List<Integer> unavailableRoomIdList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checker_room);
//**********RecyclerView***********
        recyclerView = (RecyclerView) findViewById(R.id.RecyclerViewID);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//**********************************

        btnCreate = findViewById(R.id.btnCreateRoomID);
        etRoom = findViewById(R.id.etRoomID);
        btnJoin = findViewById(R.id.btnjoinID);

        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        refSignUpPlayers = database.getReference("Signed Up Players");
        refRoom = database.getReference("Room").child("available");
        refUnavailableRoom = database.getReference("Room").child("unavailable");

        mAuth.addAuthStateListener(new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                user = mAuth.getCurrentUser();
            }
        });

        refSignUpPlayers.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                user = mAuth.getCurrentUser();
                for(DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()){
                    if(dataSnapshot1.getValue(Player.class).getUsername().equals(user.getDisplayName())){
                        player = dataSnapshot1.getValue(Player.class);
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        refRoom.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                roomManager.getRoomList().clear();
                roomManager.getIdList().clear();
                for(DataSnapshot roomSnapshot : dataSnapshot.getChildren()){
                        roomManager.getRoomList().add(roomSnapshot.getValue(Room.class));
                        roomManager.getIdList().add(roomSnapshot.getValue(Room.class).getId());

                }
                recyclerViewAdapter = new RecyclerViewAdapter(CheckerRoomActivity.this, roomManager.getRoomList());
                recyclerView.setAdapter(recyclerViewAdapter);
                recyclerViewAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        refUnavailableRoom.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                unavailableRoomIdList.clear();
                for(DataSnapshot roomSnapshot : dataSnapshot.getChildren()){
                    unavailableRoomIdList.add(roomSnapshot.getValue(Room.class).getId());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                room = roomManager.createRoom(player);
                refRoom.child(String.valueOf(room.getId())).setValue(room);
                Intent intent = new Intent(CheckerRoomActivity.this,BlackCheckerActivity.class);
                intent.putExtra("room",room);
                startActivity(intent);
            }
        });

        btnJoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Boolean exist = false;
                if (!etRoom.getText().toString().equals("")){
                    int roomNum = Integer.parseInt(etRoom.getText().toString());
                    if (roomNum != 0) {
                        for (int i = 0; i < roomManager.getRoomList().size(); i++) {
                            if (roomNum == roomManager.getRoomList().get(i).getId()) {
                                exist = true;
                                Room room = roomManager.getRoomList().get(i);
                                room.setPlayer2(player);
                                room.setAvailability(false);
                                refRoom.child(String.valueOf(room.getId())).setValue(room);
                                Intent intent = new Intent(CheckerRoomActivity.this, RedCheckerActivity.class);
                                intent.putExtra("room", room);
                                CheckerRoomActivity.this.startActivity(intent);
                                break;
                            }
                        }
                        if (exist == false) {
                            Toast.makeText(CheckerRoomActivity.this, "Room Doesn't Exist", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
                else{//join random room
                    if(roomManager.getRoomList().size() == 0){
                        Toast.makeText(CheckerRoomActivity.this, "No Available Rooms", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        int min = 0;
                        int max = roomManager.getRoomList().size();
                        int range = (roomManager.getRoomList().size() - 1) + 1;
                        int random = (int) (Math.random() * range) + min;
                        Room room = roomManager.getRoomList().get(random);
                        room.setPlayer2(player);
                        room.setAvailability(false);
                        refRoom.child(String.valueOf(room.getId())).setValue(room);
                        Intent intent = new Intent(CheckerRoomActivity.this, RedCheckerActivity.class);
                        intent.putExtra("room", room);
                        CheckerRoomActivity.this.startActivity(intent);
                    }
                }
            }
        });



    }
}
