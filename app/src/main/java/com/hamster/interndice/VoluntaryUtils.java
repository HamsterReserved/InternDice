package com.hamster.interndice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 * Created by hamster on 16/6/1.
 */
public class VoluntaryUtils {
    static ArrayList<Voluntary> findAllVoluntaryWithDest(Destinations.DestDesc dest,
                                                         ArrayList<Voluntary> vols,
                                                         int ordinal) {
        ArrayList<Voluntary> result = new ArrayList<>(9);

        for (Voluntary vol : vols) {
            if (!vol.isConfirmed() && vol.destination.get(ordinal).equals(dest))
                result.add(vol);
        }
        return result;
    }

    static void confirmAll(ArrayList<Voluntary> vols, int ordinal) {
        for (Voluntary vol : vols) {
            vol.confirm(ordinal);
        }
    }

    static Destinations.DestDesc findMostWantedDest(ArrayList<Voluntary> vols, int ordinal) {
        HashMap<Double, Destinations.DestDesc> map = new HashMap<>(vols.size());
        for (Destinations.DestDesc dest : Destinations.allDestinations) {
            int wantedCount = findAllVoluntaryWithDest(dest, vols, ordinal).size();
            if (wantedCount != 0) {
                double wantedRate = (double) dest.getAvailablePosititons() / wantedCount;
                map.put(wantedRate, dest);
            }
        }

        Object[] key_arr = map.keySet().toArray();
        Arrays.sort(key_arr);
        return map.get(key_arr[key_arr.length - 1]);
    }

    static ArrayList<Voluntary> getAllRemaining(ArrayList<Voluntary> vols) {
        ArrayList<Voluntary> result = new ArrayList<>(26);

        for (Voluntary vol : vols) {
            if (!vol.isConfirmed())
                result.add(vol);
        }
        return result;
    }

    private static ArrayList<Integer> getRandomNumbers(int min, int max, int num) {
        ArrayList<Integer> result = new ArrayList<>(max - min + 1);

        if (max - min + 1 < num || num <= 0) {
            //TODO ERROR
            ResultPresenter.getInstance().printSingleLine("ERROR: random numbers cannot be generated!");
        } else if (max - min + 1 == num) {
            for (int i = min; i <= max; i++) {
                result.add(i);
            }
        } else {
            int collected = 1;
            int rand = (int) (Math.random() * (max - min) + min);
            result.add(rand);
            while (collected < num) {
                rand = (int) (Math.random() * (max - min) + min);
                if (!result.contains(rand)) {
                    result.add(rand);
                    collected++;
                }
            }
        }
        return result;
    }

    static ArrayList<Voluntary> getRandomSelection(ArrayList<Voluntary> vols, int number) {
        ArrayList<Integer> rand = getRandomNumbers(0, vols.size(), number);

        ArrayList<Voluntary> result = new ArrayList<>(number);

        for (Integer i : rand) {
            result.add(vols.get(i));
        }
        return result;
    }
}
