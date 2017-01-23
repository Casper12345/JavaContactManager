package Tests;

import com.intellij.util.containers.ArrayListSet;
import org.junit.Before;
import org.junit.Test;
import src.Contact;
import src.ContactImpl;
import src.PastMeeting;
import src.PastMeetingImpl;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Set;


import static org.junit.Assert.*;

/**
 * JUnit test for src.PastMeetingImpl
 */
public class PastMeetingImplTest {
    private PastMeeting pastMeetingTestTwo;
    private Calendar calendar = new GregorianCalendar(2016,11,30,12,12);
    private Set<Contact> contacts = new ArrayListSet<>();
    private String notes = "myNotesAreImportant";
    private boolean Throw;

    @Before
    public void before(){
        contacts.add(new ContactImpl(1,"John", "John is cool"));
        Throw = false;
        pastMeetingTestTwo = new PastMeetingImpl(1,calendar, contacts, notes);

    }

    @Test
    public void constructorTestOne(){

        try{
            new PastMeetingImpl(1,calendar,contacts,null);
        }catch (NullPointerException ex){
            Throw = true;
        }

        assertTrue(Throw);
        // set Throw to false
        Throw = false;

        try{
           new PastMeetingImpl(1,calendar,contacts,notes);
        }catch (NullPointerException ex){
            Throw = true;
        }
        assertFalse(Throw);

    }

    @Test
    public void getNotes() throws Exception {
        String notesReturned = pastMeetingTestTwo.getNotes();
        String notesForTest = "myNotesAreImportant";

        assertEquals(notesReturned, notesForTest );


    }

}