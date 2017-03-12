package main.java.impl;

import com.intellij.util.containers.ArrayListSet;
import main.java.spec.Contact;
import main.java.spec.ContactManager;
import main.java.spec.FutureMeeting;
import main.java.spec.IOOperations;
import main.java.spec.Meeting;
import main.java.spec.PastMeeting;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Implements interface main.java.spec.ContactManager.
 */
public class ContactManagerImpl implements ContactManager {
    /**
     * Global instance of IOOperations.
     */
    private IOOperations iOHandler = new IOOperationsImpl();
    /**
     * Set that holds contacts in the system.
     */
    private Set<Contact> contactSet = new ArrayListSet<>();
    /**
     * Set that holds futureMeeting in the system.
     */
    private Set<FutureMeeting> futureMeetingSet = new ArrayListSet<>();
    /**
     * Set that holds past meetings in the set.
     */
    private Set<PastMeeting> pastMeetingSet = new ArrayListSet<>();
    /**
     * Global instance of Calendar.
     */
    private Calendar presentDate = Calendar.getInstance();

    /**
     * accessor for contacts set.
     *
     * @return contacts is system
     */
    public Set<Contact> getContactSet() {
        return contactSet;
    }

    /**
     * accessor for futureMeeting set.
     *
     * @return futureMeetings in system
     */
    public Set<FutureMeeting> getFutureMeetingSet() {
        return futureMeetingSet;
    }

    /**
     * accessor for pastMeeting set.
     *
     * @return pastMeetings in system
     */
    public Set<PastMeeting> getPastMeetingSet() {
        return pastMeetingSet;
    }

    /**
     * Mutator for contactSet.
     * This method is used to mutate contactSet for testing purposes
     * @param contactSetToGet sets contacts in the system.
     *
     */
    public void setContactSet(final Set<Contact> contactSetToGet) {
        this.contactSet = contactSetToGet;
    }

    /**
     * Mutator for futureMeetingSet.
     * This method is used to mutate futureMeetingSet for testing purposes
     * @param futureMeetingSetToSet sets futureMeetings in the system.
     */
    public void setFutureMeetingSet(final Set<FutureMeeting>
                                            futureMeetingSetToSet) {
        this.futureMeetingSet = futureMeetingSetToSet;
    }

    /**
     * Mutator for pastMeetingSet.
     * This method is used to access pastMeetingSet for testing purposes
     * @param pastMeetingSetToSet sets pastMeetings in the system.
     */
    public void setPastMeetingSet(final Set<PastMeeting> pastMeetingSetToSet) {
        this.pastMeetingSet = pastMeetingSetToSet;
    }

    /**
     * Implements addFutureMeeting.
     * @param contacts a set of contacts that will participate in the meeting
     * @param date the date on which the meeting will take place
     * @return id of the meeting
     * @throws IllegalArgumentException is thrown if date precedes past date
     * @throws NullPointerException is thrown if the meeting or date is null
     */
    @Override
    public int addFutureMeeting(final Set<Contact> contacts,
                                final Calendar date)
            throws IllegalArgumentException, NullPointerException {

        if (date.before(presentDate)) {
            throw new IllegalArgumentException();
        }

        // using streams and containsAll to check contact match by id
        boolean isInContactSet = contactSet.stream().map(Contact::getId)
                .collect(Collectors.toList()).containsAll(contacts.stream()
                        .map(Contact::getId).collect(Collectors.toList()));

        if (!isInContactSet) {
            throw new IllegalArgumentException();
        }

        // create meeting and add it to set
        Meeting futureMeetingToGenerateId =
                new FutureMeetingImpl(1, date, contacts);

        // generate unique id
        int generatedId = new IdGeneratorImpl().genId(futureMeetingToGenerateId,
                        futureMeetingSet);

        FutureMeeting futureMeetingToSet =
                new FutureMeetingImpl(generatedId, date, contacts);

        futureMeetingSet.add(futureMeetingToSet);

        return generatedId;
    }

