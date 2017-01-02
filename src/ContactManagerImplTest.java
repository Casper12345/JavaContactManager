import com.intellij.util.containers.ArrayListSet;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Set;

import static org.junit.Assert.*;

/**
 * JUnit test for ContactManagerImpl
 */
public class ContactManagerImplTest {

    private ContactManagerImpl testContactManager;
    private boolean Throw;
    private Set<Contact> contacts;
    private Calendar calendar;


    @Before
    public void setUp() throws Exception {
        testContactManager = new ContactManagerImpl();
        Throw = false;
        Contact contact = new ContactImpl(1,"Peter", "myNotes");
        Contact contact1 = new ContactImpl(2,"John", "moreNotes");
        contacts = new ArrayListSet<>();
        contacts.add(contact);
        contacts.add(contact1);

        Contact contact2 = new ContactImpl(1,"Peter", "myNotes");
        Contact contact3 = new ContactImpl(2,"John", "moreNotes");
        Set<Contact> testContactSet = new ArrayListSet<>();
        testContactSet.add(contact2);
        testContactSet.add(contact3);

        testContactManager.setContactSet(testContactSet);

        calendar = new GregorianCalendar(2018,1,22,12,12);
    }


    @Test
    public void addFutureMeetingOne() throws Exception {
        // setting calender to past date
        calendar = new GregorianCalendar(2010,1,22,12,12);

        try{
            testContactManager.addFutureMeeting(contacts,calendar);
        }catch (IllegalArgumentException ex){
            Throw = true;
        }

        assertTrue(Throw);




    }

    @Test
    public void addFutureMeetingTwo(){

        //check if contact is not existing

        Contact contact2 = new ContactImpl(3,"Poul", "myNotes");
        Contact contact3 = new ContactImpl(4,"Finn", "moreNotes");
        Set<Contact> testContactSet = new ArrayListSet<>();
        testContactSet.add(contact2);
        testContactSet.add(contact3);

        testContactManager.setContactSet(testContactSet);


        try{
            testContactManager.addFutureMeeting(contacts,calendar);
        }catch (IllegalArgumentException ex){
            Throw = true;
        }

        assertTrue(Throw);
        // set Throw to false
        Throw = false;

        Contact contact = new ContactImpl(1,"Peter", "myNotes");
        Contact contact1 = new ContactImpl(2,"John", "moreNotes");
        Set<Contact> testContactSetTwo = new ArrayListSet<>();
        testContactSetTwo.add(contact);
        testContactSetTwo.add(contact1);

        testContactManager.setContactSet(testContactSetTwo);


        try{
            testContactManager.addFutureMeeting(contacts,calendar);
        }catch (IllegalArgumentException ex){
            Throw = true;
        }

        assertFalse(Throw);

        // Check if contact is unknown
        // set Throw to false
        Throw = false;

        // add more contacts to contacts

        Contact contact4 = new ContactImpl(3, "Eric", "SickNotes");
        Contact contact5 = new ContactImpl(4, "Sam", "Notifying");

        contacts.add(contact4);
        contacts.add(contact5);

        try{
            testContactManager.addFutureMeeting(contacts,calendar);
        }catch (IllegalArgumentException ex){
            Throw = true;
        }

        assertTrue(Throw);


    }

    /**
     * check if the date is null
     */
    @Test
    public void addFutureMeetingThree(){

        try{
            testContactManager.addFutureMeeting(contacts,null);
        }catch (NullPointerException ex){
            Throw = true;
        }

        assertTrue(Throw);
    }

    /**
     *
     * check if meeting is null
     */
    @Test
    @Ignore
    public void addFutureMeetingFour(){

        try{
            testContactManager.addFutureMeeting(contacts,calendar);
        }catch (NullPointerException ex){
            Throw = true;
        }

        assertTrue(Throw);

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