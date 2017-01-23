package src;

import java.util.Calendar;
import java.util.Set;

/**
 * Implements src.FutureMeeting and extends src.MeetingImpl
 */
public class FutureMeetingImpl extends MeetingImpl implements FutureMeeting {

    public FutureMeetingImpl(int id, Calendar date, Set contacts){
        super(id, date, contacts);
    }

}