    /**
     * Implements getPastMeeting.
     * @param id the ID for the meeting
     * @return id of the meeting added to the system
     * @throws IllegalStateException is thrown
     * if date succeeds present date or is null
     */
    @Override
    public PastMeeting getPastMeeting(final int id)
            throws IllegalStateException {

        PastMeeting pastMeetingToReturn = null;

        for (PastMeeting i: pastMeetingSet) {
            if (i.getId() == id) {
                pastMeetingToReturn = i;
            }
        }

        if (pastMeetingToReturn != null
                && pastMeetingToReturn.getDate().after(presentDate)) {
            throw new IllegalStateException();
        }

        return pastMeetingToReturn;
    }

    /**
     * Implements getFutureMeeting.
     * @param id the ID for the meeting
     * @return futureMeeting with given id or null
     * @throws IllegalStateException if meeting null or precedes past date
     */
    @Override
    public FutureMeeting getFutureMeeting(final int id)
            throws IllegalStateException {

        FutureMeeting futureMeetingToReturn = null;

        for (FutureMeeting i: futureMeetingSet) {
            if (i.getId() == id) {
                futureMeetingToReturn = i;
            }
        }

        if (futureMeetingToReturn != null
                && futureMeetingToReturn.getDate().before(presentDate)) {
            throw new IllegalStateException();
        }

        return futureMeetingToReturn;
    }

    /**
     * Implements getMeeting.
     * @param id the ID for the meeting
     * @return meeting with given id or null
     */
    @Override
    public Meeting getMeeting(final int id) {

        Meeting meetingToReturn = null;

        for (FutureMeeting i : futureMeetingSet) {
            if (i.getId() == id) {
                meetingToReturn = i;
            }
        }

        for (PastMeeting j : pastMeetingSet) {
            if (j.getId() == id) {
                meetingToReturn = j;
            }
        }
        return meetingToReturn;
    }

    /**
     * Implements getFutureMeetingList.
     * @param contact one of the user’s contacts
     * @return list of future meeting by contact
     * @throws NullPointerException if contact is null
     */
    @Override
    public List<Meeting> getFutureMeetingList(final Contact contact)
            throws NullPointerException {

        List<Meeting> meetingToReturn = new ArrayList<>();
        Set<Meeting> setToRemoveDuplicates = new ArrayListSet<>();

        if (contact == null) {
            throw new NullPointerException();
        }

        for (FutureMeeting i : futureMeetingSet) {
            if (i.getContacts().contains(contact)) {
                setToRemoveDuplicates.add(i);
            }
        }
        meetingToReturn.addAll(setToRemoveDuplicates);

        // sort chronologically
        meetingToReturn.sort(Comparator.comparing(Meeting::getDate));

        return meetingToReturn;
    }

    /**
     * Implements getMeetingListOn.
     * @param date the date
     * @return list on meetings by date
     */
    @Override
    public List<Meeting> getMeetingListOn(final Calendar date) {

        List<Meeting> meetingToReturn = new ArrayList<>();
        Set<Meeting> setToRemoveDuplicates = new ArrayListSet<>();

        if (date == null) {
            throw new NullPointerException();
        }

        for (FutureMeeting i : futureMeetingSet) {
            if (i.getDate().equals(date)) {
                setToRemoveDuplicates.add(i);
            }
        }
        for (PastMeeting i : pastMeetingSet) {
            if (i.getDate().equals(date)) {
                setToRemoveDuplicates.add(i);
            }
        }

        meetingToReturn.addAll(setToRemoveDuplicates);

        return meetingToReturn;
    }

