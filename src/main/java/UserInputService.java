public interface UserInputService extends AutoCloseable {
    String getUserInput(String prompt);
    int getUserInputInt(String prompt);

}
