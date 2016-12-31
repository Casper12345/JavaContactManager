import com.intellij.util.containers.ArrayListSet;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Set;

/**
 * Created by Casper on 22/12/2016.
 */
public class ContactManagerImpl {

    public int addFutureMeeting(Set<Contact> contacts, Calendar date){

        return 5;
    }

    public PastMeeting getPastMeeting(int id){
       return new PastMeetingImpl(2, new GregorianCalendar(2010,12,18,11,11), new ArrayListSet<>(),"");
    }




}
