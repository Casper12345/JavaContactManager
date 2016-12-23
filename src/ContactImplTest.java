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
    @Ignore
    public void getName() throws Exception {
        assertEquals();

    }

    @Test
    @Ignore
    public void getNotes() throws Exception {

    }

    @Test
    @Ignore
    public void addNotes() throws Exception {

    }

}