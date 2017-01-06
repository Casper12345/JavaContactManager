import com.intellij.util.ScrambledOutputStream;
import com.intellij.util.containers.ArrayListSet;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

/**
 * JUnit test for IOOperations.
 *
 */
public class IOOperationsImplTest {

    private IOOperations testIO = new IOOperationsImpl();
    private ContactManager testContactManager;

    @Before
    public void setUp(){
        testContactManager = new ContactManagerImpl();
    }


    @Test
    public void writeContactsToFile() throws Exception {
        testIO.overWriteFile();
        Contact first = new ContactImpl(23, "Paolo", "Blah, blah, blah");
        Contact second = new ContactImpl(34, "Erik", "more, more, more");
        Set<Contact>  testContacts = new ArrayListSet<>();
        testContacts.add(first);
        testContacts.add(second);

        testContactManager.addNewContact("Josh", "Notes, yes");
        Set<Contact> contactsReturned  = testContactManager.getContacts("Josh");
        Contact returned = (Contact) contactsReturned.toArray()[0];
        returned.addNotes("no");
        Set<Contact> contactsReturnedTwo  = testContactManager.getContacts("Josh");
        Contact returnedTwo = (Contact) contactsReturnedTwo.toArray()[0];

        testContacts.add(returnedTwo);
        testIO.writeContactsToFile(testContacts);

    }

    @Test
    public void readFromFile() throws Exception {
        IOOperations testIO = new IOOperationsImpl();
        List<List<String>> input = testIO.readFromFile();

        assertEquals(input.get(0).get(0), "Contact");
        assertEquals(input.get(1).get(0), "Contact");
        assertEquals(input.get(0).get(1), "23");
        assertEquals(input.get(1).get(1), "34");
        assertEquals(input.get(0).get(2), "Paolo");
        assertEquals(input.get(1).get(2), "Erik");
        assertEquals(input.get(0).get(3), "Blah, blah, blah");
        assertEquals(input.get(1).get(3), "more, more, more");
        assertEquals(input.get(2).get(3), "Notes, yes" + "\n" +"no");
    }

    @Test
    public void writeFutureMeetingsToFile() throws Exception {

        int id1 = testContactManager.addNewContact("Josh", "Notes, yes");
        int id2 = testContactManager.addNewContact("Jill", "Hello");
        testContactManager.getContacts(id1,id2);

        Calendar testCal = new GregorianCalendar(2019,2,21,11,12);

        int meetingId = testContactManager.addFutureMeeting(testContactManager.getContacts(id1,id2), testCal);

        Set<FutureMeeting> testMeetingSet = new ArrayListSet<>();
        testMeetingSet.add(testContactManager.getFutureMeeting(meetingId));

        testIO.writeFutureMeetingsToFile(testMeetingSet);

        IOOperations testIO = new IOOperationsImpl();
        List<List<String>> input = testIO.readFromFile();
        assertEquals(input.get(3).get(2),"2019:2:21:11:12");
        assertEquals(input.get(3).get(3),"["+id1+", "+id2+"]");

    }

    @Test
    public void writePastMeetingsToFile() throws Exception {

        int id1 = testContactManager.addNewContact("Josh", "Notes, yes");
        int id2 = testContactManager.addNewContact("Jill", "Hello");
        testContactManager.getContacts(id1,id2);

        Calendar testCal = new GregorianCalendar(2012,2,21,11,12);

        int meetingId = testContactManager.addNewPastMeeting(testContactManager.getContacts(id1,id2), testCal, "Notes");

        Set<PastMeeting> testMeetingSet = new ArrayListSet<>();
        testMeetingSet.add(testContactManager.getPastMeeting(meetingId));

        testIO.writePastMeetingsToFile(testMeetingSet);

        IOOperations testIO = new IOOperationsImpl();
        List<List<String>> input = testIO.readFromFile();
        assertEquals(input.get(4).get(4),"Notes");


    }

}