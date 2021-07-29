package com.example.mycricket;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Scorecard extends AppCompatActivity {

    private long backpresstime;

    private TextView tvsb,tvsbl,tvsr,tvs4,tvs6,tvssr;
    private TextView tvnsb,tvnsbl,tvnsr,tvns4,tvns6,tvnssr;
    private TextView tvb,tvbo,tvbm,tvbr,tvbw,tvber;
    private TextView btn0,btn1,btn2,btn3,btn4,btn6,btnswb,btnrtr;
    private TextView tvtsr,tvto,tvt1,tvt2;
    private CheckBox cbwd,cbnb,cbby,cblb,cbw;


    int sbl,sr,s4,s6,ssr;
    int c=0, tscr,w=0, ovr=0, br=0;
    int nsbl,nsr,ns4,ns6,nssr;
    float bo,bm,bw,ber=0, no=0;
    float x[] = {(float) 0.1, (float) 0.2, (float) 0.3, (float) 0.4, (float) 0.5, (float) 1};


    FirebaseDatabase rootnode;
    DatabaseReference ref;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scorecard);

        tvsb = findViewById(R.id.tvsb);
        tvsbl = findViewById(R.id.tvsbl);
        tvsr = findViewById(R.id.tvsr);
        tvs4 = findViewById(R.id.tvs4);
        tvs6 = findViewById(R.id.tvs6);
        tvssr = findViewById(R.id.tvssr);
        tvnsb = findViewById(R.id.tvnsb);
        tvnsbl = findViewById(R.id.tvnsbl);
        tvnsr = findViewById(R.id.tvnsr);
        tvns4 = findViewById(R.id.tvns4);
        tvns6 = findViewById(R.id.tvns6);
        tvnssr = findViewById(R.id.tvnssr);
        tvb = findViewById(R.id.tvb);
        tvbo = findViewById(R.id.tvbo);
        tvbm = findViewById(R.id.tvbm);
        tvbr = findViewById(R.id.tvbr);
        tvbw = findViewById(R.id.tvbw);
        tvber = findViewById(R.id.tvber);
        tvtsr = findViewById(R.id.tvtsr);
        tvto = findViewById(R.id.tvto);
        tvt1 = findViewById(R.id.tvt1);
        tvt2 = findViewById(R.id.tvt2);
        cbwd = findViewById(R.id.cbwd);
        cbnb = findViewById(R.id.cbnb);
        cbby = findViewById(R.id.cbby);
        cblb = findViewById(R.id.cblb);
        cbw = findViewById(R.id.cbw);


        String skr = getIntent().getStringExtra("stkr");
        String nskr = getIntent().getStringExtra("nstkr");
        String opb = getIntent().getStringExtra("opb");

        String t1 = getIntent().getStringExtra("tm1");
        String t2 = getIntent().getStringExtra("tm2");

        tvsb.setText(skr);
        tvnsb.setText(nskr);
        tvb.setText(opb);

        tvt1.setText(t1);
        tvt2.setText(t2);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

       // setContentView(R.layout.activity_scorecard);
        hnb();


        cbwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(cbwd.isChecked() && c==0)
                {
                    tscr = tscr + 1;
                    sbl--;
                }
                else
                {
                    tscr = tscr + 1;
                    nsbl--;
                }
                if(cbnb.isChecked())
                {
                    cbnb.setChecked(false);
                }
                if(cbby.isChecked())
                {
                    cbby.setChecked(false);
                }
                if(cblb.isChecked())
                {
                    cblb.setChecked(false);
                }

                br++;

            }
        });

        cbnb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(cbnb.isChecked())
                {
                    tscr = tscr + 1;
                }
                if(cbwd.isChecked())
                {
                    cbwd.setChecked(false);
                }

                br++;

            }
        });

        cbby.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(cbby.isChecked())
                {
                    tscr = tscr + 0;
                }
                if(cblb.isChecked())
                {
                    cblb.setChecked(false);
                }
                if(cbwd.isChecked())
                {
                    cbwd.setChecked(false);
                }

            }
        });

        cblb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(cblb.isChecked())
                {
                    tscr = tscr + 0;
                }
                if(cbwd.isChecked())
                {
                    cbwd.setChecked(false);
                }
                if(cbby.isChecked())
                {
                    cbby.setChecked(false);
                }

            }
        });

        cbw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(cbw.isChecked())
                {
                    w++;
                }

            }
        });




        btn0 = findViewById(R.id.btn0);
        btn0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tscr = tscr + 0;
                tvtsr.setText(String.valueOf(tscr) + "-" + String.valueOf(w));

                ovr++;
                br = br + 0;
                tvbr.setText(String.valueOf(br));
                tvbw.setText(String.valueOf(w));
                if(no<1)
                    tvber.setText(String.valueOf(br));
                else
                {
                    ber = (br/no);
                    tvber.setText(String.format("%.2f",ber));
                }


                if (c==0)
                {
                    sbl = sbl + 1;
                    sr = sr + 0;
                    s4 = s4 + 0;
                    s6 = s6 + 0;
                    ssr = (sr / sbl) * 100;
                    tvsr.setText(String.valueOf(sr));
                    tvsbl.setText(String.valueOf(sbl));
                    tvs4.setText(String.valueOf(s4));
                    tvs6.setText(String.valueOf(s6));
                    tvssr.setText(String.valueOf(ssr));
                }
                else
                {
                    nsbl = nsbl + 1;
                    nsr = nsr + 0;
                    ns4 = ns4 + 0;
                    ns6 = ns6 + 0;
                    nssr = (nsr / nsbl) * 100;
                    tvsr.setText(String.valueOf(nsr));
                    tvsbl.setText(String.valueOf(nsbl));
                    tvs4.setText(String.valueOf(ns4));
                    tvs6.setText(String.valueOf(ns6));
                    tvssr.setText(String.valueOf(nssr));

                }



                if(cbwd.isChecked())
                {
                    cbwd.setChecked(false);
                }
                if(cbnb.isChecked())
                {
                    cbnb.setChecked(false);
                }
                if(cbby.isChecked())
                {
                    cbby.setChecked(false);
                }
                if(cblb.isChecked())
                {
                    cblb.setChecked(false);
                }
                if(cbw.isChecked())
                {
                    cbw.setChecked(false);
                }



                rootnode=FirebaseDatabase.getInstance();
                ref=rootnode.getReference("Teams");

                String str = tvsb.getText().toString();
                String nstr = tvnsb.getText().toString();
                String blr = tvb.getText().toString();

                String tm1 = tvt1.getText().toString();
                String tm2 = tvt2.getText().toString();

                HashMap<String,String> striker = new HashMap<>();
                striker.put("Name",str);
                striker.put("Runs",String.valueOf(sr));
                striker.put("BallsFaced",String.valueOf(sbl));
                striker.put("NoOfFours",String.valueOf(s4));
                striker.put("NoOfSixes",String.valueOf(s6));
                striker.put("StrikeRate",String.valueOf(ssr));

                HashMap<String,String> nonstriker = new HashMap<>();
                nonstriker.put("Name",nstr);
                nonstriker.put("Runs",String.valueOf(nsr));
                nonstriker.put("BallsFaced",String.valueOf(nsbl));
                nonstriker.put("NoOfFours",String.valueOf(ns4));
                nonstriker.put("NoOfSixes",String.valueOf(ns6));
                nonstriker.put("StrikeRate",String.valueOf(nssr));

                HashMap<String,String> bowler = new HashMap<>();
                bowler.put("Name",blr);
                bowler.put("NoOfWickets",String.valueOf(w));
                bowler.put("Runs",String.valueOf(br));
                bowler.put("EconomyRate",String.valueOf(ber));


                if (ovr != 6)
                {
                    tvbo.setText(String.valueOf(x[ovr-1]+no));
                    tvto.setText(String.valueOf(x[ovr-1]+no));
                    bowler.put("NoOfOvers",String.valueOf(x[ovr-1]+no));

                }
                else
                {
                    tvbo.setText(String.valueOf(x[ovr-1]+no));
                    tvto.setText(String.valueOf(x[ovr-1]+no));
                    bowler.put("NoOfOvers",String.valueOf(x[ovr-1]+no));
                    no++;
                    ovr=0;
                }


                ref.child(tm1).child(str).setValue(striker);
                ref.child(tm1).child(nstr).setValue(nonstriker);
                ref.child(tm2).child(blr).setValue(bowler);


            }
        });



        btn1 = findViewById(R.id.btn1);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tscr = tscr + 1;
                tvtsr.setText(String.valueOf(tscr) + "-" + String.valueOf(w));

                ovr++;
                br = br + 1;
                tvbr.setText(String.valueOf(br));
                tvbw.setText(String.valueOf(w));
                if(no<1)
                    tvber.setText(String.valueOf(br));
                else
                {
                    ber = (br/no);
                    tvber.setText(String.format("%.2f",ber));
                }

                if(c==0)
                {
                    sbl = sbl + 1;
                    sr = sr + 1;
                    s4 = s4 + 0;
                    s6 = s6 + 0;
                    ssr = (sr / sbl) * 100;
                    tvsr.setText(String.valueOf(sr));
                    tvsbl.setText(String.valueOf(sbl));
                    tvs4.setText(String.valueOf(s4));
                    tvs6.setText(String.valueOf(s6));
                    tvssr.setText(String.valueOf(ssr));
                    c = c + 1;
                }
                else
                {
                    nsbl = nsbl + 1;
                    nsr = nsr + 1;
                    ns4 = ns4 + 0;
                    ns6 = ns6 + 0;
                    nssr = (nsr / nsbl) * 100;
                    tvsr.setText(String.valueOf(nsr));
                    tvsbl.setText(String.valueOf(nsbl));
                    tvs4.setText(String.valueOf(ns4));
                    tvs6.setText(String.valueOf(ns6));
                    tvssr.setText(String.valueOf(nssr));
                    c=0;
                }


                String temp = tvsb.getText().toString();
                String tmp = tvnsb.getText().toString();
                tvsb.setText(tmp);
                tvnsb.setText(temp);

                temp = tvsbl.getText().toString();
                tmp = tvnsbl.getText().toString();
                tvsbl.setText(tmp);
                tvnsbl.setText(temp);

                temp = tvsr.getText().toString();
                tmp = tvnsr.getText().toString();
                tvsr.setText(tmp);
                tvnsr.setText(temp);

                temp = tvs4.getText().toString();
                tmp = tvns4.getText().toString();
                tvs4.setText(tmp);
                tvns4.setText(temp);

                temp = tvs6.getText().toString();
                tmp = tvns6.getText().toString();
                tvs6.setText(tmp);
                tvns6.setText(temp);

                temp = tvssr.getText().toString();
                tmp = tvnssr.getText().toString();
                tvssr.setText(tmp);
                tvnssr.setText(temp);


                if(cbwd.isChecked())
                {
                    cbwd.setChecked(false);
                }
                if(cbnb.isChecked())
                {
                    cbnb.setChecked(false);
                }
                if(cbby.isChecked())
                {
                    cbby.setChecked(false);
                }
                if(cblb.isChecked())
                {
                    cblb.setChecked(false);
                }
                if(cbw.isChecked())
                {
                    cbw.setChecked(false);
                }


                rootnode=FirebaseDatabase.getInstance();
                ref=rootnode.getReference("Teams");

                String str = tvsb.getText().toString();
                String nstr = tvnsb.getText().toString();
                String blr = tvb.getText().toString();

                String tm1 = tvt1.getText().toString();
                String tm2 = tvt2.getText().toString();

                HashMap<String,String> striker = new HashMap<>();
                striker.put("Name",str);
                striker.put("Runs",String.valueOf(sr));
                striker.put("BallsFaced",String.valueOf(sbl));
                striker.put("NoOfFours",String.valueOf(s4));
                striker.put("NoOfSixes",String.valueOf(s6));
                striker.put("StrikeRate",String.valueOf(ssr));

                HashMap<String,String> nonstriker = new HashMap<>();
                nonstriker.put("Name",nstr);
                nonstriker.put("Runs",String.valueOf(nsr));
                nonstriker.put("BallsFaced",String.valueOf(nsbl));
                nonstriker.put("NoOfFours",String.valueOf(ns4));
                nonstriker.put("NoOfSixes",String.valueOf(ns6));
                nonstriker.put("StrikeRate",String.valueOf(nssr));

                HashMap<String,String> bowler = new HashMap<>();
                bowler.put("Name",blr);
                bowler.put("NoOfWickets",String.valueOf(w));
                bowler.put("Runs",String.valueOf(br));
                bowler.put("EconomyRate",String.valueOf(ber));



                if (ovr != 6)
                {
                    tvbo.setText(String.valueOf(x[ovr-1]+no));
                    tvto.setText(String.valueOf(x[ovr-1]+no));
                    bowler.put("NoOfOvers",String.valueOf(x[ovr-1]+no));

                }
                else
                {
                    tvbo.setText(String.valueOf(x[ovr-1]+no));
                    tvto.setText(String.valueOf(x[ovr-1]+no));
                    bowler.put("NoOfOvers",String.valueOf(x[ovr-1]+no));
                    no++;
                    ovr=0;
                }



                ref.child(tm1).child(str).setValue(striker);
                ref.child(tm1).child(nstr).setValue(nonstriker);
                ref.child(tm2).child(blr).setValue(bowler);

            }
        });


        btn2 = findViewById(R.id.btn2);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tscr = tscr + 2;
                tvtsr.setText(String.valueOf(tscr) + "-" + String.valueOf(w));

                ovr++;
                br = br + 2;
                tvbr.setText(String.valueOf(br));
                tvbw.setText(String.valueOf(w));
                if(no<1)
                    tvber.setText(String.valueOf(br));
                else
                {
                    ber = (br/no);
                    tvber.setText(String.format("%.2f",ber));
                }

                if(c==0)
                {
                    sbl = sbl + 1;
                    sr = sr + 2;
                    s4 = s4 + 0;
                    s6 = s6 + 0;
                    ssr = (sr / sbl) * 100;
                    tvsr.setText(String.valueOf(sr));
                    tvsbl.setText(String.valueOf(sbl));
                    tvs4.setText(String.valueOf(s4));
                    tvs6.setText(String.valueOf(s6));
                    tvssr.setText(String.valueOf(ssr));
                }
                else
                {
                    nsbl = nsbl + 1;
                    nsr = nsr + 2;
                    ns4 = ns4 + 0;
                    ns6 = ns6 + 0;
                    nssr = (nsr / nsbl) * 100;
                    tvsr.setText(String.valueOf(nsr));
                    tvsbl.setText(String.valueOf(nsbl));
                    tvs4.setText(String.valueOf(ns4));
                    tvs6.setText(String.valueOf(ns6));
                    tvssr.setText(String.valueOf(nssr));
                }


                if(cbwd.isChecked())
                {
                    cbwd.setChecked(false);
                }
                if(cbnb.isChecked())
                {
                    cbnb.setChecked(false);
                }
                if(cbby.isChecked())
                {
                    cbby.setChecked(false);
                }
                if(cblb.isChecked())
                {
                    cblb.setChecked(false);
                }
                if(cbw.isChecked())
                {
                    cbw.setChecked(false);
                }


                rootnode=FirebaseDatabase.getInstance();
                ref=rootnode.getReference("Teams");

                String str = tvsb.getText().toString();
                String nstr = tvnsb.getText().toString();
                String blr = tvb.getText().toString();

                String tm1 = tvt1.getText().toString();
                String tm2 = tvt2.getText().toString();

                HashMap<String,String> striker = new HashMap<>();
                striker.put("Name",str);
                striker.put("Runs",String.valueOf(sr));
                striker.put("BallsFaced",String.valueOf(sbl));
                striker.put("NoOfFours",String.valueOf(s4));
                striker.put("NoOfSixes",String.valueOf(s6));
                striker.put("StrikeRate",String.valueOf(ssr));

                HashMap<String,String> nonstriker = new HashMap<>();
                nonstriker.put("Name",nstr);
                nonstriker.put("Runs",String.valueOf(nsr));
                nonstriker.put("BallsFaced",String.valueOf(nsbl));
                nonstriker.put("NoOfFours",String.valueOf(ns4));
                nonstriker.put("NoOfSixes",String.valueOf(ns6));
                nonstriker.put("StrikeRate",String.valueOf(nssr));

                HashMap<String,String> bowler = new HashMap<>();
                bowler.put("Name",blr);
                bowler.put("NoOfWickets",String.valueOf(w));
                bowler.put("Runs",String.valueOf(br));
                bowler.put("EconomyRate",String.valueOf(ber));



                if (ovr != 6)
                {
                    tvbo.setText(String.valueOf(x[ovr-1]+no));
                    tvto.setText(String.valueOf(x[ovr-1]+no));
                    bowler.put("NoOfOvers",String.valueOf(x[ovr-1]+no));

                }
                else
                {
                    tvbo.setText(String.valueOf(x[ovr-1]+no));
                    tvto.setText(String.valueOf(x[ovr-1]+no));
                    bowler.put("NoOfOvers",String.valueOf(x[ovr-1]+no));
                    no++;
                    ovr=0;
                }



                ref.child(tm1).child(str).setValue(striker);
                ref.child(tm1).child(nstr).setValue(nonstriker);
                ref.child(tm2).child(blr).setValue(bowler);

            }
        });


        btn3 = findViewById(R.id.btn3);
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tscr = tscr + 3;
                tvtsr.setText(String.valueOf(tscr) + "-" + String.valueOf(w));

                ovr++;
                br = br + 3;
                tvbr.setText(String.valueOf(br));
                tvbw.setText(String.valueOf(w));
                if(no<1)
                    tvber.setText(String.valueOf(br));
                else
                {
                    ber = (br/no);
                    tvber.setText(String.format("%.2f",ber));
                }

                if(c==0)
                {
                    sbl = sbl + 1;
                    sr = sr + 3;
                    s4 = s4 + 0;
                    s6 = s6 + 0;
                    ssr = (sr / sbl) * 100;
                    tvsr.setText(String.valueOf(sr));
                    tvsbl.setText(String.valueOf(sbl));
                    tvs4.setText(String.valueOf(s4));
                    tvs6.setText(String.valueOf(s6));
                    tvssr.setText(String.valueOf(ssr));
                    c = c + 1;
                }
                else
                {
                    nsbl = nsbl + 1;
                    nsr = nsr + 3;
                    ns4 = ns4 + 0;
                    ns6 = ns6 + 0;
                    nssr = (nsr / nsbl) * 100;
                    tvsr.setText(String.valueOf(nsr));
                    tvsbl.setText(String.valueOf(nsbl));
                    tvs4.setText(String.valueOf(ns4));
                    tvs6.setText(String.valueOf(ns6));
                    tvssr.setText(String.valueOf(nssr));
                    c=0;
                }


                String temp = tvsb.getText().toString();
                String tmp = tvnsb.getText().toString();
                tvsb.setText(tmp);
                tvnsb.setText(temp);

                temp = tvsbl.getText().toString();
                tmp = tvnsbl.getText().toString();
                tvsbl.setText(tmp);
                tvnsbl.setText(temp);

                temp = tvsr.getText().toString();
                tmp = tvnsr.getText().toString();
                tvsr.setText(tmp);
                tvnsr.setText(temp);

                temp = tvs4.getText().toString();
                tmp = tvns4.getText().toString();
                tvs4.setText(tmp);
                tvns4.setText(temp);

                temp = tvs6.getText().toString();
                tmp = tvns6.getText().toString();
                tvs6.setText(tmp);
                tvns6.setText(temp);

                temp = tvssr.getText().toString();
                tmp = tvnssr.getText().toString();
                tvssr.setText(tmp);
                tvnssr.setText(temp);

                if(cbwd.isChecked())
                {
                    cbwd.setChecked(false);
                }
                if(cbnb.isChecked())
                {
                    cbnb.setChecked(false);
                }
                if(cbby.isChecked())
                {
                    cbby.setChecked(false);
                }
                if(cblb.isChecked())
                {
                    cblb.setChecked(false);
                }
                if(cbw.isChecked())
                {
                    cbw.setChecked(false);
                }


                rootnode=FirebaseDatabase.getInstance();
                ref=rootnode.getReference("Teams");

                String str = tvsb.getText().toString();
                String nstr = tvnsb.getText().toString();
                String blr = tvb.getText().toString();

                String tm1 = tvt1.getText().toString();
                String tm2 = tvt2.getText().toString();

                HashMap<String,String> striker = new HashMap<>();
                striker.put("Name",str);
                striker.put("Runs",String.valueOf(sr));
                striker.put("BallsFaced",String.valueOf(sbl));
                striker.put("NoOfFours",String.valueOf(s4));
                striker.put("NoOfSixes",String.valueOf(s6));
                striker.put("StrikeRate",String.valueOf(ssr));

                HashMap<String,String> nonstriker = new HashMap<>();
                nonstriker.put("Name",nstr);
                nonstriker.put("Runs",String.valueOf(nsr));
                nonstriker.put("BallsFaced",String.valueOf(nsbl));
                nonstriker.put("NoOfFours",String.valueOf(ns4));
                nonstriker.put("NoOfSixes",String.valueOf(ns6));
                nonstriker.put("StrikeRate",String.valueOf(nssr));

                HashMap<String,String> bowler = new HashMap<>();
                bowler.put("Name",blr);
                bowler.put("NoOfWickets",String.valueOf(w));
                bowler.put("Runs",String.valueOf(br));
                bowler.put("EconomyRate",String.valueOf(ber));



                if (ovr != 6)
                {
                    tvbo.setText(String.valueOf(x[ovr-1]+no));
                    tvto.setText(String.valueOf(x[ovr-1]+no));
                    bowler.put("NoOfOvers",String.valueOf(x[ovr-1]+no));

                }
                else
                {
                    tvbo.setText(String.valueOf(x[ovr-1]+no));
                    tvto.setText(String.valueOf(x[ovr-1]+no));
                    bowler.put("NoOfOvers",String.valueOf(x[ovr-1]+no));
                    no++;
                    ovr=0;
                }



                ref.child(tm1).child(str).setValue(striker);
                ref.child(tm1).child(nstr).setValue(nonstriker);
                ref.child(tm2).child(blr).setValue(bowler);

            }
        });


        btn4 = findViewById(R.id.btn4);
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tscr = tscr + 4;
                tvtsr.setText(String.valueOf(tscr) + "-" + String.valueOf(w));

                ovr++;
                br = br + 4;
                tvbr.setText(String.valueOf(br));

                tvbw.setText(String.valueOf(w));
                if(no<1)
                    tvber.setText(String.valueOf(br));
                else
                {
                    ber = (br/no);
                    tvber.setText(String.format("%.2f",ber));
                }
                if(c==0)
                {
                    sbl = sbl + 1;
                    sr = sr + 4;
                    s4 = s4 + 1;
                    s6 = s6 + 0;
                    ssr = (sr / sbl) * 100;
                    tvsr.setText(String.valueOf(sr));
                    tvsbl.setText(String.valueOf(sbl));
                    tvs4.setText(String.valueOf(s4));
                    tvs6.setText(String.valueOf(s6));
                    tvssr.setText(String.valueOf(ssr));
                }
                else
                {
                    nsbl = nsbl + 1;
                    nsr = nsr + 4;
                    ns4 = ns4 + 1;
                    ns6 = ns6 + 0;
                    nssr = (nsr / nsbl) * 100;
                    tvsr.setText(String.valueOf(nsr));
                    tvsbl.setText(String.valueOf(nsbl));
                    tvs4.setText(String.valueOf(ns4));
                    tvs6.setText(String.valueOf(ns6));
                    tvssr.setText(String.valueOf(nssr));
                }



                if(cbwd.isChecked())
                {
                    cbwd.setChecked(false);
                }
                if(cbnb.isChecked())
                {
                    cbnb.setChecked(false);
                }
                if(cbby.isChecked())
                {
                    cbby.setChecked(false);
                }
                if(cblb.isChecked())
                {
                    cblb.setChecked(false);
                }
                if(cbw.isChecked())
                {
                    cbw.setChecked(false);
                }


                rootnode=FirebaseDatabase.getInstance();
                ref=rootnode.getReference("Teams");

                String str = tvsb.getText().toString();
                String nstr = tvnsb.getText().toString();
                String blr = tvb.getText().toString();

                String tm1 = tvt1.getText().toString();
                String tm2 = tvt2.getText().toString();

                HashMap<String,String> striker = new HashMap<>();
                striker.put("Name",str);
                striker.put("Runs",String.valueOf(sr));
                striker.put("BallsFaced",String.valueOf(sbl));
                striker.put("NoOfFours",String.valueOf(s4));
                striker.put("NoOfSixes",String.valueOf(s6));
                striker.put("StrikeRate",String.valueOf(ssr));

                HashMap<String,String> nonstriker = new HashMap<>();
                nonstriker.put("Name",nstr);
                nonstriker.put("Runs",String.valueOf(nsr));
                nonstriker.put("BallsFaced",String.valueOf(nsbl));
                nonstriker.put("NoOfFours",String.valueOf(ns4));
                nonstriker.put("NoOfSixes",String.valueOf(ns6));
                nonstriker.put("StrikeRate",String.valueOf(nssr));

                HashMap<String,String> bowler = new HashMap<>();
                bowler.put("Name",blr);
                bowler.put("NoOfWickets",String.valueOf(w));
                bowler.put("Runs",String.valueOf(br));
                bowler.put("EconomyRate",String.valueOf(ber));



                if (ovr != 6)
                {
                    tvbo.setText(String.valueOf(x[ovr-1]+no));
                    tvto.setText(String.valueOf(x[ovr-1]+no));
                    bowler.put("NoOfOvers",String.valueOf(x[ovr-1]+no));

                }
                else
                {
                    tvbo.setText(String.valueOf(x[ovr-1]+no));
                    tvto.setText(String.valueOf(x[ovr-1]+no));
                    bowler.put("NoOfOvers",String.valueOf(x[ovr-1]+no));
                    no++;
                    ovr=0;
                }



                ref.child(tm1).child(str).setValue(striker);
                ref.child(tm1).child(nstr).setValue(nonstriker);
                ref.child(tm2).child(blr).setValue(bowler);

            }
        });


        btn6 = findViewById(R.id.btn6);
        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tscr = tscr + 6;
                tvtsr.setText(String.valueOf(tscr) + "-" + String.valueOf(w));

                ovr++;
                br = br + 6;
                tvbr.setText(String.valueOf(br));
                tvbw.setText(String.valueOf(w));
                if(no<1)
                    tvber.setText(String.valueOf(br));
                else
                {
                    ber = (br/no);
                    tvber.setText(String.format("%.1f",ber));
                }

                if(c==0)
                {
                    sbl = sbl + 1;
                    sr = sr + 6;
                    s4 = s4 + 0;
                    s6 = s6 + 1;
                    ssr = (sr / sbl) * 100;
                    tvsr.setText(String.valueOf(sr));
                    tvsbl.setText(String.valueOf(sbl));
                    tvs4.setText(String.valueOf(s4));
                    tvs6.setText(String.valueOf(s6));
                    tvssr.setText(String.valueOf(ssr));
                }
                else
                {
                    nsbl = nsbl + 1;
                    nsr = nsr + 6;
                    ns4 = ns4 + 0;
                    ns6 = ns6 + 1;
                    nssr = (nsr / nsbl) * 100;
                    tvsr.setText(String.valueOf(nsr));
                    tvsbl.setText(String.valueOf(nsbl));
                    tvs4.setText(String.valueOf(ns4));
                    tvs6.setText(String.valueOf(ns6));
                    tvssr.setText(String.valueOf(nssr));
                }


                if(cbwd.isChecked())
                {
                    cbwd.setChecked(false);
                }
                if(cbnb.isChecked())
                {
                    cbnb.setChecked(false);
                }
                if(cbby.isChecked())
                {
                    cbby.setChecked(false);
                }
                if(cblb.isChecked())
                {
                    cblb.setChecked(false);
                }
                if(cbw.isChecked())
                {
                    cbw.setChecked(false);
                }


                rootnode=FirebaseDatabase.getInstance();
                ref=rootnode.getReference("Teams");

                String str = tvsb.getText().toString();
                String nstr = tvnsb.getText().toString();
                String blr = tvb.getText().toString();

                String tm1 = tvt1.getText().toString();
                String tm2 = tvt2.getText().toString();

                HashMap<String,String> striker = new HashMap<>();
                striker.put("Name",str);
                striker.put("Runs",String.valueOf(sr));
                striker.put("BallsFaced",String.valueOf(sbl));
                striker.put("NoOfFours",String.valueOf(s4));
                striker.put("NoOfSixes",String.valueOf(s6));
                striker.put("StrikeRate",String.valueOf(ssr));

                HashMap<String,String> nonstriker = new HashMap<>();
                nonstriker.put("Name",nstr);
                nonstriker.put("Runs",String.valueOf(nsr));
                nonstriker.put("BallsFaced",String.valueOf(nsbl));
                nonstriker.put("NoOfFours",String.valueOf(ns4));
                nonstriker.put("NoOfSixes",String.valueOf(ns6));
                nonstriker.put("StrikeRate",String.valueOf(nssr));

                HashMap<String,String> bowler = new HashMap<>();
                bowler.put("Name",blr);
                bowler.put("NoOfWickets",String.valueOf(w));
                bowler.put("Runs",String.valueOf(br));
                bowler.put("EconomyRate",String.valueOf(ber));



                if (ovr != 6)
                {
                    tvbo.setText(String.valueOf(x[ovr-1]+no));
                    tvto.setText(String.valueOf(x[ovr-1]+no));
                    bowler.put("NoOfOvers",String.valueOf(x[ovr-1]+no));

                }
                else
                {
                    tvbo.setText(String.valueOf(x[ovr-1]+no));
                    tvto.setText(String.valueOf(x[ovr-1]+no));
                    bowler.put("NoOfOvers",String.valueOf(x[ovr-1]+no));
                    no++;
                    ovr=0;
                }



                ref.child(tm1).child(str).setValue(striker);
                ref.child(tm1).child(nstr).setValue(nonstriker);
                ref.child(tm2).child(blr).setValue(bowler);


            }
        });


        btnswb = findViewById(R.id.btnswb);
        btnswb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String temp = tvsb.getText().toString();
                String tmp = tvnsb.getText().toString();
                tvsb.setText(tmp);
                tvnsb.setText(temp);

                temp = tvsbl.getText().toString();
                tmp = tvnsbl.getText().toString();
                tvsbl.setText(tmp);
                tvnsbl.setText(temp);

                temp = tvsr.getText().toString();
                tmp = tvnsr.getText().toString();
                tvsr.setText(tmp);
                tvnsr.setText(temp);

                temp = tvs4.getText().toString();
                tmp = tvns4.getText().toString();
                tvs4.setText(tmp);
                tvns4.setText(temp);

                temp = tvs6.getText().toString();
                tmp = tvns6.getText().toString();
                tvs6.setText(tmp);
                tvns6.setText(temp);

                temp = tvssr.getText().toString();
                tmp = tvnssr.getText().toString();
                tvssr.setText(tmp);
                tvnssr.setText(temp);

                if(c==0)
                    c = c + 1;
                else
                    c=0;

            }
        });


        btnrtr = findViewById(R.id.btnrtr);
        btnrtr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Scorecard.this,RetireBatsman.class);
                startActivity(intent);

            }
        });

    }


    private void hnb()
    {
        this.getWindow().getDecorView()
                .setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION |
                        View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION |
                        View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
    }


    @Override
    public void onBackPressed() {

        if(backpresstime + 2000 >System.currentTimeMillis())
        {
            super.onBackPressed();
            Intent intent = new Intent(Scorecard.this,FirstFragment.class);
            startActivity(intent);
            return;
        }
        else
        {
            Toast.makeText(getBaseContext(), " Press back again to EXIT ", Toast.LENGTH_SHORT).show();
        }
        backpresstime = System.currentTimeMillis();


    }
}