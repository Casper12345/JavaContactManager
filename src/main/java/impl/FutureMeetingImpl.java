package main.java.impl;

import main.java.spec.FutureMeeting;
import java.util.Calendar;
import java.util.Set;

/**
 * Implements main.java.spec.FutureMeeting
 * and extends main.java.impl.MeetingImpl.
 */
public class FutureMeetingImpl extends MeetingImpl implements FutureMeeting {

    /**
     * Implements future meeting.
     * @param id is id of future meeting
     * @param date is date of future meeting
     * @param contacts is contacts of future meeting
     */
    public FutureMeetingImpl(final int id,
                             final Calendar date, final Set contacts) {
        super(id, date, contacts);
    }
}
