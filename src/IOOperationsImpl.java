import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Implements IOOperations
 */
public class IOOperationsImpl implements IOOperations{

    private final String REGEX = ", (?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)";

    public List<List<String>> readFromFile() {
        BufferedReader reader;
        List<List<String>> inputList;

        while (true) {
            try {
                reader = new BufferedReader(new FileReader("contacts.txt"));

                inputList = reader.lines().map(line -> Stream.of(line.split(REGEX))
                        .map(a -> a.replace("\"", "")).map(b -> b.replace("<newline>","\n"))
                        .collect(Collectors.toList())).collect(Collectors.toList());
                break;
            } catch (FileNotFoundException ex) {
                try {
                    File fileHandle = new File("contacts.txt");
                    fileHandle.createNewFile();

                }catch (IOException e){
                    e.printStackTrace();
                }

            }
        }
        return inputList;
    }

    public void writeContactsToFile(Set<Contact> contacts){
        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter("contacts.txt"));
            for(Contact i : contacts){
                writer.write("Contact");
                writer.write(", ");
                writer.write(Integer.toString(i.getId()));
                writer.write(", ");
                writer.write(i.getName());
                writer.write(", ");
                writer.write("\"");
                writer.write(i.getNotes().replace("\n","<newline>"));
                writer.write("\"");
                writer.write(", ");
                writer.newLine();
            }

            writer.flush();

        }catch (IOException ex){
            ex.printStackTrace();
        }

    }

    public void writeFutureMeetingsToFile(Set<FutureMeeting> futureMeetings){

    }
}
