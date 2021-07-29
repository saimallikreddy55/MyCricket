package com.example.mycricket;

import android.content.Context;
import android.renderscript.Sampler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import java.util.ArrayList;
import java.util.jar.Attributes;

public class TeamsAdapter extends FirebaseRecyclerAdapter<Teams, TeamsAdapter.TeamsViewHolder> {

    public TeamsAdapter(@NonNull FirebaseRecyclerOptions<Teams> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull TeamsViewHolder holder, int position, @NonNull Teams model) {
        holder.tvteams.setText(model.getTeamname());

    }


    @NonNull
    @Override
    public TeamsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.teams_view, viewGroup, false);
        return new TeamsViewHolder(v);
    }

    public static class TeamsViewHolder extends RecyclerView.ViewHolder {

        TextView tvteams;
        public TeamsViewHolder(@NonNull View itemView) {
            super(itemView);

            tvteams = itemView.findViewById(R.id.tvteams);

        }
    }
}