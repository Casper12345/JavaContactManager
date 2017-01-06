import com.intellij.util.containers.ArrayListSet;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Calendar.YEAR;

/**
 * Implements IOOperations
 */
public class IOOperationsImpl implements IOOperations{

    private static final String REGEX = ", (?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)";
    private static String filePath = "contacts.txt";

    public List<List<String>> readFromFile() {
        BufferedReader reader;
        List<List<String>> inputList;

        while (true) {
            try {

                reader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath),
                        Charset.defaultCharset()));
                inputList = reader.lines().map(line -> Stream.of(line.split(REGEX))
                        .map(a -> a.replace("\"", "")).map(b -> b.replace("<newline>","\n"))
                        .collect(Collectors.toList())).collect(Collectors.toList());

                reader.close();
                break;
            } catch (FileNotFoundException ex) {
                try {
                    File fileHandle = new File("contacts.txt");
                    boolean created = fileHandle.createNewFile();


                }catch (IOException e){
                    e.printStackTrace();
                }

            }catch (IOException e){
                e.printStackTrace();
            }

        }
        return inputList;
    }

    public void writeContactsToFile(Set<Contact> contacts){
        BufferedWriter writer;
        try {

            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filePath,true),"UTF-8"));

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
            writer.close();

        }catch (IOException ex){
            ex.printStackTrace();
        }

    }

    public void writeFutureMeetingsToFile(Set<FutureMeeting> futureMeetings){

        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filePath,true),"UTF-8"));
            for(FutureMeeting i : futureMeetings){
                writer.write("FutureMeeting");
                writer.write(", ");
                writer.write(Integer.toString(i.getId()));
                writer.write(", ");
                writer.write(Integer.toString(i.getDate().get(Calendar.YEAR)));
                writer.write(":");
                writer.write(Integer.toString(i.getDate().get(Calendar.MONTH)));
                writer.write(":");
                writer.write(Integer.toString(i.getDate().get(Calendar.DATE)));
                writer.write(":");
                writer.write(Integer.toString(i.getDate().get(Calendar.HOUR)));
                writer.write(":");
                writer.write(Integer.toString(i.getDate().get(Calendar.MINUTE)));
                writer.write(", ");
                writer.write("\"");
                writer.write(i.getContacts().stream().map(Contact::getId).collect(Collectors.toList()).toString());
                writer.write("\"");
                writer.write(", ");
                writer.newLine();
            }

            writer.flush();
            writer.close();

        }catch (IOException ex){
            ex.printStackTrace();
        }

    }

    public void writePastMeetingsToFile(Set<PastMeeting> pastMeetings){

        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filePath,true),"UTF-8"));
            for(PastMeeting i : pastMeetings){
                writer.write("PastMeeting");
                writer.write(", ");
                writer.write(Integer.toString(i.getId()));
                writer.write(", ");
                writer.write(Integer.toString(i.getDate().get(Calendar.YEAR)));
                writer.write(":");
                writer.write(Integer.toString(i.getDate().get(Calendar.MONTH)));
                writer.write(":");
                writer.write(Integer.toString(i.getDate().get(Calendar.DATE)));
                writer.write(":");
                writer.write(Integer.toString(i.getDate().get(Calendar.HOUR)));
                writer.write(":");
                writer.write(Integer.toString(i.getDate().get(Calendar.MINUTE)));
                writer.write(", ");
                writer.write("\"");
                writer.write(i.getContacts().stream().map(Contact::getId).collect(Collectors.toList()).toString());
                writer.write("\"");
                writer.write(", ");
                writer.write("\"");
                writer.write(i.getNotes().replace("\n","<newline>"));
                writer.write("\"");
                writer.newLine();
            }

            writer.flush();
            writer.close();

        }catch (IOException ex){
            ex.printStackTrace();
        }

    }

    public void overWriteFile() {

        try {
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filePath),"UTF-8"));
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public Set<Contact> readContactsFromFile(){

        Set<Contact> toReturn = new ArrayListSet<>();

        List<List<String>> contactsInput = this.readFromFile().stream()
                .filter(a -> a.get(0).equals("Contact")).collect(Collectors.toList());

        for(List<String> i: contactsInput){
            int id; String name; String notes;
            id = Integer.parseInt(i.get(1));
            name = i.get(2);
            notes = i.get(3);
            toReturn.add(new ContactImpl(id,name,notes));

        }

        System.out.println(contactsInput);
        System.out.println(toReturn);


        return toReturn;
    }
}
