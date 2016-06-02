package com.hamster.interndice;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by hamster on 16/6/2.
 * <p/>
 * Parser for Excel CSV
 */
public class VoteFileParser {
    public static void parse(DiceOperator operator, String path) {
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(path));
        } catch (Exception e) {
            ResultPresenter.getInstance().printSingleLine("ERROR: File not found: " + path);
            return;
        }

        String line = "";
        ResultPresenter.getInstance().printSingleLine(reader.toString());
        try {
            while (true) {
                line = reader.readLine();
                if (line == null || line.isEmpty()) {
                    break;
                }
                String parts[] = line.split(",");
                ArrayList<Destinations.DestDesc> dests = new ArrayList<>(3);
                dests.add(Destinations.allDestinations[Integer.parseInt(parts[1])]);
                dests.add(Destinations.allDestinations[Integer.parseInt(parts[2])]);
                dests.add(Destinations.allDestinations[Integer.parseInt(parts[3])]);
                operator.addVoluntary(parts[0], dests);
            }
        } catch (IOException e) {
            ResultPresenter.getInstance().printSingleLine("ERROR: IOException "
                    + e.getLocalizedMessage());
        } catch (NumberFormatException e) {
            ResultPresenter.getInstance().printSingleLine("ERROR: not a number "
                    + e.getLocalizedMessage());
        } catch (IndexOutOfBoundsException e) {
            ResultPresenter.getInstance().printSingleLine("ERROR: file format error, too little elements "
                    + e.getLocalizedMessage());
        }
    }
}
