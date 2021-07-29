package com.example.mycricket;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Objects;

public class OpeningBatsman extends AppCompatActivity {

    private AutoCompleteTextView stkr;
    private AutoCompleteTextView nstkr;
    private AutoCompleteTextView opbow;
    private Button b2;


    FirebaseDatabase rootnode;
    DatabaseReference ref;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opening_batsman);

        stkr = findViewById(R.id.atvskr);
        nstkr = findViewById(R.id.atvnstr);
        opbow = findViewById(R.id.atvob);


        String t1 = getIntent().getStringExtra("team1");
        String t2 = getIntent().getStringExtra("team2");



        rootnode=FirebaseDatabase.getInstance();
        ref=rootnode.getReference().child("Teams");




        ArrayList<String> team1 = new ArrayList<String>();
        ArrayAdapter <String> adp;
        adp = new ArrayAdapter<>(this,android.R.layout.select_dialog_item,team1);
        stkr.setThreshold(1);
        stkr.setAdapter(adp);
        nstkr.setThreshold(1);
        nstkr.setAdapter(adp);
        ref.child(t1).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull  Task<DataSnapshot> task) {

               for( DataSnapshot dataSnapshot : Objects.requireNonNull(task.getResult()).getChildren()) {
                   team1.add(String.valueOf(dataSnapshot.child("Name").getValue()));
                   adp.notifyDataSetChanged();
               }
            }
        });

        ArrayList<String> team2 = new ArrayList<String>();
        ArrayAdapter <String> adp2;
        adp2 = new ArrayAdapter<>(this,android.R.layout.select_dialog_item,team2);
        opbow.setThreshold(1);
        opbow.setAdapter(adp2);
        ref.child(t2).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull  Task<DataSnapshot> task) {

                for( DataSnapshot dataSnapshot : Objects.requireNonNull(task.getResult()).getChildren()) {
                    team2.add(String.valueOf(dataSnapshot.child("Name").getValue()));
                    adp2.notifyDataSetChanged();
                }
            }
        });



        b2 = findViewById(R.id.b2);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {




                String skr = stkr.getText().toString();
                String nskr = nstkr.getText().toString();
                String opb = opbow.getText().toString();



                Intent intent = new Intent(OpeningBatsman.this, Scorecard.class);
                intent.putExtra("stkr",skr);
                intent.putExtra("nstkr",nskr);
                intent.putExtra("opb",opb);
                intent.putExtra("tm1",t1);
                intent.putExtra("tm2",t2);
                startActivity(intent);













            }
        });
    }



}