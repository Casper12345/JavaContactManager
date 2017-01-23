package src;

import java.util.Calendar;
import java.util.Set;

/**
 * Abstract class implements meeting.
 */
public abstract class MeetingImpl {

    private int id;
    private Calendar date;
    private Set contacts;

    public MeetingImpl(int id, Calendar date, Set contacts) throws IllegalArgumentException, NullPointerException{
        this.id = id;
        this.date = date;
        this.contacts = contacts;

        if(id < 0){
            throw new IllegalArgumentException();
        }

        if(contacts.isEmpty()){
            throw new IllegalArgumentException();
        }
        // I only throw if date is null, as null instance of set automatically throws a NullPointException
        if(date == null){
            throw new NullPointerException();
        }

    }

    public int getId(){
        return id;
    }

    public Calendar getDate(){
        return date;
    }

    public Set<Contact> getContacts(){
        return contacts;
    }


}

