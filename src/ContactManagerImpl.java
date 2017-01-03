import com.intellij.util.containers.ArrayListSet;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Implements interface ContactManager
 */
public class ContactManagerImpl implements ContactManager {

    private Set<Contact> contactSet = new ArrayListSet<>();
    private Set<FutureMeeting> futureMeetingSet = new ArrayListSet<>();
    private Set<PastMeeting> pastMeetingSet = new ArrayListSet<>();
    private Calendar presentDate = Calendar.getInstance();

    /**
     * This method is used to access contactSet for testing purposes
     * @param contactSet
     */
    public void setContactSet(Set<Contact> contactSet){
        this.contactSet = contactSet;
    }

    /**
     * This method is used to access futureMeetingSet for testing purposes
     * @param futureMeetingSet
     */
    public void setFutureMeetingSet(Set<FutureMeeting> futureMeetingSet){

        this.futureMeetingSet = futureMeetingSet;
    }

    /**
     * This method is used to access pastMeetingSet for testing purposes
     * @param pastMeetingSet
     */
    public void setPastMeetingSet(Set<PastMeeting> pastMeetingSet){

        this.pastMeetingSet = pastMeetingSet;
    }


    public int addFutureMeeting(Set<Contact> contacts, Calendar date)
            throws IllegalArgumentException, NullPointerException{

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

        futureMeetingSet.add(futureMeetingToSet);

        return generatedId;
    }

    public PastMeeting getPastMeeting(int id) throws IllegalStateException{

        PastMeeting pastMeetingToReturn = null;

        for(PastMeeting i: pastMeetingSet){
            if(i.getId() == id){
                pastMeetingToReturn = i;
            }
        }

        if(pastMeetingToReturn != null && pastMeetingToReturn.getDate().after(presentDate)){
            throw new IllegalStateException();
        }

        return pastMeetingToReturn;
    }


    public FutureMeeting getFutureMeeting(int id) throws IllegalStateException{

        FutureMeeting futureMeetingToReturn = null;

        for(FutureMeeting i: futureMeetingSet){
            if(i.getId() == id){
                futureMeetingToReturn = i;
            }
        }

        if(futureMeetingToReturn != null && futureMeetingToReturn.getDate().before(presentDate)){
            throw new IllegalStateException();
        }

        return futureMeetingToReturn ;
    }

    public Meeting getMeeting(int id){

        Meeting meetingToReturn = null;

        for(FutureMeeting i : futureMeetingSet){
            if(i.getId() == id){
                meetingToReturn = i;
            }
        }

        for(PastMeeting j : pastMeetingSet){
            if(j.getId() == id){
                meetingToReturn = j;
            }
        }
        return meetingToReturn;
    }

    public List<Meeting> getFutureMeetingList(Contact contact) throws NullPointerException{

        List<Meeting> meetingToReturn = new ArrayList<>();
        Set<Meeting> setToRemoveDuplicates = new ArrayListSet<>();

        if(contact == null){
            throw new NullPointerException();
        }

        for(FutureMeeting i : futureMeetingSet){
            if(i.getContacts().contains(contact)){
                setToRemoveDuplicates.add(i);
            }
        }
        meetingToReturn.addAll(setToRemoveDuplicates);

        // sort chronologically
        meetingToReturn.sort((a,b) -> a.getDate().compareTo(b.getDate()));

        return meetingToReturn;
    }


    public List<Meeting> getMeetingListOn(Calendar date){

        List<Meeting> meetingToReturn = new ArrayList<>();
        Set<Meeting> setToRemoveDuplicates = new ArrayListSet<>();

        if(date == null){
            throw new NullPointerException();
        }

        for(FutureMeeting i : futureMeetingSet){
            if(i.getDate().equals(date)){
                setToRemoveDuplicates.add(i);
            }
        }
        for(PastMeeting i : pastMeetingSet){
            if(i.getDate().equals(date)){
                setToRemoveDuplicates.add(i);
            }
        }

        meetingToReturn.addAll(setToRemoveDuplicates);

        return meetingToReturn;
    }

    public List<PastMeeting> getPastMeetingListFor(Contact contact){

        List<PastMeeting> pastMeetingToReturn = new ArrayList<>();
        Set<PastMeeting> setToRemoveDuplicates = new ArrayListSet<>();

        if(contact == null){
            throw new NullPointerException();
        }

        for(PastMeeting i : pastMeetingSet){
            if(i.getContacts().contains(contact)){
                setToRemoveDuplicates.add(i);
            }

        }
        pastMeetingToReturn.addAll(setToRemoveDuplicates);

        // sort chronologically
        pastMeetingToReturn.sort((a,b) -> a.getDate().compareTo(b.getDate()));

        return pastMeetingToReturn;
    }


    public int addNewPastMeeting(Set<Contact> contacts, Calendar date, String text)
            throws IllegalArgumentException, NullPointerException{

        if(text == null){
            throw new NullPointerException();
        }

        if (date.after(presentDate)){
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
        Meeting pastMeetingToGenerateId = new PastMeetingImpl(1,date,contacts, text);

        int generatedId = new IdGeneratorImpl().genId(pastMeetingToGenerateId);

        PastMeeting pastMeetingToSet = new PastMeetingImpl(generatedId, date, contacts, text);

        pastMeetingSet.add(pastMeetingToSet);

        return generatedId;

    }


    public PastMeeting addMeetingNotes(int id, String text)
            throws IllegalArgumentException, IllegalStateException{

        //Meeting meetingToBeOperatedOn = null;
        PastMeeting meetingToBeReturned = null;

        if(text == null){
            throw new NullPointerException();
        }

        for(FutureMeeting i : futureMeetingSet){
            if(i.getId() == id){
                meetingToBeReturned = new PastMeetingImpl(i.getId(),i.getDate(),i.getContacts(), text);
                futureMeetingSet.remove(i);
                pastMeetingSet.add(meetingToBeReturned);
            }
        }
        for(PastMeeting i : pastMeetingSet){
            if(i.getId() == id){
                ((PastMeetingImpl) i).addToNotes(text);
                meetingToBeReturned = i;
            }
        }

        if(meetingToBeReturned == null){
            throw new IllegalArgumentException();
        }

        if(meetingToBeReturned.getDate().after(presentDate)){
            throw new IllegalStateException();
        }
        return meetingToBeReturned;
    }

    public int addNewContact(String name, String notes) throws IllegalArgumentException, NullPointerException {

        if(name.equals("") || notes.equals("")){
            throw new IllegalArgumentException();
        }

        // create meeting and add it to set
        Contact contactToGenerateId = new ContactImpl(1,name,notes);
        // generate unique id
        int generatedId = new IdGeneratorImpl().genId(contactToGenerateId);

        Contact contactToSet = new ContactImpl(generatedId,name,notes);

        contactSet.add(contactToSet);

        return generatedId;
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
