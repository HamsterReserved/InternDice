package com.hamster.interndice;

import android.util.Log;

/**
 * Created by hamster on 16/6/1.
 *
 * Print the result. This is way too weak, maybe we can improve it, a lot.
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
        Log.i(TAG, "printSingleLine: " + line);
    }
}
