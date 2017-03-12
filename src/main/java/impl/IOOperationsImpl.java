package main.java.impl;

import com.intellij.util.containers.ArrayListSet;
import main.java.spec.Contact;
import main.java.spec.FutureMeeting;
import main.java.spec.IOOperations;
import main.java.spec.PastMeeting;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Implements main.java.spec.IOOperations.
 */
public class IOOperationsImpl implements IOOperations {
    /**
     * Global set of contacts read in from file to be used by other methods.
     */
    private Set<Contact> contactsLoaded = new ArrayListSet<>();
    /**
     * Regular expressions for parsing cvs file.
     */
    private static final String REGEX = ", (?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)";
    /**
     * FilePath for safe file.
     */
    private static String filePath = "contacts.txt";

    /**
     * Implements readFromFile.
     * @return list of list of strings that are read from file
     */
    @Override
    public List<List<String>> readFromFile() {
        BufferedReader reader;
        List<List<String>> inputList;

        while (true) {
            try {

                reader = new BufferedReader(new InputStreamReader(
                        new FileInputStream(filePath),
                        Charset.defaultCharset()));
                inputList = reader.lines()
                        .map(line -> Stream.of(line.split(REGEX))
                                .map(a -> a.replace("\"", ""))
                                .map(b -> b.replace("<newline>", "\n"))
                        .collect(Collectors.toList()))
                        .collect(Collectors.toList());

                reader.close();
                break;
            } catch (FileNotFoundException ex) {
                try {
                    File fileHandle = new File("contacts.txt");
                    boolean created = fileHandle.createNewFile();


                } catch (IOException e) {
                    e.printStackTrace();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return inputList;
    }

    /**
     * Implements writeContactsToFile.
     * @param contacts is a set of contacts to write to file
     */
    @Override
    public void writeContactsToFile(final Set<Contact> contacts) {
        BufferedWriter writer;
        try {

            writer = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(filePath, true), "UTF-8"));

            for (Contact i : contacts) {
                writer.write("main.java.spec.Contact");
                writer.write(", ");
                writer.write(Integer.toString(i.getId()));
                writer.write(", ");
                writer.write(i.getName());
                writer.write(", ");
                writer.write("\"");
                writer.write(i.getNotes().replace("\n", "<newline>"));
                writer.write("\"");
                writer.write(", ");
                writer.newLine();
            }

            writer.flush();
            writer.close();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Implements writeFutureMeetingsToFile.
     * @param futureMeetings is a set of futureMeetings to write to file
     */
    @Override
    public void writeFutureMeetingsToFile(final Set<FutureMeeting>
                                                      futureMeetings) {

        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(filePath, true), "UTF-8"));
            for (FutureMeeting i : futureMeetings) {
                writer.write("main.java.spec.FutureMeeting");
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
                writer.write(Integer.toString(i.getDate()
                        .get(Calendar.MINUTE)));
                writer.write(", ");
                writer.write("\"");
                writer.write(i.getContacts().stream().map(Contact::getId)
                        .collect(Collectors.toList()).toString());
                writer.write("\"");
                writer.write(", ");
                writer.newLine();
            }

            writer.flush();
            writer.close();

        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    /**
     * Implements writePastMeetingsToFile.
     * @param pastMeetings is a set of pastMeetings to write to file
     */
    @Override
    public void writePastMeetingsToFile(final Set<PastMeeting> pastMeetings) {

        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(filePath, true), "UTF-8"));
            for (PastMeeting i : pastMeetings) {
                writer.write("main.java.spec.PastMeeting");
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
                writer.write(Integer.toString(i.getDate()
                        .get(Calendar.MINUTE)));
                writer.write(", ");
                writer.write("\"");
                writer.write(i.getContacts().stream().map(Contact::getId)
                        .collect(Collectors.toList()).toString());
                writer.write("\"");
                writer.write(", ");
                writer.write("\"");
                writer.write(i.getNotes().replace("\n", "<newline>"));
                writer.write("\"");
                writer.newLine();
            }

            writer.flush();
            writer.close();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Implements overWriteFile.
     */
    @Override
    public void overWriteFile() {

        try {
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(filePath), "UTF-8"));
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Implement readContactsFromFile.
     * @return set of contacts read from file
     */
    public Set<Contact> readContactsFromFile() {

        Set<Contact> toReturn = new ArrayListSet<>();

        List<List<String>> contactsInput = this.readFromFile().stream()
                .filter(a -> a.get(0).equals("main.java.spec.Contact"))
                .collect(Collectors.toList());

        for (List<String> i: contactsInput) {
            int id; String name; String notes;
            id = Integer.parseInt(i.get(1));
            name = i.get(2);
            notes = i.get(3);
            toReturn.add(new ContactImpl(id, name, notes));
        }

        contactsLoaded = toReturn;

        return toReturn;
    }

    /**
     * Implements readFutureMeetingFromFile.
     * @return set of futureMeetings read from file
     */
    @Override
    @SuppressWarnings("Duplicates")
    public Set<FutureMeeting> readFutureMeetingFromFile() {

        Set<FutureMeeting> toReturn = new ArrayListSet<>();

        List<List<String>> futureMeetingInput = this.readFromFile().stream()
                .filter(a -> a.get(0).equals("main.java.spec.FutureMeeting"))
                .collect(Collectors.toList());

        for (List<String> i: futureMeetingInput) {
            int id; List<Integer> date = new ArrayList<>();
            Set<Contact> contacts = new ArrayListSet<>();
                id = Integer.parseInt(i.get(1));
                date.add(Integer.parseInt(i.get(2).split(":")[0]));
                date.add(Integer.parseInt(i.get(2).split(":")[1]));
                date.add(Integer.parseInt(i.get(2).split(":")[2]));
                date.add(Integer.parseInt(i.get(2).split(":")[3]));
                date.add(Integer.parseInt(i.get(2).split(":")[4]));
                String temp;
                temp = i.get(3).replace("[", "");
                temp = temp.replace("]", "");

                for (String j : temp.split(", ")) {
                    List<Contact> contactsIdList = contactsLoaded.stream()
                            .filter(a -> a.getId() == Integer.parseInt(j))
                            .collect(Collectors.toList());

                    for (Contact k : contactsIdList) {
                        if (k.getId() == Integer.parseInt(j)) {
                            contacts.add(k);
                        }
                    }
                }
            try {
                toReturn.add(new FutureMeetingImpl(id,
                        new GregorianCalendar(date.get(0),
                        date.get(1), date.get(2), date.get(3),
                                date.get(4)), contacts));
            } catch (IllegalArgumentException ex) {
                return toReturn;
            }
        }
        return toReturn;
    }

    /**
     * Implements readPastMeetingFromFile.
     * @return set of pastMeetings read from file
     */
    @Override
    @SuppressWarnings("Duplicates")
    public Set<PastMeeting> readPastMeetingFromFile() {

        Set<PastMeeting> toReturn = new ArrayListSet<>();
        List<List<String>> pastMeetingInput = this.readFromFile().stream()
                .filter(a -> a.get(0).equals("main.java.spec.PastMeeting"))
                .collect(Collectors.toList());

        for (List<String> i: pastMeetingInput) {
            int id; List<Integer> date = new ArrayList<>();
            Set<Contact> contacts = new ArrayListSet<>();
            String notes;

            id = Integer.parseInt(i.get(1));
            date.add(Integer.parseInt(i.get(2).split(":")[0]));
            date.add(Integer.parseInt(i.get(2).split(":")[1]));
            date.add(Integer.parseInt(i.get(2).split(":")[2]));
            date.add(Integer.parseInt(i.get(2).split(":")[3]));
            date.add(Integer.parseInt(i.get(2).split(":")[4]));
            String temp;
            temp = i.get(3).replace("[", "");
            temp = temp.replace("]", "");

            for (String j : temp.split(", ")) {
                List<Contact> contactsIdList = contactsLoaded.stream()
                        .filter(a -> a.getId() == Integer.parseInt(j))
                        .collect(Collectors.toList());

                for (Contact k : contactsIdList) {
                    if (k.getId() == Integer.parseInt(j)) {
                        contacts.add(k);
                    }
                }
            }

            notes = i.get(4);

            try {
                toReturn.add(new PastMeetingImpl(id,
                        new GregorianCalendar(date.get(0),
                        date.get(1), date.get(2), date.get(3),
                        date.get(4)), contacts, notes));
            } catch (IllegalArgumentException ex) {
                return toReturn;
            }
        }
        return toReturn;
    }
}
