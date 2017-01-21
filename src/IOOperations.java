import java.util.List;
import java.util.Set;

/**
 * A class to manage IOOperations
 */
public interface IOOperations {

    /**
     * This method reads and parses the data from contacts.txt
     * @return List of list of strings read from csv files.
     */
    List<List<String>> readFromFile();

    /**
     * This method Contacts to the csv file contacts.txt
     */
    void writeContactsToFile(Set<Contact> contacts);

    /**
     * This method FutureMeeting to the csv file contacts.txt
     */
    void writeFutureMeetingsToFile(Set<FutureMeeting> futureMeetings);

    /**
     * This method PastMeeting to the csv file contacts.txt
     */
    void writePastMeetingsToFile(Set<PastMeeting> pastMeetings);

    /**
     * This method flushes and overrides files with Contacts, FutureMeetings and Pastmeetings
     * from the system.
     */
    void overWriteFile();


    /**
     * This method reads contacts from file and stores them in Set<Contact>
     * @return Set<Contact>
     */
    Set<Contact> readContactsFromFile();


    /**
     * This method reads futureMeetings from file and stores them in Set<FutureMeeting>
     * @return Set<FutureMeeting>
     */
    Set<FutureMeeting> readFutureMeetingFromFile();

    /**
     * This method reads PastMeeting from file and stores them in Set<PastMeeting>
     * @return Set<PastMeeting>
     */
    Set<PastMeeting> readPastMeetingFromFile();




}
