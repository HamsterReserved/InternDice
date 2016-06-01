package com.hamster.interndice;

import java.util.ArrayList;

/**
 * Created by hamster on 16/6/1.
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
        Destinations.DestDesc lastMostWanted = null;

        for (; currentRound <= 2; currentRound++) {
            ResultPresenter.getInstance().printSingleLine("ROUND " + currentRound + " started!");

            for (Destinations.DestDesc dest : Destinations.allDestinations) {
                ArrayList<Voluntary> currentDestVoluntary = VoluntaryUtils.
                        findAllVoluntaryWithDest(dest, allVoluntary, currentRound);

                if (currentDestVoluntary.size() > 0 &&
                        currentDestVoluntary.size() <= dest.getAvailablePosititons()) {
                    ResultPresenter.getInstance().printSingleLine("In this round, "
                            + dest.getDescription() + " did not reach its limit (or exactly full).");
                    VoluntaryUtils.confirmAll(currentDestVoluntary, currentRound);
                }
            }


            for (int i = 0; i < Destinations.allDestinations.length; i++) {
                ArrayList<Voluntary> currentVoluntary = VoluntaryUtils.getAllRemaining(allVoluntary);
                if (currentVoluntary.size() == 0) {
                    return; // Finished
                }
                Destinations.DestDesc mostWantedDest = VoluntaryUtils.findMostWantedDest(currentVoluntary,
                        currentRound);
                if (!mostWantedDest.equals(lastMostWanted)) {
                    ResultPresenter.getInstance().printSingleLine("SUB-ROUND for most wanted target: "
                            + mostWantedDest.getDescription());
                    lastMostWanted = mostWantedDest;
                    currentVoluntary = VoluntaryUtils.getRandomSelection(currentVoluntary,
                            Math.min(mostWantedDest.getAvailablePosititons(), currentVoluntary.size()));
                    if (currentVoluntary.size() == 0) {
                        ResultPresenter.getInstance().printSingleLine("In this sub-round, the most wanted target is "
                                + mostWantedDest.getDescription()
                                + " but it has run out of positions. "
                                + "Some people will LOSE their choice. What a pity!");
                    } else {
                        VoluntaryUtils.confirmAll(currentVoluntary, currentRound);
                    }
                }
            }
            lastMostWanted = null;
        }

        ResultPresenter.getInstance().printSingleLine("FINISHED. These are the remaining:");
        for (Voluntary vol : allVoluntary) {
            if (!vol.isConfirmed())
                ResultPresenter.getInstance().printSingleLine(vol.studentName);
        }
    }
}
