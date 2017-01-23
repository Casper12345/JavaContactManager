package Tests;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import src.Contact;
import src.ContactImpl;

import static org.junit.Assert.*;

/**
 * Tests used to create src.Contact ImplTest
 */
public class ContactImplTest {

    private Contact testContact;

    @Before
    public void setupTestContact() {
        testContact = new ContactImpl(1, "Paul", "myNotes");
    }


    @Test
    public void getId() throws Exception {
        Assert.assertEquals(1, testContact.getId());

    }

    @Test
    public void getName() throws Exception {
        Assert.assertEquals("Paul", testContact.getName());

    }

    @Test
    public void getNotes() throws Exception {
        Assert.assertEquals("myNotes", testContact.getNotes());
    }

    @Test
    public void addNotes() throws Exception {
        StringBuilder toCompare = new StringBuilder(testContact.getNotes());
        // testing string builder
        Assert.assertEquals(testContact.getNotes(), toCompare.toString());
        // adding notes
        testContact.addNotes("moreNotes");
        // adding notes stringbuilder
        toCompare.append("\n"+"moreNotes");
        // testing again
        Assert.assertEquals(testContact.getNotes(), toCompare.toString());

    }

    @Test
    public void testConstructorOneFirst(){
        Contact testContactTwo;
        boolean Thrown = false;

        try {
            new ContactImpl(-1, "Mark","Something");
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
            new ContactImpl(10, null,"Something");
        } catch (IllegalArgumentException ex){
            ex.printStackTrace();
        } catch (NullPointerException ex){
            Thrown = true;
        }
        assertTrue(Thrown);
        // setting Thrown back to false
        Thrown = false;

        try {
            new ContactImpl(10, "Mark",null);
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
            new ContactImpl(-1, "Mark");
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
            new ContactImpl(10,null);
        } catch (IllegalArgumentException ex){
            ex.printStackTrace();
        } catch (NullPointerException ex){
            Thrown = true;
        }
        assertTrue(Thrown);
    }


}