import com.intellij.util.containers.ArrayListSet;

import java.io.*;
import java.nio.charset.Charset;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;


/**
 * Implements IOOperations
 */
public class IOOperationsImpl implements IOOperations{
    private Set<Contact> contactsLoaded = new ArrayListSet<>();
    private static final String REGEX = ", (?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)";
    private static String filePath = "contacts.txt";

    @Override
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

    @Override
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

    @Override
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

    @Override
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

    @Override
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

        contactsLoaded = toReturn;

        return toReturn;
    }

    @Override
    @SuppressWarnings("Duplicates")
    public Set<FutureMeeting> readFutureMeetingFromFile(){

        Set<FutureMeeting> toReturn = new ArrayListSet<>();

        List<List<String>> futureMeetingInput = this.readFromFile().stream()
                .filter(a -> a.get(0).equals("FutureMeeting")).collect(Collectors.toList());

        for(List<String> i: futureMeetingInput){
            int id; List<Integer> date = new ArrayList<>(); Set<Contact> contacts = new ArrayListSet<>();
                id = Integer.parseInt(i.get(1));
                date.add(Integer.parseInt(i.get(2).split(":")[0]));
                date.add(Integer.parseInt(i.get(2).split(":")[1]));
                date.add(Integer.parseInt(i.get(2).split(":")[2]));
                date.add(Integer.parseInt(i.get(2).split(":")[3]));
                date.add(Integer.parseInt(i.get(2).split(":")[4]));
                String temp;
                temp = i.get(3).replace("[","");
                temp = temp.replace("]","");

                for(String j : temp.split(", ")){
                    List<Contact> contactsIdList = contactsLoaded.stream()
                            .filter(a -> a.getId() == Integer.parseInt(j))
                            .collect(Collectors.toList());

                    for(Contact k : contactsIdList){
                        if(k.getId() == Integer.parseInt(j)){
                            contacts.add(k);
                        }
                    }
                }
            try {
                toReturn.add(new FutureMeetingImpl(id, new GregorianCalendar(date.get(0),
                        date.get(1), date.get(2), date.get(3), date.get(4)), contacts));
            } catch (IllegalArgumentException ex){
                return toReturn;
            }
        }


        return toReturn;

    }

    @Override
    @SuppressWarnings("Duplicates")
    public Set<PastMeeting> readPastMeetingFromFile(){

        Set<PastMeeting> toReturn = new ArrayListSet<>();
        List<List<String>> pastMeetingInput = this.readFromFile().stream()
                .filter(a -> a.get(0).equals("PastMeeting")).collect(Collectors.toList());


        for(List<String> i: pastMeetingInput){
            int id; List<Integer> date = new ArrayList<>(); Set<Contact> contacts = new ArrayListSet<>();
            String notes;

            id = Integer.parseInt(i.get(1));
            date.add(Integer.parseInt(i.get(2).split(":")[0]));
            date.add(Integer.parseInt(i.get(2).split(":")[1]));
            date.add(Integer.parseInt(i.get(2).split(":")[2]));
            date.add(Integer.parseInt(i.get(2).split(":")[3]));
            date.add(Integer.parseInt(i.get(2).split(":")[4]));
            String temp;
            temp = i.get(3).replace("[","");
            temp = temp.replace("]","");

            for(String j : temp.split(", ")){
                List<Contact> contactsIdList = contactsLoaded.stream()
                        .filter(a -> a.getId() == Integer.parseInt(j))
                        .collect(Collectors.toList());

                for(Contact k : contactsIdList){
                    if(k.getId() == Integer.parseInt(j)){
                        contacts.add(k);
                    }
                }
            }

            notes = i.get(4);

            try {
                toReturn.add(new PastMeetingImpl(id, new GregorianCalendar(date.get(0),
                        date.get(1), date.get(2), date.get(3), date.get(4)), contacts, notes));
            }catch (IllegalArgumentException ex){
                return toReturn;
            }
        }

        return toReturn;

    }
}
