package com.hamster.interndice;

import java.util.ArrayList;

/**
 * Created by hamster on 16/6/1.
 *
 * Main logic for picking people.
 */
public class DiceOperator {
    private ArrayList<Voluntary> allVoluntary = new ArrayList<>(26);

    public void addVoluntary(String name, ArrayList<Destinations.DestDesc> dests) {
        Voluntary newVol = new Voluntary();
        newVol.studentName = name;
        newVol.destination = dests;
        allVoluntary.add(newVol);
    }

    public void run() {
        int currentRound = 0;

        /* 3x voluntary, loop 3 times */
        for (; currentRound <= 2; currentRound++) {
            ResultPresenter.getInstance().printSingleLine("ROUND " + currentRound + " started!");

            /* Check if any target company... */
            for (Destinations.DestDesc dest : Destinations.allDestinations) {
                ArrayList<Voluntary> currentDestVoluntary = VoluntaryUtils.
                        findAllVoluntaryWithDest(dest, allVoluntary, currentRound);

                /* ...offers more than students' requests... */
                if (currentDestVoluntary.size() > 0 &&
                        currentDestVoluntary.size() <= dest.getAvailablePositions()) {
                    ResultPresenter.getInstance().printSingleLine("In this round, "
                            + dest.getDescription() + " did not reach its limit (or exactly full).");

                    /* ...so we can fill up the positions now. */
                    VoluntaryUtils.confirmAll(currentDestVoluntary, currentRound);
                }
            }

            /* For the targets offering less than requests... */
            ArrayList<Voluntary> currentVoluntary = VoluntaryUtils.getAllRemaining(allVoluntary);
            /* ...we sort the company list by popularity... */
            ArrayList<Destinations.DestDesc> mostWantedDest = VoluntaryUtils.sortByMostWanted(currentVoluntary,
                    currentRound);

            for (Destinations.DestDesc dest : mostWantedDest) {
                ResultPresenter.getInstance().printSingleLine("SUB-ROUND for most wanted target: "
                        + dest.getDescription());
                currentVoluntary = VoluntaryUtils.findAllVoluntaryWithDest(dest, currentVoluntary, currentRound);

                /* ...and ramdonly pick students... */
                currentVoluntary = VoluntaryUtils.getRandomSelection(currentVoluntary,
                        Math.min(dest.getAvailablePositions(), currentVoluntary.size()));
                if (currentVoluntary.size() == 0) {
                    ResultPresenter.getInstance().printSingleLine("In this sub-round, the most wanted target is "
                            + dest.getDescription()
                            + " but it has run out of positions. "
                            + "Some people will LOSE their choice. What a pity!");
                } else {
                    /* ...to fill the targets one by one. */
                    VoluntaryUtils.confirmAll(currentVoluntary, currentRound);
                }

                /* Prepare for next destination.
                 * currentVoluntary is findAllVoluntaryWithDest'ed from this destination.
                 */
                currentVoluntary = VoluntaryUtils.getAllRemaining(allVoluntary);
            }
        }

        /* Some are unlucky and all the positions in their voluntary are taken */
        ResultPresenter.getInstance().printSingleLine("FINISHED. These are the remaining:");
        for (Voluntary vol : allVoluntary) {
            if (!vol.isConfirmed())
                ResultPresenter.getInstance().printSingleLine(vol.studentName);
        }
    }
}
