package labs.grishchenko;

import java.util.Scanner;

import labs.grishchenko.Performancer;

public class Main {
    public static void main(String[] args) {
        Performancer performancer = new Performancer();
        PerformanceResult result;

        try(Scanner scanner = new Scanner(System.in)){
            System.out.print("Введите количество элементов: ");
            int numberOfItems = scanner.nextInt();

            result = performancer.generateAddItemsPerfomanceResult(numberOfItems);
            result.print("ADD method result:");

            result = performancer.generateGetItemsPerfomanceResult(numberOfItems);
            result.print("GET method result:");

            result = performancer.generateDeleteItemsPerfomanceResult(numberOfItems);
            result.print("DELETE method result:");
        }
    }
}