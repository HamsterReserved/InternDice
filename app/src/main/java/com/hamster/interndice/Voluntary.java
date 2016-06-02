package com.hamster.interndice;

import java.util.ArrayList;

/**
 * Created by hamster on 16/6/1.
 * <p/>
 * Data model for a voluntary
 */
public class Voluntary {
    public String studentName;
    public ArrayList<Destinations.DestDesc> destination = new ArrayList<>(3);
    private boolean isConfirmed;

    public boolean isConfirmed() {
        return isConfirmed;
    }

    /**
     * Confirm this voluntary at given ordinal
     *
     * @param ordinal confirm this ordinal
     */
    public void confirm(int ordinal) {
        if (!isConfirmed) {
            isConfirmed = true;
            destination.get(ordinal).occupyOne();
            ResultPresenter.getInstance().printSingleLine("Confirmed: "
                    + studentName + " goes to " + destination.get(ordinal).getDescription());
        } else {
            ResultPresenter.getInstance().printSingleLine("ERROR: Repeated confirmation: "
                    + studentName + " goes to " + destination.get(ordinal).getDescription());
        }
    }
}
