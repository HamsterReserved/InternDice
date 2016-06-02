package com.hamster.interndice;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DiceOperator operator = new DiceOperator();

        VoteFileParser.parse(operator, "/sdcard/233.txt");
        operator.run();
    }
}
