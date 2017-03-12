package main.java.impl;

import main.java.spec.PastMeeting;
import java.util.Calendar;
import java.util.Set;

/**
 * Implements main.java.spec.PastMeeting.
 */
public class PastMeetingImpl extends MeetingImpl implements PastMeeting {
    /**
     * Notes of pastMeeting not included in MeetingImpl.
     */
    private String notes;

    /**
     * Constructor method.
     * @param id of pastMeeting
     * @param date of pastMeeting
     * @param contacts set of participants
     * @param notesToAdd for pastMeeting
     * @throws NullPointerException if notes are null
     */
    public PastMeetingImpl(final int id, final Calendar date,
                           final Set contacts, final String notesToAdd)
            throws NullPointerException {

        super(id, date, contacts);

        if (notesToAdd == null) {
            throw new NullPointerException();
        }
        this.notes = notesToAdd;
    }

    /**
     * Implements getNotes.
     * @return notes of pastMeeting
     */
    @Override
    public String getNotes() {
        return notes;
    }

    /**
     * Additional method to be able to add notes.
     * @param notesToAdd
     * Additional notes
     */
    public void addToNotes(final String notesToAdd) {
        this.notes += "\n" + notesToAdd;
    }

}
