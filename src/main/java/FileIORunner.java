import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class FileIORunner {
    private static String fileName = "";
    public static void main(String[] args) throws IOException {
        // Implement the use of arguments to write to files
        System.out.println(args.length);
        if (args.length >= 1) {
            readAndWriting(args);
        } else {
            System.out.println("invalid arguments");
            return;
        }

        UserOutputService userOutputService = new SysoutUserOutputService();

        try (UserInputService userInputService = new ScannerUserInputService(userOutputService)) {
            // loop flag
            boolean keepRunningProgram = true;

            // Instantiate person list
            List<Person> personList = new ArrayList<>();

            // prompt user if they want to restore from file
            userOutputService.printMessage("Restore People from File?");
            String shouldRestore = userInputService.getUserInput("Enter 'Y' if you wish to restore");

            if (shouldRestore.toUpperCase().equals("Y")) {
                // This is a List of Person Objects, parsed from the CSV File if User wishes to restore
                userOutputService.printMessage(fileName);
                personList = FileReader.parseCsv(fileName);
//                FileReader.writeJson(personList);
            }

            while(keepRunningProgram) {
                // Prompt Users with 3 Options
                userOutputService.printMessage("\n1. Add a person to the list");
                userOutputService.printMessage("2. Print a list of current persons");
                userOutputService.printMessage("3. Exit program");
                String option = userInputService.getUserInput("Enter Option 1, 2 or 3:");

                if (option.equals("1")) {
                    // Go through process of creating person and adding to list
                    AddPersonToList addPersonToList = new AddPersonToList(userInputService);
                    Person p = addPersonToList.createPerson();
                    personList.add(p);
                    // Write the instantiation of person list to csv file
                    FileReader.writeCsv(fileName, personList);
                } else if (option.equals("2")) {
                    // Print a list of current persons
                    // printPersonList(personList);

                    // Printing List as JSON
                    printPersonListAsJSON(personList);

                } else if (option.equals("3")) {
                    // Exit the program
                    userOutputService.printMessage("Exiting program...");
                    keepRunningProgram = false;
                }
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void readAndWriting(String[] args) {
        // Get file name from first argument
        if (args.length > 0) {
            fileName = args[0];
        }
        System.out.println(fileName);
        // If file does not exist, exit program
        if (!new File(fileName).exists()) {
            System.out.println("Can't open file.");
            System.exit(1);
        }

        System.out.println("The user has chosen this file: " + fileName);
    }

    public static void printPersonList(List<Person> personList) {
        for (Person person : personList) {
            System.out.println(person.toString());
        }
    }

    static void printPersonListAsJSON(List<Person> personList) throws JsonProcessingException {
        String json = new ObjectMapper().writeValueAsString(personList);
        // System.out.println(json);

        // formatted JSON
        String newJson = "";
        for (String s : json.split("},")) {
            newJson += s + "}\n";
        }
        System.out.println("Formatted JSON:\n" + newJson);
    }
}
