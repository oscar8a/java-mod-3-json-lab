import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileReader {

    public static List<Person> parseCsv(String fileName) {
        // create a list of people to save our data to
        List<Person> people = new ArrayList<>();

        // open the file using Scanner
        File csvFile = new File(fileName);
        Scanner scanner;
        try {
            scanner = new Scanner(csvFile);
        } catch (Exception e) {
            System.out.println("File does not exist");
            return null;
        }
        System.out.println("File does exist!");

        // read off the header
        String header = scanner.nextLine();

        // While there is a next line in the file
        while (scanner.hasNextLine()) {
            //Read off the next line
            String thisLine = scanner.nextLine();

            //Split on commas
            String[] peopleStrings = thisLine.split(",");

            //Make a person object
            String age = peopleStrings[0];
            String name = peopleStrings[1];

            Person p = new Person(age, name);

            //Add the person instance to the list
            people.add(p);
        }

        //Return people list
        return people;
    }

    public static void writeCsv(String fileName, List<Person> personList) {
        try (FileWriter fileWriter = new FileWriter(fileName)){
            // add header to csv file
            fileWriter.write("Age, Name\n");
            for (Person person : personList) {
                // write all person instances into the File
                fileWriter.write(person.toString() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
