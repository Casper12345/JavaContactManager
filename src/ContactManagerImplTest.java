import com.intellij.util.containers.ArrayListSet;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.hamcrest.*;

import java.util.*;

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

    // addFutureMeeting

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

    // getPastMeeting

    @Test
    public void returnsPastMeetingFromId() throws Exception {

        Calendar pastCalendar = new GregorianCalendar(2011,10,12,12,12);

        PastMeeting testMeetingOne = new PastMeetingImpl(45, pastCalendar, contacts, "Notes");
        PastMeeting testMeetingTwo = new PastMeetingImpl(34, pastCalendar, contacts, "MoreNotes");
        Set<PastMeeting> testPastMeetingsSet = new ArrayListSet<>();
        testPastMeetingsSet.add(testMeetingOne);
        testPastMeetingsSet.add(testMeetingTwo);


        testContactManager.setPastMeetingSetMeetingSet(testPastMeetingsSet);

        PastMeeting pastMeetingReturned = testContactManager.getPastMeeting(45);
        assertEquals(testMeetingOne.getId(), pastMeetingReturned.getId());
        PastMeeting pastMeetingReturnedTwo = testContactManager.getPastMeeting(34);
        assertEquals(testMeetingTwo, pastMeetingReturnedTwo);

    }

    @Test
    public void returnsNullWhenNotFound(){
        Calendar pastCalendar = new GregorianCalendar(2011,10,12,12,12);

        PastMeeting testMeetingOne = new PastMeetingImpl(45, pastCalendar, contacts, "Notes");
        PastMeeting testMeetingTwo = new PastMeetingImpl(34, pastCalendar, contacts, "MoreNotes");
        Set<PastMeeting> testPastMeetingsSet = new ArrayListSet<>();
        testPastMeetingsSet.add(testMeetingOne);
        testPastMeetingsSet.add(testMeetingTwo);

        testContactManager.setPastMeetingSetMeetingSet(testPastMeetingsSet);

        PastMeeting pastMeetingReturned = testContactManager.getPastMeeting(700);
        assertNull(pastMeetingReturned);


    }

    @Test
    public void meetingFromTheFuture(){

        try {

            Calendar pastCalendar = new GregorianCalendar(2020, 10, 12, 12, 12);

            PastMeeting testMeetingOne = new PastMeetingImpl(45, pastCalendar, contacts, "Notes");
            Set<PastMeeting> testPastMeetingsSet = new ArrayListSet<>();
            testPastMeetingsSet.add(testMeetingOne);

            testContactManager.setPastMeetingSetMeetingSet(testPastMeetingsSet);

            PastMeeting pastMeetingReturned = testContactManager.getPastMeeting(45);

        }catch (IllegalStateException ex){
            Throw = true;
        }

        assertTrue(Throw);

    }

    // getFutureMeeting

    @Test
    public void returnsFutureMeetingFromId() throws Exception {

        Calendar futureCalendar = new GregorianCalendar(2018,10,12,12,12);

        FutureMeeting testMeetingOne = new FutureMeetingImpl(45, futureCalendar, contacts);
        FutureMeeting testMeetingTwo = new FutureMeetingImpl(34, futureCalendar, contacts);
        Set<FutureMeeting> testFutureMeetingsSet = new ArrayListSet<>();
        testFutureMeetingsSet.add(testMeetingOne);
        testFutureMeetingsSet.add(testMeetingTwo);


        testContactManager.setFutureMeetingSet(testFutureMeetingsSet);

        FutureMeeting futureMeetingReturned = testContactManager.getFutureMeeting(45);
        assertEquals(testMeetingOne.getId(), futureMeetingReturned.getId());
        FutureMeeting futureMeetingReturnedTwo = testContactManager.getFutureMeeting(34);
        assertEquals(testMeetingTwo, futureMeetingReturnedTwo);

    }

    @Test
    public void futureMeetingReturnsNullWhenNotFound(){
        Calendar futureCalendar = new GregorianCalendar(2018,10,12,12,12);

        FutureMeeting testMeetingOne = new FutureMeetingImpl(45, futureCalendar, contacts);
        FutureMeeting testMeetingTwo = new FutureMeetingImpl(34, futureCalendar, contacts);
        Set<FutureMeeting> testFutureMeetingsSet = new ArrayListSet<>();
        testFutureMeetingsSet.add(testMeetingOne);
        testFutureMeetingsSet.add(testMeetingTwo);


        testContactManager.setFutureMeetingSet(testFutureMeetingsSet);

        FutureMeeting futureMeetingReturned = testContactManager.getFutureMeeting(700);
        assertNull(futureMeetingReturned);


    }

    @Test
    public void meetingFromThePast(){

        try {

            Calendar pastCalendar = new GregorianCalendar(2010, 10, 12, 12, 12);

            FutureMeeting testMeetingOne = new FutureMeetingImpl(45, pastCalendar, contacts);
            Set<FutureMeeting> testFutureMeetingsSet = new ArrayListSet<>();
            testFutureMeetingsSet.add(testMeetingOne);


            testContactManager.setFutureMeetingSet(testFutureMeetingsSet);

            FutureMeeting futureMeetingReturned = testContactManager.getFutureMeeting(45);

        }catch (IllegalStateException ex){
            Throw = true;
        }

        assertTrue(Throw);

    }

    // getMeeting


    @Test
    public void getMeetingGetId() throws Exception {
        Calendar futureCalendar = new GregorianCalendar(2018,10,12,12,12);

        FutureMeeting testMeetingOne = new FutureMeetingImpl(45, futureCalendar, contacts);
        PastMeeting testMeetingTwo = new PastMeetingImpl(34, calendar, contacts, "NewNotes");
        Set<FutureMeeting> testFutureMeetingsSet = new ArrayListSet<>();
        testFutureMeetingsSet.add(testMeetingOne);
        Set<PastMeeting> testPastMeetingsSet = new ArrayListSet<>();
        testPastMeetingsSet.add(testMeetingTwo);
        testContactManager.setFutureMeetingSet(testFutureMeetingsSet);
        testContactManager.setPastMeetingSetMeetingSet(testPastMeetingsSet);


        Meeting meetingReturned = testContactManager.getMeeting(34);

        assertEquals(testMeetingTwo, meetingReturned);


    }
    @Test
    public void getMeetingGetIdReturnNull() throws Exception {

        Calendar futureCalendar = new GregorianCalendar(2018,10,12,12,12);

        FutureMeeting testMeetingOne = new FutureMeetingImpl(45, futureCalendar, contacts);
        FutureMeeting testMeetingTwo = new FutureMeetingImpl(34, futureCalendar, contacts);
        Set<FutureMeeting> testFutureMeetingsSet = new ArrayListSet<>();
        testFutureMeetingsSet.add(testMeetingOne);
        testFutureMeetingsSet.add(testMeetingTwo);


        testContactManager.setFutureMeetingSet(testFutureMeetingsSet);

        Meeting MeetingReturned = testContactManager.getFutureMeeting(322);
        assertNull(MeetingReturned);


    }

    //getFutureMeetingList

    @Test
    public void getFutureMeetingListReturnsList() throws Exception {
        Contact contact = new ContactImpl(1,"Peter", "myNotes");
        Contact contact1 = new ContactImpl(2,"John", "moreNotes");
        Contact contact2 = new ContactImpl(10,"Josh", "notieNotes");

        // setting contactSet to avoid IllegalArgumentException
        Set<Contact> fullContactSet = new ArrayListSet<>();
        fullContactSet.add(contact);
        fullContactSet.add(contact1);
        fullContactSet.add(contact2);
        testContactManager.setContactSet(fullContactSet);


        Set<Contact> contactTestSet = new ArrayListSet<>();
        contactTestSet.add(contact);
        contactTestSet.add(contact1);

        Set<Contact> contactTestSetTwo = new ArrayListSet<>();
        contactTestSetTwo.add(contact);
        contactTestSetTwo.add(contact2);


        Set<FutureMeeting> emptySet = new ArrayListSet<>();

        testContactManager.setFutureMeetingSet(emptySet);

        testContactManager.addFutureMeeting(contactTestSet,calendar);
        testContactManager.addFutureMeeting(contactTestSetTwo,calendar);


        List<Meeting> futureMeetingListReturned = testContactManager.getFutureMeetingList(contact);

        assertEquals(futureMeetingListReturned.get(0).getContacts(),contactTestSet);
        assertEquals(futureMeetingListReturned.get(1).getContacts(),contactTestSetTwo);


    }

    @Test
    public void getFutureMeetingListRemoveDuplicates() throws Exception {

        Contact contact = new ContactImpl(1,"Peter", "myNotes");
        Contact contact1 = new ContactImpl(2,"John", "moreNotes");
        Contact contact2 = new ContactImpl(10,"Josh", "notieNotes");

        // setting contactSet to avoid IllegalArgumentException
        Set<Contact> fullContactSet = new ArrayListSet<>();
        fullContactSet.add(contact);
        fullContactSet.add(contact1);
        fullContactSet.add(contact2);
        testContactManager.setContactSet(fullContactSet);


        Set<Contact> contactTestSet = new ArrayListSet<>();
        contactTestSet.add(contact);
        contactTestSet.add(contact1);

        Set<Contact> contactTestSetTwo = new ArrayListSet<>();
        contactTestSetTwo.add(contact);
        contactTestSetTwo.add(contact2);


        Set<FutureMeeting> emptySet = new ArrayListSet<>();

        testContactManager.setFutureMeetingSet(emptySet);

        testContactManager.addFutureMeeting(contactTestSet,calendar);
        testContactManager.addFutureMeeting(contactTestSet,calendar);


        List<Meeting> futureMeetingListReturned = testContactManager.getFutureMeetingList(contact);

        assertNotEquals(futureMeetingListReturned.get(0),futureMeetingListReturned.get(1));


    }
    @Test
    public void getFutureMeetingListReturnsEmptyList() throws Exception {

        Contact contact = new ContactImpl(1,"Peter", "myNotes");
        Contact contact1 = new ContactImpl(2,"John", "moreNotes");
        Contact contact2 = new ContactImpl(10,"Josh", "notieNotes");

        // setting contactSet to avoid IllegalArgumentException
        Set<Contact> fullContactSet = new ArrayListSet<>();
        fullContactSet.add(contact);
        fullContactSet.add(contact1);
        fullContactSet.add(contact2);
        testContactManager.setContactSet(fullContactSet);


        Set<Contact> contactTestSet = new ArrayListSet<>();
        contactTestSet.add(contact);
        contactTestSet.add(contact1);


        Set<FutureMeeting> emptySet = new ArrayListSet<>();

        testContactManager.setFutureMeetingSet(emptySet);

        testContactManager.addFutureMeeting(contactTestSet,calendar);

        List<Meeting> futureMeetingListReturned = testContactManager.getFutureMeetingList(contact2);

        assertTrue(futureMeetingListReturned.isEmpty());


    }

    @Test
    public void getFutureMeetingListChronological() throws Exception {

        Contact contact = new ContactImpl(1,"Peter", "myNotes");
        Contact contact1 = new ContactImpl(2,"John", "moreNotes");
        Contact contact2 = new ContactImpl(10,"Josh", "notieNotes");

        // setting contactSet to avoid IllegalArgumentException
        Set<Contact> fullContactSet = new ArrayListSet<>();
        fullContactSet.add(contact);
        fullContactSet.add(contact1);
        fullContactSet.add(contact2);
        testContactManager.setContactSet(fullContactSet);


        Set<Contact> contactTestSet = new ArrayListSet<>();
        contactTestSet.add(contact);
        contactTestSet.add(contact1);

        Set<FutureMeeting> emptySet = new ArrayListSet<>();

        testContactManager.setFutureMeetingSet(emptySet);

        Calendar firstCalendar = new GregorianCalendar(2020,0,10,12,12);
        Calendar secondCalendar = new GregorianCalendar(2020,1,10,12,12);
        Calendar thirdCalendar = new GregorianCalendar(2020,2,10,12,12);

        testContactManager.addFutureMeeting(contactTestSet, thirdCalendar);
        testContactManager.addFutureMeeting(contactTestSet,firstCalendar);
        testContactManager.addFutureMeeting(contactTestSet,secondCalendar);


        List<Meeting> futureMeetingListReturned = testContactManager.getFutureMeetingList(contact);

        assertTrue(futureMeetingListReturned.get(0).getDate().before(futureMeetingListReturned.get(1).getDate()));
        assertTrue(futureMeetingListReturned.get(1).getDate().before(futureMeetingListReturned.get(2).getDate()));



    }

    @Test
    public void getFutureMeetingListThrowNullPointerException() throws Exception {

        Contact contact = new ContactImpl(1,"Peter", "myNotes");
        Contact contact1 = new ContactImpl(2,"John", "moreNotes");

        Set<Contact> contactTestSet = new ArrayListSet<>();
        contactTestSet.add(contact);
        contactTestSet.add(contact1);

        Set<FutureMeeting> emptySet = new ArrayListSet<>();

        testContactManager.setFutureMeetingSet(emptySet);

        testContactManager.addFutureMeeting(contactTestSet,calendar);

        try {

            testContactManager.getFutureMeetingList(null);


        }catch (NullPointerException ex){
            Throw = true;
        }

        assertTrue(Throw);

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