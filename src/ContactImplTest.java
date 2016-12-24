import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Tests used to create Contact ImplTest
 */
public class ContactImplTest {

    private Contact testContact;

    @Before
    public void setupTestContact() {

        try{
            testContact = new ContactImpl(1, "Paul", "myNotes");

        }catch (IllegalArgumentException ex){
            ex.getStackTrace();
        } catch (NullPointerException ex){
            ex.getStackTrace();
        }
    }


    @Test
    public void getId() throws Exception {
        assertEquals(1, testContact.getId());

    }

    @Test
    public void getName() throws Exception {
        assertEquals("Paul", testContact.getName());

    }

    @Test
    public void getNotes() throws Exception {
        assertEquals("myNotes", testContact.getNotes());
    }

    @Test
    public void addNotes() throws Exception {
        StringBuilder toCompare = new StringBuilder(testContact.getNotes());
        // testing string builder
        assertEquals(testContact.getNotes(), toCompare.toString());
        // adding notes
        testContact.addNotes("moreNotes");
        // adding notes stringbuilder
        toCompare.append("\n"+"moreNotes");
        // testing again
        assertEquals(testContact.getNotes(), toCompare.toString());

    }

    @Test
    public void testConstructorOneFirst(){
        Contact testContactTwo;
        boolean Thrown = false;

        try {
            testContactTwo = new ContactImpl(-1, "Mark","Something");
        } catch (IllegalArgumentException ex){
            Thrown = true;
        } catch (NullPointerException ex){
            ex.printStackTrace();
        }
        assertTrue(Thrown);

    }

    @Test
    public void testConstructorOneSecond(){
        Contact testContactTwo;
        boolean Thrown = false;

        try {
            testContactTwo = new ContactImpl(10, null,"Something");
        } catch (IllegalArgumentException ex){
            ex.printStackTrace();
        } catch (NullPointerException ex){
            Thrown = true;
        }
        assertTrue(Thrown);
        // setting Thrown back to false
        Thrown = false;

        try {
            testContactTwo = new ContactImpl(10, "Mark",null);
        } catch (IllegalArgumentException ex){
            ex.printStackTrace();
        } catch (NullPointerException ex){
            Thrown = true;
        }
        assertTrue(Thrown);
    }

    @Test
    public void testConstructorTwoFirst(){
        Contact testContactTwo;
        boolean Thrown = false;

        try {
            testContactTwo = new ContactImpl(-1, "Mark");
        } catch (IllegalArgumentException ex){
            Thrown = true;
        } catch (NullPointerException ex){
            ex.printStackTrace();
        }
        assertTrue(Thrown);

    }

    @Test
    public void testConstructorTwoSecond(){
        Contact testContactTwo;
        boolean Thrown = false;

        try {
            testContactTwo = new ContactImpl(10,null);
        } catch (IllegalArgumentException ex){
            ex.printStackTrace();
        } catch (NullPointerException ex){
            Thrown = true;
        }
        assertTrue(Thrown);
    }


}