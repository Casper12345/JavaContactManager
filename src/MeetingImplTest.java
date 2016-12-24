import com.intellij.util.containers.ArrayListSet;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

/**
 * Junit test for TDD of MeetingImpl
 */
public class MeetingImplTest {

    private MeetingImpl testMeetingTwo;

    @Before
    public void setUpTest() {
        ArrayListSet<Contact> myContacts = new ArrayListSet<>();
        myContacts.add(new ContactImpl(1,"George","myImportantNotes"));
        testMeetingTwo = new MeetingMockClass(1, new Date(22122016), myContacts);

    }

    @Test
    public void constructorTestPartOne(){
        MeetingImpl testMeeting;
        boolean Throw = false;

        try{
            testMeeting = new MeetingMockClass(1, new Date(22122016), new ArrayListSet());
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
            testMeeting = new MeetingMockClass(1, new Date(22122016), contacts);
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
            testMeeting = new MeetingMockClass(-1, new Date(22122016), contacts);

        }catch(IllegalArgumentException ex){
            Throw = true;
        }
        assertTrue(Throw);

        // set Throw back to false
        Throw = false;

        try{
            testMeeting = new MeetingMockClass(1, new Date(22122016), contacts);

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
            testMeeting = new MeetingMockClass(1, null, contacts);

        }catch(NullPointerException ex){
            Throw = true;
        }

        assertTrue(Throw);
        // set Throw back to false
        Throw = false;

        try{
            testMeeting = new MeetingMockClass(1, new Date(22122016), contacts);

        }catch(NullPointerException ex){
            Throw = true;
        }

        assertFalse(Throw);

    }

    @Test
    public void getId(){

        assertEquals(1,testMeetingTwo.getId());
        assertNotEquals(2,testMeetingTwo.getId());

    }

    @Test
    public void getDate() {

        assertEquals(new Date(22122016),testMeetingTwo.getDate());
        assertNotEquals(new Date(10122010),testMeetingTwo.getDate());

    }

}