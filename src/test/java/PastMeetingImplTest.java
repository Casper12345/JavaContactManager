package test.java;

import com.intellij.util.containers.ArrayListSet;
import org.junit.Before;
import org.junit.Test;
import main.java.spec.Contact;
import main.java.impl.ContactImpl;
import main.java.spec.PastMeeting;
import main.java.impl.PastMeetingImpl;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Set;
import static org.junit.Assert.*;

/**
 * JUnit test for main.java.impl.PastMeetingImpl.
 */
public class PastMeetingImplTest {
    /**
     * Past meeting for test.
     */
    private PastMeeting pastMeetingTestTwo;
    /**
     * Calendar for test.
     */
    private Calendar calendar = new GregorianCalendar(2016, 11, 30, 12, 12);
    /**
     * Contacts for test.
     */
    private Set<Contact> contacts = new ArrayListSet<>();
    /**
     * Notes for test.
     */
    private String notes = "myNotesAreImportant";
    /**
     * Boolean to assert if exception is thrown.
     */
    private boolean isThrown;

    /**
     * Before.
     */
    @Before
    public void before() {
        contacts.add(new ContactImpl(1, "John", "John is cool"));
        isThrown = false;
        pastMeetingTestTwo = new PastMeetingImpl(1, calendar, contacts, notes);
    }

    /**
     * See desc.
     */
    @Test
    public void constructorTestOne() {

        try {
            new PastMeetingImpl(1, calendar, contacts, null);
        } catch (NullPointerException ex) {
            isThrown = true;
        }

        assertTrue(isThrown);
        // set isThrown to false
        isThrown = false;

        try {
           new PastMeetingImpl(1, calendar, contacts, notes);
        } catch (NullPointerException ex) {
            isThrown = true;
        }
        assertFalse(isThrown);

    }

    /**
     * See desc.
     * @throws Exception ex
     */
    @Test
    public void getNotes() throws Exception {
        String notesReturned = pastMeetingTestTwo.getNotes();
        String notesForTest = "myNotesAreImportant";

        assertEquals(notesReturned, notesForTest);
    }
}
