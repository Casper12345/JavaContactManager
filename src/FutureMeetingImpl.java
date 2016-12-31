import java.util.Calendar;
import java.util.Set;

/**
 * Implements FutureMeeting and extends MeetingImpl
 */
public class FutureMeetingImpl extends MeetingImpl implements FutureMeeting {

    public FutureMeetingImpl(int id, Calendar date, Set contacts){
        super(id, date, contacts);
    }

}
