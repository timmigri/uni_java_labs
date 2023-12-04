package labs.grishchenko;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try(Scanner scanner = new Scanner(System.in)) {
            System.out.print("Enter a path in resource folder (without .properties): ");
            String resourcesPath = scanner.nextLine(); 
            Injector injector = new Injector(resourcesPath + ".properties");
            SomeBean sb = injector.inject(new SomeBean());
            sb.foo();
        } catch (InjectionException e) {
            e.printStackTrace();
        }
    }
}