package labs.grishchenko.models;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/*
 * Person model
 */
public class Person {
    public final int id;
    public final String name;
    public final String gender;
    public final Date birthday;
    public final Division division;
    public final int salary;

    public Person(int id, String name, String gender, Date birthday, Division division, int salary) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.birthday = birthday;
        this.division = division;
        this.salary = salary;
    }

    @Override
    public String toString() {
        SimpleDateFormat dateFormatter = new SimpleDateFormat("dd.MM.yyyy", Locale.ENGLISH);
        return new StringBuilder()
            .append(String.format("Person #%d: %s", id, name))
            .append("\n")
            .append(String.format("Gender: %s", gender))
            .append("\n")
            .append(String.format("Birthday: %s", dateFormatter.format(birthday)))
            .append("\n")
            .append(division)
            .append("\n")
            .append(String.format("Salary: %d", salary))
            .append("\n")
            .toString();
    }
}
