public class AddPersonToList {
    private final UserInputService userInputService;

    public AddPersonToList(UserInputService userInputService) {
        this.userInputService = userInputService;
    }

    public Person createPerson() {
        String firstName = userInputService.getUserInput("What's the person's first name?");
        String lastName = userInputService.getUserInput("What's the person's last name?");
        int birthDay = userInputService.getUserInputInt("What's the person's birth Day only, no Month, no Year?");
        int birthMonth = userInputService.getUserInputInt("What's the person's birth Month?");
        int birthYear = userInputService.getUserInputInt("What's the person's birth Year?");
        return new Person(firstName, lastName, birthDay, birthMonth, birthYear);
    }
}
