package com.hamster.interndice;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by hamster on 16/6/1.
 *
 * Utility for performing operations of voluntary
 */
public class VoluntaryUtils {
    /**
     * Find all UNCONFIRMED voluntary with given destination
     *
     * @param dest    destination to search for
     * @param vols    (ArrayList) of voluntary
     * @param ordinal find in this ordinal
     * @return (ArrayList) of voluntary found
     */
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

    /**
     * Confirm all voluntary with given ordinal
     *
     * @param vols    (ArrayList) of voluntary
     * @param ordinal confirm voluntary at this ordinal
     */
    static void confirmAll(ArrayList<Voluntary> vols, int ordinal) {
        for (Voluntary vol : vols) {
            vol.confirm(ordinal);
        }
    }

    /* For sorting use */
    private static class Container {
        public double rate;
        public Destinations.DestDesc dest;
    }

    /**
     * Sort the destination list by popularity (the `OFFER/REQUEST` ratio)
     *
     * @param vols    (ArrayList) of voluntary to gather popularity info
     * @param ordinal order of voluntary
     * @return (ArrayList) of destination companies, with popularity ascending
     */
    static ArrayList<Destinations.DestDesc> sortByMostWanted(ArrayList<Voluntary> vols, int ordinal) {
        ArrayList<Container> conts = new ArrayList<>(7);

        /* Calculate popularity one by one */
        for (Destinations.DestDesc dest : Destinations.allDestinations) {
            int wantedCount = findAllVoluntaryWithDest(dest, vols, ordinal).size();
            if (wantedCount != 0) {
                double wantedRate = (double) dest.getAvailablePositions() / wantedCount;
                Container cont = new Container();
                cont.rate = wantedRate;
                cont.dest = dest;
                conts.add(cont);
            }
        }

        /* Sort by popularity */
        Comparator<Container> comparator = new Comparator<Container>() {
            @Override
            public int compare(Container lhs, Container rhs) {
                if (lhs.rate < rhs.rate)
                    return -1;
                else if (lhs.rate == rhs.rate)
                    return 0;
                else
                    return 1;
            }
        };
        Collections.sort(conts, comparator);

        /* Unpack things */
        ArrayList<Destinations.DestDesc> result = new ArrayList<>(conts.size());
        for (Container cont : conts) {
            result.add(cont.dest);
        }
        return result;
    }

    /**
     * Get all unconfirmed voluntary
     *
     * @param vols (ArrayList) of voluntary
     * @return (ArrayList) of unconfirmed voluntary
     */
    static ArrayList<Voluntary> getAllRemaining(ArrayList<Voluntary> vols) {
        ArrayList<Voluntary> result = new ArrayList<>(26);

        for (Voluntary vol : vols) {
            if (!vol.isConfirmed())
                result.add(vol);
        }
        return result;
    }

    /**
     * Generate specific number of unique random numbers in given range
     *
     * @param min range min
     * @param max range max
     * @param num number of results
     * @return ArrayList of random numbers
     */
    private static ArrayList<Integer> getRandomNumbers(int min, int max, int num) {
        ArrayList<Integer> result = new ArrayList<>(max - min + 1);

        if (max - min + 1 < num || num <= 0) {
            //ResultPresenter.getInstance().printSingleLine("ERROR: random numbers cannot be generated! min=max=num="+min+"," +max+","+num);
            return result; /* Empty */
        } else if (max - min + 1 == num) {
            for (int i = min; i <= max; i++) {
                result.add(i);
            }
        } else {
            int collected = 0;
            int rand;
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

    /**
     * Select given number of voluntary in the list randomly
     *
     * @param vols   all the voluntary
     * @param number number to select
     * @return (ArrayList) of voluntary selected
     */
    static ArrayList<Voluntary> getRandomSelection(ArrayList<Voluntary> vols, int number) {
        ArrayList<Integer> rand = getRandomNumbers(0, vols.size(), number);

        ArrayList<Voluntary> result = new ArrayList<>(number);

        for (Integer i : rand) {
            result.add(vols.get(i));
        }
        return result;
    }
}
