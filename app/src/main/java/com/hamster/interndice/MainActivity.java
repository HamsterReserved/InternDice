package com.hamster.interndice;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DiceOperator operator = new DiceOperator();

        ArrayList<Destinations.DestDesc> desc1 = new ArrayList<>(3);
        desc1.add(Destinations.DEST_NANJING_PANNENG);
        desc1.add(Destinations.DEST_NANJING_PANNENG);
        desc1.add(Destinations.DEST_NANJING_PANNENG);
        operator.addVoluntary("D1", desc1);

        ArrayList<Destinations.DestDesc> desc2 = new ArrayList<>(3);
        desc2.add(Destinations.DEST_WUHAN);
        desc2.add(Destinations.DEST_WUHAN);
        desc2.add(Destinations.DEST_WUHAN);
        operator.addVoluntary("D2", desc2);

        ArrayList<Destinations.DestDesc> desc3 = new ArrayList<>(3);
        desc3.add(Destinations.DEST_WUHAN);
        desc3.add(Destinations.DEST_WUHAN);
        desc3.add(Destinations.DEST_NANJING_PANNENG);
        operator.addVoluntary("D3", desc3);

        ArrayList<Destinations.DestDesc> desc4 = new ArrayList<>(3);
        desc4.add(Destinations.DEST_SHANGHAI_SIYUAN);
        desc4.add(Destinations.DEST_NANJING_PANNENG);
        desc4.add(Destinations.DEST_WUHAN);
        operator.addVoluntary("D4", desc4);

        ArrayList<Destinations.DestDesc> desc5 = new ArrayList<>(3);
        desc5.add(Destinations.DEST_WUHAN);
        desc5.add(Destinations.DEST_SHANGHAI_SIYUAN);
        desc5.add(Destinations.DEST_NANJING_PANNENG);
        operator.addVoluntary("D5", desc5);

        operator.run();
    }
}
