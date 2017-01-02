import com.intellij.util.containers.ArrayListSet;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Implements interface ContactManager
 */
public class ContactManagerImpl implements ContactManager {

    private Set<Contact> contactSet = new ArrayListSet<>();
    private Set<Meeting> meetingSet = new ArrayListSet<>();

    /**
     * This method is used to access contactSet for testing purposes
     * @param contactSet
     */
    public void setContactSet(Set<Contact> contactSet){
        this.contactSet = contactSet;
    }

    /**
     * This method is used to access meetingSet for testing purposes
     * @param meetingSet
     */
    public void setMeetingSet(Set<Meeting> meetingSet){

        this.meetingSet = meetingSet;
    }



    public int addFutureMeeting(Set<Contact> contacts, Calendar date)
            throws IllegalArgumentException, NullPointerException{

        Calendar presentDate = Calendar.getInstance();

        if (date.before(presentDate)){
            throw new IllegalArgumentException();
        }

        // using streams and containsAll to check contact match by id
        boolean isInContactSet = contactSet.stream().map(Contact::getId)
                .collect(Collectors.toList()).containsAll(contacts.stream()
                        .map(Contact::getId).collect(Collectors.toList()));

        if(!isInContactSet) {
            throw new IllegalArgumentException();
        }

        // create meeting and add it to set
        Meeting futureMeetingToGenerateId = new FutureMeetingImpl(1,date,contacts);
        // generate unique id
        int generatedId = new IdGeneratorImpl().genId(futureMeetingToGenerateId);

        FutureMeeting futureMeetingToSet = new FutureMeetingImpl(generatedId, date, contacts);

        meetingSet.add(futureMeetingToSet);

        return generatedId;
    }

    public PastMeeting getPastMeeting(int id){

        PastMeeting pastMeetingToReturn = null;

        List<PastMeeting> pastMeetings = meetingSet.stream().filter(a -> a instanceof PastMeeting)
                .map(a -> (PastMeeting) a).collect(Collectors.toList());

        for(PastMeeting i: pastMeetings){
            if(i.getId() == id){
                pastMeetingToReturn = i;
            }else{
                pastMeetingToReturn = null;
            }
        }

        return pastMeetingToReturn;
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

    // Utility functions for ContactManagerImpl
    private boolean isInContactSet(Set<Contact> contacts) {
        boolean found = false;
        for (Contact i : contacts) {
            for (Contact j : contactSet) {
                if (j.getId() == i.getId()) {
                    found = true;
                }
            }
        }
        return found;
    }

}
