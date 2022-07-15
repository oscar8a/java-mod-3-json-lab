import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class Person {
    private String firstName;
    private String lastName;
    private int birthDay;
    private int birthMonth;
    private int birthYear;
    private List<Person> friends;

    public Person() throws IOException {
        List<Person> restoredPersons = Arrays.asList(new ObjectMapper().readValue(new File("PERSON_LIST_JSON_FILENAME"), Person[].class));
    }

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

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getBirthDay() {
        return birthDay;
    }

    public int getBirthMonth() {
        return birthMonth;
    }

    public int getBirthYear() {
        return birthYear;
    }

    public List<Person> getFriends() {
        return friends;
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
