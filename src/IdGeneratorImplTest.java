import org.junit.Test;
import static org.junit.Assert.*;
import java.security.SecureRandom;
import java.math.BigInteger;

/**
 * JUnit test for IdGenerator
 */
public class IdGeneratorImplTest {

    private SecureRandom random = new SecureRandom();
    private IdGenerator testGenerator = new IdGeneratorImpl();

    @Test
    public void genId() throws Exception {

        Contact testContactOne = new ContactImpl(1, "Poul", "goodNotes");
        int testIdOne = testGenerator.genId(testContactOne);

        Contact testContactTwo = new ContactImpl(1, "Eric", "badNotes");
        int testIdTwo = testGenerator.genId(testContactTwo);

        assertNotEquals(testIdOne,testIdTwo);

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

            int testIdOne = testGenerator.genId(testContactOne);
            int testIdTwo = testGenerator.genId(testContactTwo);

            assertNotEquals(testIdOne,testIdTwo);

        }



    }

    private String randomStringGenerator() {
        return new BigInteger(130, random).toString(32);
    }

}