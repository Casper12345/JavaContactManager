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

    @Before
    public void setUp(){
        testIO = new IOOperationsImpl();
    }


    @Test
    public void writeToFile() throws Exception {
        Contact first = new ContactImpl(23, "Paolo", "Blah");
        Contact second = new ContactImpl(34, "Erik", "more");
        Set<Contact>  testContacts = new ArrayListSet<>();
        testContacts.add(first);
        testContacts.add(second);
        testIO.writeContactsToFile(testContacts);

    }

    @Test
    public void readContactsFromFile() throws Exception {
        IOOperations testIO = new IOOperationsImpl();
        List<List<String>> input = testIO.readFromFile();

        assertEquals(input.get(0).get(0), "Contact");
        assertEquals(input.get(1).get(0), "Contact");
        assertEquals(input.get(0).get(1), "23");
        assertEquals(input.get(1).get(1), "34");
        assertEquals(input.get(0).get(2), "Paolo");
        assertEquals(input.get(1).get(2), "Erik");
        assertEquals(input.get(0).get(3), "Blah");
        assertEquals(input.get(1).get(3), "more");

        System.out.print(input.get(0).get(0));
        System.out.print(input.get(0).get(1));
        System.out.print(input.get(0).get(2));
        System.out.print(input.get(0).get(3));

    }

}