import java.util.Scanner;

class ScannerUserInputService implements UserInputService {
    private final Scanner scanner;
    private final UserOutputService userOutputService;

    public ScannerUserInputService(UserOutputService userOutputService) {
        this.scanner = new Scanner(System.in);
        this.userOutputService = userOutputService;
    }

    public String getUserInput(String prompt) {
        userOutputService.printMessage(prompt);
        String input = scanner.nextLine();
        if (input.isBlank()) {
            return getUserInput(prompt);
        }
        return input;
    }

    public int getUserInputInt(String prompt) {
        userOutputService.printMessage(prompt);
        String input = scanner.nextLine();
        if (input.isBlank()) {
            return getUserInputInt(prompt);
        }
        return Integer.parseInt(input);
    }

    @Override
    public void close() throws Exception {
        scanner.close();
    }
}
