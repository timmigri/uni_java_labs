package labs.grishchenko;
import labs.grishchenko.models.Division;
import labs.grishchenko.models.Person;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.net.URISyntaxException;
import java.net.URL;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;
import com.opencsv.CSVParserBuilder;

public class CSVPersonReader {
    /** Map, that contains id of division by it's name */
    Map<String, Integer> divisionIds = new HashMap<String, Integer>();

    /**
     * 
     * @param pathToResource - Path in resource folder
     * @param delimeter - CSV column delimeter
     * @return Parsed list of persons
     * @throws IOException
     * @throws CsvValidationException
     * @throws URISyntaxException
     * @throws ParseException
     */
    public List<Person> read(String pathToResource, char delimeter) throws IOException, CsvValidationException, URISyntaxException, ParseException {
        divisionIds.clear();
        List<Person> persons = new ArrayList<Person>();
        CSVReader csvReader = new CSVReaderBuilder(new FileReader(getAbsolutePathToFile(pathToResource)))
            .withCSVParser(new CSVParserBuilder().withSeparator(delimeter).build())
            .withSkipLines(1)
            .build();
        String[] parsedLine = null;
        while ((parsedLine = csvReader.readNext()) != null) {
            persons.add(getPersonFromLine(parsedLine));
        }
        return persons;
    }

    /**
     * Transform line to Person model
     * @param parsedLine - Line from CSVFile 
     * @return Person
     * @throws ParseException
     */
    private Person getPersonFromLine(String[] parsedLine) throws ParseException {
        SimpleDateFormat dateFormatter = new SimpleDateFormat("dd.MM.yyyy", Locale.ENGLISH);
        return new Person(
            Integer.parseInt(parsedLine[0]),
            parsedLine[1],
            parsedLine[2],
            dateFormatter.parse(parsedLine[3]),
            getDivisionByName(parsedLine[4]),
            Integer.parseInt(parsedLine[5])
        );
    }

    /**
     * Get Division by extract id from divisionIds Map or creating new Division object
     * @param name - Name of division
     * @return Division
     */
    private Division getDivisionByName(String name) {
        if (divisionIds.containsKey(name)) {
            return new Division(divisionIds.get(name), name);
        }
        Division division = new Division(name);
        divisionIds.put(name, division.id);
        return division;
    }

    /**
     * 
     * @param pathToResource - Path in resource folder
     * @return Absolute path to file which could be read by FileReader
     * @throws URISyntaxException
     */
    private String getAbsolutePathToFile(String pathToResource) throws URISyntaxException {
        URL resource = getClass().getClassLoader().getResource(pathToResource);
        return Paths.get(resource.toURI()).toFile().getAbsolutePath();
    }
}
