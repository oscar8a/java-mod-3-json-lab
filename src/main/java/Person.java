public class Person {
    private final String age;
    private final String name;

    public Person(String age, String name) {
        this.age = age;
        this.name = name;
    }

    @Override
    public String toString() {
        return age + ", " + name;
    }
}
