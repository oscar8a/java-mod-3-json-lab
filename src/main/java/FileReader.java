import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

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
            String firstName = peopleStrings[0];
            String lastName = peopleStrings[1];
            int birthDay = Integer.parseInt(peopleStrings[2]);
            int birthMonth = Integer.parseInt(peopleStrings[3]);
            int birthYear = Integer.parseInt(peopleStrings[4]);

            Person p = new Person(firstName, lastName, birthDay, birthMonth, birthYear);

            //Add the person instance to the list
            people.add(p);
        }

        //Return people list
        return people;
    }

    public static void writeCsv(String fileName, List<Person> personList) {
        try (FileWriter fileWriter = new FileWriter(fileName)){
            StringBuffer allPersonsAsCSV = new StringBuffer();
            // add header to csv file
            fileWriter.write("Age, Name, BirthDay, BirthMonth, BirthYear\n");
            for (Person person : personList) {
                // write all person instances into the File
                fileWriter.write(person.formatAsCSV() + "\n");

                // JSON Stuff
                allPersonsAsCSV.append(person.formatAsCSV());
                writeToFile("PERSON_LIST_FILENAME", allPersonsAsCSV.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
    public static void writeToFile(String fileName, String text) throws IOException {
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(fileName);
            fileWriter.write(text);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        } finally {
            if (fileWriter != null)
                fileWriter.close();
        }
    }
}
