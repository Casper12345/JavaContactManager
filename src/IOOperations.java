import java.util.List;

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
     * This method writes the data to the csv file contacts.txt
     */
    void writeToFile();

}
