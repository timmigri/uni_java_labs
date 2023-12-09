package labs.grishchenko;
import java.util.List;
import labs.grishchenko.models.Person;

public class Main {
    public static void main(String[] args) {
        try {
            CSVPersonReader reader = new CSVPersonReader();
            List<Person> persons = reader.read("foreign_names.csv", ';');
            int maxDivisionId = 0;
            for (int i = 0; i < persons.size(); i++) {
                int divisionId = persons.get(i).getDivision().getId();
                if (divisionId > maxDivisionId) {
                    maxDivisionId = divisionId;
                }
            }
            System.out.println(String.format("Parsed %d persons, found %d divisions", persons.size(), maxDivisionId));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}