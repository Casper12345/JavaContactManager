package Tests;

import com.intellij.util.containers.ArrayListSet;
import org.junit.Before;
import org.junit.Test;
import src.*;

import static org.junit.Assert.*;
import java.security.SecureRandom;
import java.math.BigInteger;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Set;

/**
 * JUnit test for src.IdGenerator
 */
public class IdGeneratorImplTest {

    private SecureRandom random = new SecureRandom();
    private IdGenerator testGenerator = new IdGeneratorImpl();
    private Set<Contact> contacts = new ArrayListSet<>();
    private Contact testContactOne;
    private Contact testContactTwo;


    @Before
    public void setup(){
        testContactOne = new ContactImpl(1, "Poul", "goodNotes");
        testContactTwo = new ContactImpl(2, "Eric", "badNotes");

        contacts.add(testContactOne);
        contacts.add(testContactTwo);

    }

    @Test
    public void genId() throws Exception {
        int testIdOne = testGenerator.genId(testContactOne, contacts);

        int testIdTwo = testGenerator.genId(testContactTwo, contacts);

        assertNotEquals(testIdOne,testIdTwo);

    }

    @Test
    public void checkIfZero(){

        Calendar calendar = new GregorianCalendar(2020, 2,2,1,11);
        Set<FutureMeeting> testObjects = new ArrayListSet<>();
        FutureMeeting one = new FutureMeetingImpl(2, calendar, contacts);

        boolean isZero = false;

        for (int i=0; i < 2000000; i++){
            IdGeneratorImplTest n = new IdGeneratorImplTest();
            Contact testContactOne =
                    new ContactImpl(1,
                            n.randomStringGenerator(),
                            n.randomStringGenerator());

            int testIdOne = testGenerator.genId(one,testObjects);
            if (testIdOne == 0){
                isZero = true;
            }

        }
        assertFalse(isZero);
    }


    @Test
    public void stressTestIdGenerator(){
        IdGeneratorImplTest n = new IdGeneratorImplTest();

        for (int i=0; i < 20000; i++){
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

            assertNotEquals(testIdOne,testIdTwo);

        }



    }

    private String randomStringGenerator() {
        return new BigInteger(130, random).toString(32);
    }

}