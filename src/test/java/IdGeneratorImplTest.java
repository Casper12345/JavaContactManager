package test.java;

import com.intellij.util.containers.ArrayListSet;
import main.java.impl.ContactImpl;
import main.java.impl.FutureMeetingImpl;
import main.java.impl.IdGeneratorImpl;
import main.java.spec.Contact;
import main.java.spec.FutureMeeting;
import main.java.spec.IdGenerator;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import java.security.SecureRandom;
import java.math.BigInteger;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Set;

/**
 * JUnit test for main.java.spec.IdGenerator.
 */
public class IdGeneratorImplTest {
    /**
     * Random generator.
     */
    private SecureRandom random = new SecureRandom();
    /**
     * Test id generator.
     */
    private IdGenerator testGenerator = new IdGeneratorImpl();
    /**
     * Contacts for test purpose.
     */
    private Set<Contact> contacts = new ArrayListSet<>();
    /**
     * Contact.
     */
    private Contact testContactOne;
    /**
     * Contact.
     */
    private Contact testContactTwo;

    /**
     * Before.
     */
    @Before
    public void setup() {
        testContactOne = new ContactImpl(1, "Poul", "goodNotes");
        testContactTwo = new ContactImpl(2, "Eric", "badNotes");

        contacts.add(testContactOne);
        contacts.add(testContactTwo);

    }

    /**
     * See desc.
     * @throws Exception ex
     */
    @Test
    public void genId() throws Exception {
        int testIdOne = testGenerator.genId(testContactOne, contacts);

        int testIdTwo = testGenerator.genId(testContactTwo, contacts);

        assertNotEquals(testIdOne, testIdTwo);

    }

    /**
     * See desc.
     */
    @Test
    public void checkIfZero() {

        Calendar calendar = new GregorianCalendar(2020, 2, 2, 1, 11);
        Set<FutureMeeting> testObjects = new ArrayListSet<>();
        FutureMeeting one = new FutureMeetingImpl(2, calendar, contacts);

        boolean isZero = false;

        for (int i = 0; i < 2000000; i++) {
            new ContactImpl(1,
                            randomStringGenerator(),
                            randomStringGenerator());

            int testIdOne = testGenerator.genId(one, testObjects);
            if (testIdOne == 0) {
                isZero = true;
            }

        }
        assertFalse(isZero);
    }

    /**
     * See desc.
     */
    @Test
    public void stressTestIdGenerator() {
        IdGeneratorImplTest n = new IdGeneratorImplTest();

        for (int i = 0; i < 20000; i++) {
            Contact testContactOne =
                    new ContactImpl(1,
                            n.randomStringGenerator(),
                            n.randomStringGenerator());

            Contact testContactTwo =
                    new ContactImpl(1,
                            n.randomStringGenerator(),
                            n.randomStringGenerator());

            int testIdOne = testGenerator.genId(testContactOne, contacts);
            int testIdTwo = testGenerator.genId(testContactTwo, contacts);

            assertNotEquals(testIdOne, testIdTwo);

        }
    }

    /**
     * Random string generator.
     * @return random string.
     */
    private String randomStringGenerator() {
        return new BigInteger(130, random).toString(32);
    }
}
