package test.java;

import com.intellij.util.containers.ArrayListSet;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import main.java.spec.Contact;
import main.java.impl.ContactImpl;
import main.java.impl.MeetingImpl;
import main.java.impl.MeetingMockClass;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.junit.Assert.*;

/**
 * Junit test for TDD of main.java.impl.MeetingImpl.
 */
public class MeetingImplTest {
    /**
     * Test meeting.
     */
    private MeetingImpl testMeetingTwo;

    /**
     * Before.
     */
    @Before
    public void setUpTest() {
        ArrayListSet<Contact> myContacts = new ArrayListSet<>();
        myContacts.add(new ContactImpl(1, "George", "myImportantNotes"));
        testMeetingTwo = new MeetingMockClass(1, new GregorianCalendar(2016,
                Calendar.DECEMBER, 24, 13, 12), myContacts);
    }

    /**
     * See desc.
     */
    @Test
    public void constructorTestPartOne() {
        boolean isThrown = false;

        try {
            new MeetingMockClass(1,
                    new GregorianCalendar(2016, Calendar.DECEMBER, 24, 13, 12),
                    new ArrayListSet());
        } catch (IllegalArgumentException ex) {
            isThrown = true;
        }

        assertTrue(isThrown);
        // set isThrown back to false
        isThrown = false;
        // creating contact set
        ArrayListSet<Contact> contacts = new ArrayListSet<>();
        contacts.add(new ContactImpl(1, "George", "myImportantNotes"));

        try {
            new MeetingMockClass(1, new GregorianCalendar(2016,
                    Calendar.DECEMBER, 24, 13, 12), contacts);
        } catch (IllegalArgumentException ex) {
            isThrown = true;
        }

        assertFalse(isThrown);
    }

    /**
     * See desc.
     */
    @Test
    public void constructorTestPartTwo() {
        boolean isThrown = false;
        ArrayListSet<Contact> contacts = new ArrayListSet<>();
        contacts.add(new ContactImpl(1, "George", "myImportantNotes"));

        try {
            new MeetingMockClass(-1, new GregorianCalendar(2016,
                    Calendar.DECEMBER, 24, 13, 12), contacts);

        } catch (IllegalArgumentException ex) {
            isThrown = true;
        }
        assertTrue(isThrown);

        // set isThrown back to false
        isThrown = false;

        try {
            new MeetingMockClass(1, new GregorianCalendar(2016,
                    Calendar.DECEMBER, 24, 13, 12),
                    contacts);

        } catch (IllegalArgumentException ex) {
            isThrown = true;
        }

        assertFalse(isThrown);
    }

    /**
     * See desc.
     */
    @Test
    public void constructorTestPartThree() {
        boolean isThrown = false;
        ArrayListSet<Contact> contacts = new ArrayListSet<>();
        contacts.add(new ContactImpl(1, "George", "myImportantNotes"));

        try {
            new MeetingMockClass(1, null, contacts);

        } catch (NullPointerException ex) {
            isThrown = true;
        }

        assertTrue(isThrown);
        // set isThrown back to false
        isThrown = false;

        try {
            new MeetingMockClass(1, new GregorianCalendar(2016,
                    Calendar.DECEMBER, 24, 13, 12),
                    contacts);

        } catch (NullPointerException ex) {
            isThrown = true;
        }

        assertFalse(isThrown);

    }

    /**
     * See desc.
     */
    @Test
    public void getId() {

        Assert.assertEquals(1, testMeetingTwo.getId());
        Assert.assertNotEquals(2, testMeetingTwo.getId());
    }

    /**
     * See desc.
     */
    @Test
    public void getDate() {

        Assert.assertEquals(new GregorianCalendar(2016,
                        Calendar.DECEMBER, 24, 13, 12),
                testMeetingTwo.getDate());
        Assert.assertNotEquals(new GregorianCalendar(2019,
                        Calendar.DECEMBER, 28, 23, 30),
                testMeetingTwo.getDate());
    }

    /**
     * See desc.
     */
    @Test
    public void getContacts() {
        ArrayListSet<Contact> contacts = new ArrayListSet<>();
        contacts.add(new ContactImpl(1, "George", "myImportantNotes"));
        for (Contact i: contacts) {
            for (Contact j : testMeetingTwo.getContacts()) {
                Assert.assertEquals(i.getId(), j.getId());
            }
        }
    }
}
