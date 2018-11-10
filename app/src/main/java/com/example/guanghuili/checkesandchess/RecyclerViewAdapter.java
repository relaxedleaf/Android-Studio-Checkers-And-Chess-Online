package com.example.guanghuili.checkesandchess;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.guanghuili.checkesandchess.Checkers.Room;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{
    private Context context;
    private LayoutInflater inflater;
    private ArrayList<Room>roomList;

    public RecyclerViewAdapter(Context context, ArrayList<Room>roomList){
        this.roomList = roomList;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cardview, viewGroup, false);
        return new ViewHolder(view, context);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.ViewHolder viewHolder, int position) {
        Room room = roomList.get(position);


    }

    @Override
    public int getItemCount() {
        return roomList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvUsername;
        public TextView tvRoomID;

        public ViewHolder(View view, Context ctx) {
            super(view);

            context = ctx;
            tvUsername = view.findViewById(R.id.tvUsernameID);
            tvRoomID = view.findViewById(R.id.tvRoomID);

        }
    }
}
