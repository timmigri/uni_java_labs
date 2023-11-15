package labs.grishchenko;

/*
 * Class, that contains result of performance test
 */
public class PerformanceResult {
    /** Number of items */
    public final long numberOfItems;
    /** Result for ArrayList */
    public final long arrayListResult;
    /** Result for LinkedList */
    public final long linkedListResult;

    /** Constructor */
    PerformanceResult(long numberOfItems, long arrayListResult, long linkedListResult) {
        this.numberOfItems = numberOfItems;
        this.arrayListResult = arrayListResult;
        this.linkedListResult = linkedListResult;
    }

    /**
     * Prints PerformanceResult
     * @param title - title, that prints before results
     */
    public void print(String title) {
        System.out.println(title);
        System.out.printf("%-10s | %-15s | %-20s\n", "Collection", "Number of items", "Execution time (ms)");
        System.out.printf("%-10s | %-15d | %-20d\n", "ArrayList", numberOfItems, arrayListResult);
        System.out.printf("%-10s | %-15d | %-20d\n", "LinkedList", numberOfItems, linkedListResult);
        System.out.print("\n\n");
    }
}
