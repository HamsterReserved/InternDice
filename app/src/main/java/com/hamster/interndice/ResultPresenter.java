package com.hamster.interndice;

import android.util.Log;

/**
 * Created by hamster on 16/6/1.
 */
public class ResultPresenter {
    private static ResultPresenter instance = new ResultPresenter();
    private static String TAG = "InternDice_Result";

    private ResultPresenter() {
    }

    public static ResultPresenter getInstance() {
        return instance;
    }

    public void printSingleLine(String line) {
        // TODO Use log for now
        Log.i(TAG, "printSingleLine: " + line);
    }
}
