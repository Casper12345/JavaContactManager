package src;

import java.util.List;
import java.util.Set;

/**
 * A class to manage src.IOOperations
 */
public interface IOOperations {

    /**
     * This method reads and parses the data from contacts.txt
     * @return List of list of strings read from csv files.
     */
    List<List<String>> readFromFile();

    /**
     * This method writes Contacts to the csv file contacts.txt
     */
    void writeContactsToFile(Set<Contact> contacts);

    /**
     * This method writes src.FutureMeeting to the csv file contacts.txt
     */
    void writeFutureMeetingsToFile(Set<FutureMeeting> futureMeetings);

    /**
     * This method writes src.PastMeeting to the csv file contacts.txt
     */
    void writePastMeetingsToFile(Set<PastMeeting> pastMeetings);

    /**
     * This method flushes and overrides files with Contacts, FutureMeetings and Pastmeetings
     * from the system.
     */
    void overWriteFile();


    /**
     * This method reads contacts from file and stores them in Set<src.Contact>
     * @return Set<src.Contact>
     */
    Set<Contact> readContactsFromFile();


    /**
     * This method reads futureMeetings from file and stores them in Set<src.FutureMeeting>
     * @return Set<src.FutureMeeting>
     */
    Set<FutureMeeting> readFutureMeetingFromFile();

    /**
     * This method reads src.PastMeeting from file and stores them in Set<src.PastMeeting>
     * @return Set<src.PastMeeting>
     */
    Set<PastMeeting> readPastMeetingFromFile();

}
