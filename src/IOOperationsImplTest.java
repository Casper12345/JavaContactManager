import com.intellij.util.ScrambledOutputStream;
import com.intellij.util.containers.ArrayListSet;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

/**
 * JUnit test for IOOperations.
 */
public class IOOperationsImplTest {

    private IOOperations testIO;
    private ContactManager testContactManager;

    @Before
    public void setUp(){
        testIO = new IOOperationsImpl();
        testContactManager = new ContactManagerImpl();
    }


    @Test
    public void writeContactsToFile() throws Exception {
        Contact first = new ContactImpl(23, "Paolo", "Blah, blah, blah");
        Contact second = new ContactImpl(34, "Erik", "more, more, more");
        Set<Contact>  testContacts = new ArrayListSet<>();
        testContacts.add(first);
        testContacts.add(second);
        testIO.writeContactsToFile(testContacts);

        testContactManager.addNewContact("Josh", "Notes, yes");
        Set<Contact> contactsReturned  = testContactManager.getContacts("Josh");
        Contact returned = (Contact) contactsReturned.toArray()[0];
        returned.addNotes("no");
        Set<Contact> contactsReturnedTwo  = testContactManager.getContacts("Josh");
        Contact returnedTwo = (Contact) contactsReturnedTwo.toArray()[0];

        testContacts.add(returnedTwo);
        testIO.writeContactsToFile(testContacts);

    }

    @Test
    public void readFromFile() throws Exception {
        IOOperations testIO = new IOOperationsImpl();
        List<List<String>> input = testIO.readFromFile();

        assertEquals(input.get(0).get(0), "Contact");
        assertEquals(input.get(1).get(0), "Contact");
        assertEquals(input.get(0).get(1), "23");
        assertEquals(input.get(1).get(1), "34");
        assertEquals(input.get(0).get(2), "Paolo");
        assertEquals(input.get(1).get(2), "Erik");
        assertEquals(input.get(0).get(3), "Blah, blah, blah");
        assertEquals(input.get(1).get(3), "more, more, more");
        assertEquals(input.get(2).get(3), "Notes, yes" + "\n" +"no");
    }

    @Test
    public void writeFutureMeetingsToFile() throws Exception {




    }
}