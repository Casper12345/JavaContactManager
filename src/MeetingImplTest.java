import com.intellij.util.containers.ArrayListSet;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

/**
 * Junit test for TDD of MeetingImpl
 */
public class MeetingImplTest {

    @Before
    public void setUpTest() {

    }

    @Test
    public void constructorTest(){
        MeetingImpl testMeeting;
        boolean Throw = false;

        try{
            testMeeting = new MeetingMockClass(1, new Date(22122016), new ArrayListSet());
        }catch(IllegalArgumentException ex){
            Throw = true;
        }

        assertTrue(Throw);
    }

}