package src;

import com.intellij.util.containers.ArrayListSet;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Implements interface src.ContactManager
 */
public class ContactManagerImpl implements ContactManager {

    private IOOperations IOHandler = new IOOperationsImpl();
    private Set<Contact> contactSet = new ArrayListSet<>();
    private Set<FutureMeeting> futureMeetingSet = new ArrayListSet<>();
    private Set<PastMeeting> pastMeetingSet = new ArrayListSet<>();
    private Calendar presentDate = Calendar.getInstance();


    public Set<Contact> getContactSet(){ return contactSet; }

    public Set<FutureMeeting> getFutureMeetingSet() { return futureMeetingSet; }

    public Set<PastMeeting> getPastMeetingSet() {
        return pastMeetingSet;
    }


    /**
     * This method is used to access contactSet for testing purposes
     * @param contactSet sets contacts in the system.
     *
     */
    public void setContactSet(Set<Contact> contactSet){
        this.contactSet = contactSet;
    }

    /**
     * This method is used to access futureMeetingSet for testing purposes
     * @param futureMeetingSet sets futureMeetings in the system.
     */
    public void setFutureMeetingSet(Set<FutureMeeting> futureMeetingSet){
        this.futureMeetingSet = futureMeetingSet;
    }

    /**
     * This method is used to access pastMeetingSet for testing purposes
     * @param pastMeetingSet sets pastMeetings in the system.
     */
    public void setPastMeetingSet(Set<PastMeeting> pastMeetingSet){
        this.pastMeetingSet = pastMeetingSet;
    }


    @Override
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
        int generatedId = new IdGeneratorImpl().genId(futureMeetingToGenerateId, futureMeetingSet);

        FutureMeeting futureMeetingToSet = new FutureMeetingImpl(generatedId, date, contacts);

        futureMeetingSet.add(futureMeetingToSet);

        return generatedId;
    }

    @Override
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

    @Override
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

    @Override
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

    @Override
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
        meetingToReturn.sort(Comparator.comparing(Meeting::getDate));

        return meetingToReturn;
    }

    @Override
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

    @Override
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
        pastMeetingToReturn.sort(Comparator.comparing(Meeting::getDate));

        return pastMeetingToReturn;
    }

    @Override
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

        int generatedId = new IdGeneratorImpl().genId(pastMeetingToGenerateId,pastMeetingSet);

        PastMeeting pastMeetingToSet = new PastMeetingImpl(generatedId, date, contacts, text);

        pastMeetingSet.add(pastMeetingToSet);

        return generatedId;

    }

    @Override
    public PastMeeting addMeetingNotes(int id, String text)
            throws IllegalArgumentException, IllegalStateException{

        //src.Meeting meetingToBeOperatedOn = null;
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

    @Override
    public int addNewContact(String name, String notes) throws IllegalArgumentException, NullPointerException {

        if(name.equals("") || notes.equals("")){
            throw new IllegalArgumentException();
        }

        // create meeting and add it to set
        Contact contactToGenerateId = new ContactImpl(1,name,notes);
        // generate unique id
        int generatedId = new IdGeneratorImpl().genId(contactToGenerateId, contactSet);

        Contact contactToSet = new ContactImpl(generatedId,name,notes);

        contactSet.add(contactToSet);

        return generatedId;
    }

    @Override
    public Set<Contact> getContacts(String name) throws NullPointerException{

        // matching with regular expression
        return contactSet.stream()
                .filter(a -> a.getName().matches("(.*)"+name+"(.*)"))
                .collect(Collectors.toSet());

    }

    @Override
    public Set<Contact> getContacts(int... ids){
        Set<Contact> setToReturn = new ArrayListSet<>();

        if(ids.length == 0){
            throw new IllegalArgumentException();
        }
        for(int i: ids) {
            if(!contactSet.stream().map(Contact::getId).collect(Collectors.toList()).contains(i)){
                throw new IllegalArgumentException();
            }
        }
        for(Contact i : contactSet){
            for(int j: ids){
                if(i.getId() == j){
                    setToReturn.add(i);
                }
            }
        }

        return setToReturn;
    }

    @Override
    public void flush(){

        IOHandler.overWriteFile();
        IOHandler.writeContactsToFile(contactSet);
        IOHandler.writeFutureMeetingsToFile(futureMeetingSet);
        IOHandler.writePastMeetingsToFile(pastMeetingSet);
    }


    /**
     * loadFromFile loads the contents of contacts.txt to the system.
     * It is not a part of the interface and has to be invoked before any operations take place,
     * as it loads the stored content into the system.
      */
    public void loadFromFile(){

        contactSet.addAll(readContactsToSet());
        futureMeetingSet.addAll(readFutureMeetingsToSet());
        pastMeetingSet.addAll(readPastMeetingsToSet());

    }

    /**
     * Auxiliary method for loadFromFIle, that eliminates duplicate contacts from the fileReader
     */
    private Set<Contact> readContactsToSet(){

        Set<Contact> contactsRead = IOHandler.readContactsFromFile();

        return contactsRead.stream().filter(a -> !contactSet.stream()
                    .map(Contact::getId).collect(Collectors.toSet()).contains(a.getId())).
                    collect(Collectors.toSet());

    }

    /**
     * Auxiliary method for loadFromFIle, that eliminates duplicate futureMeetings from the fileReader
     */
    private Set<FutureMeeting> readFutureMeetingsToSet(){

        Set<FutureMeeting> futureMeetingsRead = IOHandler.readFutureMeetingFromFile();

        return futureMeetingsRead.stream().filter(a -> !futureMeetingSet.stream()
                   .map(FutureMeeting::getId).collect(Collectors.toList()).contains(a.getId())).
                    collect(Collectors.toSet());

    }

    /**
     * Auxiliary method for loadFromFIle, that eliminates duplicate pastMeeting from the fileReader
     */
    private Set<PastMeeting> readPastMeetingsToSet(){

        Set<PastMeeting> pastMeetingsRead = IOHandler.readPastMeetingFromFile();

        return pastMeetingsRead.stream().filter(a -> !pastMeetingSet.stream()
                .map(PastMeeting::getId).collect(Collectors.toList()).contains(a.getId())).
                collect(Collectors.toSet());

    }


}