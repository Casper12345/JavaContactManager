package test.java;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import main.java.spec.Contact;
import main.java.impl.ContactImpl;

import static org.junit.Assert.*;

/**
 * Tests used to create main.java.spec.Contact ImplTest.
 */
public class ContactImplTest {
    /**
     * For testing purpose.
     */
    private Contact testContact;

    /**
     * Before.
     */
    @Before
    public void setupTestContact() {
        testContact = new ContactImpl(1, "Paul", "myNotes");
    }

    /**
     * Testing get id.
     * @throws Exception ex
     */
    @Test
    public void getId() throws Exception {
        Assert.assertEquals(1, testContact.getId());

    }

    /**
     * Testing getName.
     * @throws Exception ex
     */
    @Test
    public void getName() throws Exception {
        Assert.assertEquals("Paul", testContact.getName());

    }

    /**
     * Testing getNotes.
     * @throws Exception ex
     */
    @Test
    public void getNotes() throws Exception {
        Assert.assertEquals("myNotes", testContact.getNotes());
    }

    /**
     * Testing addNotes.
     * @throws Exception ex
     */
    @Test
    public void addNotes() throws Exception {
        StringBuilder toCompare = new StringBuilder(testContact.getNotes());
        // testing string builder
        Assert.assertEquals(testContact.getNotes(), toCompare.toString());
        // adding notes
        testContact.addNotes("moreNotes");
        // adding notes stringbuilder
        toCompare.append("\n" + "moreNotes");
        // testing again
        Assert.assertEquals(testContact.getNotes(), toCompare.toString());

    }

    /**
     * Testing contructor.
     */
    @Test
    public void testConstructorOneFirst() {
        boolean thrown = false;

        try {
            new ContactImpl(-1, "Mark", "Something");
        } catch (IllegalArgumentException ex) {
            thrown = true;
        } catch (NullPointerException ex) {
            ex.printStackTrace();
        }
        assertTrue(thrown);

    }

    /**
     * Testing contructor.
     */
    @Test
    public void testConstructorOneSecond() {
        boolean thrown = false;

        try {
            new ContactImpl(10, null, "Something");
        } catch (IllegalArgumentException ex) {
            ex.printStackTrace();
        } catch (NullPointerException ex) {
            thrown = true;
        }
        assertTrue(thrown);
        // setting thrown back to false
        thrown = false;

        try {
            new ContactImpl(10, "Mark", null);
        } catch (IllegalArgumentException ex) {
            ex.printStackTrace();
        } catch (NullPointerException ex) {
            thrown = true;
        }
        assertTrue(thrown);
    }

    /**
     * Testing constructor.
     */
    @Test
    public void testConstructorTwoFirst() {
        boolean thrown = false;

        try {
            new ContactImpl(-1, "Mark");
        } catch (IllegalArgumentException ex) {
            thrown = true;
        } catch (NullPointerException ex) {
            ex.printStackTrace();
        }
        assertTrue(thrown);

    }

    /**
     * Testing contructor.
     */
    @Test
    public void testConstructorTwoSecond() {
        boolean thrown = false;

        try {
            new ContactImpl(10, null);
        } catch (IllegalArgumentException ex) {
            ex.printStackTrace();
        } catch (NullPointerException ex) {
            thrown = true;
        }
        assertTrue(thrown);
    }
}
