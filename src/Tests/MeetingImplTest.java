package Tests;

import com.intellij.util.containers.ArrayListSet;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import src.Contact;
import src.ContactImpl;
import src.MeetingImpl;
import src.MeetingMockClass;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.junit.Assert.*;

/**
 * Junit test for TDD of src.MeetingImpl
 */
public class MeetingImplTest {

    private MeetingImpl testMeetingTwo;

    @Before
    public void setUpTest() {
        ArrayListSet<Contact> myContacts = new ArrayListSet<>();
        myContacts.add(new ContactImpl(1,"George","myImportantNotes"));
        testMeetingTwo = new MeetingMockClass(1, new GregorianCalendar(2016,Calendar.DECEMBER,24,13,12), myContacts);

    }

    @Test
    public void constructorTestPartOne(){
        MeetingImpl testMeeting;
        boolean Throw = false;

        try{
            new MeetingMockClass(1, new GregorianCalendar(2016,Calendar.DECEMBER,24,13,12), new ArrayListSet());
        }catch(IllegalArgumentException ex){
            Throw = true;
        }

        assertTrue(Throw);
        // set Throw back to false
        Throw = false;
        // creating contact set
        ArrayListSet<Contact> contacts = new ArrayListSet<>();
        contacts.add(new ContactImpl(1,"George","myImportantNotes"));

        try{
            new MeetingMockClass(1, new GregorianCalendar(2016,Calendar.DECEMBER,24,13,12), contacts);
        }catch(IllegalArgumentException ex){
            Throw = true;
        }

        assertFalse(Throw);

    }

    @Test
    public void constructorTestPartTwo(){
        MeetingImpl testMeeting;
        boolean Throw = false;
        ArrayListSet<Contact> contacts = new ArrayListSet<>();
        contacts.add(new ContactImpl(1,"George","myImportantNotes"));

        try{
            new MeetingMockClass(-1, new GregorianCalendar(2016,Calendar.DECEMBER,24,13,12), contacts);

        }catch(IllegalArgumentException ex){
            Throw = true;
        }
        assertTrue(Throw);

        // set Throw back to false
        Throw = false;

        try{
            new MeetingMockClass(1, new GregorianCalendar(2016,Calendar.DECEMBER,24,13,12), contacts);

        }catch(IllegalArgumentException ex){
            Throw = true;
        }

        assertFalse(Throw);
    }

    @Test
    public void constructorTestPartThree(){
        MeetingImpl testMeeting;
        boolean Throw = false;
        ArrayListSet<Contact> contacts = new ArrayListSet<>();
        contacts.add(new ContactImpl(1,"George","myImportantNotes"));

        try{
            new MeetingMockClass(1, null, contacts);

        }catch(NullPointerException ex){
            Throw = true;
        }

        assertTrue(Throw);
        // set Throw back to false
        Throw = false;

        try{
            new MeetingMockClass(1, new GregorianCalendar(2016,Calendar.DECEMBER,24,13,12), contacts);

        }catch(NullPointerException ex){
            Throw = true;
        }

        assertFalse(Throw);

    }

    @Test
    public void getId(){

        Assert.assertEquals(1,testMeetingTwo.getId());
        Assert.assertNotEquals(2,testMeetingTwo.getId());

    }

    @Test
    public void getDate() {

        Assert.assertEquals(new GregorianCalendar(2016,Calendar.DECEMBER,24,13,12),testMeetingTwo.getDate());
        Assert.assertNotEquals(new GregorianCalendar(2019,Calendar.DECEMBER,28,23,30),testMeetingTwo.getDate());

    }

    @Test
    public void getContacts(){
        ArrayListSet<Contact> contacts = new ArrayListSet<>();
        contacts.add(new ContactImpl(1,"George","myImportantNotes"));
        for(Contact i: contacts){
            for(Contact j : testMeetingTwo.getContacts()){
                Assert.assertEquals(i.getId(), j.getId());
            }
        }
    }

}