package labs.grishchenko;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.LinkedList;


/*
 * Class, that making performance test
 */
public class Performancer {
    /**
     * Generates ArrayList for further performance tests
     * @param numberOfItems - number of items in ArrayList
     * @return ArrayList
     */
    private ArrayList<Integer> generateArrayList(int numberOfItems) {
        ArrayList<Integer> arrayList = new ArrayList<Integer>();
        for (int i = 0; i < numberOfItems; i++) {
            arrayList.add(i);
        }
        return arrayList;
    }

    /**
     * Generates LinkedList for further performance tests
     * @param numberOfItems - number of items in LinkedList
     * @return LinkedList
     */
    private LinkedList<Integer> generateLinkedList(int numberOfItems) {
        LinkedList<Integer> linkedList = new LinkedList<Integer>();
        for (int i = 0; i < numberOfItems; i++) {
            linkedList.add(i);
        }
        return linkedList;
    }


    /**
     * Making add items performance test 
     * @param numberOfItems - number of items to test
     * @return PerformanceResult
     */
    public PerformanceResult generateAddItemsPerfomanceResult(int numberOfItems) {
        Instant start, end;

        start = Instant.now();
        generateArrayList(numberOfItems);
        end = Instant.now();
        long arrayListResult = Duration.between(start, end).toMillis();

        start = Instant.now();
        generateArrayList(numberOfItems);
        end = Instant.now();
        long linkedListResult = Duration.between(start, end).toMillis();

        return new PerformanceResult(numberOfItems, arrayListResult, linkedListResult);
    }

    /**
     * Making get items performance test 
     * @param numberOfItems - number of items to test
     * @return PerformanceResult
     */
    public PerformanceResult generateGetItemsPerfomanceResult(int numberOfItems) {
        Instant start, end;
        ArrayList<Integer> arrayList = generateArrayList(numberOfItems);
        LinkedList<Integer> linkedList = generateLinkedList(numberOfItems);

        start = Instant.now();
        for (int i = 0; i < numberOfItems; i++) {
            arrayList.get(i);
        }
        end = Instant.now();
        long arrayListResult = Duration.between(start, end).toMillis();

        start = Instant.now();
        for (int i = 0; i < numberOfItems; i++) {
            linkedList.get(i);
        }
        end = Instant.now();
        long linkedListResult = Duration.between(start, end).toMillis();

        return new PerformanceResult(numberOfItems, arrayListResult, linkedListResult);
    }

    /**
     * Making delete items performance test 
     * @param numberOfItems - number of items to test
     * @return PerformanceResult
     */
    public PerformanceResult generateDeleteItemsPerfomanceResult(int numberOfItems) {
        Instant start, end;
        ArrayList<Integer> arrayList = generateArrayList(numberOfItems);
        LinkedList<Integer> linkedList = generateLinkedList(numberOfItems);

        start = Instant.now();
        for (int i = numberOfItems - 1; i >= 0; i--) {
            arrayList.remove(i);
        }
        end = Instant.now();
        long arrayListResult = Duration.between(start, end).toMillis();

        start = Instant.now();
        for (int i = numberOfItems - 1; i >= 0; i--) {
            linkedList.remove(i);
        }
        end = Instant.now();
        long linkedListResult = Duration.between(start, end).toMillis();

        return new PerformanceResult(numberOfItems, arrayListResult, linkedListResult);
    }
}
