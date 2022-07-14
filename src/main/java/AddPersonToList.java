public class AddPersonToList {
    private final UserInputService userInputService;

    public AddPersonToList(UserInputService userInputService) {
        this.userInputService = userInputService;
    }

    public Person createPerson() {
        String age = userInputService.getUserInput("What's the person's age?");
        String name = userInputService.getUserInput("What's the person's name?");
        return new Person(age, name);
    }
}
