package main.java.impl;

import main.java.spec.Contact;
import java.util.Calendar;
import java.util.Set;

/**
 * Abstract class implements meeting.
 */
public abstract class MeetingImpl {
    /**
     * Id of meeting.
     */
    private int id;
    /**
     * Calendar date of meeting.
     */
    private Calendar date;
    /**
     * Set of contacts participating in the meeting.
     */
    private Set contacts;

    /**
     * Constructor method.
     * @param idToSet of meeting
     * @param dateToSet of meeting
     * @param contactsToSet set of participating contacts
     * @throws IllegalArgumentException thrown if id is negative
     * @throws NullPointerException if set of contacts is empty
     */
    public MeetingImpl(final int idToSet,
                       final Calendar dateToSet, final Set contactsToSet)
            throws IllegalArgumentException, NullPointerException {
        this.id = idToSet;
        this.date = dateToSet;
        this.contacts = contactsToSet;

        if (id < 0) {
            throw new IllegalArgumentException();
        }

        if (contacts.isEmpty()) {
            throw new IllegalArgumentException();
        }
        // I only throw if date is null, as null instance
        // of set automatically throws a NullPointException
        if (date == null) {
            throw new NullPointerException();
        }
    }

    /**
     * Accessor for id.
     * @return id of meeting
     */
    public int getId() {
        return id;
    }

    /**
     * Accessor for date.
     * @return date for meeting
     */
    public Calendar getDate() {
        return date;
    }

    /**
     * Accessor for contacts.
     * @return set of contacts
     */
    public Set<Contact> getContacts() {
        return contacts;
    }
}

