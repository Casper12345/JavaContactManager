import org.junit.Before;
import org.junit.Test;
import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.junit.Assert.*;

/**
 * JUnit test for ContactManagerImpl
 */
public class ContactManagerImplTest {
    @Before
    public void setUp() throws Exception {

    }


    @Test
    public void addFutureMeeting() throws Exception {
        Calendar testCalendarOne = Calendar.getInstance();
        System.out.println(testCalendarOne.getTime());
        Calendar testCalendarTwo = new GregorianCalendar(2016,12,28,11,11);
        assertTrue(testCalendarOne.before(testCalendarTwo));



    }

    @Test
    public void getPastMeeting() throws Exception {

    }

    @Test
    public void getFutureMeeting() throws Exception {

    }

    @Test
    public void getMeeting() throws Exception {

    }

    @Test
    public void getFutureMeetingList() throws Exception {

    }

    @Test
    public void getMeetingListOn() throws Exception {

    }

    @Test
    public void getPastMeetingListFor() throws Exception {

    }

    @Test
    public void addNewPastMeeting() throws Exception {

    }

    @Test
    public void addMeetingNotes() throws Exception {

    }

    @Test
    public void addNewContact() throws Exception {

    }

    @Test
    public void getContacts() throws Exception {

    }

    @Test
    public void getContacts1() throws Exception {

    }

    @Test
    public void flush() throws Exception {

    }

}