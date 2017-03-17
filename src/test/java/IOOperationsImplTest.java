package test.java;

import com.intellij.util.containers.ArrayListSet;
import main.java.impl.ContactImpl;
import main.java.impl.ContactManagerImpl;
import main.java.impl.IOOperationsImpl;
import main.java.spec.Contact;
import main.java.spec.ContactManager;
import main.java.spec.FutureMeeting;
import main.java.spec.IOOperations;
import main.java.spec.PastMeeting;
import org.junit.Assert;
import org.junit.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

/**
 * JUnit test for main.java.spec.IOOperations.
 * The tests are run as a bundle from one methods, as they have to be run in a
 * certain order.
 */
public class IOOperationsImplTest {
    /**
     * IO Operations.
     */
    private IOOperations testIO = new IOOperationsImpl();
    /**
     * ContactManager for test purpose.
     */
    private ContactManager testContactManager = new ContactManagerImpl();

    /**
     * Main method that generates and runs all the tests together
     * to achieve specific order of tests.
     */
    @Test
    public void testAll() {
        IOOperationsImplTest i = new IOOperationsImplTest();
        i.writeContactsToFile();
        i.readFromFile();
        i.writeFutureMeetingsToFile();
        i.writeFutureMeetingsToFile();
        i.writePastMeetingsToFile();
        i.readContactsFromFile();
        i.readFutureMeetingFromFile();
        i.readPastMeetingFromFile();
    }

    /**
     * See desc.
     */
    public void writeContactsToFile() {
        testIO.overWriteFile();
        Contact first = new ContactImpl(23, "Paolo", "Blah, blah, blah");
        Contact second = new ContactImpl(34, "Erik", "more, more, more");
        Set<Contact>  testContacts = new ArrayListSet<>();
        testContacts.add(first);
        testContacts.add(second);

        testContactManager.addNewContact("Josh", "Notes, yes");
        Set<Contact> contactsReturned  =
                testContactManager.getContacts("Josh");
        Contact returned = (Contact) contactsReturned.toArray()[0];
        returned.addNotes("no");
        Set<Contact> contactsReturnedTwo =
                testContactManager.getContacts("Josh");
        Contact returnedTwo = (Contact) contactsReturnedTwo.toArray()[0];

        testContacts.add(returnedTwo);
        testIO.writeContactsToFile(testContacts);
    }

    /**
     * See desc.
     */
    public void readFromFile() {

        IOOperations testIO = new IOOperationsImpl();
        List<List<String>> input = testIO.readFromFile();

        assertEquals(input.get(0).get(0), "main.java.spec.Contact");
        assertEquals(input.get(1).get(0), "main.java.spec.Contact");
        assertEquals(input.get(0).get(1), "23");
        assertEquals(input.get(1).get(1), "34");
        assertEquals(input.get(0).get(2), "Paolo");
        assertEquals(input.get(1).get(2), "Erik");
        assertEquals(input.get(0).get(3), "Blah, blah, blah");
        assertEquals(input.get(1).get(3), "more, more, more");
        assertEquals(input.get(2).get(3), "Notes, yes" + "\n" + "no");
    }

    /**
     * See desc.
     */
    public void writeFutureMeetingsToFile() {

        testIO.overWriteFile();
        int id1 = testContactManager.addNewContact("Wilson", "Notes, yes");
        int id2 = testContactManager.addNewContact("Kate", "Hello");
        testContactManager.getContacts(id1, id2);

        Calendar testCal = new GregorianCalendar(2019, 2, 21, 11, 12);

        int meetingId = testContactManager
                .addFutureMeeting(testContactManager
                        .getContacts(id1, id2), testCal);

        Set<FutureMeeting> testMeetingSet = new ArrayListSet<>();
        testMeetingSet.add(testContactManager.getFutureMeeting(meetingId));

        testIO.writeFutureMeetingsToFile(testMeetingSet);

        IOOperations testIO = new IOOperationsImpl();
        List<List<String>> input = testIO.readFromFile();
        assertEquals(input.get(0).get(2), "2019:2:21:11:12");
        assertEquals(input.get(0).get(3), "[" + id1 + ", " + id2 + "]");
        testIO.writeContactsToFile(testContactManager.getContacts(id1, id2));
    }

    /**
     * See desc.
     */
    public void writePastMeetingsToFile() {

        int id1 = testContactManager.addNewContact("Miles", "Notes, yes");
        int id2 = testContactManager.addNewContact("Jill", "Hello");

        Calendar testCal = new GregorianCalendar(2012, 2, 21, 11, 12);

        int meetingId = testContactManager
                .addNewPastMeeting(testContactManager
                        .getContacts(id1, id2), testCal, "Notes");

        Set<PastMeeting> testMeetingSet = new ArrayListSet<>();
        testMeetingSet.add(testContactManager.getPastMeeting(meetingId));

        testIO.writePastMeetingsToFile(testMeetingSet);

        IOOperations testIO = new IOOperationsImpl();
        List<List<String>> input = testIO.readFromFile();
        assertEquals(input.get(3).get(4), "Notes");
        testIO.writeContactsToFile(testContactManager.getContacts(id1, id2));

    }

    /**
     * See desc.
     */
    public void readContactsFromFile() {

        int id1 = testContactManager
                .addNewContact("Quinn", "Notes from hell");
        int id2 = testContactManager
                .addNewContact("Jerry", "Notes from heaven");

        Set<Contact> writeTOFile = testContactManager.getContacts(id1, id2);

        testIO.writeContactsToFile(writeTOFile);

        Set<Contact> testSet = testIO.readContactsFromFile();

        Contact one = (Contact) testSet.toArray()[4];
        Contact two = (Contact) testSet.toArray()[5];

        Assert.assertEquals(id1, one.getId());
        Assert.assertEquals(id2, two.getId());
    }

    /**
     * See desc.
     */
    public void readFutureMeetingFromFile() {
        // creating test future meeting

        int id1 = testContactManager.addNewContact("Bill", "Notes, yes");
        int id2 = testContactManager.addNewContact("Monkey", "Hello");

        Calendar testCal = new GregorianCalendar(2020, 2, 21, 11, 12);

        int meetingId = testContactManager
                .addFutureMeeting(testContactManager
                        .getContacts(id1, id2), testCal);

        Set<FutureMeeting> testMeetingSet = new ArrayListSet<>();
        testMeetingSet.add(testContactManager.getFutureMeeting(meetingId));

        testIO.writeContactsToFile(testContactManager.getContacts(id1, id2));

        testIO.writeFutureMeetingsToFile(testMeetingSet);

        testIO.readContactsFromFile();

        Set<FutureMeeting> returnedFutureMeetings =
                testIO.readFutureMeetingFromFile();

        FutureMeeting testMeetingReturned =
                (FutureMeeting) returnedFutureMeetings.toArray()[1];

        Contact testContactReturnedOne =
                (Contact) testMeetingReturned.getContacts().toArray()[0];

        Contact testContactReturnedTwo =
                (Contact) testMeetingReturned.getContacts().toArray()[1];

        Assert.assertEquals(testContactReturnedOne.getName(), "Bill");
        Assert.assertEquals(testContactReturnedTwo.getName(), "Monkey");
    }

    /**
     * See desc.
     */
    public void readPastMeetingFromFile() {

        Set<PastMeeting> pastMeetingsReturned =
                testIO.readPastMeetingFromFile();

        PastMeeting returnedPastMeeting =
                (PastMeeting) pastMeetingsReturned.toArray()[0];

        Assert.assertEquals(returnedPastMeeting.getNotes(), "Notes");
    }
}
