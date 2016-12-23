import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Casper on 22/12/2016.
 */
public class ContactImplTest {

    private Contact testContact;

    @Before
    public void setupTestContact() {
        testContact = new ContactImpl(1, "Paul", "myNotes");
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

}