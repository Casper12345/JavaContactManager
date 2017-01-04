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


}
