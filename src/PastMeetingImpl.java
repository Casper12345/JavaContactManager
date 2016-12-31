import com.intellij.util.containers.ArrayListSet;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Set;

/**
 * Created by Casper on 25/12/2016.
 */
public class PastMeetingImpl extends MeetingImpl implements PastMeeting {

    private String notes;


    public PastMeetingImpl(int id, Calendar date, Set contacts, String notes){
        super(id, date, contacts);
        this.notes = notes;
    }

    public String getNotes(){
        return "";
    }


}
