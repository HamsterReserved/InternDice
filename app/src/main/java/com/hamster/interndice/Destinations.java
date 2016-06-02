package com.hamster.interndice;

/**
 * Created by hamster on 16/6/1.
 *
 * All possible destinations and number of their offers.
 */
public class Destinations {
    public static class DestDesc {
        private String description;
        private int availablePositions;

        private DestDesc(String desc, int pos) {
            description = desc;
            availablePositions = pos;
        }

        public String getDescription() {
            return description;
        }

        public int getAvailablePositions() {
            return availablePositions;
        }

        public void occupyOne() {
            if (availablePositions > 0) {
                availablePositions--;
            } else {
                ResultPresenter.getInstance().printSingleLine("ERROR: " + description + " is already fully booked!");
            }
        }
    }

    public static DestDesc DEST_XIAN = new DestDesc("Xi'an Xidian", 9);
    public static DestDesc DEST_SHANGHAI_MOTOR = new DestDesc("Shanghai Motor", 4);
    public static DestDesc DEST_EAST_MOTOR = new DestDesc("East Motor", 3);
    public static DestDesc DEST_CHANGZHOU = new DestDesc("Changzhou Xidian", 3);
    public static DestDesc DEST_NANJING_PANNENG = new DestDesc("Nanjing Panneng", 3);
    public static DestDesc DEST_WUHAN = new DestDesc("Wuhan Haomai", 2);
    public static DestDesc DEST_SHANGHAI_SIYUAN = new DestDesc("Shanghai Siyuan", 2);

    public static DestDesc allDestinations[] = {
            DEST_XIAN,
            DEST_SHANGHAI_MOTOR,
            DEST_EAST_MOTOR,
            DEST_CHANGZHOU,
            DEST_NANJING_PANNENG,
            DEST_WUHAN,
            DEST_SHANGHAI_SIYUAN
    };
}