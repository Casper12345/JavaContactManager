import com.intellij.util.containers.ArrayListSet;
import java.util.*;

/**
 * Implements interface ContactManager
 */
public class ContactManagerImpl implements ContactManager {

    private Set<Contact> contactSet = new ArrayListSet<>();
    private Set<FutureMeeting> futureMeetingSet = new ArrayListSet<>();


    public int addFutureMeeting(Set<Contact> contacts, Calendar date) throws IllegalArgumentException{

        Calendar presentDate = Calendar.getInstance();

        if (date.before(presentDate)){
            throw new IllegalArgumentException();
        }

        return 5;
    }



    public PastMeeting getPastMeeting(int id){
       return new PastMeetingImpl(2, new GregorianCalendar(2010,12,18,11,11), new ArrayListSet<>(),"");
    }


    public FutureMeeting getFutureMeeting(int id){
        return new FutureMeetingImpl(1,new GregorianCalendar(2010,12,18,11,11), new ArrayListSet<>()) ;
    }

    public Meeting getMeeting(int id){
        return new FutureMeetingImpl(1,new GregorianCalendar(2010,12,18,11,11), new ArrayListSet<>()) ;
    }

    public List<Meeting> getFutureMeetingList(Contact contact){
        return new ArrayList<>();
    }

    public List<Meeting> getMeetingListOn(Calendar date){
        return new ArrayList<>();
    }

    public List<PastMeeting> getPastMeetingListFor(Contact contact){
        return new ArrayList<>();
    }

    public int addNewPastMeeting(Set<Contact> contacts, Calendar date, String text){
        return 2;
    }

    public PastMeeting addMeetingNotes(int id, String text){
        return new PastMeetingImpl(2, new GregorianCalendar(2010,12,18,11,11), new ArrayListSet<>(),"");
    }

    public int addNewContact(String name, String notes){
        return 2;
    }

    public Set<Contact> getContacts(String name){
        return new ArrayListSet<>();
    }

    public Set<Contact> getContacts(int... ids){
        return new ArrayListSet<>();
    }

    public void flush(){

    }
}
