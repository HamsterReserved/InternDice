package com.hamster.interndice;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by hamster on 16/6/2.
 * <p/>
 * Parser for Excel CSV
 *
 * Format: "NAME,1stID_Of_Voluntary,2ndID,3rdID"
 *
 * Empty lines are not allowed.
 */
public class VoteFileParser {
    public static void parse(DiceOperator operator, String path) {
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(path));
        } catch (FileNotFoundException e) {
            ResultPresenter.getInstance().printSingleLine("ERROR: File not found: " + path);
            return;
        }

        String line;
        try {
            while (true) {
                line = reader.readLine();
                if (line == null || line.isEmpty()) {
                    break;
                }
                String parts[] = line.split(",");
                ArrayList<Destinations.DestDesc> dests = new ArrayList<>(4);
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
            ResultPresenter.getInstance().printSingleLine("ERROR: file format error, too few elements "
                    + e.getLocalizedMessage());
        }
    }
}