    /**
     * Implements getPastMeetingListFor.
     * @param contact one of the user’s contacts
     * @return list of past meetings by contact
     */
    @Override
    public List<PastMeeting> getPastMeetingListFor(final Contact contact) {

        List<PastMeeting> pastMeetingToReturn = new ArrayList<>();
        Set<PastMeeting> setToRemoveDuplicates = new ArrayListSet<>();

        if (contact == null) {
            throw new NullPointerException();
        }

        for (PastMeeting i : pastMeetingSet) {
            if (i.getContacts().contains(contact)) {
                setToRemoveDuplicates.add(i);
            }

        }
        pastMeetingToReturn.addAll(setToRemoveDuplicates);

        // sort chronologically
        pastMeetingToReturn.sort(Comparator.comparing(Meeting::getDate));

        return pastMeetingToReturn;
    }

    /**
     * Implements addNewPastMeeting.
     * @param contacts a set of participants
     * @param date the date on which the meeting took place
     * @param text messages to be added about the meeting.
     * @return id of added meeting
     * @throws IllegalArgumentException is thrown is text is null
     * @throws NullPointerException is thrown if date succeeds present date
     */
    @Override
    public int addNewPastMeeting(final Set<Contact> contacts,
                                 final Calendar date, final String text)
            throws IllegalArgumentException, NullPointerException {

        if (text == null) {
            throw new NullPointerException();
        }

        if (date.after(presentDate)) {
            throw new IllegalArgumentException();
        }

        // using streams and containsAll to check contact match by id
        boolean isInContactSet = contactSet.stream().map(Contact::getId)
                .collect(Collectors.toList()).containsAll(contacts.stream()
                        .map(Contact::getId).collect(Collectors.toList()));

        if (!isInContactSet) {
            throw new IllegalArgumentException();
        }

        // create meeting and add it to set
        Meeting pastMeetingToGenerateId =
                new PastMeetingImpl(1, date, contacts, text);

        int generatedId = new IdGeneratorImpl().genId(pastMeetingToGenerateId,
                pastMeetingSet);

        PastMeeting pastMeetingToSet =
                new PastMeetingImpl(generatedId, date, contacts, text);

        pastMeetingSet.add(pastMeetingToSet);

        return generatedId;

    }

    /**
     * Implements addMeetingNotes.
     * @param id the ID of the meeting
     * @param text messages to be added about the meeting.
     * @return pastMeeting with notes added
     * @throws NullPointerException is thrown if text is null
     * @throws IllegalArgumentException is thrown
     * if meeting precedes present date
     * @throws IllegalStateException is thrown if meeting doesn't exist
     *
     */
    @Override
    public PastMeeting addMeetingNotes(final int id, final String text)
            throws IllegalArgumentException,
            IllegalStateException, NullPointerException {

        PastMeeting meetingToBeReturned = null;

        if (text == null) {
            throw new NullPointerException();
        }

        for (FutureMeeting i : futureMeetingSet) {
            if (i.getId() == id) {
                meetingToBeReturned = new PastMeetingImpl(i.getId(),
                        i.getDate(), i.getContacts(), text);
                futureMeetingSet.remove(i);
                pastMeetingSet.add(meetingToBeReturned);
            }
        }
        for (PastMeeting i : pastMeetingSet) {
            if (i.getId() == id) {
                ((PastMeetingImpl) i).addToNotes(text);
                meetingToBeReturned = i;
            }
        }

        if (meetingToBeReturned == null) {
            throw new IllegalArgumentException();
        }

        if (meetingToBeReturned.getDate().after(presentDate)) {
            throw new IllegalStateException();
        }
        return meetingToBeReturned;
    }

    /**
     * Implements addNewContact.
     * @param name the name of the contact.
     * @param notes notes to be added about the contact.
     * @return id of added contact
     * @throws IllegalArgumentException if name is or notes are empty strings
     * @throws NullPointerException if names of notes are null
     */
    @Override
    public int addNewContact(final String name, final String notes)
            throws IllegalArgumentException, NullPointerException {

        if (name.equals("") || notes.equals("")) {
            throw new IllegalArgumentException();
        }

        // create meeting and add it to set
        Contact contactToGenerateId = new ContactImpl(1, name, notes);

        // generate unique id
        int generatedId =
                new IdGeneratorImpl().genId(contactToGenerateId, contactSet);

        Contact contactToSet = new ContactImpl(generatedId, name, notes);

        contactSet.add(contactToSet);

        return generatedId;
    }

