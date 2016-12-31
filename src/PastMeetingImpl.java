import com.intellij.util.containers.ArrayListSet;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Set;

/**
 * Created by Casper on 25/12/2016.
 */
public class PastMeetingImpl implements PastMeeting{

    public int getId(){
        return 2;
    }


    public Calendar getDate(){
        return new GregorianCalendar();
    }

    public Set<Contact> getContacts(){
        return new ArrayListSet<>();
    }


    public String getNotes(){
        return "";
    }


}
