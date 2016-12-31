import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Casper on 31/12/2016.
 */
public class IdGeneratorImplTest {
    @Test
    public void genId() throws Exception {

        IdGenerator testGenerator = new IdGeneratorImpl();
        Contact testContactOne = new ContactImpl(1, "Poul", "goodNotes");
        int testIdOne = testGenerator.genId(testContactOne);

        Contact testContactTwo = new ContactImpl(1, "Eric", "badNotes");
        int testIdTwo = testGenerator.genId(testContactTwo);

        assertEquals(testContactOne,testContactTwo);



    }

}