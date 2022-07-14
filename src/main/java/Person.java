public class Person {
    private String firstName;
    private String lastName;
    private int birthDay;
    private int birthMonth;
    private int birthYear;

    public Person(String firstName, String lastName, int birthDay, int birthMonth, int birthYear) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthYear = birthYear;
        this.birthMonth = birthMonth;
        this.birthDay = birthDay;
    }

    @Override
    public String toString() {
        StringBuffer personString = new StringBuffer();
        personString.append("firstName = " + firstName);
        personString.append("\n");
        personString.append("lastName = " + lastName);
        personString.append("\n");
        personString.append("Birth date = ");
        personString.append(birthMonth + "/");
        personString.append(birthDay+ "/");
        personString.append(birthYear);
        personString.append("\n");

        return personString.toString();
    }
    public String formatAsCSV() {
        StringBuffer personString = new StringBuffer();
        personString.append(firstName);
        personString.append(",");
        personString.append(lastName);
        personString.append(",");
        personString.append(birthYear);
        personString.append(",");
        personString.append(birthMonth);
        personString.append(",");
        personString.append(birthDay);

        return personString.toString();
    }

}