    /**
     * Implements getContacts.
     * @param name the string to search for
     * @return set of contacts
     * @throws NullPointerException is thrown if name is null
     */
    @Override
    public Set<Contact> getContacts(final String name)
            throws NullPointerException {

        // matching with regular expression
        return contactSet.stream()
                .filter(a -> a.getName().matches("(.*)" + name + "(.*)"))
                .collect(Collectors.toSet());

    }

    /**
     * Implements getContacts.
     * @param ids an arbitrary number of contact IDs
     * @return set of contacts by ids
     */
    @Override
    public Set<Contact> getContacts(final int... ids) {
        Set<Contact> setToReturn = new ArrayListSet<>();

        if (ids.length == 0) {
            throw new IllegalArgumentException();
        }
        for (int i: ids) {
            if (!contactSet.stream().map(Contact::getId)
                    .collect(Collectors.toList()).contains(i)) {
                throw new IllegalArgumentException();
            }
        }
        for (Contact i : contactSet) {
            for (int j: ids) {
                if (i.getId() == j) {
                    setToReturn.add(i);
                }
            }
        }

        return setToReturn;
    }

    /**
     * Implements flush.
     */
    @Override
    public void flush() {
        iOHandler.overWriteFile();
        iOHandler.writeContactsToFile(contactSet);
        iOHandler.writeFutureMeetingsToFile(futureMeetingSet);
        iOHandler.writePastMeetingsToFile(pastMeetingSet);
    }

    /**
     * loadFromFile loads the contents of contacts.txt to the system.
     * It is not a part of the interface
     * and has to be invoked before any operations take place,
     * as it loads the stored content into the system.
      */
    public void loadFromFile() {
        contactSet.addAll(readContactsToSet());
        futureMeetingSet.addAll(readFutureMeetingsToSet());
        pastMeetingSet.addAll(readPastMeetingsToSet());

    }

    /**
     * Auxiliary method for loadFromFIle,
     * that eliminates duplicate contacts from the fileReader
     * by filtering against contacts in system memory.
     * @return set of contacts read in from file,
     * that are not already in system memory
     */
    private Set<Contact> readContactsToSet() {

        Set<Contact> contactsRead = iOHandler.readContactsFromFile();

        return contactsRead.stream().filter(a -> !contactSet.stream()
                    .map(Contact::getId)
                    .collect(Collectors.toSet()).contains(a.getId())).
                    collect(Collectors.toSet());
    }

    /**
     * Auxiliary method for loadFromFIle,
     * that eliminates duplicate futureMeetings from the fileReader
     * by filtering against contacts in system memory.
     * @return a set of futureMeeting, that are not already in system memory
     */
    private Set<FutureMeeting> readFutureMeetingsToSet() {

        Set<FutureMeeting> futureMeetingsRead =
                iOHandler.readFutureMeetingFromFile();

        return futureMeetingsRead.stream()
                .filter(a -> !futureMeetingSet.stream()
                   .map(FutureMeeting::getId).collect(Collectors.toList())
                        .contains(a.getId()))
                .collect(Collectors.toSet());
    }

    /**
     * Auxiliary method for loadFromFIle,
     * that eliminates duplicate pastMeeting from the fileReader
     * by filtering against contacts in system memory.
     * @return a set of pastMeeting, that are not already in system memory
     */
    private Set<PastMeeting> readPastMeetingsToSet() {

        Set<PastMeeting> pastMeetingsRead = iOHandler.readPastMeetingFromFile();

        return pastMeetingsRead.stream().filter(a -> !pastMeetingSet.stream()
                .map(PastMeeting::getId)
                .collect(Collectors.toList()).contains(a.getId()))
                .collect(Collectors.toSet());
    }
}
