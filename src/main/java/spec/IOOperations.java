package main.java.spec;

import java.util.List;
import java.util.Set;

/**
 * A class to manage main.java.spec.IOOperations.
 */
public interface IOOperations {

    /**
     * This method reads and parses the data from contacts.txt.
     * @return List of list of strings read from csv files.
     */
    List<List<String>> readFromFile();

    /**
     * This method writes Contacts to the csv file contacts.txt.
     * @param contacts to write to file
     */
    void writeContactsToFile(Set<Contact> contacts);

    /**
     * This method writes main.java.spec.FutureMeeting
     * to the csv file contacts.txt.
     * @param futureMeetings to write to file
     */
    void writeFutureMeetingsToFile(Set<FutureMeeting> futureMeetings);

    /**
     * This method writes main.java.spec.PastMeeting
     * to the csv file contacts.txt.
     * @param pastMeetings to write to file
     */
    void writePastMeetingsToFile(Set<PastMeeting> pastMeetings);

    /**
     * This method flushes and overrides files with Contacts,
     * FutureMeetings and Pastmeetings
     * from the system.
     */
    void overWriteFile();


    /**
     * This method reads contacts from file and
     * stores them in Set<main.java.spec.Contact>.
     * @return Set<main.java.spec.Contact>
     */
    Set<Contact> readContactsFromFile();


    /**
     * This method reads futureMeetings from file and
     * stores them in Set<main.java.spec.FutureMeeting>.
     * @return Set<main.java.spec.FutureMeeting>
     */
    Set<FutureMeeting> readFutureMeetingFromFile();

    /**
     * This method reads main.java.spec.PastMeeting
     * from file and stores them in Set<main.java.spec.PastMeeting>.
     * @return Set<main.java.spec.PastMeeting>
     */
    Set<PastMeeting> readPastMeetingFromFile();

}
