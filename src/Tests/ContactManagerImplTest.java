package Tests;

import com.intellij.util.containers.ArrayListSet;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import src.*;

import java.util.*;

import static org.junit.Assert.*;

/**
 * JUnit test for src.ContactManagerImpl
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

    // tests for addFutureMeeting

    @Test
    public void addFutureMeetingThrowsIllegalArgumentExceptionWithPastDate() throws Exception {
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
    public void addFutureMeetingThrowsIllegalArgumentExceptionWithContactUnknown(){

        //check for thrown exception with unknown contact

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

    @Test
    public void addFutureMeetingThrowsIllegalArgumentExceptionNullDate(){

        // throws IllegalArgumentsException if date is Null

        try{
            testContactManager.addFutureMeeting(contacts,null);
        }catch (NullPointerException ex){
            Throw = true;
        }

        assertTrue(Throw);
    }

    @Test
    public void addFutureMeetingThrowsNullPointerException(){

        // throw NullPointerException if contacts is null

        try{
            testContactManager.addFutureMeeting(null,calendar);
        }catch (NullPointerException ex){
            Throw = true;
        }

        assertTrue(Throw);
    }

    // tests for getPastMeeting

    @Test
    public void returnsPastMeetingFromId() throws Exception {

        // tests that past meeting is returned from id

        Calendar pastCalendar = new GregorianCalendar(2011,10,12,12,12);

        PastMeeting testMeetingOne = new PastMeetingImpl(45, pastCalendar, contacts, "Notes");
        PastMeeting testMeetingTwo = new PastMeetingImpl(34, pastCalendar, contacts, "MoreNotes");
        Set<PastMeeting> testPastMeetingsSet = new ArrayListSet<>();
        testPastMeetingsSet.add(testMeetingOne);
        testPastMeetingsSet.add(testMeetingTwo);


        testContactManager.setPastMeetingSet(testPastMeetingsSet);

        PastMeeting pastMeetingReturned = testContactManager.getPastMeeting(45);
        Assert.assertEquals(testMeetingOne.getId(), pastMeetingReturned.getId());
        PastMeeting pastMeetingReturnedTwo = testContactManager.getPastMeeting(34);
        Assert.assertEquals(testMeetingTwo, pastMeetingReturnedTwo);
    }

    @Test
    public void returnsNullWhenNotFound(){

        // tests that null is returned when not found

        Calendar pastCalendar = new GregorianCalendar(2011,10,12,12,12);

        PastMeeting testMeetingOne = new PastMeetingImpl(45, pastCalendar, contacts, "Notes");
        PastMeeting testMeetingTwo = new PastMeetingImpl(34, pastCalendar, contacts, "MoreNotes");
        Set<PastMeeting> testPastMeetingsSet = new ArrayListSet<>();
        testPastMeetingsSet.add(testMeetingOne);
        testPastMeetingsSet.add(testMeetingTwo);

        testContactManager.setPastMeetingSet(testPastMeetingsSet);

        PastMeeting pastMeetingReturned = testContactManager.getPastMeeting(700);
        assertNull(pastMeetingReturned);
    }

    @Test
    public void meetingFromTheFuture(){

        // tests that IllegalStateException is thrown with future meeting

        try {

            Calendar pastCalendar = new GregorianCalendar(2020, 10, 12, 12, 12);

            PastMeeting testMeetingOne = new PastMeetingImpl(45, pastCalendar, contacts, "Notes");
            Set<PastMeeting> testPastMeetingsSet = new ArrayListSet<>();
            testPastMeetingsSet.add(testMeetingOne);

            testContactManager.setPastMeetingSet(testPastMeetingsSet);

            PastMeeting pastMeetingReturned = testContactManager.getPastMeeting(45);

        }catch (IllegalStateException ex){
            Throw = true;
        }

        assertTrue(Throw);
    }

    // tests for getFutureMeeting

    @Test
    public void returnsFutureMeetingFromId() throws Exception {

        // tests that future meeting is retrieved from id

        Calendar futureCalendar = new GregorianCalendar(2018,10,12,12,12);

        FutureMeeting testMeetingOne = new FutureMeetingImpl(45, futureCalendar, contacts);
        FutureMeeting testMeetingTwo = new FutureMeetingImpl(34, futureCalendar, contacts);
        Set<FutureMeeting> testFutureMeetingsSet = new ArrayListSet<>();
        testFutureMeetingsSet.add(testMeetingOne);
        testFutureMeetingsSet.add(testMeetingTwo);

        testContactManager.setFutureMeetingSet(testFutureMeetingsSet);

        FutureMeeting futureMeetingReturned = testContactManager.getFutureMeeting(45);
        Assert.assertEquals(testMeetingOne.getId(), futureMeetingReturned.getId());
        FutureMeeting futureMeetingReturnedTwo = testContactManager.getFutureMeeting(34);
        Assert.assertEquals(testMeetingTwo, futureMeetingReturnedTwo);

    }

    @Test
    public void futureMeetingReturnsNullWhenNotFound(){

        // Checks that null is returned

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

        // checks that IllegalStateException is thrown with future meeting

        try {

            Calendar pastCalendar = new GregorianCalendar(2010, 10, 12, 12, 12);

            FutureMeeting testMeetingOne = new FutureMeetingImpl(45, pastCalendar, contacts);
            Set<FutureMeeting> testFutureMeetingsSet = new ArrayListSet<>();
            testFutureMeetingsSet.add(testMeetingOne);

            testContactManager.setFutureMeetingSet(testFutureMeetingsSet);

            testContactManager.getFutureMeeting(45);

        }catch (IllegalStateException ex){
            Throw = true;
        }

        assertTrue(Throw);
    }

    // tests for getMeeting

    @Test
    public void getMeetingGetId() throws Exception {

        // tests that meeting is retrieved from id

        Calendar futureCalendar = new GregorianCalendar(2018,10,12,12,12);

        FutureMeeting testMeetingOne = new FutureMeetingImpl(45, futureCalendar, contacts);
        PastMeeting testMeetingTwo = new PastMeetingImpl(34, calendar, contacts, "NewNotes");
        Set<FutureMeeting> testFutureMeetingsSet = new ArrayListSet<>();
        testFutureMeetingsSet.add(testMeetingOne);
        Set<PastMeeting> testPastMeetingsSet = new ArrayListSet<>();
        testPastMeetingsSet.add(testMeetingTwo);
        testContactManager.setFutureMeetingSet(testFutureMeetingsSet);
        testContactManager.setPastMeetingSet(testPastMeetingsSet);

        Meeting meetingReturned = testContactManager.getMeeting(34);

        Assert.assertEquals(testMeetingTwo, meetingReturned);

    }
    @Test
    public void getMeetingGetIdReturnNull() throws Exception {

        // tests that null is returned

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

    //tests for getFutureMeetingList

    @Test
    public void getFutureMeetingListReturnsList() throws Exception {

       // checks that list is returned

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

        // set futureMeetingSet to empty
        Set<FutureMeeting> emptySet = new ArrayListSet<>();
        testContactManager.setFutureMeetingSet(emptySet);
        testContactManager.addFutureMeeting(contactTestSet,calendar);
        testContactManager.addFutureMeeting(contactTestSetTwo,calendar);

        List<Meeting> futureMeetingListReturned = testContactManager.getFutureMeetingList(contact);

        Assert.assertEquals(futureMeetingListReturned.get(0).getContacts(),contactTestSet);
        Assert.assertEquals(futureMeetingListReturned.get(1).getContacts(),contactTestSetTwo);
    }

    @Test
    public void getFutureMeetingListRemoveDuplicates() throws Exception {

        // checks that duplicates are removed
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

        // set futureMeetingSet to empty
        Set<FutureMeeting> emptySet = new ArrayListSet<>();
        testContactManager.setFutureMeetingSet(emptySet);

        testContactManager.addFutureMeeting(contactTestSet,calendar);
        testContactManager.addFutureMeeting(contactTestSet,calendar);

        List<Meeting> futureMeetingListReturned = testContactManager.getFutureMeetingList(contact);

        Assert.assertNotEquals(futureMeetingListReturned.get(0),futureMeetingListReturned.get(1));

    }

    @Test
    public void getFutureMeetingListReturnsEmptyList() throws Exception {

        // checks that empty list is returned

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

        // set futureMeetingSet to empty
        Set<FutureMeeting> emptySet = new ArrayListSet<>();
        testContactManager.setFutureMeetingSet(emptySet);

        testContactManager.addFutureMeeting(contactTestSet,calendar);

        List<Meeting> futureMeetingListReturned = testContactManager.getFutureMeetingList(contact2);

        assertTrue(futureMeetingListReturned.isEmpty());
    }

    @Test
    public void getFutureMeetingListChronological() throws Exception {

        // checks that list is chronologically sorted
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

        // set futureMeetingSet to empty
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

        // check that NullPointerException is thrown with parameter null

        Contact contact = new ContactImpl(1,"Peter", "myNotes");
        Contact contact1 = new ContactImpl(2,"John", "moreNotes");

        Set<Contact> contactTestSet = new ArrayListSet<>();
        contactTestSet.add(contact);
        contactTestSet.add(contact1);

        // set futureMeetingSet to empty
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

    // getMeetingListOn

    @Test
    public void getMeetingListOnReturnsList() throws Exception {

        // checks that list is returned

        // set futureMeetingSet to empty
        Set<FutureMeeting> emptySet = new ArrayListSet<>();
        testContactManager.setFutureMeetingSet(emptySet);

        Calendar testCalendar = new GregorianCalendar(2012,0,12,12,12);
        PastMeeting testMeetingOne = new PastMeetingImpl(230, testCalendar, contacts, "notes");
        PastMeeting testMeetingTwo = new PastMeetingImpl(330, testCalendar, contacts, "moreNotes");

        Set<PastMeeting> testMeetingSet = new ArrayListSet<>();
        testMeetingSet.add(testMeetingOne);
        testMeetingSet.add(testMeetingTwo);

        testContactManager.setPastMeetingSet(testMeetingSet);

        List<Meeting> meetingsReturned = testContactManager.getMeetingListOn(testCalendar);

        Assert.assertEquals(meetingsReturned.get(0).getId(),230);
        Assert.assertEquals(meetingsReturned.get(1).getId(),330);

        testContactManager.addFutureMeeting(contacts,calendar);

        List<Meeting> meetingsReturnedTwo = testContactManager.getMeetingListOn(calendar);

        Assert.assertEquals(meetingsReturnedTwo.get(0).getDate(),calendar);
    }

    @Test
    public void getMeetingListOnReturnsEmptyList() throws Exception {

        // checks that empty list is returned

        // set futureMeetingSet to empty
        Set<FutureMeeting> emptySet = new ArrayListSet<>();
        testContactManager.setFutureMeetingSet(emptySet);

        GregorianCalendar testCalendar = new GregorianCalendar(2030, 1,1,1,12);

        List<Meeting> meetingsReturned = testContactManager.getMeetingListOn(testCalendar);

        testContactManager.addFutureMeeting(contacts,calendar);

        assertTrue(meetingsReturned.isEmpty());

    }

    @Test
    public void getMeetingListOnThrowNullPointerException() throws Exception {

        // checks that NullPointerException is thrown with null parameter

        try{
            List<Meeting> meetingsReturned = testContactManager.getMeetingListOn(null);
            testContactManager.addFutureMeeting(contacts,calendar);
            assertTrue(meetingsReturned.isEmpty());
        }catch (NullPointerException ex){
            Throw = true;
        }

        assertTrue(Throw);
    }

    // tests for getPastMeetingListFor

    @Test
    public void getPastMeetingListForReturnsList() throws Exception {

        // checks that list is returned

        // set futureMeetingSet to empty
        Set<PastMeeting> emptySet = new ArrayListSet<>();
        testContactManager.setPastMeetingSet(emptySet);

        Contact contact = new ContactImpl(1,"Peter", "myNotes");
        Contact contact1 = new ContactImpl(2,"John", "moreNotes");

        Set<Contact> contactTestSet = new ArrayListSet<>();
        contactTestSet.add(contact);
        contactTestSet.add(contact1);

        PastMeeting testMeetingOne = new PastMeetingImpl(230, calendar, contactTestSet, "notes");
        PastMeeting testMeetingTwo = new PastMeetingImpl(330, calendar, contactTestSet, "moreNotes");

        Set<PastMeeting> testMeetingSet = new ArrayListSet<>();
        testMeetingSet.add(testMeetingOne);
        testMeetingSet.add(testMeetingTwo);

        testContactManager.setPastMeetingSet(testMeetingSet);

        List<PastMeeting> meetingsReturned = testContactManager.getPastMeetingListFor(contact);

        Assert.assertEquals(meetingsReturned.get(0).getId(),230);
        Assert.assertEquals(meetingsReturned.get(1).getId(),330);

    }

    @Test
    public void getPastMeetingListForReturnsEmptyList() throws Exception {

        // checks that empty list is returned

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

        // set futureMeetingSet to empty
        Set<PastMeeting> emptySet = new ArrayListSet<>();
        testContactManager.setPastMeetingSet(emptySet);

        testContactManager.addFutureMeeting(contactTestSet,calendar);

        List<PastMeeting> pastMeetingListReturned = testContactManager.getPastMeetingListFor(contact2);

        assertTrue(pastMeetingListReturned.isEmpty());

    }
    @Test
    public void getPastMeetingListForChronological() throws Exception {

        // checks that list is sorted chronologically

        Contact contact = new ContactImpl(1, "Peter", "myNotes");
        Contact contact1 = new ContactImpl(2, "John", "moreNotes");
        Contact contact2 = new ContactImpl(10, "Josh", "notieNotes");

        // setting contactSet to avoid IllegalArgumentException
        Set<Contact> fullContactSet = new ArrayListSet<>();
        fullContactSet.add(contact);
        fullContactSet.add(contact1);
        fullContactSet.add(contact2);
        testContactManager.setContactSet(fullContactSet);


        Set<Contact> contactTestSet = new ArrayListSet<>();
        contactTestSet.add(contact);
        contactTestSet.add(contact1);

        // set pastMeetingSet to empty
        Set<PastMeeting> emptySet = new ArrayListSet<>();
        testContactManager.setPastMeetingSet(emptySet);

        Calendar firstCalendar = new GregorianCalendar(2012, 0, 10, 12, 12);
        Calendar secondCalendar = new GregorianCalendar(2012, 1, 10, 12, 12);
        Calendar thirdCalendar = new GregorianCalendar(2012, 2, 10, 12, 12);

       Set<PastMeeting> pastMeetingList = new ArrayListSet<>();
       PastMeeting testMeetingOne = new PastMeetingImpl(501, firstCalendar, contactTestSet, "Blah");
       PastMeeting testMeetingTwo = new PastMeetingImpl(502, secondCalendar, contactTestSet, "haha");
       PastMeeting testMeetingThree = new PastMeetingImpl(503, thirdCalendar, contactTestSet, "ohYes");
       pastMeetingList.add(testMeetingTwo);
       pastMeetingList.add(testMeetingOne);
       pastMeetingList.add(testMeetingThree);

       testContactManager.setPastMeetingSet(pastMeetingList);


       List<PastMeeting> pastMeetingListReturned = testContactManager.getPastMeetingListFor(contact);

       assertTrue(pastMeetingListReturned.get(0).getDate().before(pastMeetingListReturned.get(1).getDate()));
       assertTrue(pastMeetingListReturned.get(1).getDate().before(pastMeetingListReturned.get(2).getDate()));

    }

    @Test
    public void getPastMeetingListForThrowNullPointerException() throws Exception {

        // checks that NullPointerException is thrown with past null parameter

        Contact contact = new ContactImpl(1,"Peter", "myNotes");
        Contact contact1 = new ContactImpl(2,"John", "moreNotes");

        Set<Contact> contactTestSet = new ArrayListSet<>();
        contactTestSet.add(contact);
        contactTestSet.add(contact1);

        // set futureMeetingSet to empty
        Set<FutureMeeting> emptySet = new ArrayListSet<>();
        testContactManager.setFutureMeetingSet(emptySet);

        Calendar pastCalendar = new GregorianCalendar(2012, 0, 10, 12, 12);

        Set<PastMeeting> pastMeetingList = new ArrayListSet<>();
        new PastMeetingImpl(501,pastCalendar , contactTestSet, "Blah");
        new PastMeetingImpl(502, pastCalendar, contactTestSet, "haha");

        testContactManager.setPastMeetingSet(pastMeetingList);

        try {

            testContactManager.getPastMeetingListFor(null);


        }catch (NullPointerException ex){
            Throw = true;
        }

        assertTrue(Throw);

    }

    // tests for addNewPastMeeting

    @Test
    public void addNewPastMeetingIllegalArgumentExceptionIsThrownWithFutureDate() throws Exception {

        // checks that IllegalArgumentException is thrown with future date

        try{
            testContactManager.addNewPastMeeting(contacts,calendar, "Notes");
        }catch (IllegalArgumentException ex){
            Throw = true;
        }

        assertTrue(Throw);

    }

    @Test
    public void addNewPastMeetingTwoThrowsIllegalArgumentExceptionWithUnknownContact(){

        // checks if IllegalArgumentException is thrown with unknown contact

        // setting calender to past date to avoid IllegalArgumentException
        calendar = new GregorianCalendar(2010,1,22,12,12);

        //check if contact is not existing

        Contact contact2 = new ContactImpl(3,"Poul", "myNotes");
        Contact contact3 = new ContactImpl(4,"Finn", "moreNotes");
        Set<Contact> testContactSet = new ArrayListSet<>();
        testContactSet.add(contact2);
        testContactSet.add(contact3);

        testContactManager.setContactSet(testContactSet);

        try{
            testContactManager.addNewPastMeeting(contacts,calendar,"Notes");
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
            testContactManager.addNewPastMeeting(contacts,calendar, "Blah");
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
            testContactManager.addNewPastMeeting(contacts,calendar, "Note");
        }catch (IllegalArgumentException ex){
            Throw = true;
        }

        assertTrue(Throw);
    }


    @Test
    public void addPastMeetingThreeNullPointerExceptionThownWithDateNull(){

        // checks if NullPointerException is thrown with Null date

        try{
            testContactManager.addNewPastMeeting(contacts,null, "blah");
        }catch (NullPointerException ex){
            Throw = true;
        }

        assertTrue(Throw);
    }


    @Test
    public void addPastMeetingFourNullPointerExceptionThownContactsNull(){

        // Checks if NullPointerException is thrown with null contacts

        Calendar testCalendar = new GregorianCalendar(2012,11,1,23,41);

        try{
            testContactManager.addNewPastMeeting(null,testCalendar,"Blah");
        }catch (NullPointerException ex){
            Throw = true;
        }

        assertTrue(Throw);
    }


    @Test
    public void addPastMeetingNullPointerExceptionThrownWithNullNotes(){

        // checks if NullPointerException is thrown with null notes

        Calendar testCalendar = new GregorianCalendar(2012,11,1,23,41);

        try{
            testContactManager.addNewPastMeeting(contacts,testCalendar, null);
        }catch (NullPointerException ex){
            Throw = true;
        }

        assertTrue(Throw);
    }

    // tests for addMeetingNotes

    @Test
    public void addMeetingNotesMeetingNotExists() throws Exception {

        // checks if IllegalArgumentException is thrown with unknown meeting

        Calendar pastCalendar = new GregorianCalendar(2015, 1,1,12,12);

        FutureMeeting firstMeeting = new FutureMeetingImpl(90, pastCalendar, contacts);
        FutureMeeting secondMeeting = new FutureMeetingImpl(91, pastCalendar, contacts);

        Set<FutureMeeting> testFutureMeetings = new ArrayListSet<>();
        testFutureMeetings.add(firstMeeting);
        testFutureMeetings.add(secondMeeting);

        testContactManager.setFutureMeetingSet(testFutureMeetings);

        try{
            testContactManager.addMeetingNotes(909,"NewImportantNotes");
        }catch (IllegalArgumentException ex){
            Throw = true;
        }

        assertTrue(Throw);
    }

    @Test
    public void addMeetingNotesMeetingSetInFuture() throws Exception {

        // checks if IllegalStateException is thrown with meeting set in the future

        FutureMeeting firstMeeting = new FutureMeetingImpl(90, calendar, contacts);
        FutureMeeting secondMeeting = new FutureMeetingImpl(91, calendar, contacts);

        Set<FutureMeeting> testFutureMeetings = new ArrayListSet<>();
        testFutureMeetings.add(firstMeeting);
        testFutureMeetings.add(secondMeeting);

        testContactManager.setFutureMeetingSet(testFutureMeetings);

        try{
            testContactManager.addMeetingNotes(90,"NewImportantNotes");
        }catch (IllegalStateException ex){
            Throw = true;
        }

        assertTrue(Throw);

    }

    @Test
    public void addMeetingNotesNotesAreNull() throws Exception {

        // checks if NullPointerException is thrown with added notes null

        Calendar pastCalendar = new GregorianCalendar(2015, 1,1,12,12);

        FutureMeeting firstMeeting = new FutureMeetingImpl(90, pastCalendar, contacts);
        FutureMeeting secondMeeting = new FutureMeetingImpl(91, pastCalendar, contacts);

        Set<FutureMeeting> testFutureMeetings = new ArrayListSet<>();
        testFutureMeetings.add(firstMeeting);
        testFutureMeetings.add(secondMeeting);

        testContactManager.setFutureMeetingSet(testFutureMeetings);

        try{
            testContactManager.addMeetingNotes(90,null);
        }catch (NullPointerException ex){
            Throw = true;
        }

        assertTrue(Throw);

    }

    @Test
    public void addMeetingNotesConvertMeeting() throws Exception {

        // checks if future meeting is converted to past meeting

        Calendar pastCalendar = new GregorianCalendar(2015, 1,1,12,12);

        FutureMeeting firstMeeting = new FutureMeetingImpl(90, pastCalendar, contacts);
        FutureMeeting secondMeeting = new FutureMeetingImpl(91, pastCalendar, contacts);

        Set<FutureMeeting> testFutureMeetings = new ArrayListSet<>();
        testFutureMeetings.add(firstMeeting);
        testFutureMeetings.add(secondMeeting);

        testContactManager.setFutureMeetingSet(testFutureMeetings);

        PastMeeting pastMeetingReturned = testContactManager.addMeetingNotes(90,"Notes");

        Assert.assertEquals(firstMeeting.getId(),pastMeetingReturned.getId());
    }

    @Test
    public void addMeetingNotesNodesAdded() throws Exception {

        // checks if pastmeeting gets notes added

        // setting pastMeetingSet to empty
        Set<PastMeeting> empty = new ArrayListSet<>();
        testContactManager.setPastMeetingSet(empty);

        Calendar pastCalendar = new GregorianCalendar(2015, 1,1,12,12);

        PastMeeting firstMeeting = new PastMeetingImpl(90, pastCalendar, contacts, "NewStuff");

        Set<PastMeeting> testPastMeetings = new ArrayListSet<>();
        testPastMeetings.add(firstMeeting);

        testContactManager.setPastMeetingSet(testPastMeetings);

        PastMeeting pastMeetingReturned = testContactManager.addMeetingNotes(90,"Notes");

        Object returnedSet = testContactManager.getPastMeetingSet().toArray()[0];

        PastMeeting returned = (PastMeeting)returnedSet;

        Assert.assertEquals(returned.getNotes(),pastMeetingReturned.getNotes());
    }

    // tests for addNewContact

    @Test
    public void addNewContactIllegalArgumentException() throws Exception {

        // checks if IllegalArgumentException is thrown with empty strings

        try{
            testContactManager.addNewContact("","");
        }catch (IllegalArgumentException ex){
            Throw = true;
        }

        assertTrue(Throw);
    }

    @Test
    public void addNewContactNullPointerException() throws Exception {

        // checks if NullPointerException is thrown with null parameters

        try{
            testContactManager.addNewContact(null,null);
        }catch (NullPointerException ex){
            Throw = true;
        }

        assertTrue(Throw);
    }

    // tests for getContacts

    @Test
    public void getContactsReturns() throws Exception {

        // checks if getContacts returns contacts

       testContactManager.addNewContact("James Morgan","BlahBlah");

        Set<Contact> contactsReturned = testContactManager.getContacts("James M");

        Contact returned = (Contact)contactsReturned.toArray()[0];

        Assert.assertEquals(returned.getName(),"James Morgan");
    }

    @Test
    public void getContactsReturnsFullList() throws Exception {
        // checks if full contact list is returned with empty string

        // set contact to empty
        Set<Contact> empty = new ArrayListSet<>();
        testContactManager.setContactSet(empty);

        testContactManager.addNewContact("James Morgan","BlahBlah");
        testContactManager.addNewContact("Elliot Paulson","MoreBlah");
        testContactManager.addNewContact("Richard Head","Point");

        Set<Contact> contactsReturned = testContactManager.getContacts("");

        Assert.assertEquals(testContactManager.getContactSet().size(), contactsReturned.size());
    }

    // tests for getContactsTwo

    @Test
    public void getContactsOneReturnById() throws Exception {

        // checks if contacts are returned by id

        // set contact to empty
        Set<Contact> empty = new ArrayListSet<>();
        testContactManager.setContactSet(empty);

        testContactManager.addNewContact("James Morgan","BlahBlah");
        int id2 = testContactManager.addNewContact("Elliot Paulson","MoreBlah");
        int id3 = testContactManager.addNewContact("Richard Head","Point");

        Set<Contact> contactsReturned = testContactManager.getContacts(id2, id3);

        Contact oneReturned = (Contact)contactsReturned.toArray()[0];
        Contact twoReturned = (Contact)contactsReturned.toArray()[1];

        Assert.assertEquals(oneReturned.getId(), id2);
        Assert.assertEquals(twoReturned.getId(), id3);
    }

    @Test
    public void getContactsOneIllegalArgumentExceptionNoIds() throws Exception {

        // checks if IllegalArgumentException is thrown with no ids

        try {
            testContactManager.getContacts();
        }catch (IllegalArgumentException e){
            Throw = true;
        }
        assertTrue(Throw);
    }

    @Test
    public void getContactsOneIllegalArgumentExceptionNoExistingContact() throws Exception {

        // checks if IllegalArgumentException is thrown with non existing id

        // set contact to empty
        Set<Contact> empty = new ArrayListSet<>();
        testContactManager.setContactSet(empty);

        int id1 = testContactManager.addNewContact("James Morgan","BlahBlah");
        int id2 = testContactManager.addNewContact("Elliot Paulson","MoreBlah");
        int id3 = testContactManager.addNewContact("Richard Head","Point");
        int fakeId = 210;

        try {
            testContactManager.getContacts(id2, id3, fakeId);
        }catch (IllegalArgumentException e){
            Throw = true;
        }
        assertTrue(Throw);
        // set Throw to false
        Throw = false;

        try {
            testContactManager.getContacts(id2, id3, id1);
        }catch (IllegalArgumentException e){
            Throw = true;
        }

        assertFalse(Throw);
    }

    // flush

    @Test
    public void flushToFile() throws Exception {

        // checks if data is flushed to file

        // set contactSet to empty
        Set<Contact> empty = new ArrayListSet<>();
        testContactManager.setContactSet(empty);
        IOOperations IO = new IOOperationsImpl();

        // overwrite file
        IO.overWriteFile();

        int contact1 = testContactManager.addNewContact("Peter","LongNotes");
        int contact2 = testContactManager.addNewContact("Erik","ShortNotes");

        int futureMeeting = testContactManager
                .addFutureMeeting(testContactManager.getContacts(contact1,contact2),calendar);

        int pastMeeting = testContactManager.addNewPastMeeting(testContactManager.getContacts(contact1,contact2),
                new GregorianCalendar(1999, 1,2,12,12), "Notes");

        testContactManager.flush();

        List<List<String>> input = IO.readFromFile();

        assertEquals(input.get(0).get(1), Integer.toString(contact1));
        assertEquals(input.get(1).get(1), Integer.toString(contact2));
        assertEquals(input.get(2).get(1), Integer.toString(futureMeeting));
        assertEquals(input.get(3).get(1), Integer.toString(pastMeeting));
    }

    @Test
    public void loadFromFile(){

        IOOperations IO = new IOOperationsImpl();
        IO.overWriteFile();

        testContactManager.setContactSet(new ArrayListSet<>());
        testContactManager.setFutureMeetingSet(new ArrayListSet<>());
        testContactManager.setPastMeetingSet(new ArrayListSet<>());

        Calendar pastCalendar = new GregorianCalendar(1900, 2,3,1,1);

        int contactOne = testContactManager.addNewContact("Poul", "Notes");

        testContactManager.
                addFutureMeeting(testContactManager.getContacts(contactOne), calendar);

        testContactManager.
                addNewPastMeeting(testContactManager.getContacts(contactOne), pastCalendar, "yes");

        testContactManager.flush();
        testContactManager.loadFromFile();

        Set<Contact> contactsRead = IO.readContactsFromFile();

        Set<Contact> contactsFromSet = testContactManager.getContactSet();

        Contact readContact = (Contact)contactsRead.toArray()[0];

        Contact fromSetContact = (Contact)contactsFromSet.toArray()[0];

        Assert.assertEquals(readContact.getId(), fromSetContact.getId());

        Set<FutureMeeting> futureMeetingsFromSet = testContactManager.getFutureMeetingSet();
        Set<FutureMeeting> futureMeetingsRead = IO.readFutureMeetingFromFile();

        FutureMeeting readFutureMeeting = (FutureMeeting)futureMeetingsRead.toArray()[0];
        FutureMeeting fromSetFutureMeeting = (FutureMeeting)futureMeetingsFromSet.toArray()[0];

        Assert.assertEquals(readFutureMeeting.getId(), fromSetFutureMeeting.getId());


        Set<PastMeeting> pastMeetingsFromSet = testContactManager.getPastMeetingSet();
        Set<PastMeeting> pastMeetingsRead = IO.readPastMeetingFromFile();

        PastMeeting readPastMeeting = (PastMeeting)pastMeetingsRead.toArray()[0];
        PastMeeting fromSetPastMeeting = (PastMeeting)pastMeetingsFromSet.toArray()[0];

        Assert.assertEquals(readPastMeeting.getId(), fromSetPastMeeting.getId());
    }
}