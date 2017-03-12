package main.java.impl;

import java.util.Calendar;
import java.util.Set;

/**
 * MockUp class for main.java.impl.MeetingImpl.
 * This class serves for testing purposes.
 */
public class MeetingMockClass extends MeetingImpl {
    /**
     * Constructor.
     * @param id of meeting
     * @param date of meeting
     * @param contacts of participants
     */
    public MeetingMockClass(final int id, final Calendar date,
                            final Set contacts) {
        super(id, date, contacts);
    }
}
