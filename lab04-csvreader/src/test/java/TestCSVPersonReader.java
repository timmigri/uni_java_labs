import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import labs.grishchenko.models.Person;
import labs.grishchenko.CSVPersonReader;

public class TestCSVPersonReader {
    CSVPersonReader reader = new CSVPersonReader();

    @Test
    public void test_readingPersons() {
        try {
            List<Person> persons = reader.read("foreign_names.csv", ';');
            Assertions.assertEquals(persons.get(0).getId(), 28281);
            Assertions.assertEquals(persons.get(1).getName(), "Aala");
            Assertions.assertEquals(persons.get(2).getSalary(), 1000);
            Assertions.assertEquals(persons.size(), 25898);
        } catch (Exception e) {
            Assertions.fail(e.getMessage());
        }
    }

    @Test
    public void test_readingPersonDivisions() {
        try {
            List<Person> persons = reader.read("foreign_names.csv", ';');
            Assertions.assertEquals(persons.get(0).getDivision().getName(), "I");
            Assertions.assertNotEquals(persons.get(1).getDivision(), persons.get(2).getDivision());
            Assertions.assertEquals(persons.get(3).getDivision(), persons.get(5).getDivision());

            int maxDivisionId = 0;
            for (int i = 0; i < persons.size(); i++) {
                int divisionId = persons.get(i).getDivision().getId();
                if (divisionId > maxDivisionId) {
                    maxDivisionId = divisionId;
                }
            }
            Assertions.assertEquals(maxDivisionId, 15);

        } catch (Exception e) {
            Assertions.fail(e.getMessage());
        }
    }

    @Test
    public void test_failedReadingFile() {
        Assertions.assertThrows(RuntimeException.class, () -> {
            reader.read("foreign_names.cs", ';');
        });
        Assertions.assertThrows(RuntimeException.class, () -> {
            reader.read("foreign_names.csv", ',');
        });
    }
}
