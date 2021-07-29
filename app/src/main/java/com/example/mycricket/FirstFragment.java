package com.example.mycricket;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FirstFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FirstFragment extends Fragment {


    private Button b1;
    private AutoCompleteTextView t1, t2;
    private RadioGroup tms,btbw;
    private RadioButton tm1,tm2,bt,bw;


    int tone=0,ttwo=0;

    FirebaseDatabase rootnode;
    DatabaseReference ref;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FirstFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FirstFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FirstFragment newInstance(String param1, String param2) {
        FirstFragment fragment = new FirstFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }


    }

    private Button findViewById(int btn) {

        b1 = findViewById(R.id.b1);

        return null;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_first, container, false);

        t1 = v.findViewById(R.id.actvtm1);
        t2 = v.findViewById(R.id.actvtm2);
        tm1 = v.findViewById(R.id.rbtm1);
        tm2 = v.findViewById(R.id.rbtm2);
        tms = v.findViewById(R.id.rgtms);
        btbw = v.findViewById(R.id.rgbtbw);
        bt = v.findViewById(R.id.rbbt);
       bw = v.findViewById(R.id.rbbw);



        t1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                String rt1 = t1.getText().toString();
                tm1.setText(rt1);

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        t2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                String rt2 = t2.getText().toString();
                tm2.setText(rt2);

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        tms.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int i) {

                switch (i){

                    case R.id.rbtm1:
                        tone=1;
                        ttwo=0;
                        break;
                    case R.id.rbtm2:
                        ttwo=1;
                        tone=0;
                        break;
                }

            }
        });


        btbw.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int i) {

                switch (i) {

                    case R.id.rbbt:
                        Toast.makeText(getActivity(),"-> " + tone + " "+ ttwo,Toast.LENGTH_LONG).show();

                    case R.id.rbbw:
                        Toast.makeText(getActivity(),"-> " + tone + " "+ ttwo,Toast.LENGTH_LONG).show();
                }
            }
        });
        

        rootnode=FirebaseDatabase.getInstance();
        ref=rootnode.getReference("Teams");


        ArrayList<String> team1 = new ArrayList<String>();
        ArrayAdapter <String> adp;
        adp = new ArrayAdapter<>(getActivity(),android.R.layout.select_dialog_item,team1);
        t1.setThreshold(1);
        t1.setAdapter(adp);
        t2.setThreshold(1);
        t2.setAdapter(adp);
        ref.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {

                for( DataSnapshot dataSnapshot : Objects.requireNonNull(task.getResult()).getChildren()) {
                    team1.add(String.valueOf(dataSnapshot.getKey()));
                    adp.notifyDataSetChanged();
                }
            }
        });







        b1 = v.findViewById(R.id.b1);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {




                Intent intent = new Intent(getActivity(), OpeningBatsman.class);
                intent.putExtra("team1", t1.getText().toString());
                intent.putExtra("team2", t2.getText().toString());
                startActivity(intent);


            }
        });

        return v;
    }


}


