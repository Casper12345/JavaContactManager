import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by Casper on 01/01/2017.
 */
public class IOOperationsImpl implements IOOperations{

    private final String REGEX = ",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)";

    public List<List<String>> readFromFile(){
        BufferedReader reader;
        List<List<String>> inputList = new ArrayList<>();

        try {
            reader = new BufferedReader(new FileReader("contacts.txt"));

            inputList = reader.lines().map(line -> Stream.of(line.split(REGEX))
                    .collect(Collectors.toList())).collect(Collectors.toList());

        }catch (FileNotFoundException ex){
            System.out.println("File Not Found");
        }

        return inputList;
    }

    public void writeToFile(){

    }
}
