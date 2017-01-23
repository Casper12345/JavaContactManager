package src;

import java.util.Calendar;
import java.util.Set;

/**
 * Implements src.PastMeeting
 */
public class PastMeetingImpl extends MeetingImpl implements PastMeeting {

    private String notes;

    public PastMeetingImpl(int id, Calendar date, Set contacts, String notes) throws NullPointerException {

        super(id, date, contacts);

        if(notes == null){
            throw new NullPointerException();
        }
        this.notes = notes;
    }

    @Override
    public String getNotes(){
        return notes;
    }

    /**
     * Additional method to be able to add notes
     * @param notes
     * Additional notes
     */
    public void addToNotes(String notes){
        this.notes += "\n" + notes;
    }

}
