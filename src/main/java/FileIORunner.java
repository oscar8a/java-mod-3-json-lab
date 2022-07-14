import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileIORunner {
    public static void main(String[] args) throws IOException {

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
                personList = FileReader.parseCsv("simple.data");
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
                    FileReader.writeCsv("simple.data", personList);
                } else if (option.equals("2")) {
                    // Print a list of current persons
                    printPersonList(personList);
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

    public static void printPersonList(List<Person> personList) {
        for (Person person : personList) {
            System.out.println(person.toString());
        }
    }
}
